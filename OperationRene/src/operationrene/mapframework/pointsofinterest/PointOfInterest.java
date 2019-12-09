package operationrene.mapframework.pointsofinterest;

import java.io.Serializable;
import java.util.Arrays;

public abstract class PointOfInterest implements Serializable {
    
    protected PointType pointType;
    protected int roomID;
    protected int[] requiredKeysID;
    protected int width;
    protected int height;

    public PointOfInterest(PointType pointType, int roomID, int[] requiredKeysID, int width, int height) {
        this.pointType = pointType;
        this.roomID = roomID;
        this.requiredKeysID = requiredKeysID;
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public String toString() {
        return String.format("POINT_OF_INTEREST: %s, roomID: %d, reqKeys: %s, w: %d, h: %d", pointType.toString(), roomID, Arrays.toString(requiredKeysID), width, height);
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
