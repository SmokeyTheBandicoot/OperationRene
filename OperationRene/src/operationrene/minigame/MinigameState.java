package operationrene.minigame;

import java.util.ArrayList;
import operationrene.maingame.CommandCode;
import operationrene.maingame.Difficulty;
import operationrene.maingame.ExplorationGame;
import operationrene.maingame.GameplayState;
import operationrene.StateID;
import static operationrene.gui.PauseWindow.setPauseInstance;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class MinigameState extends GameplayState{
    
    protected boolean completed;
    protected boolean escPressed;
    protected int elementID;
    protected ArrayList<Integer> keysID;
    
    private int countErrors;
    

    public MinigameState(int difficulty) {
        super(difficulty);
    }
    
    
    public void init(GameContainer gc, StateBasedGame sbg, int elementID, ArrayList<Integer> keysID) throws SlickException {
        
        super.init(gc, sbg);
        this.completed = false;
        this.escPressed = false;
        this.elementID = elementID;
        this.keysID = keysID;
        if(this.difficulty == Difficulty.MEDIUM){
            this.countErrors = 0;
        }else{
            this.countErrors = -1;
        }
        
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        super.update(gc, sbg, delta);
        
        if(this.completed || this.escPressed){
            sbg.enterState(StateID.EXPLORATION_ID);
            //System.out.println("MA lo fa");
        }
        
        Input input = gc.getInput();
        
         if (input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_LSHIFT)) {
           
           this.completed = true;
        }
        
       if (input.isKeyDown(1)) {
            setPauseInstance(gc,sbg,this.getID());
            sbg.enterState(StateID.PAUSE_MENU_ID);
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
                this.timer.increaseTime(-10);
                System.out.println("Easy");
                break;
            case Difficulty.MEDIUM:
                if(this.countErrors<3){
                    this.countErrors++;
                    this.timer.increaseTime(-10);
                }else{
                    this.timer.setTime(0);
                }
                System.out.println("Medium");
                break;
            case Difficulty.HARD:
                this.timer.setTime(0);
                System.out.println("HARD");
                break;
                
            
        }
        
    }
}