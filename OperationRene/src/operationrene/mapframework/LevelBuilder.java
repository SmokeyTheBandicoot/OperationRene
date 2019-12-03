package operationrene.mapframework;

import java.util.HashMap;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.mapframework.pointsofinterest.PointOfInterest.PointType;
import operationrene.utils.ProgressTree;
import operationrene.utils.ProgressTree.Node;

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
        
        Node<PointOfInterest> node = null;
        for (LocationAndSize ls : locked.keySet()) {
            if (locked.get(ls).getPointType() == PointType.Safe) {
                node = new Node<>(locked.get(ls));
            }
        }
        
        ProgressTree progress = new ProgressTree(node);
        
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
