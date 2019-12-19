package operationrene.minigame;

import java.util.ArrayList;
import operationrene.core.CommandCode;
import operationrene.core.Difficulty;
import operationrene.core.ExplorationGame;
import operationrene.core.GameplayState;
import operationrene.core.StateID;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class MinigameState extends GameplayState{
    
    protected boolean completed;
    protected boolean escPressed;
    protected int elementID;
    protected ArrayList<Integer> keysID;
    

    public MinigameState(int difficulty) {
        super(difficulty);
    }
    
    
    public void init(GameContainer gc, StateBasedGame sbg, int elementID, ArrayList<Integer> keysID) throws SlickException {
        
        super.init(gc, sbg);
        this.completed = false;
        this.escPressed = false;
        this.elementID = elementID;
        this.keysID = keysID;
        
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        super.update(gc, sbg, delta);
        
        if(this.completed || this.escPressed){
            sbg.enterState(StateID.EXPLORATION_ID);
            System.out.println("MA lo fa");
        }
    }
    
    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException{
        
        if(this.completed){
            
            ((ExplorationGame)game.getState(StateID.EXPLORATION_ID)).addKeys(this.keysID);
            ((ExplorationGame)game.getState(StateID.EXPLORATION_ID)).setElementID(elementID);
           
        }
        
    }
    
    public void enter(GameContainer container, StateBasedGame game) throws SlickException{
        
        this.escPressed = false;
        
    }
    
    @Override
    public void keyReleased(int key, char c){
        
        if(key == CommandCode.ESC){
            
            this.escPressed = true;
            
        }
        
    }
    
    
    protected void errorDone(){
        
        switch(this.difficulty){

            case Difficulty.EASY:
                // diminuisce il tempo 
                System.out.println("Easy");
                break;
            case Difficulty.MEDIUM:
                //diminuisce il tempo max 3 tentativi
                System.out.println("Medium");
                break;
            case Difficulty.HARD:
                //perdi merda
                System.out.println("HARD");
                break;
                
            
        }
        
    }
}