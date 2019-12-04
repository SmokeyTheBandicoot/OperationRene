/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.mapframework;

import operationrene.mapframework.pointsofinterest.PointOfInterest;
import java.util.HashMap;
import operationrene.mapframework.pointsofinterest.*;
import operationrene.mapframework.pointsofinterest.PointOfInterest.PointType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miky Gargiulo
 */
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
        
        unlocks.put(new Location(1, 2), new Key(PointType.Key, -1, new int[]{-1}, 1, 1));
        lockeds.put(new Location(2, 3), new Door(PointType.Door, -1, new int[]{-1}, 1, 1, false, true));
        other.put(new Location(5, 6), new Alarm(PointType.AlarmZone, -1, new int[]{-1}, 5, 5, null));
        
        LevelMap level = new LevelMap(-1, matrix, unlocks, lockeds, other);
        
        String path = "assets/levels/level_test.dat";
        LevelSerializer.saveLevel(level, path);
        
        System.out.println("loadLevel");
        
        LevelMap loaded = LevelSerializer.loadLevel(path);
        
        assertEquals(loaded.getMatrix(), level.getMatrix());
        System.out.println("matrix ok");

        //TODO: Check for Points of interest also
        
    }
    
}
