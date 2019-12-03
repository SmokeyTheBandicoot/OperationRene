package operationrene.mapframework.pointsofinterest;

public class Door extends PointOfInterest {
    
    private boolean isLocked;
    private boolean isOpen;

    public Door(boolean isLocked, boolean isOpen, int ID) {
        super(PointType.Door, ID);
        this.isLocked = isLocked;
        this.isOpen = isOpen;
    }
    
    
    
}
