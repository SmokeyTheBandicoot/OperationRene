package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class EscapePoint extends PointOfInterest {
    
    public EscapePoint(int roomID, ArrayList<Integer> keys) {
        super(PointOfInterest.PointType.EscapePoint, roomID, keys, new Size(1, 1));
    }
    
}
