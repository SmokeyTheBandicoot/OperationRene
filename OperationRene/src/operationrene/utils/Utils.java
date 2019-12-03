package operationrene.utils;

import static operationrene.mapframework.LevelBuilder.*;
import operationrene.mapframework.LevelRoom;

public class Utils {

    public static LevelRoom rotateRoom(LevelRoom room, Rotation r){
        
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
    
    public static LevelRoom flipRoom(LevelRoom room, Flipping f) {
        
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
                    System.arraycopy(mat, 0, c, 0, mat[0].length);
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
}