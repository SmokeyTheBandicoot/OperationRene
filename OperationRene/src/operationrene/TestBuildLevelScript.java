package operationrene;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import operationrene.mapframework.LevelMap;
import operationrene.mapframework.levelbuilder.LevelBuilder;
import operationrene.mapframework.levelbuilder.ProgressionRandomizer;
import operationrene.mapframework.matrixprops.*;
import operationrene.mapframework.pointsofinterest.*;
import operationrene.utils.MatrixUtils;

public class TestBuildLevelScript {

    
    public static void main(String[] args) {
        
        LevelBuilder lb = new LevelBuilder();
        lb.buildLevel();
        LevelMap lm = lb.getBuildingLevel();
        MatrixUtils.debugMatrix(lm.getMatrix());
        
        
        /* Integer[][] matrix = new Integer [][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 2, 0, 0, 1, 0, 2, 1},
            {1, 0, 1, 0, 2, 0, 0, 2, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 2, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 2, 0, 0, 2, 0, 0, 1},
            {1, 0, 3, 0, 2, 0, 0, 2, 0, 4, 1},
            {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} 
        };
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        lockeds.put(new Location(1, 1), new Safe(1, new int[]{}, new Size(1, 1)));
        lockeds.put(new Location(4, 2), new Door(1, new int[]{}, new Size(1, 2), false));
        lockeds.put(new Location(7, 3), new Door(2, new int[]{}, new Size(1, 2), false));
        lockeds.put(new Location(4, 8), new Door(3, new int[]{}, new Size(1, 2), false));
        lockeds.put(new Location(7, 8), new Door(4, new int[]{}, new Size(1, 2), false));
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        unlocks.put(new Location(1, 4), new Key(1, new int[]{25}));
        unlocks.put(new Location(9, 1), new Key(2, new int[]{34}));
        unlocks.put(new Location(1, 8), new Key(3, new int[]{46}));
        unlocks.put(new Location(9, 8), new Key(4, new int[]{57}));
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        HashMap<Location, Room> rooms = new HashMap<>();
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, rooms);
        */
        
        ProgressionRandomizer pr = new ProgressionRandomizer(lm);
        try {
            pr.randomize();
        } catch (Exception ex) {
            Logger.getLogger(TestBuildLevelScript.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // MatrixUtils.debugMatrix(lb.getBuildingLevel().getMatrix());
        // MatrixUtils.debugMatrix(lb.getBuildingLevel().getMatrix());
        ProceduralLevelPartsGenerator.debugPoints(lm.getUnlockingObjects());
        ProceduralLevelPartsGenerator.debugPoints(lm.getLockedObjects());
        ProceduralLevelPartsGenerator.debugPoints(lm.getOtherObjects());
        ProceduralLevelPartsGenerator.debugRooms(lm.getRooms());
        
        
        
        
    }
    
}
