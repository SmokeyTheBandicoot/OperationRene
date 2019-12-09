package operationrene.mapframework.pointsofinterest;

import operationrene.mapframework.Size;

public class Key extends PointOfInterest {

    public Key(int roomID, int[] requiredKeysID) {
        super(PointType.Key, roomID, requiredKeysID, new Size(1, 1));
    }
    
}
