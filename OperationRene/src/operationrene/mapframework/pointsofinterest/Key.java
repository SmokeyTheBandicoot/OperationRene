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
    
    public Key(int roomID, int gameType, ArrayList<Integer> requiredKeysID) {   
        super(PointType.Key, roomID, requiredKeysID, new Size(1, 1));
        this.gameType = gameType;
    }
    
    @Override
    public String toString() {
        return super.toString() + "; Minigame: " + nameFromID();
    }
    
    private String nameFromID() {
        switch (gameType) {
            case 3:
                return "WIRES";
            case 4:
                return "KEYPAD";
            case 5:
                return "MEMORY";
            case 6:
                return "STRONGBOX";
            case 13:
                return "SIMONSAYS";
            case 14:
                return "WORDS";
            default:
                return "UNSELECTED";
        }
    }
}
