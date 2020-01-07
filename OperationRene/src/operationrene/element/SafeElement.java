/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.element;

import java.util.logging.Level;
import java.util.logging.Logger;
import operationrene.maingame.ExplorationGame;
import operationrene.StateID;
import operationrene.mapframework.pointsofinterest.Key;
import operationrene.mapframework.pointsofinterest.Safe;
import operationrene.minigame.KeyPadGame;
import operationrene.minigame.MemoryGame;
import operationrene.minigame.SimonSays;
import operationrene.minigame.StrongBoxGame;
import operationrene.minigame.WiresGame;
import operationrene.minigame.WordsMinigame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author antonio
 */
public class SafeElement extends Element implements InteractiveObjectInterface {
    
    private Safe info;
   

    public SafeElement( Safe info, int elementID, int posX, int posY){
        
        //this.started = false;
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
        
        ExplorationGame exg = (ExplorationGame)sbg.getState(StateID.EXPLORATION_ID);
        sbg.addState(new StrongBoxGame(exg.difficulty));
        
        try {
            ((StrongBoxGame)sbg.getState(StateID.STRONGBOX_ID)).init(sbg.getContainer(), sbg, this.elementId, this.info.getRequiredKeysID());
        } catch (SlickException ex) {
            Logger.getLogger(MinigameElement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sbg.enterState(StateID.STRONGBOX_ID);
    }
    
}
