package operationrene.core;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import static operationrene.OperationRene.PATH_RESOURCES;

import  static operationrene.OperationRene.font;
import operationrene.OperationRene;
import operationrene.minigame.WiresGame;

public class ExplorationGame extends GameplayState {

    private Player player = null;
    private GameMap map = null;
    private String goal = "Steal the safe!";
    private ArrayList<Integer> keys = null;
    private int elementID;
    
    private char mapLevel;
    
    private StateBasedGame sbg;
    
    private Element collisionedElement; 

    
    public ExplorationGame(int difficulty, char mapLevel){
        
        super(difficulty);
        this.mapLevel = mapLevel;
        
    }
    
    @Override
    public int getID() {
        return StateID.EXPLORATION_ID;
    }

    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
        super.init(container, game);
        this.timer.startTimer(120);
        this.keys = new ArrayList<Integer>();
        this.elementID = -1;
        this.collisionedElement = null;
        this.sbg = game;
        this.map = new GameMap(this.mapLevel);
        this.player = new Player(PATH_RESOURCES + "character/Rene.png",PATH_RESOURCES + "character/ExclamationPoint.png", 100, PlayerState.DOWN_STOP, this.map.getPlayerStartPosition().getWidth()*32, this.map.getPlayerStartPosition().getHeight()*32, 32, 40, 1);
        //this.map.drawblackroom(16, 5, 25, 7);
        //this.map.drawMap();
        //this.map.drawroom(14, 1, 33, 12,69);
        //this.map.drawroom(14, 14, 33, 11,69);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        this.map.draw();
        this.player.draw();
        g.draw(new Rectangle(1, 1, OperationRene.WIDTH - 1, OperationRene.HEIGHT - 1));
        for(Element e: this.map.getElements()){
            g.draw(e.shape );
        }
        font.drawString(10, 50, "TIME REMAINING: " + this.timer.getTime(), Color.red);
        font.drawString(10, 80, "GOAL: "+goal, Color.green);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        super.update(container, game, delta);
        
        Input input = container.getInput();

        if (input.isKeyDown(CommandCode.LEFT)) {
            if (!this.map.checkCollision(this.player.posX - 1, this.player.posY, this.player.width, this.player.height)) {
                this.player.update(CommandCode.LEFT);
            }
        } else if (input.isKeyDown(CommandCode.RIGHT)) {
            if (!this.map.checkCollision(this.player.posX + 1, this.player.posY, this.player.width, this.player.height)) {
                this.player.update(CommandCode.RIGHT);
            }
        } else if (input.isKeyDown(CommandCode.UP)) {
            if (!this.map.checkCollision(this.player.posX, this.player.posY - 1, this.player.width, this.player.height)) {
                this.player.update(CommandCode.UP);
            }
        } else if (input.isKeyDown(CommandCode.DOWN)) {
            if (!this.map.checkCollision(this.player.posX, this.player.posY + 1, this.player.width, this.player.height)) {
                this.player.update(CommandCode.DOWN);
            }
        } else if (input.isKeyDown(1)) {
            game.enterState(0);//da modificare
        } else {
            if (!this.map.checkCollision(this.player.posX, this.player.posY, this.player.width, this.player.height)) {
                this.player.update(CommandCode.NONE);
            }        
        }
        
        collisionedElement = this.player.checkCollision(this.map.getElements());
        
    }

    public void keyReleased(int key, char c){
        
        if(key == CommandCode.INTERACT){
            
            if((this.collisionedElement!= null)&&(InteractiveObjectInterface.class.isAssignableFrom(collisionedElement.getClass()))){
                ((InteractiveObjectInterface)collisionedElement).interact(this.sbg);
            }
            
        }
        
    }
    
    public void enter(GameContainer container, StateBasedGame game) throws SlickException{
        
        OperationRene sbg = (OperationRene)game;
        System.out.println("ENTER EXPLORATION: "+this.elementID);
        if(this.elementID != -1){
            
            Iterator<Element> iterator = this.map.getElements().iterator();
            boolean flag = false;
            
            while(iterator.hasNext()){
                
                Element e = iterator.next();
                
                if(e.elementId == this.elementID){
                    
                    iterator.remove();
                    flag = true;
                    
                }
                
            }
            
            if(!flag){
                System.err.print("Elemento non trovato!");
            }
            
            this.elementID = -1;
            
        }
        
    }
    
    public void addKeys(ArrayList<Integer> otherKeys){
        
        this.keys.addAll(otherKeys);
        
    }
 
    public ArrayList<Integer> getKeys(){
        return this.keys;
    }
    
    public void setElementID(int elementID) {
        
        this.elementID = elementID;
    
    }
    
    public GameMap getMap(){
        
        return this.map;
        
    }
    
}
