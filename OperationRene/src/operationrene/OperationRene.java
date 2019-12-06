package operationrene;

import operationrene.core.ReneGame;
import operationrene.gui.MainWindow;
import operationrene.gui.SettingWindow;
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
    public static final String PATH_RESOURCES = "C:\\Users\\Rickma\\Documents\\NetBeansProjects\\Slick2D\\src\\extra\\";
    

    public OperationRene(String title) {
        super(title+" Options");
    }

   
   
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MainWindow());
        this.addState(new SettingWindow());
        //this.addState(new ExplorationGame());
        
        
    }

    public static void main(String[] args) throws SlickException {

        AppGameContainer app = new AppGameContainer(new OperationRene("Rene"));
        app.setDisplayMode(ReneGame.width, ReneGame.height, ReneGame.fullscreen);
        app.setIcon(PATH_RESOURCES+"logo.png");
        MUSIC = new Music(PATH_RESOURCES+"music.ogg");
        MUSIC.loop();
        app.start();

    }
       
}
