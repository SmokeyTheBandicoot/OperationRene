package operationrene.core;

import java.util.ArrayList;
import operationrene.OperationRene;
import operationrene.mapframework.matrixprops.Size;
import operationrene.mapframework.pointsofinterest.Door;
import operationrene.mapframework.pointsofinterest.Key;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class GameMap {

    private TiledMap map;
    private int width;
    private int height;
    private int posX;
    private int posY;
    private ArrayList<Element> elements;
    private Size playerStartPosition;
    /*Integer[][] matrix = new Integer [][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
        };*/

    public GameMap(char type) throws SlickException {
        
        this.elements = new ArrayList<Element>();
        
        switch (type){
            
            case '1':
                this.map = new TiledMap("assets/tilesets/Level1/Livello1.tmx");
                this.width = OperationRene.WIDTH;
                this.height = OperationRene.HEIGHT;
                this.posX = 0;
                this.posY = 0;
                ArrayList<Integer> array1 = new ArrayList<Integer>();
                array1.add(3);
                this.elements.add(new DoorElement(new Door(0,array1,new Size(2,1),false),1,320/32,130/32));
                ArrayList<Integer> array2 = new ArrayList<Integer>();
                array2.add(3);
                this.elements.add(new DoorElement(new Door(0,array2,new Size(2,1),false),0,1025/32,385/32));
                ArrayList<Integer> array3 = new ArrayList<Integer>();
                array3.add(3);
                //this.elements.add(new MinigameElement(new Key(1,StateID.MEMORY_ID,array3),4,200,200));
                //this.elements.add(new DoorElement(new Door(0,new int[]{2},new Size(32,16),false),0,336,160));                //this.elements.add(new DoorElement(new Door(0,new int[]{2},new Size(32,16),false),0,336,160));
            
            case '2':
                this.map = new TiledMap("assets/tilesets/Level2/Livello2.tmx");
                this.width = OperationRene.WIDTH;
                this.height = OperationRene.HEIGHT;
                this.posX = 0;
                this.posY = 0;
                this.playerStartPosition = new Size(10,12);
                this.elements.add(new DoorElement(new Door(0,null,new Size(2,1),true),0,16,10));
                this.elements.add(new DoorElement(new Door(0,null,new Size(2,1),true),1,23,15));
                this.elements.add(new DoorElement(new Door(0,this.getFilledArray(new int[]{0,1}),new Size(1,2),true),2,29,12));
                this.elements.add(new DoorElement(new Door(0,this.getFilledArray(new int[]{2}),new Size(1,2),true),3,8,12));
                this.elements.add(new MinigameElement(new Key(0,StateID.WIRES_ID,this.getFilledArray(new int[]{0})),4,11,5));
                this.elements.add(new MinigameElement(new Key(0,StateID.KEYPAD_ID,this.getFilledArray(new int[]{1})),5,27,17));
                this.elements.add(new MinigameElement(new Key(0,StateID.MEMORY_ID,this.getFilledArray(new int[]{2})),6,34,5));
                
        }
        
    }

    public void draw() {

        this.map.render(posX, posY);

    }

    public boolean checkCollision(int posX, int posY, int width, int height) {

        int wallLayer = map.getLayerIndex("walls");
        int objectLayer = map.getLayerIndex("objects");
        
        if ((map.getTileId(posX / 32, posY / 32, objectLayer) != 0)||(map.getTileId(posX / 32, posY / 32, wallLayer) != 0)) {//ANGOLO ALTO-SINISTRA
            return true;
        } else if ((map.getTileId((posX + width) / 32, (posY + height) / 32, objectLayer) != 0)||(map.getTileId((posX + width) / 32, (posY + height) / 32, wallLayer) != 0)) {//ANGOLO BASSO-DESTRA
            return true;
        } else if ((map.getTileId((posX + width) / 32, posY / 32, objectLayer) != 0)||(map.getTileId((posX + width) / 32, posY / 32, wallLayer) != 0)) {//ANGOLO ALTO-DESTRA
            return true;
        } else if ((map.getTileId(posX / 32, (posY + height) / 32, objectLayer) != 0)||(map.getTileId(posX / 32, (posY + height) / 32, objectLayer) != 0)) {//ANGOLO BASSO-SINISTRA
            return true;
        } else if ((map.getTileId(posX / 32, (posY + (height/2)) / 32, objectLayer) != 0)||(map.getTileId(posX / 32, (posY + (height/2)) / 32, objectLayer) != 0)) {//CENTRO SINISTRA
            return true;
        } else if ((map.getTileId((posX + width) / 32, (posY + (height/2)) / 32, objectLayer) != 0)|(map.getTileId((posX + width) / 32, (posY + (height/2)) / 32, objectLayer) != 0)) {//CENTRO DESTRA
            return true;    
        } else {
            return false;
        }

    }
    
    public ArrayList<Element> getElements() {
        
        return elements;
        
    }
    
    public void setTileId(int x, int y, int layer, int id){
        
        map.setTileId(x, y, layer, id);
        
    }
    
    public void deleteTile(int x, int y, String layerName){
        
        this.map.setTileId(x, y, this.map.getLayerIndex(layerName), 0);
        
    }
    
    public void unlockroom(int x, int y,int xdim, int ydim){
        for(int i=x;i<x+xdim;i++){
            for(int j=y;j<y+ydim;j++){
               for(int z=0;z<=2;z++){
                        map.setTileId(i,j,3,map.getTileId(i, j, z));
               }
            }
        }
    }
    
    public void drawblackroom(int x,int y, int xdim,int ydim){
        for(int i=x;i<x+xdim;i++){
            for(int j=y;j<y+ydim;j++){
               map.setTileId(i,j,3,69);
            }
        }
    }
    
    private ArrayList<Integer> getFilledArray(int[] data){
        
        ArrayList<Integer> array = new ArrayList<Integer>();
        
        for (int d : data){
            
            array.add(d);
            
        }
        
        return array;
        
    }
    
    public Size getPlayerStartPosition(){
        
        return this.playerStartPosition;
        
    }
    
    /*
    public void drawMap(){
       for(int i=0;i<matrix.length;i++){
           for (int j = 0; j < matrix[0].length; j++) {
               switch(matrix[i][j]){
                       case 0 :
                           mappa.setTileId(j,i, 1, 92);
                           break;
                       case 1:
                           mappa.setTileId(j,i, 1, 132);
                           break;
                       case 2:
                           mappa.setTileId(j,i, 1, 141);
               }
           }
       }
    }
    */
    
    /*
    public void drawroom(int x,int y, int xdim,int ydim,int id){
        for(int i=x;i<x+xdim;i++){
            for(int j=y;j<y+ydim;j++){
               mappa.setTileId(i,j , 1, id);
            }
        }
    }
*/
}
