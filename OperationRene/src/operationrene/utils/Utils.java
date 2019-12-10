package operationrene.utils;

import java.util.List;
import static operationrene.mapframework.levelbuilder.LevelBuilder.*;
import operationrene.mapframework.*;

public class Utils {

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
    
    /**
     * @param sizes The available sizes. The only assumption is that 
     * width >= height for all sizes
     * @param availableSpace The rectangle where to set the size in
     * @param fitHeight Wheter or not to search for the tallest size to fit, 
     * then select the size with that height and the largest width possible..
     * Otherwise, search for the widest size, and try to fit the largest height.
     * @return ndex of the sizes list corresponding to the chosen size. -1 if
     * none of the provided sizes can fit in the space or availableSpace is null
     */
    public static int getBiggestFittingSize(List<Size> sizes, Size availableSpace, boolean fitHeight) {
        
        if (availableSpace == null) return -1;
        
        Size curSize = null;
        for (Size s : sizes) {
            // Can the current size fit in the available 
            if (s.getHeight() <= availableSpace.getHeight())
                if (s.getWidth() <= availableSpace.getWidth())
                        
                   // Is the current size bigger than the current selected size?
                    // If current selected is null, then current iteration will be the current selected size
                    if (curSize == null) curSize = s;
                    else 
                        if (fitHeight) {
                            // Otherwise, check dimensions and set current selected accordingly
                            if (s.getHeight() >= curSize.getHeight())
                                if (s.getWidth() > curSize.getWidth()) curSize = s;
                        } else {
                            if (s.getWidth() >= curSize.getWidth())
                            if (s.getHeight() > curSize.getHeight()) curSize = s;
                        }   
        }
        return sizes.indexOf(curSize);
        
    }
}
