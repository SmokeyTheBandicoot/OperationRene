/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.mapframework;

import operationrene.mapframework.pointsofinterest.PointOfInterest;
import java.util.HashMap;
import operationrene.mapframework.pointsofinterest.*;
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
        HashMap<LocationAndSize, PointOfInterest> poi = new HashMap<>();
        
        poi.put(new LocationAndSize(1, 2, 3, 4), new Key(0));
        poi.put(new LocationAndSize(2, 3, 4, 5), new Door(false, true, 0));
        
        LevelMap level = new LevelMap(matrix, poi);
        
        String path = "assets/levels/level_test.dat";
        LevelSerializer.saveLevel(level, path);
        
        System.out.println("loadLevel");
        
        LevelMap loaded = LevelSerializer.loadLevel(path);
        
        assertEquals(loaded.getMatrix(), level.getMatrix());
        System.out.println("matrix ok");

        //TODO: Check for Points of interest also
        
    }
    
}
