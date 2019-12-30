package operationrene.mapframework.matrixprops;

// Class used to describe 2 parameters of an object: 

import java.io.Serializable;

// Location and Size expressed with ints. X,Y refers to the Top-Left corner
public class Location implements Serializable {
    
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    private Location() {
        this(0, 0);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return String.format("x: %d, y: %d", x, y);
    }
    
}