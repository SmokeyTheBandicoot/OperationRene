package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class Door extends PointOfInterest {
    
    private boolean isOpen;

    public Door(int roomID, ArrayList<Integer> requiredKeysID, Size size, boolean isOpen) {
        super(PointType.Door, roomID, requiredKeysID, size);
        this.isOpen = isOpen;
    }

    
    
    
    
}
