/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import operationrene.mapframework.matrixprops.*;

/**
 *
 * @author Giuse
 */
public class MatrixUtils {
    // Utils for matrix
    /**
     * Pastes a matrix into another matrix with a fixed offset
     * @param levelMatrix Matrix where to paste roomMatrix
     * @param roomMatrix Matrix to paste
     * @param loc Offset
     * @return levelMatrix with the roomMatrix pasted in at the desired location (offset)
     */
    public static Integer[][] addMatrix (Integer [][] levelMatrix, Integer [][] roomMatrix, Location loc){
        for (int i = 0; i < roomMatrix.length; i++)
            for (int j = 0; j < roomMatrix[0].length; j++){
                levelMatrix[loc.getX() + i][loc.getY() + j] = roomMatrix[i][j];
            }     
        return levelMatrix;
    }
    
    /**
     * Prints a matrix in stdout
     * @param matrix matrix to print
     */
    public static void debugMatrix(Integer [][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.print("\n");
        }
        // System.out.println("\n\n");
    }
}
