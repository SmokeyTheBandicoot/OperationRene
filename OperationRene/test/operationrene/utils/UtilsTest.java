/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import java.util.ArrayList;
import operationrene.mapframework.levelbuilder.LevelBuilder;
import java.util.HashMap;
import java.util.List;
import operationrene.alarm.MapAlarm;
import operationrene.mapframework.*;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Miky Gargiulo
 */
public class UtilsTest {
    
    public UtilsTest() {
    }
    
     /**
     * Test of getBiggestFittingSize method, of class Utils.
     */
    @Test
    public void testGetBiggestFittingSize() {
        System.out.println("getBiggestFittingSize");
           
        Size available = null;
        int [] expected = new int []{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 3, 3, 1, 
            1, 1, 1, 1, 1, -1, 3, 3, 1, 1, 1, 1, 1, 1, -1, 3, 3, 1, 1, 2, 2, 
            2, 2, -1, 3, 3, 1, 1, 2, 2, 2, 2, -1, 3, 3, 1, 1, 4, 4, 4, 4, -1, 
            3, 3, 1, 1, 4, 4, 4, 4, };
        int [] results = new int [81];
        int index = 0;
            
        for (int i = 4; i <= 12; i++)
            for (int j = 4; j <= 12; j++) {
                available = new Size(i, j);
                int k = Utils.getBiggestFittingSize(MapAlarm.getMinigameDimensions(), available, true);
                results[index++] = k;
            }

        
        Assert.assertArrayEquals(expected, results);
        // Assert.assertArrayEquals(expected, results);
        
    }

    /**
     * Test of rotateRoom method, of class Utils.
     */
    @Test
    public void testRotateRoom() {
        
        System.out.println("rotateRoom");
        
        
        System.out.println("RIGHT ROTATION");
        LevelMap room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        LevelBuilder.Rotation r = LevelBuilder.Rotation.RIGHT;
        LevelMap expResult = new LevelMap(-1, new Integer[][]{{4, 1}, {5, 2}, {6, 3}}, null, null, null);
        LevelMap result = Utils.rotateRoom(room, r);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());

        
        System.out.println("LEFT ROTATION");
        room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        r = LevelBuilder.Rotation.LEFT;
        expResult = new LevelMap(-1, new Integer[][]{{3, 6}, {2, 5}, {1, 4}}, null, null, null);
        result = Utils.rotateRoom(room, r);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
        
        
        System.out.println("180 DEG ROTATION");
        room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        r = LevelBuilder.Rotation.DEG180;
        expResult = new LevelMap(-1, new Integer[][]{{6, 5, 4}, {3, 2, 1}}, null, null, null);
        result = Utils.rotateRoom(room, r);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
    }
 
    /**
     * Test of flipRoom method, of class Utils.
     */
    @Test
    public void testFlipRoom() {
        
        System.out.println("flipRoom");
        
        System.out.println("HORIZONTAL FLIPPING");
        LevelMap room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        LevelBuilder.Flipping f = LevelBuilder.Flipping.HORIZONTAL;
        LevelMap expResult = new LevelMap(-1, new Integer[][]{{3, 2, 1}, {6, 5, 4}}, null, null, null);
        LevelMap result = Utils.flipRoom(room, f);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());

        
        System.out.println("VERTICAL FLIPPING");
        room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        f = LevelBuilder.Flipping.VERTICAL;
        expResult = new LevelMap(-1, new Integer[][]{{4, 5, 6}, {1, 2, 3}}, null, null, null);
        room = Utils.flipRoom(room, f);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
        
        
        System.out.println("BOTH FLIPPING");
        room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        f = LevelBuilder.Flipping.BOTH;
        expResult = new LevelMap(-1, new Integer[][]{{6, 5, 4}, {3, 2, 1}}, null, null, null);
        result = Utils.flipRoom(room, f);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
    }
    
}
