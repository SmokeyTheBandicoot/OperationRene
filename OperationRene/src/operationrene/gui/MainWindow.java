package operationrene.gui;


import operationrene.OperationRene;
import static operationrene.OperationRene.*;
import static operationrene.core.ExplorationGame.GAMEOVER;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import operationrene.core.StateID;
import static operationrene.minigame.KeyPadGame.resultKeyPad;
import org.newdawn.slick.Image;
import static operationrene.minigame.WiresGame.resultWires;


/**
 *
 * @author Rickma
 */
public class MainWindow extends BasicGameState {

    Button play;
    Button setting;
    Button exit;
    boolean test = true;
    Image title;

    @Override
    public int getID() {
        return StateID.MENU_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        title = new Image("assets/sprites/controls/title.PNG");
        
        play = new Button(ButtonType.PLAY, OperationRene.WIDTH/2, 450);
        setting = new Button(ButtonType.SETTINGS, OperationRene.WIDTH/2,550);
        exit = new Button(ButtonType.EXIT, OperationRene.WIDTH/2, 650);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        play.render(grphcs);
        setting.render(grphcs);
        exit.render(grphcs);
        title.draw(OperationRene.WIDTH/2 - title.getWidth()/2, 0);
        
        

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        play.update(gc);
        setting.update(gc);
        exit.update(gc);

        if (play.isClicked()) {
                    

            //sbg.getState(2).leave(gc, sbg);
            sbg.enterState(2);
            
            if (GAMEOVER == true){
                GAMEOVER = false;
                resultWires = 0;
                resultKeyPad = 0;
                REMAINING_TIME= MAXIMUM_TIME;
                CURRENT_TIME = 0;
                MUSIC.setPosition(0);
            sbg.getState(StateID.EXPLORATION_ID).init(gc, sbg);
            if(sbg.getState(StateID.WIRES_ID)!= null) 
                sbg.getState(StateID.WIRES_ID).init(gc, sbg);
            if(sbg.getState(StateID.KEYPAD_ID) != null)
                sbg.getState(StateID.KEYPAD_ID).init(gc, sbg);

            sbg.enterState(2);
            }
            
            

        }
        if (setting.isClicked()) {
            sbg.enterState(1);

        }
        if (exit.isClicked()) {
            System.exit(0);
        }

    }

}
