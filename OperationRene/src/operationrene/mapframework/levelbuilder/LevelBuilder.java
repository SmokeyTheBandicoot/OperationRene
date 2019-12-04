package operationrene.mapframework.levelbuilder;

import java.util.HashMap;
import operationrene.mapframework.*;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.utils.ProgressTree;

public class LevelBuilder {
    
    protected LevelMap lm;
    
    public boolean addRoom(LevelMap lm, Rotation r, Flipping f) {
        if (this.lm == null){
            this.lm = lm;
            return true;
        }
        
        
       return false;
    }
    
    public LevelMap buildLevel(){
        
        // Door, Safe, FixedLaserAlarm, CameraAlarm
        HashMap<Location, PointOfInterest> locked = lm.getLockedObjects();
        
        // Key, Minigames, Magnetic keys
        HashMap<Location, PointOfInterest> unlocks = lm.getUnlockingObjects();
        
        // PressureAlarm, PulsatingLasers, EntryPoint
        HashMap<Location, PointOfInterest> other = lm.getOtherObjects();
        
        //ProgressTree progress = new ProgressTree(node);
        
        //TODO: Implement the rest of the progress tree
        
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
