package operationrene.mapframework.pointsofinterest;

import java.io.Serializable;

public abstract class PointOfInterest implements Serializable {
    
    protected PointType pointType;
    protected int ID;

    public PointOfInterest(PointType pointType, int ID) {
        this.pointType = pointType;
        this.ID = ID;
    }   
    
    public enum PointType {
        Key,
        Door,
        Safe,
        AlarmZone,
        AlarmMechanism,
        EntryPoint,
        EscapePoint
    }
    
}