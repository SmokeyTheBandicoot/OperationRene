
package operationrene.mapframework;

import operationrene.mapframework.matrixprops.Direction;
import java.io.*;
import operationrene.mapframework.levelbuilder.LevelSerializer;
import operationrene.mapframework.matrixprops.Size;
import operationrene.mapframework.matrixprops.Location;
import operationrene.utils.*;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.mapframework.matrixprops.*;
import java.util.HashMap;
import operationrene.mapframework.pointsofinterest.*;
import org.junit.Assert;
import org.junit.Test;


public class LevelSerializerTest {
    
    public LevelSerializerTest() {
    }
    


    /**
     * Test of saveLevel and loadLevel methods, of class LevelSerializer.
     */
    @Test
    public void testSaveLoadLevel() {
        
        System.out.println("saveLevel");
        
        Integer[][] matrix = new Integer[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> other = new HashMap<>();
        HashMap<Location, Room> rooms = new HashMap<>();
        
        
        unlocks.put(new Location(1, 2), new Key(-1, new int[]{-1}));
        lockeds.put(new Location(2, 3), new Door(-1, new int[]{-1}, new Size(1, 1), false));
        other.put(new Location(5, 6), new Alarm(-1, new Size(5, 5)));
        rooms.put(new Location(1, 1), new Room(-1, new Size(111, 222), Direction.LEFT));
        
        LevelMap level = new LevelMap(-1, matrix, unlocks, lockeds, other, rooms);
        
        String path = "assets/levels/level_test.dat";
        LevelSerializer.saveLevel(level, path);
        
        System.out.println("loadLevel");
        
        LevelMap loaded = LevelSerializer.loadLevel(path);
        
        // MatrixUtils.debugMatrix(loaded.getMatrix());
        // MatrixUtils.debugMatrix(level.getMatrix());

        Assert.assertArrayEquals(loaded.getMatrix(), level.getMatrix());
        System.out.println("matrix ok");
        
        File f = new File(path);
        f.delete();

        //TODO: Check for Points of interest also
    }
}
