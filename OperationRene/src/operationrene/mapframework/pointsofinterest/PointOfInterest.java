package operationrene.mapframework.pointsofinterest;

import java.io.Serializable;
import operationrene.minigame.Minigame;

public abstract class PointOfInterest implements Serializable {
    
    protected PointType pointType;
    protected int ID;

    public PointOfInterest(PointType pointType, int ID) {
        this.pointType = pointType;
        this.ID = ID;
    }  

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public enum PointType {
        Key,
        Door,
        Safe,
        AlarmZone,
        Minigame,
        EntryPoint,
        EscapePoint
    }
    
}
