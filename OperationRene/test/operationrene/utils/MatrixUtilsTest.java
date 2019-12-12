/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import operationrene.mapframework.matrixprops.*;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Giuse
 */
public class MatrixUtilsTest {
    
    public MatrixUtilsTest() {
    }
    
    /**
     * Test of addMatrix method, of class Utils.
     */
    @Test
    public void testAddMatrix() {
        System.out.println("addMatrix");
        Integer [][] matrix = new Integer [][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 2, 0},
            {0, 0, 1, 0, 0, 0, 2, 0},
            {0, 0, 0, 1, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        Integer [][] paste = new Integer [][]{
            {3, 3, 3},
            {4, 4, 4},
            {5, 5, 5}
        };
        
        Integer [][] expected = new Integer [][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 3, 3, 3, 0, 2, 0},
            {0, 0, 4, 4, 4, 0, 2, 0},
            {0, 0, 5, 5, 5, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        Integer [][] actual = MatrixUtils.addMatrix(matrix, paste, new Location(1, 2));     
        Assert.assertArrayEquals(expected, actual);
        
    }
    
}
