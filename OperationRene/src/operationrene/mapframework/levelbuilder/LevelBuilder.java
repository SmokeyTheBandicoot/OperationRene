package operationrene.mapframework.levelbuilder;

import operationrene.mapframework.*;
import operationrene.mapframework.pointsofinterest.PointOfInterest.PointType;
import operationrene.mapframework.pointsofinterest.*;

public class LevelBuilder {
    
    protected LevelMap lm;
    
    public boolean addRoom(LevelMap lm, Rotation r, Flipping f) {
        if (this.lm == null) {
            this.lm = lm;
            return true;
        }
        
        for (Location l : lm.getOtherObjects().keySet()) {
            if (lm.getOtherObjects().get(l).getPointType() == PointType.Room) {
                Room room = (Room) lm.getOtherObjects().get(l);
                
            }    
        }
        
        return false;
    }
    
    private boolean canAddInMatrix(Integer[][] originMatrix, Integer[][] toAddMatrix, int x, int y) {
        
        
        return false;
    }
    
    public LevelMap buildLevel(){
        
        // Implement randomization of rooms
        return null;
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
