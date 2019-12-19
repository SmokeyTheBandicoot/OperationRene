/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

import javax.swing.JOptionPane;
import operationrene.OperationRene;
import static operationrene.OperationRene.MUSIC;
import operationrene.core.ExplorationGame;
import operationrene.core.MapID;
import operationrene.core.StateID;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class PlayWindow extends BasicGameState{
    
    Button campaign;
    Button quickMach;
    Button undo;
    ToggleButton difficultyButton;
    boolean test = true;
    private boolean info = true;
    public static int difficulty = 0;
    
    @Override
    public int getID() {
        return StateID.GAME_MENU_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        campaign = new Button(ButtonType.CAMPAIGN, OperationRene.WIDTH / 2, 200);
        quickMach = new Button(ButtonType.QUICK_PLAY, OperationRene.WIDTH / 2, 400);
        difficultyButton = new ToggleButton(ButtonType.DIFFICULTY, OperationRene.WIDTH / 2, 600);
        undo = new Button(ButtonType.RETURN, OperationRene.WIDTH / 4, 700);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        campaign.render(grphcs);
        quickMach.render(grphcs);
        difficultyButton.render(grphcs);
        undo.render(grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        campaign.update(gc);
        undo.update(gc);
        quickMach.update(gc);
        difficultyButton.update(gc);
        
        if (campaign.isClicked()) {
            sbg.addState(new LevelMenuWindow());
            sbg.getState(StateID.LEVEL_MENU_ID).init(gc,sbg);
            sbg.enterState(StateID.LEVEL_MENU_ID);

        }
        if (undo.isClicked()) {
            sbg.enterState(StateID.MENU_ID);
        }
//        if (quickMach.isClicked()){
//            sbg.addState(new ExplorationGame(difficulty,MapID.LEVEL_RANDOM));
//            sbg.getState(StateID.EXPLORATION_ID).init(gc, sbg);
//            sbg.enterState(StateID.EXPLORATION_ID);
//         
//            
//        }
        
        if (!info) {
                JOptionPane.showMessageDialog(null,
                        "PER INTERAGIRE CON PORTE O OGGETTI, PREMERE LA BARRA SPAZIATRICE",
                        "ATTENZIONE",
                        JOptionPane.WARNING_MESSAGE);
            }
           info = true;
    } 

    
    
}
    

