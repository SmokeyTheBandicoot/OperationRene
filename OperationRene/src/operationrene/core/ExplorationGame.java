package operationrene.core;

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
    public static boolean GAMEOVER = false;
    

    

    @Override
    public int getID() {
        return StateID.EXPLORATION_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
                this.player = new Player(PATH_RESOURCES + "character/Rene.png", 100, PlayerState.DOWN_STOP, OperationRene.WIDTH / 8, OperationRene.HEIGHT / 8 + (4 * 32), 32, 40, 1);
        this.map = new GameMap("assets/tilesets/Livello1.tmx", OperationRene.WIDTH, OperationRene.HEIGHT, 0, 0);
        this.door1 = new OggettoProva(400, 688, 16, 32);
        this.door2 = new OggettoProva(1136, 448, 32, 16);
        this.map.drawMap();
        this.map.drawroom(14, 1, 33, 12,69);
        this.map.drawroom(14, 14, 33, 11,69);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        this.map.draw();
        this.player.draw();
        g.draw(new Rectangle(1, 1, OperationRene.WIDTH - 1, OperationRene.HEIGHT - 1));
        font.drawString(10, 50, "TIME REMAINING: " + OperationRene.REMAINING_TIME, Color.red);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
        OperationRene.CURRENT_TIME +=delta;
        OperationRene.REMAINING_TIME = (OperationRene.MAXIMUM_TIME - OperationRene.CURRENT_TIME/1000);
        
        if(OperationRene.REMAINING_TIME < 0 ){
            JOptionPane.showMessageDialog(null, 
                              "HAI PERSO. TEMPO SCADUTO.\nSEI STATO CATTURATO POLLASTRO.", 
                              "TEMPO SCADUTO", 
                              JOptionPane.WARNING_MESSAGE);
            GAMEOVER = true;
            game.enterState(0);
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
            game.enterState(0);
        } else {
            if (!this.map.checkCollision(this.player.posX, this.player.posY, this.player.width, this.player.height)) {
                this.player.update(CommandCode.NONE);
            }
        }

        if (this.player.isCollided(this.door1)){
            door1.shape.setLocation(0, 0);
            game.addState(new WiresGame());
            game.getState(StateID.WIRES_ID).init(container, game);
            game.enterState(StateID.WIRES_ID);
        } else {
            this.door1.interact(container);
        }
        
        if (this.player.isCollided(this.door2)){
            // CODICE SECONDO MINIGIOCO
            System.out.println("Secondo minigioco");
            door2.shape.setLocation(0, 0);
            game.addState(new KeyPadGame());
            game.getState(StateID.KEYPAD_ID).init(container, game);
            game.enterState(StateID.KEYPAD_ID);
        }
        
        if (resultWires == 1){
            //minigame superato
            
            door1.shape.setX(0);
            door1.shape.setY(0);
            map.setTileId(13, 21, 1, 92);
            map.setTileId(13, 22, 1, 92);
            map.drawroom(14 , 14, 33, 11,92);
            
            resultWires = 0;
        }
        if (resultWires== -1){
            System.out.println("aaaaaa");
            game.enterState(0);
        }
        
        if (resultKeyPad == 1){
            door2.shape.setLocation(0,0);
            map.setTileId(35, 13, 1, 92);
            map.setTileId(36, 13, 1, 92);
            map.drawroom(14, 1, 33, 12, 92);
            resultKeyPad = 0;
        }
        

    }

}
