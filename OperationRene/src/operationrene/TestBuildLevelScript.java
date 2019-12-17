package operationrene;

import operationrene.mapframework.levelbuilder.LevelBuilder;
import operationrene.utils.MatrixUtils;

public class TestBuildLevelScript {

    
    public static void main(String[] args) {
        
        LevelBuilder lb = new LevelBuilder();
        lb.buildLevel();
        
        MatrixUtils.debugMatrix(lb.getBuildingLevel().getMatrix());
    }
    
}
