package operationrene.mapframework.pointsofinterest;

import operationrene.minigame.Minigame;

public class Safe extends PointOfInterest {

    private Minigame minigame;

    public Safe(PointType pointType, int roomID, int[] requiredKeysID, int width, int height, Minigame minigame) {
        super(pointType, roomID, requiredKeysID, width, height);
        this.minigame = minigame;
    }

    
    
    
    
   
    
}
