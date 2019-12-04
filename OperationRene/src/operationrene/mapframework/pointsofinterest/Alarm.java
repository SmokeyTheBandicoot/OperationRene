package operationrene.mapframework.pointsofinterest;

import operationrene.minigame.Minigame;

public class Alarm extends PointOfInterest {
    
    private Minigame minigame;
    
    public Alarm(PointType pointType, int roomID, int[] requiredKeysID, int width, int height, Minigame minigame) {
        super(pointType, roomID, requiredKeysID, width, height);
    }
    
    
}
