package operationrene.mapframework;

import operationrene.mapframework.pointsofinterest.PointOfInterest;
import java.io.Serializable;
import java.util.*;

public class LevelMap implements Serializable {

    // Matrix for the main level collision description
    private Integer[][] matrix;
    // Dictionary for adding behaviour to the level. This dictionary will be analyzed by the LevelBuilder object
    private HashMap<LocationAndSize, PointOfInterest> lockedObjects;
    private HashMap<LocationAndSize, PointOfInterest> unlockingObjects;

    public LevelMap(Integer[][] matrix, HashMap<LocationAndSize, PointOfInterest> lockedObjects, HashMap<LocationAndSize, PointOfInterest> unlockingObjects) {
        this.matrix = matrix;
        this.lockedObjects = lockedObjects;
        this.unlockingObjects = unlockingObjects;
    }
   
    private LevelMap(){
        this(null, null, null);
    }

    public Integer[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Integer[][] matrix) {
        this.matrix = matrix;
    }

    public HashMap<LocationAndSize, PointOfInterest> getLockedObjects() {
        return lockedObjects;
    }

    public void setLockedObjects(HashMap<LocationAndSize, PointOfInterest> lockedObjects) {
        this.lockedObjects = lockedObjects;
    }

    public HashMap<LocationAndSize, PointOfInterest> getUnlockingObjects() {
        return unlockingObjects;
    }

    public void setUnlockingObjects(HashMap<LocationAndSize, PointOfInterest> unlockingObjects) {
        this.unlockingObjects = unlockingObjects;
    }

    
}
