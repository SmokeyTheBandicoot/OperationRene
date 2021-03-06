/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

import javax.swing.JOptionPane;
import operationrene.OperationRene;
import static operationrene.OperationRene.infoFlag;
import operationrene.maingame.ExplorationGame;
import operationrene.maingame.MapID;
import operationrene.StateID;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayWindow extends BasicGameState {

    Button campaign;
    Button quickMach;
    Button undo;
    ToggleButton difficultyButton;
    boolean test = true;
    public static int difficulty;
    Image infoDiff;

    @Override
    public int getID() {
        return StateID.GAME_MENU_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        difficulty = 0;
        campaign = new Button(ButtonType.CAMPAIGN, OperationRene.WIDTH / 3, 200);
        quickMach = new Button(ButtonType.QUICK_PLAY, OperationRene.WIDTH / 3 * 2, 200);
        difficultyButton = new ToggleButton(ButtonType.DIFFICULTY, OperationRene.WIDTH / 2, 400);
        undo = new Button(ButtonType.RETURN, OperationRene.WIDTH / 2, 700);
        infoDiff = new Image("assets/sprites/controls/infodiff.PNG");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        campaign.render(grphcs);
        quickMach.render(grphcs);
        difficultyButton.render(grphcs);
        undo.render(grphcs);
        infoDiff.draw(OperationRene.WIDTH / 2 - infoDiff.getWidth() / 2, 450);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        campaign.update(gc);
        undo.update(gc);
        quickMach.update(gc);
        difficultyButton.update(gc);

        if (campaign.isClicked()) {
            sbg.addState(new LevelMenuWindow());
            sbg.getState(StateID.LEVEL_MENU_ID).init(gc, sbg);
            sbg.enterState(StateID.LEVEL_MENU_ID);

        }
        if (undo.isClicked()) {
            sbg.enterState(StateID.MENU_ID);
        }
        if (quickMach.isClicked()) {
            sbg.addState(new ExplorationGame(getDifficulty(), MapID.LEVEL_RANDOM));
            sbg.getState(StateID.EXPLORATION_ID).init(gc, sbg);
            sbg.enterState(StateID.EXPLORATION_ID);
            if (!infoFlag) {
                JOptionPane.showMessageDialog(null,
                        "PER INTERAGIRE CON PORTE O OGGETTI, PREMERE LA BARRA SPAZIATRICE",
                        "ATTENZIONE",
                        JOptionPane.WARNING_MESSAGE);
                infoFlag = true;
            }
        }

    }

    public static int getDifficulty() {

        return difficulty;
    }

}
