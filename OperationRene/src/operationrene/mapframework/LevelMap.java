package operationrene.mapframework;

import operationrene.mapframework.pointsofinterest.PointOfInterest;
import java.io.Serializable;
import java.util.*;

public class LevelMap implements Serializable {

    // Matrix for the main level collision description
    protected Integer[][] matrix;
    
    // Used for procedural generation
    protected int roomID;
    
    // Dictionary for adding behaviour to the level. This dictionary will be analyzed by the LevelBuilder object
    protected HashMap<Location, PointOfInterest> lockedObjects;
    protected HashMap<Location, PointOfInterest> unlockingObjects;
    protected HashMap<Location, PointOfInterest> otherObjects;

    public LevelMap(int roomID, Integer[][] matrix, 
            HashMap<Location, PointOfInterest> lockedObjects, 
            HashMap<Location, PointOfInterest> unlockingObjects, 
            HashMap<Location, PointOfInterest> otherObjects) {
        this.matrix = matrix;
        this.roomID = roomID;
        this.lockedObjects = lockedObjects;
        this.unlockingObjects = unlockingObjects;
        this.otherObjects = otherObjects;
    }
   
    protected LevelMap(){
        this(-1, null, null, null, null);
    }

    public Integer[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Integer[][] matrix) {
        this.matrix = matrix;
    }

    public HashMap<Location, PointOfInterest> getLockedObjects() {
        return lockedObjects;
    }

    public void setLockedObjects(HashMap<Location, PointOfInterest> lockedObjects) {
        this.lockedObjects = lockedObjects;
    }

    public HashMap<Location, PointOfInterest> getUnlockingObjects() {
        return unlockingObjects;
    }

    public void setUnlockingObjects(HashMap<Location, PointOfInterest> unlockingObjects) {
        this.unlockingObjects = unlockingObjects;
    }

    public HashMap<Location, PointOfInterest> getOtherObjects() {
        return otherObjects;
    }

    public void setOtherObjects(HashMap<Location, PointOfInterest> otherObjects) {
        this.otherObjects = otherObjects;
    }

    

    
}
