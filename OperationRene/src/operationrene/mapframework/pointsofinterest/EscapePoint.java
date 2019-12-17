package operationrene.mapframework.pointsofinterest;

import operationrene.mapframework.matrixprops.Size;

public class EscapePoint extends PointOfInterest {
    
    public EscapePoint(int roomID) {
        super(PointOfInterest.PointType.EscapePoint, roomID, new int[]{-1}, new Size(1, 1));
    }
    
}
