package operationrene.core;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operationrene.mapframework.pointsofinterest.Key;
import operationrene.minigame.KeyPadGame;
import operationrene.minigame.MemoryGame;
import operationrene.minigame.WiresGame;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class MinigameElement extends Element implements InteractiveObjectInterface{
 
    private boolean started;
    private Key info;

    public MinigameElement( Key info, int elementID, int posX, int posY){
        
        this.started = false;
        this.elementId = elementID;
        this.info = info;
        this.posX = posX;
        this.posY = posY;
        this.width = this.info.getSize().getWidth()*32;
        this.height = this.info.getSize().getHeight()*32;
        this.shape = new Rectangle((posX*32)-5, (posY*32)-5, this.width+10, this.height+10);
        
    }

    @Override
    public void interact(StateBasedGame sbg) {

        //ArrayList<Integer> array = new ArrayList<Integer>();
        
        if(!started){ 
            started = true;
            switch(this.info.getGameType()){

                    case StateID.WIRES_ID:
                        
                        sbg.addState(new WiresGame());
                        try {
                            ((WiresGame)sbg.getState(StateID.WIRES_ID)).init(sbg.getContainer(), sbg, this.elementId, this.info.getRequiredKeysID());
                        } catch (SlickException ex) {
                            Logger.getLogger(MinigameElement.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sbg.enterState(StateID.WIRES_ID);
                        break;

                    case StateID.MEMORY_ID:

                        sbg.addState(new MemoryGame());
                        try {
                            ((MemoryGame)sbg.getState(StateID.MEMORY_ID)).init(sbg.getContainer(), sbg, this.elementId, this.info.getRequiredKeysID());
                        } catch (SlickException ex) {
                            Logger.getLogger(MinigameElement.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sbg.enterState(StateID.MEMORY_ID);
                        break;
                        
                    case StateID.KEYPAD_ID:

                        sbg.addState(new KeyPadGame());
                        try {
                            ((KeyPadGame)sbg.getState(StateID.KEYPAD_ID)).init(sbg.getContainer(), sbg, this.elementId, this.info.getRequiredKeysID());
                        } catch (SlickException ex) {
                            Logger.getLogger(MinigameElement.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sbg.enterState(StateID.KEYPAD_ID);
                        break;
                        
            }
        }else{
            
            sbg.enterState(this.info.getGameType());
            
        }
        
 
        
    }
    
}
