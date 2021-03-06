package operationrene.maingame;

import operationrene.StateID;
import operationrene.sound.SoundEngine;
import operationrene.sound.SoundEffect;
import javax.swing.JOptionPane;
import operationrene.gui.GameOverWindow;
import operationrene.gui.PlayWindow;
import operationrene.utils.GameTimer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public abstract class GameplayState extends BasicGameState {

    public GameTimer timer = null;
    public int difficulty;

    protected static boolean lastSeconds = false;
    
    public GameplayState(int difficulty){
        
        this.difficulty = difficulty;
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        this.timer = GameTimer.getIstance();
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        if(this.timer.getTime() <= 9 && !GameplayState.lastSeconds){
        
            SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_CLOCK_TICK);
            GameplayState.lastSeconds = true;
            
        }
        
        if(this.timer.getTime() <= 0 ){
            
            sbg.addState(new GameOverWindow());
            sbg.getState(StateID.GAME_OVER_ID).init(gc, sbg);
            sbg.enterState(StateID.GAME_OVER_ID, new  FadeOutTransition(),new FadeInTransition());
        
        }
        
    }
    
}
