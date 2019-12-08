package operationrene.mapframework.pointsofinterest;

public class Key extends PointOfInterest {

    public Key(int roomID, int[] requiredKeysID) {
        super(PointType.Key, roomID, requiredKeysID, 1, 1);
    }
    
}
