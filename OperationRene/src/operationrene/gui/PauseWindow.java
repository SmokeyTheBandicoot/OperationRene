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
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Massimo
 */
public class PauseWindow extends BasicGameState{
    
    Image title;
    Button menu;
    Button setting;
    Button resume;
    int previous_state;
    
    private static PauseWindow INSTANCE;
     
    @Override
    public int getID() {
     return StateID.PAUSE_MENU_ID;   
    }
    
    ///SINGLETHON
    public PauseWindow(int ID){
        previous_state=ID;
    }
     
    public static void setPauseInstance(GameContainer gc,StateBasedGame sbg,int ID) throws SlickException{
        if(INSTANCE==null){
          INSTANCE= new PauseWindow(ID);
          sbg.addState(INSTANCE);
          sbg.getState(StateID.PAUSE_MENU_ID).init(gc, sbg);
        }
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        title = new Image("assets/sprites/controls/pause.PNG");
        menu = new Button(ButtonType.MENU, OperationRene.WIDTH / 2, 600);
        setting = new Button(ButtonType.SETTINGS, OperationRene.WIDTH / 2, 500);
        resume= new Button (ButtonType.RESUME, OperationRene.WIDTH/2,400);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        menu.render(grphcs);
        setting.render(grphcs);
        resume.render(grphcs);
        title.draw(OperationRene.WIDTH / 2 - title.getWidth() / 2, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        menu.update(gc);
        setting.update(gc);
        resume.update(gc);
        
        if (menu.isClicked()) {
            sbg.addState(new PlayWindow());
            sbg.getState(StateID.MENU_ID).init(gc, sbg);
            sbg.enterState(StateID.MENU_ID);
        }
        if (resume.isClicked()) {
            sbg.enterState(this.previous_state);
        }
        if (setting.isClicked()) {
            SettingWindow.setSettingInstance(gc, sbg,this.getID());
            sbg.enterState(StateID.SETTING_ID);
        }
    }
    
}
