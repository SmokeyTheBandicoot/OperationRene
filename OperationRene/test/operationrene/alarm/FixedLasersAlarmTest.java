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
public class FixedLasersAlarmTest {
    
    public FixedLasersAlarmTest() {
    }

    /**
     * Test of randomizeS1 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeS1() {
        System.out.println("randomizeS1");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.SMALL);
        instance.red = 3;
        instance.blue = 2;
        instance.randomizeS1();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((i <= 1 && j >= 2) ||
                   (i >= 2 && i <= 3 && j <= 3))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeS2 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeS2() {
        System.out.println("randomizeS2");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.SMALL);
        instance.red = 1;
        instance.blue = 3;
        instance.randomizeS2();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((i >= 1 && i <= 2 && j >= 1) ||
                   (i >= 3 && j <= 2))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeS3 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeS3() {
        System.out.println("randomizeS3");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.SMALL);
        instance.red = 1;
        instance.blue = 4;
        instance.randomizeS3();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((i >= 1 && i <= 2) ||
                   (j >= 3))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeM1 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeM1() {
        System.out.println("randomizeM1");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.MEDIUM);
        instance.red = 3;
        instance.blue = 4;
        instance.randomizeM1();
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((j >= 2 && j <= 3) ||
                   (j >= 5 && j <= 6) ||
                   (i >= 5 && j <= 1) ||
                   (i <= 1 && j == 4))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeM2 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeM2() {
        System.out.println("randomizeM2");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.MEDIUM);
        instance.red = 3;
        instance.blue = 1;
        instance.randomizeM2();
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 2 && i <= 3 && j <= 3) ||
                   (i >= 4 && i <= 5 && j >= 2))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeM3 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeM3() {
        System.out.println("randomizeM3");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.MEDIUM);
        instance.red = 5;
        instance.blue = 1;
        instance.randomizeM3();
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 1 && j <= 1) ||
                   (i >= 1 && i <= 2))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeL1 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeL1() {
        System.out.println("randomizeL1");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.LARGE);
        instance.red = 8;
        instance.blue = 2;
        instance.randomizeL1();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 5 && i <= 6 && j <= 7) ||
                   (i >= 3 && i <= 4 && j >= 6))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeL2 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeL2() {
        System.out.println("randomizeL2");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.LARGE);
        instance.red = 5;
        instance.blue = 3;
        instance.randomizeL2();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 2 && i <= 3 && j >= 3 && j <= 7) ||
                   (i >= 4 && i <= 5 && j >= 3 && j != 5) ||
                   (i >= 5 && i <= 6 && j <= 4))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeL3 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeL3() {
        System.out.println("randomizeL3");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.LARGE);
        instance.red = 4;
        instance.blue = 2;
        instance.randomizeL3();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 1 && i <= 2 && j <= 6) ||
                   (i >= 3 && i <= 7 && j >= 5 && j <= 6) ||
                   (i >= 6 && i <= 7 && j >= 7))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeRS1 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeRS1() {
        System.out.println("randomizeRS1");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.RECT_SMALL);
        instance.red = 4;
        instance.blue = 5;
        instance.randomizeRS1();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if(i >= 2 && i <= 3)
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeRS2 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeRS2() {
        System.out.println("randomizeRS2");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.RECT_SMALL);
        instance.red = 6;
        instance.blue = 1;
        instance.randomizeRS2();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 1 && j <= 1) ||
                   (i >= 3))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeRL1 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeRL1() {
        System.out.println("randomizeRL1");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.RECT_LARGE);
        instance.red = 5;
        instance.blue = 4;
        instance.randomizeRL1();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i >= 5 && i <= 6 && j <= 7) ||
                   (i <= 6 && j >= 6 && j <= 7) ||
                   (i <= 1 && j >= 6))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }

    /**
     * Test of randomizeRL2 method, of class FixedLasersAlarm.
     */
    @Test
    public void testRandomizeRL2() {
        System.out.println("randomizeRL2");
        FixedLasersAlarm instance = new FixedLasersAlarm(MapAlarm.Dimension.RECT_LARGE);
        instance.red = 3;
        instance.blue = 5;
        instance.randomizeRL2();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i >= 2 && i <= 3 && j <= 7) ||
                   (i >= 4 && i <= 5 && j >= 6 && j <= 7) ||
                   (i >= 6 && i <= 7 && j >= 6))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else
                    assertTrue(instance.getMatrix()[i][j] >= 3 && instance.getMatrix()[i][j] <= 5);
    }
    
}
