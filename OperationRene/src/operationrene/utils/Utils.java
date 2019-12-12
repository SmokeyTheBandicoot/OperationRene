package operationrene.utils;

import java.util.List;
import operationrene.alarm.MapAlarm.Dimension;
import static operationrene.mapframework.levelbuilder.LevelBuilder.*;
import operationrene.mapframework.*;
import operationrene.mapframework.pointsofinterest.Room.Direction;

public class Utils {

    // Utils for rotation
    public static LevelMap rotateRoom(LevelMap room, Rotation r){
        
        Integer[][] mat = room.getMatrix();
        
        int n = mat.length;
        int m = mat[0].length;
        Integer [][] output = new Integer [m][n];

        if (null != r)
            switch (r) {
            case RIGHT:
                for (int i=0; i<n; i++)
                    for (int j=0;j<m; j++)
                        output [j][n-1-i] = mat[i][j];
                break;
            case LEFT:
                for (int i=0; i<n; i++)
                    for (int j=0;j<m; j++)
                        output [j][n-1-i] = mat[n-1-i][m-1-j];
                break;
            case DEG180:
                output = new Integer[n][m];
                for (int i=0; i<n; i++)
                    for (int j=0;j<m; j++)
                        output [i][j] = mat[n-1-i][m-1-j];
                break;
            default:
                break;
        }
            
        room.setMatrix(output);
        return room;  
    }
    
    public static LevelMap flipRoom(LevelMap room, Flipping f) {
        
        Integer[][] mat = room.getMatrix();
        
        int r = mat.length;
        int c = mat[0].length;
        
        Integer[] sup = new Integer[r];
        
        if (null != f)
            switch (f) {
            case HORIZONTAL:
                for (int i = 0; i < r; i++){
                    mat[i] = reverseArray(mat[i]);
                }
                break;
            case VERTICAL:
                for(int i=0; i < r/2; i++){
                    Integer[] temp = new Integer[c];
                    System.arraycopy(mat[i], 0, temp, 0, mat[i].length);
                    System.arraycopy(mat[r-1-i], 0, mat[i], 0, mat[i].length);
                    System.arraycopy(temp, 0, mat[r-1-i], 0, temp.length);
                }   
                break;
            case BOTH:
                return rotateRoom(room, Rotation.DEG180);
            default:
                break;
        }
                  
        room.setMatrix(mat);
        return room;
        
    }
    
    public static Integer [] reverseArray(Integer arr[]) {
        int t;
        for (int i = 0; i < arr.length / 2; i++) {
            t = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = t;
        }
        return arr;
    }
    
    
    // Utils for size
    /**
     * @param dims The available dimensions. The only assumption is that 
     * width >= height for all sizes
     * @param availableSpace The rectangle where to set the size in
     * @param fitHeight Wheter or not to search for the tallest size to fit, 
     * then select the size with that height and the largest width possible..
     * Otherwise, search for the widest size, and try to fit the largest height.
     * @return ndex of the sizes list corresponding to the chosen size. -1 if
     * none of the provided sizes can fit in the space or availableSpace is null
     */
    public static int getBiggestFittingSize(List<Dimension> dims, Size availableSpace, boolean fitHeight) {
        
        if (availableSpace == null) return -1;
        
        Size curSize = null;
        for (Dimension d : dims) {
            // Can the current size fit in the available 
            if (d.getDimSize().getHeight() <= availableSpace.getHeight())
                if (d.getDimSize().getWidth() <= availableSpace.getWidth())
                        
                   // Is the current size bigger than the current selected size?
                    // If current selected is null, then current iteration will be the current selected size
                    if (curSize == null) curSize = d.getDimSize();
                    else 
                        if (fitHeight) {
                            // Otherwise, check dimensions and set current selected accordingly
                            if (d.getDimSize().getHeight() >= curSize.getHeight())
                                if (d.getDimSize().getWidth() > curSize.getWidth()) curSize = d.getDimSize();
                        } else {
                            if (d.getDimSize().getWidth() >= curSize.getWidth())
                            if (d.getDimSize().getHeight() > curSize.getHeight()) curSize = d.getDimSize();
                        }   
        }
        
        for (Dimension d : dims)
            if (d.getDimSize() == curSize)
                return dims.indexOf(d);
        
        return -1;
    }
    
    public static boolean checkSize(Size size1, Size size2){
        if((size1.getHeight() < size2.getHeight()) || (size1.getWidth()) < size2.getWidth()){
            return true;
        } else 
            return false;
    }
    
    
    
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
    
    // Utils for Rotation
    // check if the dimension of the room in good 
    public static boolean isGood(Rotation r, Size level, Size room){
        if(r == Rotation.NONE || r == Rotation.DEG180){
            return checkSize(level, room);
        } else {
            return checkSize(room, level);
        }
    }
    
    /**
     * Calculates the rotation to do, assuming that the initial rotation is LEFT
     * @param leveldir Derired direction
     * @return The rotation needed to achieve the desired direction
     */
    public static Rotation calculateRotation(Direction leveldir){
        if (null != leveldir)switch (leveldir) {
            case Up:
                return Rotation.RIGHT;
            case Down:
                return Rotation.LEFT;
            case Left:
                return Rotation.NONE;
            default:
                return Rotation.DEG180;
        }
        return null;
    }
    
    
}
