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

            System.out.println("SAFE:");
            MatrixUtils.debugMatrix(safe.getMatrix());
            System.out.println(String.format("Trying to fit: %d %d in room %s", safe.getMatrixWidth(), safe.getMatrixHeight(), roomS.getSize()));
            System.out.println("Room rotation: " + roomS.getDir().toString());
            System.out.println("Room size:" + roomS.getSize().toString());
            System.out.println("Final virdict: " + SizeUtils.fitsInside((RoomUtils.calculateRotation(roomS.getDir())), roomS.getSize(), new Size(safe.getMatrixWidth(), safe.getMatrixHeight())));
            
            if (SizeUtils.fitsInside((RoomUtils.calculateRotation(roomS.getDir())), roomS.getSize(), new Size(safe.getMatrixWidth(), safe.getMatrixHeight()))) {
                safeGenerated = true;
                System.out.println("SAFE GENERATED");
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

        // Adding safe room...
        System.out.println("roomLoc: " + roomLoc + "; roomS: " + roomS.getSize().toString() + "; Dir: " + roomS.getDir());
        addRoom(1, safe, roomLoc, roomS.getSize(), roomS.getDir());

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
                    addRoom(i + 2, room, roomArray.get(i), levelSize, roomDir);
                    break;
                }
            }
        }

        // Row randomize progression
        ProgressionRandomizer pr = new ProgressionRandomizer(buildingLevel);
        try {
            ProgressTree<Location> pt = pr.randomize();
        } catch (Exception ex) {
            Logger.getLogger(LevelBuilder.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println(Arrays.toString(ex.getStackTrace()));
        }


    }
    
    private LevelMap loadRandomCorridor(){
        
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

        if (lm.getLockedObjects() != null) {
            if (buildingLevel.getLockedObjects() == null) {
                buildingLevel.setLockedObjects(HashMapUtils.traslate(lm.getLockedObjects(), loc));
            } else {
                for (Location iter : lm.getLockedObjects().keySet()) {
                    lm.getLockedObjects().get(iter).setRoomID(roomID);
                    
                }
                buildingLevel.getLockedObjects().putAll(HashMapUtils.traslate(lm.getLockedObjects(), loc));
            }
        }

        if (lm.getUnlockingObjects() != null) {
            if (buildingLevel.getUnlockingObjects() == null) {
                buildingLevel.setUnlockingObjects(HashMapUtils.traslate(lm.getUnlockingObjects(), loc));
            } else {
                for (Location iter : lm.getUnlockingObjects().keySet()) {
                    lm.getUnlockingObjects().get(iter).setRoomID(roomID);
                }
                buildingLevel.getUnlockingObjects().putAll(HashMapUtils.traslate(lm.getUnlockingObjects(), loc));
            }
        }

        if (lm.getOtherObjects() != null) {
            if (getBuildingLevel().getOtherObjects() == null) {
                buildingLevel.setOtherObjects(HashMapUtils.traslate(lm.getOtherObjects(), loc));
            } else {
                for (Location iter : lm.getOtherObjects().keySet()) {
                    lm.getOtherObjects().get(iter).setRoomID(roomID);
                }
                buildingLevel.getOtherObjects().putAll(HashMapUtils.traslate(lm.getOtherObjects(), loc));
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
