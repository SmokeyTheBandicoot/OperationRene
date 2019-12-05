package operationrene.mapframework.pointsofinterest;

public class Key extends PointOfInterest {

    public Key(int roomID, int[] requiredKeysID, int width, int height) {
        super(PointType.Key, roomID, requiredKeysID, width, height);
    }
    
}
