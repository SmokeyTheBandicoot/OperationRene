/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

import javax.swing.JOptionPane;
import operationrene.OperationRene;
import operationrene.core.ExplorationGame;
import operationrene.core.MapID;
import operationrene.core.StateID;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static operationrene.gui.PlayWindow.getDifficulty;


public class LevelMenuWindow extends BasicGameState {
    Button Level1;
    Button Level2;
    Button Level3;
    Button undo;
     private boolean info = true;
    
    @Override
    public int getID() {
         return StateID.LEVEL_MENU_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Level1 = new Button(ButtonType.LEVEL1,250 , 300);
        Level2 = new Button(ButtonType.LEVEL2, OperationRene.WIDTH / 2, 300);
        Level3 = new Button(ButtonType.LEVEL3, OperationRene.WIDTH - 250 , 300);
        undo= new Button(ButtonType.RETURN, OperationRene.WIDTH / 2, 700);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Level1.render(grphcs); 
        Level2.render(grphcs);
        Level3.render(grphcs);
        undo.render(grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Level1.update(gc);
        Level2.update(gc);
        Level3.update(gc);
        undo.update(gc);
        
        if (undo.isClicked()) {
            sbg.enterState(StateID.GAME_MENU_ID);
        }
        
        if (Level1.isClicked()){
            
            sbg.addState(new ExplorationGame(getDifficulty(),MapID.LEVEL_1));
            sbg.getState(StateID.EXPLORATION_ID).init(gc, sbg);
            sbg.enterState(StateID.EXPLORATION_ID);
            if (!info) {
                JOptionPane.showMessageDialog(null,
                        "PER INTERAGIRE CON PORTE O OGGETTI, PREMERE LA BARRA SPAZIATRICE",
                        "ATTENZIONE",
                        JOptionPane.WARNING_MESSAGE);
            }
           info = true;
        }
        if (Level2.isClicked()){
            
            sbg.addState(new ExplorationGame(getDifficulty(),MapID.LEVEL_2));
            sbg.getState(StateID.EXPLORATION_ID).init(gc, sbg);
            sbg.enterState(StateID.EXPLORATION_ID);
            if (!info) {
                JOptionPane.showMessageDialog(null,
                        "PER INTERAGIRE CON PORTE O OGGETTI, PREMERE LA BARRA SPAZIATRICE",
                        "ATTENZIONE",
                        JOptionPane.WARNING_MESSAGE);
            }
           info = true;
        }
        if (Level3.isClicked()){
            
            sbg.addState(new ExplorationGame(getDifficulty(),MapID.LEVEL_3));
            sbg.getState(StateID.EXPLORATION_ID).init(gc, sbg);
            sbg.enterState(StateID.EXPLORATION_ID);
            if (!info) {
                JOptionPane.showMessageDialog(null,
                        "PER INTERAGIRE CON PORTE O OGGETTI, PREMERE LA BARRA SPAZIATRICE",
                        "ATTENZIONE",
                        JOptionPane.WARNING_MESSAGE);
            }
           info = true;
        }
         
    }
    
}