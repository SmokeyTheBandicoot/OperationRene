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
     * Test of pasteMatrix method, of class Utils.
     */
    @Test
    public void testPasteMatrix() {
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
        
        Integer [][] actual = MatrixUtils.pasteMatrix(matrix, paste, new Location(2, 1), new Size(3, 3), Direction.UP);     
        
        MatrixUtils.debugMatrix(matrix);
        
        Assert.assertArrayEquals(expected, actual);
        
        expected = new Integer [][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 3, 3, 6, 6, 6, 0},
            {0, 0, 4, 4, 6, 6, 6, 0},
            {0, 0, 5, 5, 6, 6, 6, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        paste = new Integer [][] {
            {6, 6, 6},
            {6, 6, 6},
            {6, 6, 6}
        };
        
        actual = MatrixUtils.pasteMatrix(matrix, paste, new Location(2, 1), new Size(5, 3), Direction.RIGHT);
        
        MatrixUtils.debugMatrix(actual);
        
        Assert.assertArrayEquals(expected, actual);
        
    }
    
    @Test
    public void testRotateMatrix() {
        int [][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {2, 2, 2, 2},
            {0, 0, 0, 0}
        };
        
        Integer [][] newMat = MatrixUtils.rotateMatrix(matrix, Rotation.RIGHT);
        Integer [][] expected = {
            {0, 2, 5, 1},
            {0, 2, 6, 2},
            {0, 2, 7, 3},
            {0, 2, 8, 4}
        };
        Assert.assertArrayEquals(newMat, expected);
        
        
        newMat = MatrixUtils.rotateMatrix(matrix, Rotation.LEFT);
        Integer [][] expected2 = {
            {4, 8, 2, 0},
            {3, 7, 2, 0},
            {2, 6, 2, 0},
            {1, 5, 2, 0}
        };
        Assert.assertArrayEquals(newMat, expected2);
        
        newMat = MatrixUtils.rotateMatrix(matrix, Rotation.DEG180);
        Integer [][] expected3 = {
            {0, 0, 0, 0},
            {2, 2, 2, 2},
            {8, 7, 6, 5},
            {4, 3, 2, 1}
        };
        Assert.assertArrayEquals(newMat, expected3);
        
        newMat = MatrixUtils.rotateMatrix(matrix, Rotation.NONE);
        Assert.assertArrayEquals(newMat, matrix);
        
        
    }
}
