package operationrene.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import operationrene.core.StateID;
import operationrene.OperationRene;

/**
 *
 * @author Rickma
 */
public class SettingWindow extends BasicGameState {

    Button musicButton;
    Button effectMusicButton;
    Button returnButton;
    Button applyButton;

    @Override
    public int getID() {
        return StateID.SETTING_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        musicButton = new Button(ButtonType.MUSIC_SOUND, OperationRene.WIDTH/2, 150);
        effectMusicButton = new Button(ButtonType.EFFECT_SOUND, OperationRene.WIDTH/2, 250);
        returnButton = new Button(ButtonType.RETURN, OperationRene.WIDTH/5, 650);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        musicButton.render(grphcs);
        effectMusicButton.render(grphcs);
        returnButton.render(grphcs);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        musicButton.update(gc);
        effectMusicButton.update(gc);
        returnButton.update(gc);

        if (returnButton.isClicked()) {
            OperationRene.EFFECT_SOUND = effectMusicButton.getValue();
            OperationRene.MUSIC_SOUND = musicButton.getValue();
            if (OperationRene.MUSIC.playing() && OperationRene.MUSIC_SOUND == false) {
                OperationRene.MUSIC.pause();
            } else if (!OperationRene.MUSIC.playing() && OperationRene.MUSIC_SOUND == true) {
                OperationRene.MUSIC.resume();
            }
            sbg.enterState(0);

        }

    }

}
