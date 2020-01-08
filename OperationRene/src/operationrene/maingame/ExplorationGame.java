package operationrene.maingame;

import operationrene.StateID;
import operationrene.element.PlayerState;
import operationrene.element.Player;
import operationrene.element.SafeElement;
import operationrene.element.InteractiveObjectInterface;
import operationrene.element.Element;
import operationrene.sound.SoundTrack;
import operationrene.sound.SoundEngine;
import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import static operationrene.OperationRene.PATH_RESOURCES;

import  static operationrene.OperationRene.font;
import operationrene.OperationRene;
import static operationrene.gui.PauseWindow.setPauseInstance;
import operationrene.minigame.WiresGame;
import org.newdawn.slick.geom.Shape;

public class ExplorationGame extends GameplayState {

    private Player player = null;
    private GameMap map = null;
    private String goal = "Steal the safe!";
    private ArrayList<Integer> keys = null;
    private int elementID;
    
    private int mapLevel;
    
    private StateBasedGame sbg;
    
    private Element collisionedElement; 

    
    public ExplorationGame(int difficulty, int mapLevel){
        
        super(difficulty);
        this.mapLevel = mapLevel;
        
    }
    
    @Override
    public int getID() {
        return StateID.EXPLORATION_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
        super.init(container, game);
        this.timer.startTimer(300);
        GameplayState.lastSeconds = false;
        SoundEngine.getIstance().playBackgroundMusic(SoundTrack.GAME_MUSIC);
        this.keys = new ArrayList<Integer>();
        this.elementID = -1;
        this.collisionedElement = null;
        this.sbg = game;
        this.map = new GameMap(this.mapLevel);
        this.player = new Player(PATH_RESOURCES + "character/Rene.png",PATH_RESOURCES + "character/ExclamationPoint.png", 100, PlayerState.DOWN_STOP, this.map.getPlayerStartPosition().getX()*32, this.map.getPlayerStartPosition().getY()*32, 32, 40, 1);
        //this.map.drawblackroom(16, 5, 25, 7);
        //this.map.drawMap();
        //this.map.drawroom(14, 1, 33, 12,69);
        //this.map.drawroom(14, 14, 33, 11,69);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        this.map.draw();
        this.player.draw();
        
        // TESTING

//        for (Element k: map.getElements()) {
//            g.draw(k.shape);
//            
//        }
//        for(Rectangle r: map.getAlarms()){
//            g.draw(r);
//        }
        font.drawString(10, 50, "TIME REMAINING: " + this.timer.getTime(), Color.red);
        font.drawString(10, 80, "GOAL: "+goal, Color.green);
        

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        super.update(container, game, delta);
        
        if(this.map.checkAlarmCollision(this.player.shape)){
            this.timer.setTime(0);
        }
        
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
            setPauseInstance(container,game,this.getID());
            game.enterState(StateID.PAUSE_MENU_ID);
        } else {
            if (!this.map.checkCollision(this.player.posX, this.player.posY, this.player.width, this.player.height)) {
                this.player.update(CommandCode.NONE);
            }        
        }
        
        collisionedElement = this.player.checkCollision(this.map.getElements());
        
    }

    @Override
    public void keyReleased(int key, char c){
        
        if(key == CommandCode.INTERACT){
            
            if((this.collisionedElement!= null)&&(InteractiveObjectInterface.class.isAssignableFrom(collisionedElement.getClass()))){
                ((InteractiveObjectInterface)collisionedElement).interact(this.sbg);
            }
            
        }
        
    }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException{
        
        OperationRene sbg = (OperationRene)game;
        System.out.println("ENTER EXPLORATION: "+this.elementID);
        if(this.elementID != -1){
            
            Iterator<Element> iterator = this.map.getElements().iterator();
            boolean flag = false;
            
            while(iterator.hasNext()){
                
                Element e = iterator.next();
                
                if(e.elementId == this.elementID){
                    
                    if(e.getClass() == SafeElement.class){
                        this.goal = "ESCAPE !!!!!";
                        SafeElement s = (SafeElement)e;
                        switch (this.mapLevel){
                            
                            case MapID.LEVEL_1:
                                this.map.setTileId(s.posX, s.posY, 2, 13);
                                this.map.setTileId(s.posX+1, s.posY, 2, 14);
                                this.map.setTileId(s.posX, s.posY+1, 2, 23);
                                this.map.setTileId(s.posX+1, s.posY+1, 2, 24);
                                break;
                            
                            case MapID.LEVEL_2:
                                this.map.setTileId(s.posX, s.posY, 2, 183);
                                this.map.setTileId(s.posX+1, s.posY, 2, 184);
                                this.map.setTileId(s.posX, s.posY+1, 2, 193);
                                this.map.setTileId(s.posX+1, s.posY+1, 2, 194);
                                break;
                                
                            case MapID.LEVEL_3:
                                this.map.setTileId(s.posX, s.posY, 2, 183);
                                this.map.setTileId(s.posX+1, s.posY, 2, 184);
                                this.map.setTileId(s.posX, s.posY+1, 2, 193);
                                this.map.setTileId(s.posX+1, s.posY+1, 2, 194);
                                break;
                            
                            case MapID.LEVEL_RANDOM:
                                break;
                                
                        }
                    }
                    
                    iterator.remove();
                    flag = true;
                    break;
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
