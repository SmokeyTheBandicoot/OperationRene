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
     * @param originSize Max size of the rectangle where to paste
     * @param anchor Where to paste the matrix. UP = UpLeft corner, RIGHT = UpRight, DOWN = BottomRight, LEFT = BottomLeft
     * @return levelMatrix with the roomMatrix pasted in at the desired location (offset)
     */
    public static Integer[][] pasteMatrix (Integer [][] levelMatrix, Integer [][] roomMatrix, Location loc, Size originSize, Direction anchor){
        
        /*
        Location offSetLocation;
        if (anchor == Direction.UP) offSetLocation = loc;
        if (anchor == Direction.RIGHT) offSetLocation = new Location(loc.getX() + originSize.getWidth() - roomMatrix[0].length, loc.getY());
        if (anchor == Direction.DOWN) offSetLocation = new Location(loc.getY(), loc.getY() + originSize.getHeight() - roomMatrix.length);
        else offSetLocation = new Location(loc.getX() + originSize.getWidth() - roomMatrix[0].length, loc.getY() + originSize.getHeight() - roomMatrix.length);
        
        
        for (int y = 0; y < roomMatrix[0].length; y++)
            for (int x = 0; x < roomMatrix.length; x++){
                int curY = offSetLocation.getY() + y;
                int curX = offSetLocation.getX() + x;
                try {
                    levelMatrix[curY][curX] = roomMatrix[y][x];
                } catch (Exception ex) {
                    System.out.println("curY: " + curY + " curX: " + curX);
                }
                         
            }     
        return levelMatrix;
        */
        
        Location offsetLoc = getOffsetLoc(loc, new Size(roomMatrix[0].length, roomMatrix.length), originSize, anchor);
        
        for (int y = 0; y < roomMatrix.length; y++) {
            for (int x = 0; x < roomMatrix[0].length; x++) {
                levelMatrix[offsetLoc.getY() + y][offsetLoc.getX() + x] = roomMatrix[y][x];
            }
        }
        
        return levelMatrix;
    }
    
    public static Location getOffsetLoc(Location loc, Size matrixSize, Size originSize, Direction dir) {
        
        if (null != dir) switch (dir) {
            case LEFT:
                return loc;
            case UP:
                return loc;
            case DOWN:
                int Yoffset = originSize.getHeight() - matrixSize.getHeight();
                return new Location(loc.getX(), loc.getY() + Yoffset);
            case RIGHT:
                int Xoffset = originSize.getWidth() - matrixSize.getWidth();
                return new Location(loc.getX() + Xoffset, loc.getY());
            default:
                return null;
        }
        return null;
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
