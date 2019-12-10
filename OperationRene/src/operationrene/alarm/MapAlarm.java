package operationrene.alarm;

import java.util.ArrayList;
import operationrene.mapframework.Size;

public abstract class MapAlarm {

    
    
    public enum Dimension {
        SMALL,
        MEDIUM,
        LARGE,
        RECT_SMALL,
        RECT_LARGE
    }
    
    private int[][] matrix;
    private Dimension dim;
    protected int white = 0;
    protected int blue = 0;
    protected int red = 0;
    
    public static final ArrayList<Size> getMinigameSizes() {
        ArrayList<Size> sizes = new ArrayList<>();
        sizes.add(new Size(5, 5));
        sizes.add(new Size(7, 7));
        sizes.add(new Size(9, 9));
        sizes.add(new Size(7, 5));
        sizes.add(new Size(11, 9));
        return sizes;
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
            int newTile = MAUtils.getRandomIntBetween(1, 3);
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
    
    public void printMatrix() {
        
        for(int i = 0; i < this.getMatrix().length; i++) {
            for(int j = 0; j < this.getMatrix()[0].length; j++)
                System.out.print(this.getMatrix()[i][j] + " ");
            
            System.out.println("");
        }
    }
}
