package operationrene.mapframework.levelbuilder;

import operationrene.mapframework.matrixprops.Size;
import operationrene.mapframework.matrixprops.Rotation;
import operationrene.mapframework.matrixprops.Location;
import operationrene.mapframework.matrixprops.Flipping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import operationrene.ProceduralLevelPartsGenerator;
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
    
    public void buildLevel(){
        
        // Loads a random corridor
        ArrayList<String> corridors = FileUtils.listFilesInDirectory("assets/levels/proceduralgeneration/corridors");
        int randomCorridor = RandomUtils.genRandomInt(0, corridors.size() - 1);
        
        // randomCorridor = 2;
        
        // Loads the choosen corridor
        this.buildingLevel = LevelSerializer.loadLevel(corridors.get(randomCorridor));
        
        // TODO: remove the following line
        ProceduralLevelPartsGenerator.debugRooms(buildingLevel.getRooms());
        
        // Gets the keyset of the Array
        HashMap <Location, Room> rooms = buildingLevel.getRooms();
        
        // Transforms it into an arraylist
        List<Location> roomArray = new ArrayList<>(rooms.keySet());
        
        // TODO: select safe room
        
    
        // Iteration on all rooms
        for (int i = 0; i < rooms.size(); i++) {
            
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
                if (SizeUtils.fitsInside( (RoomUtils.calculateRotation(roomDir)), levelSize, roomSize)
                        && SizeUtils.coversArea(roomSize, levelSize) > 0.5 ){
                    
                    //Rotation Room (variable for caching)
                    Rotation rot = RoomUtils.calculateRotation(roomDir);
                    if (rot == Rotation.DEG180){
                        if (RandomUtils.genRandomInt(0, 1) == 0){
                            room = RoomUtils.rotateRoom(room,Rotation.DEG180);
                        } else {
                            room = RoomUtils.flipRoom(room,Flipping.HORIZONTAL);
                        }    
                    } else if (RoomUtils.calculateRotation(rooms.get(roomArray.get(i)).getDir()) == Rotation.NONE){
                        if (RandomUtils.genRandomInt(0, 1) != 0){
                            room = RoomUtils.flipRoom(room,Flipping.VERTICAL);
                        }    
                    } else {
                            room = RoomUtils.rotateRoom(room, rot);
                    }
                    
                    //adding the room at the matrix
                    addRoom(room, roomArray.get(i), levelSize, roomDir);
                    break;
                }
            } 
        }   
    }

    
    /**
     * Function responsible for overwriting the matrix and chaining the hashmaps
     * @param lm Where to paste the matrix
     * @param loc X,Y index where to paste the matrix
     * @param maxSize Max size of the paste rectangle
     * @param dir Direction where to paste the matrix (anchor to corners)
     */
    public void addRoom(LevelMap lm, Location loc, Size maxSize, Direction dir) {  
        
        if (lm.getLockedObjects() != null)
            if (buildingLevel.getLockedObjects() == null)
               buildingLevel.setLockedObjects(lm.getLockedObjects());
            else 
                buildingLevel.getLockedObjects().putAll(lm.getLockedObjects());
        
        if (lm.getUnlockingObjects() != null)
            if (buildingLevel.getUnlockingObjects() == null)
                buildingLevel.setUnlockingObjects(lm.getUnlockingObjects());
            else 
                buildingLevel.getUnlockingObjects().putAll(lm.getUnlockingObjects());
        
        if (lm.getOtherObjects() != null)
            if (getBuildingLevel().getOtherObjects() == null)
                buildingLevel.setOtherObjects(lm.getOtherObjects());
            else 
                buildingLevel.getOtherObjects().putAll(lm.getOtherObjects());
        
        // /*
        MatrixUtils.debugMatrix(buildingLevel.getMatrix());
        MatrixUtils.debugMatrix(lm.getMatrix());
        System.out.println(loc);
        // */
        buildingLevel.setMatrix(MatrixUtils.pasteMatrix(buildingLevel.getMatrix(), lm.getMatrix(), loc, maxSize, dir));
    }
    
}
