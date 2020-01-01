package operationrene.mapframework.matrixprops;

import java.io.Serializable;

public class Size implements Serializable {
    private int width;
    private int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }
    
    @Override
    public String toString() {
        return String.format("width: %d; height: %d", width, height);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == null) return false;
        if (!(o instanceof Size)) return false;
        Size s = (Size) o;
        return (this.height == s.height && this.width == s.width);
    }
    
}
