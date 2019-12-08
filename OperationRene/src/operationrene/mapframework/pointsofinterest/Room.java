package operationrene.mapframework.pointsofinterest;

public class Room extends PointOfInterest {
    
    private int width;
    private int height;
    
    public Room(int roomID, int width, int height) {
        super(PointType.Room, roomID, new int []{-1}, width, height);
        
    }
    
}
