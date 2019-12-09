package operationrene.mapframework.pointsofinterest;

public class MinigameIdentifier extends PointOfInterest{
    
    public MinigameIdentifier(int roomID) {
        super(PointType.MinigameID, roomID, new int[]{-1}, 1, 1);
    }
    
}
