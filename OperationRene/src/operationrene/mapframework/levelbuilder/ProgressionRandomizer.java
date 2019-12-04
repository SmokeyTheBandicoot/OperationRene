package operationrene.mapframework.levelbuilder;

import java.util.HashMap;
import operationrene.mapframework.LevelMap;
import operationrene.mapframework.Location;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.mapframework.pointsofinterest.PointOfInterest.PointType;
import operationrene.utils.ProgressTree;

public class ProgressionRandomizer {
    
    // Used to dynamically produce a tree which represents the sequence of keys-doors of the level
    private ProgressTree<Location> pt = null;
    
    // Level to randomize
    private LevelMap lm = null;
    
    // Cache Hashmaps
    private HashMap<Location, PointOfInterest> locks;
    private HashMap<Location, PointOfInterest> unlocks;

    public ProgressionRandomizer(LevelMap lm) {
        this.locks = lm.getLockedObjects();
        this.unlocks = lm.getUnlockingObjects();
    }
    
    /**
     * Function responsible for the complete randomization of the level,
     * and sets the correct parameters for all the objects in the hashmap
     */
    public void randomize() {
        
    }
    
    
    /**
     * Function responsible for finding the safe in the "lockeds" hashmap
     * @return 
     */
    private Location findSafe(){
        for (Location l : locks.keySet()) {
            if (locks.get(l).getPointType() == PointType.Safe)
                return l;
        }
        return null;
    }
    
    
    
}
