package operationrene.alarm;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;
import operationrene.utils.RandomUtils;


public abstract class MapAlarm {

    
    
    public enum Dimension {
        SMALL(new Size(5, 5)),
        MEDIUM(new Size(7, 7)),
        LARGE(new Size(9, 9)),
        RECT_SMALL(new Size(7, 5)),
        RECT_LARGE(new Size(11, 9));
        
        private final Size size;
        
        Dimension(Size s) {
            size = s;
        }
        
        public Size getDimSize() {
            return size;
        }
                
    }
    
    private int[][] matrix;
    private Dimension dim;
    protected int white = 0;
    protected int blue = 0;
    protected int red = 0;
    
    public static final ArrayList<Dimension> getMinigameDimensions() {
        ArrayList<Dimension> dims = new ArrayList<>();
        dims.add(Dimension.SMALL);
        dims.add(Dimension.MEDIUM);
        dims.add(Dimension.LARGE);
        dims.add(Dimension.RECT_SMALL);
        dims.add(Dimension.RECT_LARGE);
        return dims;
    }

    public MapAlarm(Dimension dim) {
        
        switch(dim) {
            case SMALL:
                this.matrix = new int[5][5];
                this.dim = Dimension.SMALL;
                break;
            case MEDIUM:
                this.matrix = new int[7][7];
                this.dim = Dimension.MEDIUM;
                break;
            case LARGE:
                this.matrix = new int[9][9];
                this.dim = Dimension.LARGE;
                break;
            case RECT_SMALL:
                this.matrix = new int[5][7];
                this.dim = Dimension.RECT_SMALL;
                break;
            case RECT_LARGE:
                this.matrix = new int[9][11];
                this.dim = Dimension.RECT_LARGE;
                break;
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Dimension getDim() {
        return dim;
    }

    public void setDim(Dimension dim) {
        this.dim = dim;
    }
    
    // This function chooses the size to randomize based on the Dimension of
    // the MapAlarm object
    public void randomize() {
        
        switch(this.getDim()) {
            case SMALL:
                randomizeS();
                break;
            case MEDIUM:
                randomizeM();
                break;
            case LARGE:
                randomizeL();
                break;
            case RECT_SMALL:
                randomizeRS();
                break;
            case RECT_LARGE:
                randomizeRL();
                break;
        }
    };
    
    public abstract void randomizeS();

    public abstract void randomizeM();
    
    public abstract void randomizeL();
    
    public abstract void randomizeRS();
    
    public abstract void randomizeRL();
    
    // This function generates a random tile color and updates the matrix
    public void updateColoredTile(int i, int j) {
        
        if(white > 0 && blue > 0 && red > 0) {
            int newTile = RandomUtils.genRandomInt(1, 3);
            this.getMatrix()[i][j] = newTile;
            controlTile(newTile);
        }
        else if(white > 0) {
            this.getMatrix()[i][j] = 1;
            white--;
        }
        else if(blue > 0) {
            this.getMatrix()[i][j] = 2;
            blue--;
        }
        else if(red > 0) {
            this.getMatrix()[i][j] = 3;
            red--;
        }
    }
    
    // This function controls the tile color and decreases the corresponding
    // attribure value
    public void controlTile(int tile) {
        
        switch(tile) {
            case 1:
                white--;
                break;
            case 2:
                blue--;
                break;
            case 3:
                red--;
                break;
        }
    }
}
