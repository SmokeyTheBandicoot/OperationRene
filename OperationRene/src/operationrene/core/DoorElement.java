 package operationrene.core;

import operationrene.mapframework.pointsofinterest.Door;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class DoorElement extends Element implements InteractiveObjectInterface{

    private Door info;
    
    public DoorElement(Door info, int elementID, int posX, int posY){
        
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
        
        if((this.info.getRequiredKeysID()==null)||(game.getKeys().containsAll(this.info.getRequiredKeysID()))){
            
            for(int x = this.posX;x<(this.posX+this.info.getSize().getWidth());x++){
                
                for(int y = this.posY;y<(this.posY+this.info.getSize().getHeight());y++){
                
                    game.getMap().deleteTile(x, y, "walls");

                }
                
            }
            
            game.getMap().getElements().remove(this);
            
            SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_OPEN_DOOR);
            
        }else{
            
            SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_LOCKED_DOOR);
            
        }
        
    }
    
    
    
}
