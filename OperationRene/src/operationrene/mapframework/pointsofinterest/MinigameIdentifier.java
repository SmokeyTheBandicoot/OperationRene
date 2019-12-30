package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class MinigameIdentifier extends PointOfInterest{
    
    public MinigameIdentifier(int roomID) {
        super(PointType.MinigameID, roomID, new ArrayList<>(), new Size(1, 1));
    }
    
}
