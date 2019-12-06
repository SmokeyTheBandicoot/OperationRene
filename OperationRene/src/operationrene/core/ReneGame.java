package operationrene.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class ReneGame extends StateBasedGame {

    public static final int height = 832;
    public static final int width = 1536;

    public static boolean fullscreen = false;

    static boolean showFPS = true;

    public static final String  title = "Operation: R.E.N.E.";

    public static final int fpslimit = 100;
    
    public static boolean MUSIC_SOUND = true;
    public static boolean EFFECT_SOUND = true;
    

    public ReneGame() {
        super(title);
    }


    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        //this.addState(new ExplorationGame());
        this.getState(StateID.EXPLORATION_ID).init(gc, this);
        this.enterState(StateID.EXPLORATION_ID);
    }

    public static void launch() throws SlickException {

        AppGameContainer app = new AppGameContainer(new ReneGame());
        app.setDisplayMode(width, height, fullscreen);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.start();

    }

}