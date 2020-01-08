/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.alarm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Amministratore
 */
public class PressureTilesAlarmTest {
    
    public PressureTilesAlarmTest() {
    }

    /**
     * Test of randomizeS1 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeS1() {
        System.out.println("randomizeS1");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.SMALL);
        instance.red = 4;
        instance.blue = 2;
        instance.randomizeS1();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((i == 1 && j <= 1) ||
                   (i >= 2 && i <= 3))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeS2 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeS2() {
        System.out.println("randomizeS2");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.SMALL);
        instance.red = 3;
        instance.blue = 2;
        instance.randomizeS2();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(i >= 3)
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeS3 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeS3() {
        System.out.println("randomizeS3");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.SMALL);
        instance.red = 3;
        instance.blue = 4;
        instance.randomizeS3();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((i >= 1 && i <= 2 && j <= 2) || 
                   (i >= 2 && i <= 3 && j >= 1) || 
                   (i == 4 && j >= 3))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeM1 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeM1() {
        System.out.println("randomizeM1");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.MEDIUM);
        instance.red = 5;
        instance.blue = 2;
        instance.randomizeM1();
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 2 && i <= 3 && j <= 3) ||
                   (i >= 4 && i <= 5 && j >= 2))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeM2 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeM2() {
        System.out.println("randomizeM2");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.MEDIUM);
        instance.red = 3;
        instance.blue = 1;
        instance.randomizeM2();
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 1 && i <= 2 && j <= 2) ||
                   (i >= 3 && i <= 4 && j >= 1 && j <= 3) ||
                   (i >= 5 && j >= 2))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeM3 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeM3() {
        System.out.println("randomizeM3");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.MEDIUM);
        instance.red = 3;
        instance.blue = 3;
        instance.randomizeM3();
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 2 && j <= 1) ||
                   (i >= 5 && j <= 5) ||
                   (i >= 3 && i <= 4 && j >= 4))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeL1 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeL1() {
        System.out.println("randomizeL1");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.LARGE);
        instance.red = 6;
        instance.blue = 4;
        instance.randomizeL1();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 1 && i <= 2 && j != 3) ||
                   (i >= 3 && i <= 7 && (j >= 1 && j <= 2 || j >= 4 && j <= 5)) ||
                   (i >= 6 && i <= 7 && j == 3))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeL2 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeL2() {
        System.out.println("randomizeL2");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.LARGE);
        instance.red = 4;
        instance.blue = 3;
        instance.randomizeL2();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 2 && i <= 3 && j <= 2) ||
                   (i >= 4 && i <= 5 && j >= 1))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeL3 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeL3() {
        System.out.println("randomizeL3");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.LARGE);
        instance.red = 4;
        instance.blue = 6;
        instance.randomizeL3();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 7 && i <= 8 && j >= 1 && j <= 5) ||
                   (i >= 3 && j >= 4 && j <= 5) ||
                   (i >= 1 && i <= 4 && j >= 5 && j <= 7) ||
                   (i >= 1 && i <= 2 && j >= 5) ||
                   (i >= 7 && i <= 8 && j == 0))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }
    
    @Test
    public void testRandomizeRS1() {
        System.out.println("randomizeRS1");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.RECT_SMALL);
        instance.red = 3;
        instance.blue = 2;
        instance.randomizeRS1();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 2 && i <= 3 && j <= 5) ||
                   (i <= 1 && j >= 4))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeRS2 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeRS2() {
        System.out.println("randomizeRS2");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.RECT_SMALL);
        instance.red = 2;
        instance.blue = 3;
        instance.randomizeRS2();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 1 && i <= 2 && j <= 2) ||
                   (i >= 3 && j >= 1))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeRL1 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeRL1() {
        System.out.println("randomizeRL1");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.RECT_LARGE);
        instance.red = 2;
        instance.blue = 3;
        instance.randomizeRL1();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i <= 1 && j >= 1) ||
                   (i == 2 && j >= 1 && j <= 2) ||
                   (i >= 3 && i <= 4 && j >= 1 && j <= 8) ||
                   (i == 5 && j >= 7 && j <= 8) ||
                   (i >= 6 && i <= 7 && j <= 8))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeRL2 method, of class PressureTilesAlarm.
     */
    @Test
    public void testRandomizeRL2() {
        System.out.println("randomizeRL2");
        PressureTilesAlarm instance = new PressureTilesAlarm(MapAlarm.Dimension.RECT_LARGE);
        instance.red = 3;
        instance.blue = 2;
        instance.randomizeRL2();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i >= 1 && i <= 2 && j <= 2) ||
                   (i >= 3 && i <= 4 && j != 0 && j != 3) ||
                   (i >= 5 && i <= 6 && j >= 1 && j <= 5))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }
    
}
