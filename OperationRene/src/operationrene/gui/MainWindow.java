package operationrene.gui;


import operationrene.OperationRene;
import operationrene.core.SoundEngine;
import operationrene.core.SoundTrack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import operationrene.core.StateID;
import org.newdawn.slick.Image;


/**
 *
 * @author Rickma
 */
public class MainWindow extends BasicGameState {

    Button play;
    Button setting;
    Button exit;
    Button credits;
    
    boolean test = true;
    Image title;
    private boolean info = true;

    @Override
    public int getID() {
        return StateID.MENU_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
 
        title = new Image("assets/sprites/controls/title.PNG");

        play = new Button(ButtonType.PLAY, OperationRene.WIDTH / 2, 450);
        setting = new Button(ButtonType.SETTINGS, OperationRene.WIDTH / 2, 550);
        exit = new Button(ButtonType.EXIT, OperationRene.WIDTH / 2, 750);
        credits= new Button (ButtonType.CREDITS,OperationRene.WIDTH/2,650);
        
        SoundEngine.getIstance().playBackgroundMusic(SoundTrack.MENU_MUSIC);
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        play.render(grphcs);
        setting.render(grphcs);
        exit.render(grphcs);
        title.draw(OperationRene.WIDTH / 2 - title.getWidth() / 2, 0);
        credits.render(grphcs);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        play.update(gc);
        setting.update(gc);
        exit.update(gc);
        credits.update(gc);

        if (play.isClicked()) {
            sbg.addState(new PlayWindow());
            sbg.getState(StateID.GAME_MENU_ID).init(gc, sbg);
            sbg.enterState(StateID.GAME_MENU_ID);

        }
        if (setting.isClicked()) {
            SettingWindow.setSettingInstance(gc, sbg);
            sbg.enterState(StateID.SETTING_ID);
           
        }
        if (exit.isClicked()) {
            System.exit(0);
        }
        if (credits.isClicked()){
            sbg.addState(new CreditsWindow());
            sbg.getState(StateID.CREDITS_MENU_ID).init(gc, sbg);
            sbg.enterState(StateID.CREDITS_MENU_ID);
        }

    }

}
