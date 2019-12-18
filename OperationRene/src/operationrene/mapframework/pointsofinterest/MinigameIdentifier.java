package operationrene.mapframework.pointsofinterest;

import operationrene.mapframework.matrixprops.Size;

public class MinigameIdentifier extends PointOfInterest{
    
    public MinigameIdentifier(int roomID) {
        super(PointType.MinigameID, roomID, null, new Size(1, 1));
    }
    
}
