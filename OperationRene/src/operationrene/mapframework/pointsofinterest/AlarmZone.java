package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class AlarmZone extends PointOfInterest {

    private AlarmIdentifier alarmIdentifier;

    public AlarmIdentifier getAlarmIdentifier() {
        return alarmIdentifier;
    }

    public void setAlarmIdentifier(AlarmIdentifier alarmIdentifier) {
        this.alarmIdentifier = alarmIdentifier;
    }

    public AlarmZone(int roomID, Size size, AlarmIdentifier alarmIdentifier) {
        super(PointType.AlarmZone, roomID, new ArrayList<>(), size);
        this.alarmIdentifier = alarmIdentifier;
    }

}
