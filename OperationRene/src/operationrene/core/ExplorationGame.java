package operationrene.core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static operationrene.OperationRene.PATH_RESOURCES;
import static operationrene.minigame.WiresGame.resultWires;
import static operationrene.minigame.KeyPadGame.resultKeyPad;
import operationrene.minigame.KeyPadGame;
import operationrene.minigame.WiresGame;
import  static operationrene.OperationRene.font;
import operationrene.OperationRene;

public class ExplorationGame extends BasicGameState {

    private Player player = null;
    private GameMap map = null;
    private InteractiveObject door1 = null;
    private InteractiveObject door2 = null;
    private InteractiveObject safe = null;
    public static boolean GAMEOVER = false;
    public Input input;
    private Robot robot;
    private String goal = "Steal the safe!";

    

    @Override
    public int getID() {
        return StateID.EXPLORATION_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
         try {
                        robot = new Robot();
                        
                    } catch (AWTException ex) {
                        Logger.getLogger(ExplorationGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
        this.player = new Player(PATH_RESOURCES + "character/Rene.png", 100, PlayerState.DOWN_STOP, OperationRene.WIDTH / 8+50, OperationRene.HEIGHT / 8 + (4 * 32), 32, 40, 1);
        this.map = new GameMap("assets/tilesets/Livello1.tmx", OperationRene.WIDTH, OperationRene.HEIGHT, 0, 0);
        this.map.drawblackroom(16, 5, 25, 7);
        this.door1 = new OggettoProva(336,160, 32, 16);
        this.door2 = new OggettoProva(1040, 416, 32, 16);
        this.safe = new OggettoProva(512, 150, 80, 80);
        if(input != null){
            robot.keyRelease(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_LEFT);
                robot.keyRelease(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_DOWN);
                   
            
        }
        //this.map.drawMap();
        //this.map.drawroom(14, 1, 33, 12,69);
        //this.map.drawroom(14, 14, 33, 11,69);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        this.map.draw();
        this.player.draw();
        g.draw(new Rectangle(1, 1, OperationRene.WIDTH - 1, OperationRene.HEIGHT - 1));
        font.drawString(10, 50, "TIME REMAINING: " + OperationRene.REMAINING_TIME, Color.red);
        font.drawString(10, 80, "GOAL: "+goal, Color.green);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
        OperationRene.CURRENT_TIME +=delta;
        OperationRene.REMAINING_TIME = (OperationRene.MAXIMUM_TIME - OperationRene.CURRENT_TIME/1000);
        
        if(OperationRene.REMAINING_TIME < 0 ){
            JOptionPane.showMessageDialog(null, 
                              "YOU LOSE. TIME OVER.\nYOU'VE BEEN CAUGHT.", 
                              "TIME OVER", 
                              JOptionPane.WARNING_MESSAGE);
            GAMEOVER = true;
            game.enterState(0);
            }

        input = container.getInput();

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
            game.enterState(0);
        } else {
            if (!this.map.checkCollision(this.player.posX, this.player.posY, this.player.width, this.player.height)) {
                this.player.update(CommandCode.NONE);
            }
        }
        
        if (this.player.isCollided(this.door1)){
            if(resultKeyPad == 1){
            JOptionPane.showMessageDialog(null, 
                              "CONGRATULATIONS. YOU WON!", 
                              "YOU WON", 
                              JOptionPane.WARNING_MESSAGE);
            GAMEOVER = true;
            game.enterState(StateID.MENU_ID);
            
            }
            else {
                JOptionPane.showMessageDialog(null, 
                              "LEVEL NOT COMPLETED!", 
                              "ATTENTION", 
                              JOptionPane.WARNING_MESSAGE);
                player.posY+=20;
                robot.keyRelease(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_LEFT);
                robot.keyRelease(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_DOWN);
                
               
            }
        }

        if (this.player.isCollided(this.door2)){
            door2.shape.setLocation(0, 0);
            game.addState(new WiresGame());
            game.getState(StateID.WIRES_ID).init(container, game);
            game.enterState(StateID.WIRES_ID);
        }
        
        if (this.player.isCollided(this.safe)){
            // CODICE SECONDO MINIGIOCO
            safe.shape.setLocation(0, 0);
            game.addState(new KeyPadGame());
            game.getState(StateID.KEYPAD_ID).init(container, game);
            game.enterState(StateID.KEYPAD_ID);
        }
        
        if (resultWires == 1){
            //minigame superato
            
            door2.shape.setLocation(0, 0);
            map.unlockroom(16, 5, 25, 7);
            map.setTileId(32, 12, 1, 160);
            map.setTileId(33, 12, 1, 160);
            map.setTileId(32, 12, 0, 160);
            map.setTileId(33, 12, 0, 160);
            
            //map.drawroom(14 , 14, 33, 11,92);
            
            resultWires = 0;
        }
        if (resultWires== -1){
            System.out.println("aaaaaa");
            game.enterState(0);
        }
        
        if (resultKeyPad == 1){
            safe.shape.setLocation(0,0);
            map.setTileId(16, 5, 3, 183);
            map.setTileId(17, 5, 3, 184);
            map.setTileId(16, 6, 3, 193);
            map.setTileId(17, 6, 3, 194);
            map.setTileId(10, 4, 1, 160);
            map.setTileId(11, 4, 1, 160);
            goal = "Escape from the building";
            //map.drawroom(14, 1, 33, 12, 92);
            //resultKeyPad = 0;
        }
        

    }

}
