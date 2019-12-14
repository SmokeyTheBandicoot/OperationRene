package operationrene.core;

import javax.swing.JOptionPane;
import operationrene.utils.GameTimer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public abstract class GameplayState extends BasicGameState {

    public GameTimer timer = null;
    private boolean outOfTime;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        this.timer = GameTimer.getIstance();
        this.outOfTime = false;
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        if(this.timer.getTime() <= 0 ){
            
            JOptionPane.showMessageDialog(null, 
                              "YOU LOSE. TIME OVER.\nYOU'VE BEEN CAUGHT.", 
                              "TIME OVER", 
                              JOptionPane.WARNING_MESSAGE);
            this.outOfTime = true;
            
            }
        
    }
    
}
