package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class AlarmIdentifier extends PointOfInterest{

    private AlarmType alarmID;

    public AlarmType getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(AlarmType minigameID) {
        this.alarmID = minigameID;
    }

    public AlarmIdentifier(int roomID, AlarmType mt) {
        super(PointType.AlarmID, roomID, new ArrayList<>(), new Size(1, 1));
        this.alarmID = mt;
    }
    
    public enum AlarmType {
        UNSELECTED,
        PRESSURE_TILES,
        FIXED_LASERS,
        PULSATING_LASERS
    }
    
    @Override
    public String toString() {
        return super.toString() + "; Alarm type: " + alarmID.toString();
    }
    
}
