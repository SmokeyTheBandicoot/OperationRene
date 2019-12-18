package operationrene.mapframework.pointsofinterest;

import operationrene.mapframework.matrixprops.Size;

public class Alarm extends PointOfInterest {
    
    public Alarm(int roomID, Size size) {
        super(PointType.AlarmZone, roomID, null, size);
    }
    
    @Override
    public String toString() {
        return String.format("ALARM: roomID: %d, w: %d, h: %d", roomID, size.getWidth(), size.getHeight());
    }
}
