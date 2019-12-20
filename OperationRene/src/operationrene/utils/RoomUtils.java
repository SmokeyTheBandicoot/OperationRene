package operationrene.utils;

import operationrene.mapframework.LevelMap;
import operationrene.mapframework.matrixprops.*;

public class RoomUtils {
    
    // Utils for rotation
    public static LevelMap rotateRoom(LevelMap room, Rotation r){
        
        Integer[][] mat = room.getMatrix();
        
        int n = mat.length;
        int m = mat[0].length;
        Integer [][] output = new Integer [m][n];

        if (null != r)
            switch (r) {
            case RIGHT:
                // Rotate matrix
                for (int i=0; i<n; i++)
                    for (int j=0;j<m; j++)
                        output [j][n-1-i] = mat[i][j];
                // Rotate HashMaps
                room.setLockedObjects(
                        HashMapUtils.rotate(room.getLockedObjects(), 
                        Rotation.RIGHT, 
                        new Size(room.getMatrixHeight(), room.getMatrixWidth())));
                room.setUnlockingObjects(
                        HashMapUtils.rotate(room.getUnlockingObjects(), 
                        Rotation.RIGHT, 
                        new Size(room.getMatrixHeight(), room.getMatrixWidth())));
                room.setOtherObjects(
                        HashMapUtils.rotate(room.getOtherObjects(), 
                        Rotation.RIGHT, 
                        new Size(room.getMatrixHeight(), room.getMatrixWidth())));
                
                break;
            case LEFT:
                for (int i=0; i<n; i++)
                    for (int j=0;j<m; j++)
                        output [j][n-1-i] = mat[n-1-i][m-1-j];
                // Rotate HashMaps
                room.setLockedObjects(
                        HashMapUtils.rotate(room.getLockedObjects(), 
                        Rotation.LEFT, 
                        new Size(room.getMatrixHeight(), room.getMatrixWidth())));
                room.setUnlockingObjects(
                        HashMapUtils.rotate(room.getUnlockingObjects(), 
                        Rotation.LEFT, 
                        new Size(room.getMatrixHeight(), room.getMatrixWidth())));
                room.setOtherObjects(
                        HashMapUtils.rotate(room.getOtherObjects(), 
                        Rotation.LEFT, 
                        new Size(room.getMatrixHeight(), room.getMatrixWidth())));
                break;
            case DEG180:
                output = new Integer[n][m];
                for (int i=0; i<n; i++)
                    for (int j=0;j<m; j++)
                        output [i][j] = mat[n-1-i][m-1-j];
                // Rotate HashMaps
                room.setLockedObjects(
                        HashMapUtils.rotate(room.getLockedObjects(), 
                        Rotation.DEG180, 
                        new Size(room.getMatrixWidth(), room.getMatrixHeight())));
                room.setUnlockingObjects(
                        HashMapUtils.rotate(room.getUnlockingObjects(), 
                        Rotation.DEG180, 
                        new Size(room.getMatrixWidth(), room.getMatrixHeight())));
                room.setOtherObjects(
                        HashMapUtils.rotate(room.getOtherObjects(), 
                        Rotation.DEG180, 
                        new Size(room.getMatrixWidth(), room.getMatrixHeight())));
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
                // Rotate HashMaps
                room.setLockedObjects(
                        HashMapUtils.flip(room.getLockedObjects(), 
                        Flipping.HORIZONTAL, 
                        new Size(room.getMatrixWidth(), room.getMatrixHeight())));
                room.setUnlockingObjects(
                        HashMapUtils.flip(room.getUnlockingObjects(), 
                        Flipping.HORIZONTAL, 
                        new Size(room.getMatrixWidth(), room.getMatrixHeight())));
                room.setOtherObjects(
                        HashMapUtils.flip(room.getOtherObjects(), 
                        Flipping.HORIZONTAL, 
                        new Size(room.getMatrixWidth(), room.getMatrixHeight())));
                break;
            case VERTICAL:
                for(int i=0; i < r/2; i++){
                    Integer[] temp = new Integer[c];
                    System.arraycopy(mat[i], 0, temp, 0, mat[i].length);
                    System.arraycopy(mat[r-1-i], 0, mat[i], 0, mat[i].length);
                    System.arraycopy(temp, 0, mat[r-1-i], 0, temp.length);
                }
                // Rotate HashMaps
                room.setLockedObjects(
                        HashMapUtils.flip(room.getLockedObjects(), 
                        Flipping.VERTICAL, 
                        new Size(room.getMatrixWidth(), room.getMatrixHeight())));
                room.setUnlockingObjects(
                        HashMapUtils.flip(room.getUnlockingObjects(), 
                        Flipping.VERTICAL, 
                        new Size(room.getMatrixWidth(), room.getMatrixHeight())));
                room.setOtherObjects(
                        HashMapUtils.flip(room.getOtherObjects(), 
                        Flipping.VERTICAL, 
                        new Size(room.getMatrixWidth(), room.getMatrixHeight())));
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
     * Calculates the rotation to do, assuming that the initial rotation is LEFT
     * @param leveldir Derired direction
     * @return The rotation needed to achieve the desired direction
     */
    public static Rotation calculateRotation(Direction leveldir){
        if (leveldir != null)
            switch (leveldir) {
                case UP:
                    return Rotation.RIGHT;
                case DOWN:
                    return Rotation.LEFT;
                case LEFT:
                    return Rotation.NONE;
                default:
                    return Rotation.DEG180;
            }
        
        return null;
    }
}
