package operationrene.mapframework.levelbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import operationrene.mapframework.*;
import operationrene.mapframework.pointsofinterest.*;
import operationrene.utils.RoomUtils;
import operationrene.utils.SizeUtils;
import operationrene.utils.MatrixUtils;


public class LevelBuilder {
    
    private static final int NROOM = 10;
    private static final int NCORRIDOR = 3;
    
    protected LevelMap buildingLevel;
    
    
    public boolean buildLevel(){
        
        // Loads a random corridor
        this.buildingLevel = LevelSerializer.loadLevel("assets/levels/proceduralgeneration/corridor"
    +(new Integer(((new Random()).nextInt(NCORRIDOR)))).toString()+".dat");
       
        // Gets the keyset of the Array
        HashMap <Location, Room> rooms = buildingLevel.getRooms();
        // Transforms it into an arraylist
        List<Location> roomArray = new ArrayList<>(rooms.keySet());

        Random rand = new Random();
    
        // Iteration on all rooms
        for (int i = 0; i < buildingLevel.getRooms().size(); i++) {
        
            // Get the size of the current iteration's room
            Size levelsize = rooms.get(roomArray.get(i)).getSize();
            
            // To avoid generating the same room twice
            ArrayList<Integer> memory = new ArrayList<>();
            
            // Generate a random int to randomly choose a room
            while (true) {
                int j = rand.nextInt(NROOM);
        
                // If room has already been chosen in a previous iteration
                if(!memory.contains(j)) {
                    // Save the choosed room
                    memory.add(j);
                    // Load level from memory
                    LevelMap room = LevelSerializer.loadLevel("assets/levels/proceduralgeneration/room"+j+".dat");
                    
                    if (SizeUtils.isGood((RoomUtils.calculateRotation(rooms.get(roomArray.get(i)).getDir())), new Size(room.getMatrixHeight(), room.getMatrixWidth()), levelsize)){
                    
                    //Rotation Room (variable for caching)
                    Rotation rot = RoomUtils.calculateRotation(rooms.get(roomArray.get(i)).getDir());
                    if (rot == Rotation.DEG180){
                        if (j % 2 == 0){
                            room = RoomUtils.rotateRoom(room,Rotation.DEG180);
                        } else {
                            room = RoomUtils.flipRoom(room,Flipping.HORIZONTAL);
                        }    
                    } else if (RoomUtils.calculateRotation(rooms.get(roomArray.get(i)).getDir()) == Rotation.NONE){
                        if (j % 2 != 0){
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
    return true;
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
    
    public enum Rotation {
        NONE,
        LEFT,
        RIGHT,
        DEG180
    }
    
    public enum Flipping {
        NONE,
        HORIZONTAL,
        VERTICAL,
        BOTH
    }
    
}
