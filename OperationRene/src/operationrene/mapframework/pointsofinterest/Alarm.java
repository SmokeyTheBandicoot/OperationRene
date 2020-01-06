package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class Alarm extends PointOfInterest {
    
    public Alarm(int roomID, Size size) {
        super(PointType.AlarmZone, roomID, new ArrayList<>(), size);
    }
    
    @Override
    public String toString() {
        return String.format("ALARM: roomID: %d, w: %d, h: %d", roomID, size.getWidth(), size.getHeight());
    }
}
