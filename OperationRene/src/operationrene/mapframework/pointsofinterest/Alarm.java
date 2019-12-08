package operationrene.mapframework.pointsofinterest;

public class Alarm extends PointOfInterest {
    
    public Alarm(int roomID, int width, int height) {
        super(PointType.AlarmZone, roomID, new int []{-1}, width, height);
    }
    
    @Override
    public String toString() {
        return String.format("ALARM: roomID: %d, w: %d, h: %d", roomID, width, height);
    }
}
