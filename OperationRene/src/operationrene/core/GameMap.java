import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class GameMap {

    private TiledMap mappa;
    private int width;
    private int height;
    private int posX;
    private int posY;

    public GameMap(String mappaURL, int width, int height, int posX, int posY) throws SlickException {
        this.mappa = this.mappa = new TiledMap(mappaURL);
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }

    public void draw(){

        this.mappa.render(posX,posY);

    }

    public boolean checkCollision(int posX, int posY,int width,int height){

        int  objectLayer = mappa.getLayerIndex("objects");
        if(mappa.getTileId(posX/32,posY/32,objectLayer)!=0){
            return true;
        }else if(mappa.getTileId((posX+width)/32,(posY+height)/32,objectLayer)!=0){
            return true;
        }else if(mappa.getTileId((posX+width)/32,posY/32,objectLayer)!=0){
            return true;
        }else if(mappa.getTileId(posX/32,(posY+height)/32,objectLayer)!=0){
            return true;
        }else{
            return false;
        }

    }

}
