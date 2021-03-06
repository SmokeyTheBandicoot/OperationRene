package operationrene.mapframework.levelbuilder;

import operationrene.mapframework.matrixprops.Size;
import operationrene.mapframework.matrixprops.Rotation;
import operationrene.mapframework.matrixprops.Location;
import operationrene.mapframework.matrixprops.Flipping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operationrene.alarm.*;
import operationrene.alarm.MapAlarm.Dimension;
import operationrene.StateID;
import operationrene.datastructures.ProgressTree;
import operationrene.mapframework.*;
import operationrene.mapframework.matrixprops.Direction;
import operationrene.mapframework.pointsofinterest.*;
import operationrene.utils.*;

public class LevelBuilder {

    protected LevelMap buildingLevel;

    // Parameters that can be used to increase difficulty
    protected float coveredAreaThreshold = 0.75f;
    protected float mapAlarmChance = 0.80f;
    protected float timeAdditionMultiplier = 1.0f;

    public LevelMap getBuildingLevel() {
        return buildingLevel;
    }

    public void buildLevel() {

        // ####################### - PHASE 1: Building the levelroom by room
        // Loads the choosen corridor
        this.buildingLevel = loadRandomCorridor();

        // Gets the keyset of the Array
        HashMap<Location, Room> rooms = buildingLevel.getRooms();

        // Transforms it into an arraylist
        List<Location> roomArray = new ArrayList<>(rooms.keySet());

        // Select a room containing a safe for the level
        ArrayList<String> safes = new ArrayList<>();
        LevelMap safe = null;

        // Get a random room where to put the safe
        int randomRoomSafe = RandomUtils.genRandomInt(0, roomArray.size() - 1);
        Location roomLoc = roomArray.get(randomRoomSafe);
        Room roomS = rooms.get(roomLoc);

        boolean safeGenerated = false;

        while (!safeGenerated) {

            if (safes.isEmpty()) {
                // List available safes (files)
                safes = FileUtils.listFilesInDirectory("assets/levels/proceduralgeneration/safes");

                randomRoomSafe = RandomUtils.genRandomInt(0, roomArray.size() - 1);
                roomLoc = roomArray.get(randomRoomSafe);
                roomS = rooms.get(roomLoc);
            }

            int randomSafe = RandomUtils.genRandomInt(0, safes.size() - 1);

            // Loads the choosen safe room into the corridor
            safe = LevelSerializer.loadLevel(safes.get(randomSafe));

            if (SizeUtils.fitsInside((RoomUtils.calculateRotation(roomS.getDir())), roomS.getSize(), new Size(safe.getMatrixWidth(), safe.getMatrixHeight()))) {
                safeGenerated = true;
                buildingLevel.getRooms().get(roomLoc).setRoomID(1);
            } else {
                safes.remove(randomSafe);
            }
        }

        //Rotation of the SAFE Room (variable for caching)
        Rotation safeRot = RoomUtils.calculateRotation(roomS.getDir());
        if (safeRot == Rotation.DEG180) {
            if (RandomUtils.genRandomInt(0, 1) == 0) {
                safe = RoomUtils.rotateRoom(safe, Rotation.DEG180);
            } else {
                safe = RoomUtils.flipRoom(safe, Flipping.HORIZONTAL);
            }
        } else if (RoomUtils.calculateRotation(rooms.get(roomArray.get(randomRoomSafe)).getDir()) == Rotation.NONE) {
            if (RandomUtils.genRandomInt(0, 1) != 0) {
                safe = RoomUtils.flipRoom(safe, Flipping.VERTICAL);
            }
        } else {
            safe = RoomUtils.rotateRoom(safe, safeRot);
            if (RandomUtils.genRandomInt(0, 1) != 0) {
                safe = RoomUtils.flipRoom(safe, Flipping.HORIZONTAL);
            }
        }

        // Adding safe room
        addRoom(1, safe, roomLoc, roomS.getSize(), roomS.getDir());
        // ---# Set the room id in the building level

        // Iteration on all rooms
        for (int i = 0; i < rooms.size(); i++) {

            // This room has already been generated as a SAFE room
            if (i == randomRoomSafe) {
                continue;
            }

            // Get the size of the current iteration's room
            Size levelSize = rooms.get(roomArray.get(i)).getSize();

            // To avoid generating the same room twice
            ArrayList<String> availableFiles = FileUtils.listFilesInDirectory("assets/levels/proceduralgeneration/rooms");

            // Generate a random int to randomly choose a room
            while (!availableFiles.isEmpty()) {

                // Gets a random available file
                int random = RandomUtils.genRandomInt(0, availableFiles.size() - 1);
                String path = availableFiles.get(random);

                // Load level from memory
                LevelMap room = LevelSerializer.loadLevel(path);

                // Removes path for already-choosen rooms
                availableFiles.remove(path);

                // Caches the direction of the room
                Direction roomDir = rooms.get(roomArray.get(i)).getDir();

                // Size of the room
                Size roomSize = new Size(room.getMatrixWidth(), room.getMatrixHeight());

                // If a room fits inside its size and it covers at least 50% of the available space
                if (SizeUtils.fitsInside((RoomUtils.calculateRotation(roomDir)), levelSize, roomSize)
                        && SizeUtils.coversArea(roomSize, levelSize) > 0.5) {

                    //Rotation Room (variable for caching)
                    Rotation rot = RoomUtils.calculateRotation(roomDir);

                    // If the room has to be rotated 180 degrees (door on the right side)
                    if (rot == Rotation.DEG180) {
                        // You can choose to either flip the room horizontally or rotate 180 degrees
                        if (RandomUtils.genRandomInt(0, 1) == 0) {
                            room = RoomUtils.rotateRoom(room, Rotation.DEG180);
                        } else {
                            room = RoomUtils.flipRoom(room, Flipping.HORIZONTAL);
                        }
                        // Otherwise, if the room doesn't have to be rotated, then take
                        // a chance to flip the room vertically (door remains to the left)
                    } else if (RoomUtils.calculateRotation(rooms.get(roomArray.get(i)).getDir()) == Rotation.NONE) {
                        if (RandomUtils.genRandomInt(0, 1) != 0) {
                            room = RoomUtils.flipRoom(room, Flipping.VERTICAL);
                        }

                        // If the room has to be rotated left or right, then rotate it accordingly
                        // then get a chance to flip the room horizontally
                    } else {
                        room = RoomUtils.rotateRoom(room, rot);
                        if (RandomUtils.genRandomInt(0, 1) == 0) {
                            room = RoomUtils.flipRoom(room, Flipping.HORIZONTAL);
                        }
                    }

                    // Add the room to the matrix
                    // roomID starts from 2 because 0 is the corridor and 1 is 
                    // the room containing the safe, generated above
                    System.out.println("Choosen room with ID " + i + 2 + ": " + path);
                    addRoom(i + 2, room, roomArray.get(i), levelSize, roomDir);
                    // ---# change room id of the building level
                    break;
                }
            }
        }

        // ####################### - PHASE 2: Randomize the progression in the rooms
        // Row randomize progression
        ProgressionRandomizer pr = new ProgressionRandomizer(buildingLevel);
        try {
            ProgressTree<Location> pt = pr.randomize();
        } catch (Exception ex) {
            Logger.getLogger(LevelBuilder.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println(Arrays.toString(ex.getStackTrace()));
        }

        // ####################### - PHASE 3: Setting up traps
        // This part is responsible for randomly choosing which alarms to show and 
        // setting up the corresponding identifiers in the matrix
        HashMap<Location, PointOfInterest> cache = buildingLevel.getOtherObjects();

        ArrayList<Location> alarmLocations = new ArrayList<>();
        HashMap<AlarmIdentifier, Location> alarmIDLocations = new HashMap<>();

        for (Location loc : cache.keySet()) {
            if (cache.get(loc).getPointType() == PointOfInterest.PointType.AlarmZone) {
                alarmLocations.add(loc);
            } else if (cache.get(loc).getPointType() == PointOfInterest.PointType.AlarmID) {
                alarmIDLocations.put((AlarmIdentifier) cache.get(loc), loc);
            }
        }

        // For each AlarmZone, generate a random MapAlarm
        for (Location l : alarmLocations) {
            MapAlarmFactory mapFactory = new MapAlarmFactory();
            AlarmZone alarm = (AlarmZone) buildingLevel.getOtherObjects().get(l);

            Location alarmIDloc = null;
            for (AlarmIdentifier ai : alarmIDLocations.keySet()) {
                if (alarm.getAlarmIdentifier() == ai) {
                    alarmIDloc = alarmIDLocations.get(ai);
                }
            }

            ArrayList<Dimension> sizes = MapAlarm.getMinigameDimensions();
            Dimension d = sizes.get(SizeUtils.getBiggestFittingSize(sizes, alarm.getSize(), true));


            MapAlarm ma = mapFactory.createRandomMapAlarm(d);

            int x = alarmIDloc.getX();
            int y = alarmIDloc.getY();


            // Now set the AlarmZone Identifiers correctly
            if (ma instanceof PressureTilesAlarm) {
                alarm.getAlarmIdentifier().setAlarmID(AlarmIdentifier.AlarmType.PRESSURE_TILES);
                alarm.setAlarmType(AlarmIdentifier.AlarmType.PRESSURE_TILES);

                switch (d) {
                    case SMALL:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.RedCircle);
                        break;
                    case MEDIUM:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.YellowCircle);
                        break;
                    case LARGE:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.GreenCircle);
                        break;
                    case RECT_SMALL:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.BlueCircle);
                        break;
                    case RECT_LARGE:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.PurpleCircle);
                        break;
                }

            } else if (ma instanceof FixedLasersAlarm) {
                alarm.getAlarmIdentifier().setAlarmID(AlarmIdentifier.AlarmType.FIXED_LASERS);
                alarm.setAlarmType(AlarmIdentifier.AlarmType.FIXED_LASERS);

                switch (d) {
                    case SMALL:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.RedSquare);
                        break;
                    case MEDIUM:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.YellowSquare);
                        break;
                    case LARGE:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.GreenSquare);
                        break;
                    case RECT_SMALL:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.BlueSquare);
                        break;
                    case RECT_LARGE:
                        buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.PurpleSquare);
                        break;
                }

            } else if (ma instanceof PulsatingLasersAlarm) {
                
                alarm.getAlarmIdentifier().setAlarmID(AlarmIdentifier.AlarmType.PULSATING_LASERS);
                alarm.setAlarmType(AlarmIdentifier.AlarmType.PULSATING_LASERS);

                int[][] mm = ma.getMatrix();
                int alarmNum = 0;
                

                for (int i = 0; i < mm.length; i++) {
                    for (int j = 0; j < mm[0].length; j++) {
                        if (mm[i][j] == 1) {
                            alarmNum++;
                        }
                    }
                }
                
                switch (d) {
                    case SMALL:
                        switch (alarmNum) {
                            case 1:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.RedHollow);
                                break;
                            case 2:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.YellowHollow);
                                break;
                            case 4:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.GreenHollow);
                                break;
                        }
                        break;

                    case MEDIUM:
                        switch (alarmNum) {
                            case 4:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.RedStar);
                                break;
                            case 13:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.YellowStar);
                                break;
                            case 8:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.GreenStar);
                                break;
                        }
                        break;

                    case LARGE:
                        switch (alarmNum) {
                            case 22:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.RedCross);
                                break;
                            case 43:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.YellowCross);
                                break;
                            case 34:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.GreenCross);
                                break;
                        }
                        break;

                    case RECT_SMALL:
                        switch (alarmNum) {
                            case 9:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.BlueHollow);
                                break;
                            case 13:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.PurpleHollow);
                                break;
                        }
                        break;

                    case RECT_LARGE:
                        switch (alarmNum) {
                            case 15:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.BlueStar);
                                break;
                            case 36:
                                buildingLevel.getMatrix()[y][x] = TileIDMapping.getMapping(TileIDMapping.TileName.PurpleStar);
                                break;
                        }
                        break;
                }

            }
            alarm.setMapAlarm(ma);

            // Now get the matrix from the map alarms and paste it, taking rotation into account
            int roomID = alarm.getRoomID();
            Room alarmRoom = null;

            for (Location loc : this.buildingLevel.getRooms().keySet()) {
                Room r = (Room) buildingLevel.getRooms().get(loc);
                if (r.getRoomID() == roomID) {
                    alarmRoom = r;
                }
            }

            Rotation rot = RoomUtils.calculateRotation(alarmRoom.getDir());

            int[][] matrix = ma.getMatrix();

            Integer[][] newMat = MatrixUtils.rotateMatrix(matrix, rot);

            MatrixUtils.pasteMatrix(buildingLevel.getMatrix(), newMat, l, alarm.getSize(), Direction.LEFT);

        }

        // ####################### - PHASE 4: Setting up minigames (KEY gameType)
        for (Location loc : buildingLevel.getUnlockingObjects().keySet()) {
            Key key = (Key) buildingLevel.getUnlockingObjects().get(loc);
            switch (RandomUtils.genRandomInt(0, 4)) {
                case 0:
                    key.setGameType(StateID.KEYPAD_ID);
                    break;
                case 1:
                    key.setGameType(StateID.MEMORY_ID);
                    break;
                case 2:
                    key.setGameType(StateID.SIMONSAYS_ID);
                    break;
                case 3:
                    key.setGameType(StateID.WORDS_ID);
                    break;
                case 4:
                    key.setGameType(StateID.WIRES_ID);
                    break;
            }
        }

    }

    private LevelMap loadRandomCorridor() {

        // Loads a random corridor
        ArrayList<String> corridors = FileUtils.listFilesInDirectory("assets/levels/proceduralgeneration/corridors");
        int randomCorridor = RandomUtils.genRandomInt(0, corridors.size() - 1);

        return LevelSerializer.loadLevel(corridors.get(randomCorridor));
    }

    /**
     * Function responsible for overwriting the matrix and chaining the hashmaps
     *
     * @param roomID all object will be set to this roomID
     * @param lm Where to paste the matrix
     * @param loc X,Y index where to paste the matrix
     * @param maxSize Max size of the paste rectangle
     * @param dir Direction where to paste the matrix (anchor to corners)
     */
    public void addRoom(int roomID, LevelMap lm, Location loc, Size maxSize, Direction dir) {

        this.buildingLevel.getRooms().get(loc).setRoomID(roomID);
        Location offsetLocation = loc;
        /*Location offsetLocation = new Location(
                loc.getX() + maxSize.getWidth() - lm.getMatrixWidth(),
                loc.getY() + maxSize.getHeight() - lm.getMatrixHeight()
        );*/

        if (null != dir) {
            switch (dir) {
                case RIGHT:
                    offsetLocation = new Location(loc.getX() + maxSize.getWidth() - lm.getMatrixWidth(), loc.getY());
                    break;
                case DOWN:
                    offsetLocation = new Location(loc.getX(), loc.getY() + maxSize.getHeight() - lm.getMatrixHeight());
                    break;
                default:
                    break;
            }
        }

        if (lm.getLockedObjects() != null) {
            if (buildingLevel.getLockedObjects() == null) {
                buildingLevel.setLockedObjects(HashMapUtils.traslate(lm.getLockedObjects(), offsetLocation));
            } else {
                for (Location iter : lm.getLockedObjects().keySet()) {
                    lm.getLockedObjects().get(iter).setRoomID(roomID);

                }
                buildingLevel.getLockedObjects().putAll(HashMapUtils.traslate(lm.getLockedObjects(), offsetLocation));
            }
        }

        if (lm.getUnlockingObjects() != null) {
            if (buildingLevel.getUnlockingObjects() == null) {
                buildingLevel.setUnlockingObjects(HashMapUtils.traslate(lm.getUnlockingObjects(), offsetLocation));
            } else {
                for (Location iter : lm.getUnlockingObjects().keySet()) {
                    lm.getUnlockingObjects().get(iter).setRoomID(roomID);
                }
                buildingLevel.getUnlockingObjects().putAll(HashMapUtils.traslate(lm.getUnlockingObjects(), offsetLocation));
            }
        }

        if (lm.getOtherObjects() != null) {
            if (getBuildingLevel().getOtherObjects() == null) {
                buildingLevel.setOtherObjects(HashMapUtils.traslate(lm.getOtherObjects(), offsetLocation));
            } else {
                for (Location iter : lm.getOtherObjects().keySet()) {
                    lm.getOtherObjects().get(iter).setRoomID(roomID);
                }
                buildingLevel.getOtherObjects().putAll(HashMapUtils.traslate(lm.getOtherObjects(), offsetLocation));
            }
        }

        /*
        MatrixUtils.debugMatrix(buildingLevel.getMatrix());
        MatrixUtils.debugMatrix(lm.getMatrix());
        System.out.println(loc);
         */
        buildingLevel.setMatrix(MatrixUtils.pasteMatrix(buildingLevel.getMatrix(), lm.getMatrix(), loc, maxSize, dir));
    }

}
