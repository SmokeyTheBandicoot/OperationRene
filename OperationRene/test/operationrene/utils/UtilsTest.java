/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import java.util.HashMap;
import operationrene.mapframework.LevelBuilder;
import operationrene.mapframework.LevelRoom;
import operationrene.mapframework.LocationAndSize;
import operationrene.mapframework.pointsofinterest.PointOfInterest.PointType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miky Gargiulo
 */
public class UtilsTest {
    
    public UtilsTest() {
    }

    /**
     * Test of rotateRoom method, of class Utils.
     */
    @Test
    public void testRotateRoom() {
        
        System.out.println("rotateRoom");
        
        HashMap<LocationAndSize, PointType> hm = new HashMap<>();
        
        System.out.println("RIGHT ROTATION");
        LevelRoom room = new LevelRoom(new Integer[][]{{1, 2, 3}, {4, 5, 6}}, hm);
        LevelBuilder.Rotation r = LevelBuilder.Rotation.RIGHT;
        LevelRoom expResult = new LevelRoom(new Integer[][]{{4, 1}, {5, 2}, {6, 3}}, hm);
        LevelRoom result = Utils.rotateRoom(room, r);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());

        
        System.out.println("LEFT ROTATION");
        room = new LevelRoom(new Integer[][]{{1, 2, 3}, {4, 5, 6}}, hm);
        r = LevelBuilder.Rotation.LEFT;
        expResult = new LevelRoom(new Integer[][]{{3, 6}, {2, 5}, {1, 4}}, hm);
        result = Utils.rotateRoom(room, r);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
        
        
        System.out.println("180 DEG ROTATION");
        room = new LevelRoom(new Integer[][]{{1, 2, 3}, {4, 5, 6}}, hm);
        r = LevelBuilder.Rotation.DEG180;
        expResult = new LevelRoom(new Integer[][]{{6, 5, 4}, {3, 2, 1}}, hm);
        result = Utils.rotateRoom(room, r);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
    }
 
    /**
     * Test of flipRoom method, of class Utils.
     */
    @Test
    public void testFlipRoom() {
        
        System.out.println("flipRoom");
        
        HashMap<LocationAndSize, PointType> hm = new HashMap<>();
        
        System.out.println("HORIZONTAL FLIPPING");
        LevelRoom room = new LevelRoom(new Integer[][]{{1, 2, 3}, {4, 5, 6}}, hm);
        LevelBuilder.Flipping f = LevelBuilder.Flipping.HORIZONTAL;
        LevelRoom expResult = new LevelRoom(new Integer[][]{{3, 2, 1}, {6, 5, 4}}, hm);
        LevelRoom result = Utils.flipRoom(room, f);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());

        
        System.out.println("VERTICAL FLIPPING");
        room = new LevelRoom(new Integer[][]{{1, 2, 3}, {4, 5, 6}}, hm);
        f = LevelBuilder.Flipping.VERTICAL;
        expResult = new LevelRoom(new Integer[][]{{4, 5, 6}, {1, 2, 3}}, hm);
        result = Utils.flipRoom(room, f);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
        
        
        System.out.println("BOTH FLIPPING");
        room = new LevelRoom(new Integer[][]{{1, 2, 3}, {4, 5, 6}}, hm);
        f = LevelBuilder.Flipping.BOTH;
        expResult = new LevelRoom(new Integer[][]{{6, 5, 4}, {3, 2, 1}}, hm);
        result = Utils.flipRoom(room, f);
        Assert.assertArrayEquals(room.getMatrix(), expResult.getMatrix());
    }
    
}
