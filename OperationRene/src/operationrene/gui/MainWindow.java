package operationrene.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import operationrene.core.StateID;
import operationrene.core.ReneGame;


/**
 *
 * @author Rickma
 */
public class MainWindow extends BasicGameState {

    Button play;
    Button setting;
    Button exit;

    @Override
    public int getID() {
        return StateID.MENU_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        play = new Button(ButtonType.PLAY, ReneGame.WIDTH/2, 100);
        setting = new Button(ButtonType.SETTINGS, ReneGame.WIDTH/2, 200);
        exit = new Button(ButtonType.EXIT, ReneGame.WIDTH/2, 600);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        play.render(grphcs);
        setting.render(grphcs);
        exit.render(grphcs);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        play.update(gc);
        setting.update(gc);
        exit.update(gc);

        if (play.isClicked()) {
                    

            sbg.enterState(2);

        }
        if (setting.isClicked()) {
            sbg.enterState(1);

        }
        if (exit.isClicked()) {
            System.exit(0);
        }

    }

}
