package operationrene.mapframework.pointsofinterest;

import operationrene.mapframework.*;

public class Room extends PointOfInterest {
    
    protected Size size;
    protected Direction dir;
    
    public Room(int roomID, Size size, Direction dir) {
        super(PointType.Room, roomID, new int []{-1}, size);
        this.dir = dir;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
    
    public enum Direction {
        Right,
        Left,
        Up,
        Down
    }
    
}
