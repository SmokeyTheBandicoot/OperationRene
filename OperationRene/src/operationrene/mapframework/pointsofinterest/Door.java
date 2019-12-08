package operationrene.mapframework.pointsofinterest;

public class Door extends PointOfInterest {
    
    private boolean isOpen;

    public Door(int roomID, int[] requiredKeysID, int width, int height, boolean isOpen) {
        super(PointType.Door, roomID, requiredKeysID, width, height);
        this.isOpen = isOpen;
    }

    
    
    
    
}
