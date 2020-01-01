package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class MinigameIdentifier extends PointOfInterest{

    private AlarmType minigameID;

    public AlarmType getMinigameID() {
        return minigameID;
    }

    public void setMinigameID(AlarmType minigameID) {
        this.minigameID = minigameID;
    }

    public MinigameIdentifier(int roomID, AlarmType mt) {
        super(PointType.MinigameID, roomID, new ArrayList<>(), new Size(1, 1));
        this.minigameID = mt;
    }
    
    public enum AlarmType {
        UNSELECTED,
        PRESSURE_TILES,
        FIXED_LASERS,
        PULSATING_LASERS
    }
    
    @Override
    public String toString() {
        return super.toString() + "; Alarm type: " + minigameID.toString();
    }
    
}
