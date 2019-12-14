 package operationrene.core;

import java.util.ArrayList;
import operationrene.mapframework.pointsofinterest.Door;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

public class DoorElement extends Element implements InteractiveObjectInterface{

    private Door info;
    
    public DoorElement(Door info, int elementID, int posX, int posY){
        
        this.elementId = elementID;
        this.info = info;
        this.posX = posX;
        this.posY = posY;
        this.width = this.info.getSize().getWidth();
        this.height = this.info.getSize().getHeight();
        this.shape = new Rectangle(posX-5, posY-5, width+10, height+10);
        
    }
    
    @Override
    public void interact(ArrayList<Integer> keys) {

        boolean flag = true;
        
        int[] requiredKeys = this.info.getRequiredKeysID();
        for(int k : requiredKeys){
            
            if(!keys.contains(k)){
                flag = false;
                break;
            }
            
        }
        
        if (flag){
            
            // aprire la porta, cancellare le caselle
            
        }else{
            //porta resta chiusa
            System.out.println("PORTA BLOCCATA");
        }
        
    }
    
    
    
}
