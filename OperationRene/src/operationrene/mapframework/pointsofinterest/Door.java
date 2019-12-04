package operationrene.mapframework.pointsofinterest;

public class Door extends PointOfInterest {
    
    private boolean isLocked;
    private boolean isOpen;

    public Door(PointType pointType, int roomID, int[] requiredKeysID, int width, int height, boolean isLocked, boolean isOpen) {
        super(pointType, roomID, requiredKeysID, width, height);
        this.isLocked = isLocked;
        this.isOpen = isOpen;
    }

    
    
    
    
}
