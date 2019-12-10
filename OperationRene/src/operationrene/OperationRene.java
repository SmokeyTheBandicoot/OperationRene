package operationrene;

import java.util.Timer;
import operationrene.core.ExplorationGame;
import operationrene.core.ReneGame;
import operationrene.core.StateID;
import operationrene.gui.MainWindow;
import operationrene.gui.SettingWindow;
import operationrene.minigame.KeyPadGame;
import operationrene.minigame.WiresGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;



/**
 *
 * @author Rickma
 */

public class OperationRene extends StateBasedGame {
    
    public static Music MUSIC;
    public static final String PATH_RESOURCES = "assets/sprites/";
    private static final String PATH_MUSIC = "assets/music/";
    public static final int MAXIMUM_TIME= 120;
    public static int REMAINING_TIME;
    public static int CURRENT_TIME = 0;
    

    public OperationRene(String title) {
        super(title+" Options");
    }

   
   
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MainWindow());
        this.addState(new SettingWindow());
        this.addState(new ExplorationGame());
        this.addState(new WiresGame());
        this.addState(new KeyPadGame());
        this.getState(StateID.EXPLORATION_ID).init(gc, this);
        this.getState(StateID.MENU_ID).init(gc, this);
        this.getState(StateID.SETTING_ID).init(gc, this);
        this.getState(StateID.WIRES_ID).init(gc, this);
        this.getState(StateID.KEYPAD_ID).init(gc, this);
        this.enterState(StateID.MENU_ID);
        
        
    }

    public static void main(String[] args) throws SlickException {

        AppGameContainer app = new AppGameContainer(new OperationRene("Rene"));
        app.setDisplayMode(ReneGame.WIDTH, ReneGame.HEIGHT, ReneGame.FULLSCREEN);
        //app.setIcon(PATH_RESOURCES+"logo.png");
        MUSIC = new Music(PATH_MUSIC+"music2.ogg");
        MUSIC.loop();
        app.start();

    }
       
}
