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
public class PulsatingLasersAlarmTest {
    
    public PulsatingLasersAlarmTest() {
    }
    
    /**
     * Test of randomizeS1 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeS1() {
        System.out.println("randomizeS1");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.SMALL);
        instance.randomizeS1();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(i == 0 && j == 4)
                    assertEquals(1, instance.getMatrix()[i][j]);
                else if(j >= 2 && j <= 3)
                    assertEquals(3, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeS2 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeS2() {
        System.out.println("randomizeS2");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.SMALL);
        instance.randomizeS2();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(j == 2 || j == 3 || (i <= 2 && j == 0))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else if(i >= 3 && j == 0)
                    assertEquals(1, instance.getMatrix()[i][j]);
                else
                    assertEquals(3, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeS3 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeS3() {
        System.out.println("randomizeS3");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.SMALL);
        instance.randomizeS3();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(j == 0 || j == 3)
                    assertEquals(3, instance.getMatrix()[i][j]);
                else if(i <= 1 && j >= 1 && j <= 2)
                    assertEquals(1, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeM1 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeM1() {
        System.out.println("randomizeM1");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.MEDIUM);
        instance.randomizeM1();
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((j >= 1 && j <= 2) ||
                   (j >= 4 && i != 3))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else if(i >= 3 && j == 3)
                    assertEquals(1, instance.getMatrix()[i][j]);
                else
                    assertEquals(3, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeM2 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeM2() {
        System.out.println("randomizeM2");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.MEDIUM);
        instance.randomizeM2();
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 5 && j <= 1) ||
                   (i >= 4 && j >= 3 && j <= 5) ||
                   (i <= 2 && j >= 2))
                    assertEquals(0, instance.getMatrix()[i][j]);
                else if((i <= 4 && j <= 1) ||
                        (i >= 4 && j == 6))
                    assertEquals(1, instance.getMatrix()[i][j]);
                else
                    assertEquals(3, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeM3 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeM3() {
        System.out.println("randomizeM3");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.MEDIUM);
        instance.randomizeM3();
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 4 && j == 1) ||
                   (i <= 4 && j == 4))
                    assertEquals(1, instance.getMatrix()[i][j]);
                else if((i <= 3 && j == 1) ||
                        (i == 4 && j >= 2 && j <= 3) ||
                        (i == 4 && j >= 5 && j <= 6) ||
                        (i >= 5 && j == 4))
                    assertEquals(3, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeL1 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeL1() {
        System.out.println("randomizeL1");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.LARGE);
        instance.randomizeL1();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(j == 2 || j == 5 || j == 8)
                    assertEquals(3, instance.getMatrix()[i][j]);
                else if((j <= 1 && i != 3 && i != 4) ||
                        (j == 3 && i >= 5) ||
                        (j == 6 && i <= 3))
                    assertEquals(1, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeL2 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeL2() {
        System.out.println("randomizeL2");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.LARGE);
        instance.randomizeL2();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 3 && j <= 5) ||
                   (i <= 6 && j == 8))
                    assertEquals(1, instance.getMatrix()[i][j]);
                else if((i <= 2 && (j == 2 || j == 5)) ||
                        (j >= 6 && j <= 7 && (i == 3 || i == 6)))
                    assertEquals(3, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeL3 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeL3() {
        System.out.println("randomizeL3");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.LARGE);
        instance.randomizeL3();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i <= 3 && j >= 2 && j <= 6) ||
                   (i >= 7 && j >= 2))
                    assertEquals(1, instance.getMatrix()[i][j]);
                else if((i >= 4 && i <= 6 && (j == 2 || j == 5)) ||
                        (i == 2 && j >= 7))
                    assertEquals(3, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeRS1 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeRS1() {
        System.out.println("randomizeRS1");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.RECT_SMALL);
        instance.randomizeRS1();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if(i <= 2 && (j == 0 || j == 3 || j == 4))
                    assertEquals(1, instance.getMatrix()[i][j]);
                else if(j == 2 || j == 5)
                    assertEquals(3, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeRS2 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeRS2() {
        System.out.println("randomizeRS2");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.RECT_SMALL);
        instance.randomizeRS2();
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if((i <= 2 && j >= 4) ||
                   (i >= 3 && j <= 1))
                    assertEquals(1, instance.getMatrix()[i][j]);
                else if((i <= 2 && j >= 2 && j <= 3) ||
                        (i >= 3 && j == 4))
                    assertEquals(3, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeRL1 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeRL1() {
        System.out.println("randomizeRL1");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.RECT_LARGE);
        instance.randomizeRL1();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i >= 4 && (j == 2 || j == 8)) ||
                   (i <= 4 && j == 5))
                    assertEquals(1, instance.getMatrix()[i][j]);
                else if((i <= 3 && (j == 2 || j == 8)) ||
                        (i == 4 && (j == 3 || j == 4 || j == 6 || j == 7)))
                    assertEquals(3, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }

    /**
     * Test of randomizeRL2 method, of class PulsatingLasersAlarm.
     */
    @Test
    public void testRandomizeRL2() {
        System.out.println("randomizeRL2");
        PulsatingLasersAlarm instance = new PulsatingLasersAlarm(MapAlarm.Dimension.RECT_LARGE);
        instance.randomizeRL2();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((j <= 3 && (i <= 1 || i >= 7)) ||
                   (i >= 3 && j >= 6 && j <= 8) ||
                   (i == 0 && j >= 9))
                    assertEquals(1, instance.getMatrix()[i][j]);
                else if((i >= 2 && i <= 6 && j == 2) ||
                        (j == 5) ||
                        (i <= 2 && j == 8))
                    assertEquals(3, instance.getMatrix()[i][j]);
                else
                    assertEquals(0, instance.getMatrix()[i][j]);
    }
    
}
