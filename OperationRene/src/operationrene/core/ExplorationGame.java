package operationrene.core;

import java.util.ArrayList;
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
    private Element collisionedElement; 

    @Override
    public int getID() {
        return StateID.EXPLORATION_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
        super.init(container, game);
        this.timer.startTimer(120);
        this.keys = new ArrayList<Integer>();
        this.collisionedElement = null;
        ((OperationRene)game).keysID = new ArrayList<Integer>();
        ((OperationRene)game).elementID = -1;
        this.player = new Player(PATH_RESOURCES + "character/Rene.png",PATH_RESOURCES + "character/ExclamationPoint.png", 100, PlayerState.DOWN_STOP, OperationRene.WIDTH / 8+50, OperationRene.HEIGHT / 8 + (4 * 32), 32, 40, 1);
        this.map = new GameMap('1');
        this.map.drawblackroom(16, 5, 25, 7);
        //this.map.drawMap();
        //this.map.drawroom(14, 1, 33, 12,69);
        //this.map.drawroom(14, 14, 33, 11,69);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        this.map.draw();
        this.player.draw();
        g.draw(new Rectangle(1, 1, OperationRene.WIDTH - 1, OperationRene.HEIGHT - 1));
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
                ((InteractiveObjectInterface)collisionedElement).interact(this.keys);
            }
            
        }
        
    }
    
    public void enter(GameContainer container, StateBasedGame game) throws SlickException{
        
        OperationRene sbg = (OperationRene)game;
        
        if(sbg.elementID != -1){
            
            this.keys.addAll(sbg.keysID);
            sbg.elementID = -1;
            sbg.keysID.removeAll(sbg.keysID);
            System.out.println("key: "+this.keys.toString());
            
        }
        
    }
    
}
