package operationrene.mapframework;

import java.util.HashMap;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.utils.ProgressTree;

public class LevelBuilder {
    
    protected LevelMap lm;
    
    public LevelRoom addRoom(LevelRoom lm, Rotation r, Flipping f) {
        return null;
    }
    
    public LevelMap buildLevel(){
        
        // Door, Safe, FixedLaserAlarm, CameraAlarm
        HashMap<LocationAndSize, PointOfInterest> locked = lm.getLockedObjects();
        
        // Key, Minigames, Magnetic keys
        HashMap<LocationAndSize, PointOfInterest> unlocks = lm.getUnlockingObjects();
        
        // PressureAlarm, PulsatingLasers, EntryPoint
        
        
        ProgressTree progress = new ProgressTree();
        
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
