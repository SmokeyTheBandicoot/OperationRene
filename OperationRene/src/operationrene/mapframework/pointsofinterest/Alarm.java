package operationrene.mapframework.pointsofinterest;

import operationrene.mapframework.Size;

public class Alarm extends PointOfInterest {
    
    public Alarm(int roomID, Size size) {
        super(PointType.AlarmZone, roomID, new int []{-1}, size);
    }
    
    @Override
    public String toString() {
        return String.format("ALARM: roomID: %d, w: %d, h: %d", roomID, size.getWidth(), size.getHeight());
    }
}
