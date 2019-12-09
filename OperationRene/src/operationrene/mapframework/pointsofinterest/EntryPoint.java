package operationrene.mapframework.pointsofinterest;

import operationrene.mapframework.Size;

public class EntryPoint extends PointOfInterest {
    
    public EntryPoint(int roomID) {
        super(PointType.EntryPoint, roomID, new int[]{-1}, new Size(1, 1));
    }
    
}
