package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class Safe extends PointOfInterest {
    
    public Safe(int roomID, ArrayList<Integer> requiredKeysID, Size size) {
        super(PointType.Safe, roomID, requiredKeysID, size);
    }
}
