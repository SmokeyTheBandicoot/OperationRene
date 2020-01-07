package operationrene;

import java.io.File;
import operationrene.gui.MainWindow;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class OperationRene extends StateBasedGame {

    public static final int HEIGHT = 832;
    public static final int WIDTH = 1536;
    public static boolean FULLSCREEN = false;
    static boolean SHOW_FPS = true;
    public static final String TITLE = "Operation: R.E.N.E.";
    public static final int FPS_LIMIT = 200;
    public static boolean MUSIC_SOUND = true;
    public static boolean EFFECT_SOUND = true;
    public static final String PATH_RESOURCES = "assets/sprites/";
    private static final String PATH_MUSIC = "assets/music/";
    public static TrueTypeFont font;

    public OperationRene(String title) {
        super(title + " Options");
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        font = new TrueTypeFont(new java.awt.Font("Cominc Sans", java.awt.Font.BOLD, 28), false);
        this.addState(new MainWindow());
        this.getState(StateID.MENU_ID).init(gc, this);
        this.enterState(StateID.MENU_ID);

    }

    public static void main(String[] args) throws SlickException {
        System.setProperty("org.lwjgl.librarypath", new File(System.getProperty("user.dir"), "lib/natives/" + LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
        AppGameContainer app = new AppGameContainer(new OperationRene("Rene"));
        //app.setIcon(PATH_RESOURCES+"logo.png");
        app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
        app.setTargetFrameRate(FPS_LIMIT);
        app.setShowFPS(SHOW_FPS);
        app.start();

    }

}
