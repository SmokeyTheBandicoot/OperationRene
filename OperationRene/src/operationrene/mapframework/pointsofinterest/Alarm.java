package operationrene.mapframework.pointsofinterest;

import operationrene.minigame.Minigame;

public class Alarm extends PointOfInterest {
    
    private Minigame minigame;
    
    public Alarm(int roomID, int[] requiredKeysID, int width, int height, Minigame minigame) {
        super(PointType.AlarmZone, roomID, requiredKeysID, width, height);
    }
    
    
}
