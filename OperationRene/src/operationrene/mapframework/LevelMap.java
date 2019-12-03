package operationrene.mapframework;

import java.io.Serializable;
import java.util.*;

public class LevelMap implements Serializable {

    // Matrix for the main level collision description
    private Integer[][] matrix;
    // Dictionary for adding behaviour to the level. This dictionary will be analyzed by the LevelBuilder object
    private HashMap<LocationAndSize, PointType> pointsOfInterest;
    

    // Full constructor
    public LevelMap(Integer[][] matrix, HashMap<LocationAndSize, PointType> pointsOfInterest) {
        this.matrix = matrix;
        this.pointsOfInterest = pointsOfInterest;
    }
    
    private LevelMap(){
        this(null, null);
    }

    public Integer[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Integer[][] matrix) {
        this.matrix = matrix;
    }

    public HashMap<LocationAndSize, PointType> getPointsOfInterest() {
        return pointsOfInterest;
    }

    public void setPointsOfInterest(HashMap<LocationAndSize, PointType> pointsOfInterest) {
        this.pointsOfInterest = pointsOfInterest;
    }
}
