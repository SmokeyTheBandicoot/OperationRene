/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

import operationrene.OperationRene;
import operationrene.core.ExplorationGame;
import operationrene.core.StateID;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Massimo
 */
public class GameOverWindow extends BasicGameState{
    
    Image title;
    Button menu;
    

    
    @Override
    public int getID() {
      return StateID.GAME_OVER_ID; 
    }
    

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        title = new Image("assets/sprites/controls/GameOver.PNG");
        menu = new Button(ButtonType.MENU, OperationRene.WIDTH / 2, 600);
        
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
            sbg.enterState(StateID.MENU_ID);
        }
      
       
    }
    
    
}
