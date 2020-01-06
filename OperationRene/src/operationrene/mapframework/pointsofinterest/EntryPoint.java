package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class EntryPoint extends PointOfInterest {
    
    public EntryPoint(int roomID) {
        super(PointType.EntryPoint, roomID, new ArrayList<>(), new Size(1, 1));
    }
    
}
