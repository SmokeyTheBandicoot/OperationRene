/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import java.util.List;
import operationrene.alarm.MapAlarm;
import operationrene.mapframework.matrixprops.*;
import operationrene.mapframework.levelbuilder.LevelBuilder;

/**
 *
 * @author Giuse
 */
public class SizeUtils {
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
    public static int getBiggestFittingSize(List<MapAlarm.Dimension> dims, Size availableSpace, boolean fitHeight) {
        
        if (availableSpace == null) return -1;
        
        Size curSize = null;
        for (MapAlarm.Dimension d : dims) {
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
        
        for (MapAlarm.Dimension d : dims)
            if (d.getDimSize() == curSize)
                return dims.indexOf(d);
        
        return -1;
    }
    
    // Utils for Rotation
    // check if the dimension of the room is good 
    public static boolean isGood(Rotation r, Size level, Size room){
        if(r == Rotation.NONE || r == Rotation.DEG180){
            return checkSize(level, room);
        } else {
            return checkSize(room, level);
        }
    }
    
    public static boolean checkSize(Size size1, Size size2){
        if((size1.getHeight() < size2.getHeight()) || (size1.getWidth()) < size2.getWidth()){
            return true;
        } else 
            return false;
    }
}
