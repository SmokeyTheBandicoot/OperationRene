package operationrene.mapframework.pointsofinterest;

import java.io.Serializable;
import java.util.Arrays;
import operationrene.mapframework.Size;

public abstract class PointOfInterest implements Serializable {
    
    protected PointType pointType;
    protected int roomID;
    protected int[] requiredKeysID;
    protected Size size;

    public PointOfInterest(PointType pointType, int roomID, int[] requiredKeysID, Size size) {
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

    public int[] getRequiredKeysID() {
        return requiredKeysID;
    }

    public void setRequiredKeysID(int[] requiredKeysID) {
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
        return String.format("POINT_OF_INTEREST: %s, roomID: %d, reqKeys: %s, w: %d, h: %d", pointType.toString(), roomID, Arrays.toString(requiredKeysID), size.getWidth(), size.getHeight());
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
        MinigameID
    }
    
}
