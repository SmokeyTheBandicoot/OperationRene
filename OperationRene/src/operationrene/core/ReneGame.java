package operationrene.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ReneGame extends StateBasedGame {

    public static final int HEIGHT = 832;
    public static final int WIDTH = 1536;

    public static boolean FULLSCREEN = false;

    static boolean SHOW_FPS = true;

    public static final String TITLE = "Operation: R.E.N.E.";

    public static final int FPS_LIMIT = 200;

    public static boolean MUSIC_SOUND = true;
    public static boolean EFFECT_SOUND = true;

    public ReneGame() {
        super(TITLE);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        //this.addState(new ExplorationGame());
        this.getState(StateID.EXPLORATION_ID).init(gc, this);
        this.enterState(StateID.EXPLORATION_ID);
    }

    public static void launch() throws SlickException {

        AppGameContainer app = new AppGameContainer(new ReneGame());
        app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
        app.setTargetFrameRate(FPS_LIMIT);
        app.setShowFPS(SHOW_FPS);
        app.start();

    }

}
