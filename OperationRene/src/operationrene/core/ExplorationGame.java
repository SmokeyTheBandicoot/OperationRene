package operationrene.core;

import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static operationrene.OperationRene.PATH_RESOURCES;
import operationrene.minigame.Wires2;

public class ExplorationGame extends BasicGameState {

    private Player player = null;
    private GameMap map = null;
    private InteractiveObject e = null;
    public static int resultMiniGame = 0;
    public static boolean GAMEOVER = false;
    public static int time = 0;
    private final int MAXIMUM_TIME= 60;
    private int REMAINING_TIME;

    @Override
    public int getID() {
        return StateID.EXPLORATION_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        this.player = new Player(PATH_RESOURCES + "character/Rene.png", 100, PlayerState.DOWN_STOP, ReneGame.WIDTH / 2, ReneGame.HEIGHT / 2 + (4 * 32), 32, 40, 1);
        this.map = new GameMap("assets/tilesets/Livello1.tmx", ReneGame.WIDTH, ReneGame.HEIGHT, 0, 0);
        this.e = new OggettoProva(975, 590, 20, 35);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        this.map.draw();
        this.player.draw();
        g.setColor(Color.red);
        g.draw(new Rectangle(1, 1, ReneGame.WIDTH - 1, ReneGame.HEIGHT - 1));
        g.drawString("TIME REMAINING: " + REMAINING_TIME, 10, 50);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
        time +=delta;
        REMAINING_TIME = (MAXIMUM_TIME - time/1000);
        
        if(REMAINING_TIME < 0 ){
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

        if (this.player.isCollided(this.e)){
            e.shape.setLocation(0, 0);
            new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(Wires2.class);
            }
        }.start();
            System.out.println(e.shape.getX());
        } else {
            this.e.interact(container);
        }
        
        if (resultMiniGame == 1){
            //minigame superato
            
            e.shape.setX(0);
            e.shape.setY(0);
            map.setTileId(31, 18, 1, 0);
            map.setTileId(31, 19, 1, 0);
            map.unlockroom(7, 1, 12, 12);
            
            resultMiniGame = 0;
        }
        if (resultMiniGame == -1){
            System.out.println("aaaaaa");
            game.enterState(0);
        }

    }

}
