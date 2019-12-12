/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import operationrene.mapframework.LevelMap;
import org.junit.Assert;
import org.junit.Test;
import operationrene.mapframework.matrixprops.*;

/**
 *
 * @author Giuce
 */
public class RoomUtilsTest {
    
    public RoomUtilsTest() {
    }

    /**
     * Test of rotateRoom method, of class Utils.
     */
    @Test
    public void testRotateRoom() {
        
        System.out.println("rotateRoom");
        
        
        System.out.println("RIGHT ROTATION");
        LevelMap room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        Rotation r = Rotation.RIGHT;
        LevelMap expResult = new LevelMap(-1, new Integer[][]{{4, 1}, {5, 2}, {6, 3}}, null, null, null);
        LevelMap result = RoomUtils.rotateRoom(room, r);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());

        
        System.out.println("LEFT ROTATION");
        room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        r = Rotation.LEFT;
        expResult = new LevelMap(-1, new Integer[][]{{3, 6}, {2, 5}, {1, 4}}, null, null, null);
        result = RoomUtils.rotateRoom(room, r);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
        
        
        System.out.println("180 DEG ROTATION");
        room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        r = Rotation.DEG180;
        expResult = new LevelMap(-1, new Integer[][]{{6, 5, 4}, {3, 2, 1}}, null, null, null);
        result = RoomUtils.rotateRoom(room, r);
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
        Flipping f = Flipping.HORIZONTAL;
        LevelMap expResult = new LevelMap(-1, new Integer[][]{{3, 2, 1}, {6, 5, 4}}, null, null, null);
        LevelMap result = RoomUtils.flipRoom(room, f);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());

        
        System.out.println("VERTICAL FLIPPING");
        room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        f = Flipping.VERTICAL;
        expResult = new LevelMap(-1, new Integer[][]{{4, 5, 6}, {1, 2, 3}}, null, null, null);
        room = RoomUtils.flipRoom(room, f);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
        
        
        System.out.println("BOTH FLIPPING");
        room = new LevelMap(-1, new Integer[][]{{1, 2, 3}, {4, 5, 6}}, null, null, null);
        f = Flipping.BOTH;
        expResult = new LevelMap(-1, new Integer[][]{{6, 5, 4}, {3, 2, 1}}, null, null, null);
        result = RoomUtils.flipRoom(room, f);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
    }
    
}
