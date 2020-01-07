package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.alarm.MapAlarm;
import operationrene.mapframework.matrixprops.Size;

public class AlarmZone extends PointOfInterest {

    private AlarmIdentifier alarmIdentifier;
    private AlarmIdentifier.AlarmType alarmType;
    
    private MapAlarm mapAlarm;

    /**
     * Get the value of mapAlarm
     *
     * @return the value of mapAlarm
     */
    public MapAlarm getMapAlarm() {
        return mapAlarm;
    }

    /**
     * Set the value of mapAlarm
     *
     * @param mapAlarm new value of mapAlarm
     */
    public void setMapAlarm(MapAlarm mapAlarm) {
        this.mapAlarm = mapAlarm;
    }


    public AlarmIdentifier.AlarmType getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(AlarmIdentifier.AlarmType alarmType) {
        this.alarmType = alarmType;
    }

    

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