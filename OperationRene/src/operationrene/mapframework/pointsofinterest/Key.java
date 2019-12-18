package operationrene.mapframework.pointsofinterest;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;

public class Key extends PointOfInterest {

    private int gameType;

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }
    
    public Key(int roomID, int gameType,ArrayList<Integer> requiredKeysID) {
        
        super(PointType.Key, roomID, requiredKeysID, new Size(1, 1));
        this.gameType = gameType;
        
    }

}
