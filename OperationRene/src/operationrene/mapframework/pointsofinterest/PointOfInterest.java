package operationrene.mapframework.pointsofinterest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import operationrene.mapframework.matrixprops.Size;

public abstract class PointOfInterest implements Serializable {
    
    protected PointType pointType;
    protected int roomID;
    protected ArrayList<Integer> requiredKeysID;
    protected Size size;

    public PointOfInterest(PointType pointType, int roomID, ArrayList<Integer> requiredKeysID, Size size) {
        this.pointType = pointType;
        this.roomID = roomID;
        this.requiredKeysID = requiredKeysID;
        this.size = size;
    }

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public ArrayList<Integer> getRequiredKeysID() {
        return requiredKeysID;
    }

    public void setRequiredKeysID(ArrayList<Integer> requiredKeysID) {
        this.requiredKeysID = requiredKeysID;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    
    @Override
    public String toString() {
        if (requiredKeysID != null)
            return String.format("POINT_OF_INTEREST: %s, roomID: %d, reqKeys: %s, w: %d, h: %d", pointType.toString(), roomID, requiredKeysID.toString(), size.getWidth(), size.getHeight());   
        else    
            return String.format("POINT_OF_INTEREST: %s, roomID: %d, reqKeys: [], w: %d, h: %d", pointType.toString(), roomID, size.getWidth(), size.getHeight());   
    }
    
    
    public enum PointType {
        Key,
        Door,
        Safe,
        Room,
        AlarmZone,
        Minigame,
        EntryPoint,
        EscapePoint,
        AlarmID
    }
    
}
