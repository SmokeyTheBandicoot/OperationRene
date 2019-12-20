package operationrene.mapframework.pointsofinterest;

import java.io.Serializable;
import operationrene.mapframework.matrixprops.*;

public class Room extends PointOfInterest implements Serializable {
    
    protected Direction dir;
    
    public Room(int roomID, Size size, Direction dir) {
        super(PointType.Room, roomID, new int []{}, size);
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
    
}
