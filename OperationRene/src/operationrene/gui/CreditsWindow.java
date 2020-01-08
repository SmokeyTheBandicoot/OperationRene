/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

import operationrene.OperationRene;
import operationrene.StateID;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CreditsWindow extends BasicGameState {

    Button returnButton;
    Image title;
    Image font;

    int i = 0, j = 0;

    @Override
    public int getID() {
        return StateID.CREDITS_MENU_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        returnButton = new Button(ButtonType.RETURN, OperationRene.WIDTH / 2, 750);
        title = new org.newdawn.slick.Image("assets/sprites/controls/title.PNG");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        font = new org.newdawn.slick.Image("assets/sprites/controls/credits_black" + j + ".png");
        returnButton.render(grphcs);
        title.draw(OperationRene.WIDTH / 2 - title.getWidth() / 2, 0);
        font.draw(OperationRene.WIDTH / 2 - font.getWidth() / 2, title.getHeight());
        i = (i + 1) % 300;
        if (i == 0) {
            j = (j + 1) % 10;
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        returnButton.update(gc);
        if (returnButton.isClicked()) {
            sbg.enterState(StateID.MENU_ID);
        }
    }

}
