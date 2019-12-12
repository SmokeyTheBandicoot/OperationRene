/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import operationrene.mapframework.LevelMap;
import operationrene.mapframework.levelbuilder.LevelBuilder;
import operationrene.mapframework.pointsofinterest.Room;

/**
 *
 * @author Giuse
 */
public class RoomUtils {
    
    // Utils for rotation
    public static LevelMap rotateRoom(LevelMap room, LevelBuilder.Rotation r){
        
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
    
    public static LevelMap flipRoom(LevelMap room, LevelBuilder.Flipping f) {
        
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
                return rotateRoom(room, LevelBuilder.Rotation.DEG180);
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
     * Calculates the rotation to do, assuming that the initial rotation is LEFT
     * @param leveldir Derired direction
     * @return The rotation needed to achieve the desired direction
     */
    public static LevelBuilder.Rotation calculateRotation(Room.Direction leveldir){
        if (leveldir != null)
            switch (leveldir) {
                case Up:
                    return LevelBuilder.Rotation.RIGHT;
                case Down:
                    return LevelBuilder.Rotation.LEFT;
                case Left:
                    return LevelBuilder.Rotation.NONE;
                default:
                    return LevelBuilder.Rotation.DEG180;
            }
        
        return null;
    }
}
