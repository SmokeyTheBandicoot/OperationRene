package operationrene.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import operationrene.core.StateID;
import operationrene.OperationRene;
import operationrene.core.ReneGame;

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
        musicButton = new Button("MusicSound_checked.PNG", "MusicSound.PNG", 1, 450, 100);
        effectMusicButton = new Button("EffectSound_checked.PNG", "EffectSound.PNG", 1, 450, 200);
        returnButton = new Button("Return.PNG", "Return_pressed.PNG", 0, 50, 600);

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
            ReneGame.EFFECT_SOUND = effectMusicButton.getValue();
            ReneGame.MUSIC_SOUND = musicButton.getValue();
            if (OperationRene.MUSIC.playing() && ReneGame.MUSIC_SOUND == false) {
                OperationRene.MUSIC.pause();
            } else if (!OperationRene.MUSIC.playing() && ReneGame.MUSIC_SOUND == true) {
                OperationRene.MUSIC.resume();
            }
            sbg.enterState(0);

        }

    }

}
