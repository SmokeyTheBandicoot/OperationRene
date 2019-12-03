package operationrene.mapframework;

import java.util.HashMap;
import operationrene.mapframework.pointsofinterest.PointOfInterest.PointType;

public class LevelRoom {
    
    protected Integer[][] matrix;
    protected HashMap<LocationAndSize, PointType> PotentialPOI;

    public LevelRoom(Integer[][] matrix, HashMap<LocationAndSize, PointType> PotentialPOI) {
        this.matrix = matrix;
        this.PotentialPOI = PotentialPOI;
    }
    
    

    public Integer[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Integer[][] matrix) {
        this.matrix = matrix;
    }

    public HashMap<LocationAndSize, PointType> getPotentialPOI() {
        return PotentialPOI;
    }

    public void setPotentialPOI(HashMap<LocationAndSize, PointType> PotentialPOI) {
        this.PotentialPOI = PotentialPOI;
    }
    
    
    
}
