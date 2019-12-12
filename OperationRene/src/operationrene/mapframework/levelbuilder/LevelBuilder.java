package operationrene.mapframework.levelbuilder;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import operationrene.mapframework.matrixprops.Size;
import operationrene.mapframework.matrixprops.Rotation;
import operationrene.mapframework.matrixprops.Location;
import operationrene.mapframework.matrixprops.Flipping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import operationrene.mapframework.*;
import operationrene.mapframework.pointsofinterest.*;
import operationrene.utils.*;

public class LevelBuilder {

    public static void saveLevel(LevelMap level, String path) {
        try {
            FileOutputStream fo = new FileOutputStream(path);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(level);
            oo.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    
    protected LevelMap buildingLevel;
    
    
    public void buildLevel(){
        
        // Loads a random corridor
        ArrayList<String> corridors = FileUtils.listFilesInDirectory("assets/levels/proceduralgeneration/corridors");
        int randomCorridor = RandomUtils.genRandomInt(0, corridors.size() - 1);
        
        // Loads the choosen corridor
        this.buildingLevel = LevelSerializer.loadLevel(corridors.get(randomCorridor));
       
        // Gets the keyset of the Array
        HashMap <Location, Room> rooms = buildingLevel.getRooms();
        // Transforms it into an arraylist
        List<Location> roomArray = new ArrayList<>(rooms.keySet());

        
        // TODO: select safe room
        
    
        // Iteration on all rooms
        for (int i = 0; i < buildingLevel.getRooms().size(); i++) {
        
            // Get the size of the current iteration's room
            Size levelsize = rooms.get(roomArray.get(i)).getSize();
            
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
                    
                if (SizeUtils.isGood((RoomUtils.calculateRotation(rooms.get(roomArray.get(i)).getDir())), new Size(room.getMatrixHeight(), room.getMatrixWidth()), levelsize)){
                    
                    //Rotation Room (variable for caching)
                    Rotation rot = RoomUtils.calculateRotation(rooms.get(roomArray.get(i)).getDir());
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
                    addRoom(room, roomArray.get(i));
                    break;
                }
            } 
        }   
    }

    
    /**
     * Function responsible for overwriting the matrix and chaining the hashmaps
     * @param lm
     * @param loc 
     */
    public void addRoom(LevelMap lm, Location loc) {  
        buildingLevel.getLockedObjects().putAll(lm.getLockedObjects());
        buildingLevel.getUnlockingObjects().putAll(lm.getUnlockingObjects());
        buildingLevel.getOtherObjects().putAll(lm.getOtherObjects());
        buildingLevel.setMatrix(MatrixUtils.addMatrix(buildingLevel.getMatrix(), lm.getMatrix(), loc));
    }
    
}
