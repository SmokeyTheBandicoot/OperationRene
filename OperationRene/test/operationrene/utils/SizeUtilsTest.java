/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import java.util.List;
import operationrene.alarm.MapAlarm;
import operationrene.mapframework.Size;
import operationrene.mapframework.levelbuilder.LevelBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giuse
 */
public class SizeUtilsTest {
    
    public SizeUtilsTest() {
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
                int k = SizeUtils.getBiggestFittingSize(MapAlarm.getMinigameDimensions(), available, true);
                results[index++] = k;
            }

        
        Assert.assertArrayEquals(expected, results);
        // Assert.assertArrayEquals(expected, results);
        
    }
    
}
