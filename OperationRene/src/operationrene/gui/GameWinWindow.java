/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

import operationrene.OperationRene;
import operationrene.sound.SoundEffect;
import operationrene.sound.SoundEngine;
import operationrene.StateID;
import operationrene.utils.GameTimer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameWinWindow extends BasicGameState {

    Image title;
    Button menu;

    @Override
    public int getID() {
        return StateID.GAME_WIN_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        GameTimer.deleteInstance();
        title = new Image("assets/sprites/controls/WinScreen.PNG");
        menu = new Button(ButtonType.MENU, OperationRene.WIDTH / 2, 600);
        SoundEngine.getIstance().stopBackgroundMusic();
        SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_WIN);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        menu.render(grphcs);
        title.draw(OperationRene.WIDTH / 2 - title.getWidth() / 2, 100);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        menu.update(gc);
        if (menu.isClicked()) {
            SoundEngine.getIstance().stopSoundEffect();
            sbg.addState(new MainWindow());
            sbg.getState(StateID.MENU_ID).init(gc, sbg);
            sbg.enterState(StateID.MENU_ID);
        }
    }

}
