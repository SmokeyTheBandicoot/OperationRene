/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

import operationrene.OperationRene;
import operationrene.core.StateID;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Massimo
 */
public class CreditsWindow extends BasicGameState{
    Button returnButton;

    @Override
    public int getID() {
        return StateID.CREDITS_MENU_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       returnButton = new  Button (ButtonType.RETURN, OperationRene.WIDTH/4, 600);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        returnButton.render(grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        returnButton.update(gc);
        if (returnButton.isClicked())
            sbg.enterState(StateID.MENU_ID);
    }
    
}
