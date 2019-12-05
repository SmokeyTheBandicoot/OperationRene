package operationrene.mapframework.pointsofinterest;

import operationrene.minigame.Minigame;

public class Safe extends PointOfInterest {

    private Minigame minigame;

    public Safe(int roomID, int[] requiredKeysID, int width, int height, Minigame minigame) {
        super(PointType.Safe, roomID, requiredKeysID, width, height);
        this.minigame = minigame;
    }

    
    
    
    
   
    
}
