package operationrene.core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

public class OggettoProva extends InteractiveObject {

    public OggettoProva(int posX, int posY, int width, int height) {

        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.shape = new Rectangle(posX,posY,width,height);

    }

    @Override
    public void interact(GameContainer gc) {
        System.out.println("OKOK");
    }
}
