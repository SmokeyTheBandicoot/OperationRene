/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

import operationrene.OperationRene;
import static operationrene.OperationRene.font;
import operationrene.maingame.GameplayState;
import operationrene.StateID;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import static operationrene.gui.PlayWindow.getDifficulty;
import org.newdawn.slick.Color;


/**
 *
 * @author Massimo
 */
public class PauseWindow extends GameplayState{
    
    Image title;
    Button menu;
    Button setting;
    Button resume;
    private int previous_state;
    private static PauseWindow INSTANCE;
     
    @Override
    public int getID() {
     return StateID.PAUSE_MENU_ID;   
    }
    
    ///SINGLETON
    private PauseWindow(int ID){
        super(getDifficulty());
        previous_state=ID;
    }
     
    public static void setPauseInstance(GameContainer gc,StateBasedGame sbg,int ID) throws SlickException{
        if(INSTANCE==null){
          INSTANCE= new PauseWindow(ID);
          sbg.addState(INSTANCE);
          sbg.getState(StateID.PAUSE_MENU_ID).init(gc, sbg);
        }
        else{
            INSTANCE.previous_state = ID;
            
        }
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.init(gc, sbg);
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
        font.drawString(10, 50, "TIME REMAINING: " + timer.getTime() , Color.red);
        //super.timer.getTime();
        //font.drawString(10, 50, "TIME REMAINING: " + super.timer.getTime(), Color.red);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        menu.update(gc);
        setting.update(gc);
        resume.update(gc);
        
        if (menu.isClicked()) {
            this.timer.stopTimer();
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
    
    public static void deleteInstance () {
    INSTANCE = null;
    }
    
}
