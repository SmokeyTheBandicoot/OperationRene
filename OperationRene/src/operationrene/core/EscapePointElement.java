package operationrene.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import operationrene.gui.GameWinWindow;
import operationrene.mapframework.pointsofinterest.Door;
import operationrene.mapframework.pointsofinterest.EscapePoint;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class EscapePointElement extends Element implements InteractiveObjectInterface{
    
    private EscapePoint info;

    EscapePointElement(EscapePoint info, int elementID, int posX, int posY) {
        
        this.elementId = elementID;
        this.info = info;
        this.posX = posX;
        this.posY = posY;
        this.width = this.info.getSize().getWidth() * 32;
        this.height = this.info.getSize().getHeight() * 32;
        this.shape = new Rectangle((posX*32)-5, (posY*32)-5, this.width+10, this.height+10);
    }

    @Override
    public void interact(StateBasedGame sbg) {
        
        ExplorationGame game = (ExplorationGame)sbg.getState(StateID.EXPLORATION_ID);
        
        if(game.getKeys().containsAll(this.info.getRequiredKeysID())){
            sbg.addState(new GameWinWindow());
            try {
                sbg.getState(StateID.GAME_WIN_ID).init(sbg.getContainer(), sbg);
            } catch (SlickException ex) {
                Logger.getLogger(EscapePointElement.class.getName()).log(Level.SEVERE, null, ex);
            }
            sbg.enterState(StateID.GAME_WIN_ID, new  FadeOutTransition(),new FadeInTransition());
        }else{
            
            SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_LOCKED_DOOR);
            
        }
        
    }
    
    
    
}
