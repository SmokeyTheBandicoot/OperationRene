package operationrene.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import operationrene.core.StateID;

//TEMP IMPORT TO TEST MINIGAME
import operationrene.minigame.Wires2;

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
        //settingView = new SettingWindow();
        play = new Button("play.png", "play_pressed.png", 0, 450, 100);
        setting = new Button("Settings.PNG", "Settings_pressed.PNG", 0, 450, 200);
        exit = new Button("exit.PNG", "exit_pressed.PNG", 0, 450, 600);
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
            
            //javafx.application.Application.launch(Wires2.class);
            

            //sbg.enterState(2);

        }
        if (setting.isClicked()) {
            sbg.enterState(1);

        }
        if (exit.isClicked()) {
            System.exit(0);
        }

    }

}
