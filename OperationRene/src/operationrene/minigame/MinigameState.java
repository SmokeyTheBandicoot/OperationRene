package operationrene.minigame;

import java.util.ArrayList;
import operationrene.OperationRene;
import operationrene.core.GameplayState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class MinigameState extends GameplayState{
    
    protected boolean completed;
    protected int elementID;
    protected ArrayList<Integer> keysID;
    
    
    public void init(GameContainer gc, StateBasedGame sbg, int elementID, ArrayList<Integer> keysID) throws SlickException {
        
        super.init(gc, sbg);
        
        this.elementID = elementID;
        this.keysID = keysID;
        
    }
    
    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException{
        
        if(this.completed){
            OperationRene sbg = (OperationRene)game;

            sbg.elementID = this.elementID;
            sbg.keysID.addAll(this.keysID);
        }
        
    }
    
}