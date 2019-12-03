package operationrene.mapframework;

public class LevelBuilder {
    
    protected LevelMap lm;
    
    public LevelRoom addRoom(LevelRoom lm, Rotation r, Flipping f) {
        return null;
    }
    
    public LevelMap buildLevel(){
        return null;
    }
    
    public enum Rotation {
        NONE,
        LEFT,
        RIGHT,
        DEG180
    }
    
    public enum Flipping {
        NONE,
        HORIZONTAL,
        VERTICAL,
        BOTH
    }
    
}
