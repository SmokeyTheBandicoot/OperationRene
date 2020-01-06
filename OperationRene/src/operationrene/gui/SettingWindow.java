package operationrene.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import operationrene.core.StateID;
import operationrene.OperationRene;
import operationrene.core.SoundEngine;
import operationrene.core.SoundTrack;

/**
 *
 * @author Rickma
 */
public class SettingWindow extends BasicGameState {

    Button musicButton;
    Button effectMusicButton;
    Button returnButton;
    Button applyButton;
    int previous_state;
    
    private static SettingWindow INSTANCE;
    
    /// VOID SINGLETHON
    public SettingWindow(int id){
       this.previous_state=id;
    }

    public int getPrevious_state() {
        return previous_state;
    }

    public static void setSettingInstance(GameContainer gc,StateBasedGame sbg,int ID) throws SlickException{
        if(INSTANCE==null){
          INSTANCE= new SettingWindow(ID);
          sbg.addState(INSTANCE);
          sbg.getState(StateID.SETTING_ID).init(gc, sbg);
        }else{
          INSTANCE.previous_state=ID;
        }
    }
       
        
     public static void setSettingInstance(GameContainer gc,StateBasedGame sbg) throws SlickException{
        setSettingInstance(gc,sbg,StateID.MENU_ID);    
    } 
    

    @Override
    public int getID() {
        return StateID.SETTING_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        musicButton = new Button(ButtonType.MUSIC_SOUND, OperationRene.WIDTH/2, 150);
        effectMusicButton = new Button(ButtonType.EFFECT_SOUND, OperationRene.WIDTH/2, 250);
        returnButton = new Button(ButtonType.RETURN, OperationRene.WIDTH/2, 700);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        musicButton.render(grphcs);
        effectMusicButton.render(grphcs);
        returnButton.render(grphcs);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        musicButton.update(gc);
        effectMusicButton.update(gc);
        returnButton.update(gc);

        if (returnButton.isClicked()) {
            
            int flag = 0;
            
            if(!OperationRene.MUSIC_SOUND && musicButton.getValue()){
                
                if (this.previous_state == StateID.MENU_ID){
                    flag = 1;
                }else{
                    flag = 2;
                }
                
            }
            
            OperationRene.EFFECT_SOUND = effectMusicButton.getValue();
            OperationRene.MUSIC_SOUND = musicButton.getValue();
            
            
            SoundEngine.getIstance().enabledBackgroudMusic(OperationRene.MUSIC_SOUND);
            SoundEngine.getIstance().enabledSoundEffect(OperationRene.EFFECT_SOUND);
            
            if(flag == 1){
                SoundEngine.getIstance().playBackgroundMusic(SoundTrack.MENU_MUSIC);
            }else if(flag == 2){
                SoundEngine.getIstance().playBackgroundMusic(SoundTrack.GAME_MUSIC);
            }
            
            sbg.enterState(this.previous_state);

        }

    }

}
