package operationrene.core;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Element {

    private PlayerState state;

    private Animation upWalk;
    private Animation downWalk;
    private Animation rightWalk;
    private Animation leftWalk;
    private Image upStop;
    private Image downStop;
    private Image rightStop;
    private Image leftStop;
    private Image exclamationPoint;
    private boolean interact;
    private int pace;

    /*public Player(String imageURL, int duration, PlayerState state) throws SlickException {

        SpriteSheet spriteSheet = new SpriteSheet(imageURL,24,32);
        this.upStop = spriteSheet.getSprite(1,0);
        this.downStop = spriteSheet.getSprite(1,2);
        this.rightStop = spriteSheet.getSprite(1,1);
        this.leftStop = spriteSheet.getSprite(1,3);
        this.upWalk = new Animation(new Image[]{spriteSheet.getSprite(0, 0),spriteSheet.getSprite(1, 0),spriteSheet.getSprite(2, 0)},duration);
        this.downWalk = new Animation(new Image[]{spriteSheet.getSprite(0, 1),spriteSheet.getSprite(1, 1),spriteSheet.getSprite(2, 1)},duration);
        this.rightWalk = new Animation(new Image[]{spriteSheet.getSprite(0, 2),spriteSheet.getSprite(1, 2),spriteSheet.getSprite(2, 2)},duration);
        this.leftWalk = new Animation(new Image[]{spriteSheet.getSprite(0, 3),spriteSheet.getSprite(1, 3),spriteSheet.getSprite(2, 3)},duration);
        this.state = state;

    }*/
    public Player(String imageURL, String exclamationPointURL,int duration, PlayerState state, int posX, int posY, int width, int height, int pace) throws SlickException {

        this.elementId = -1;
        this.pace = pace;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.interact = false;
        this.exclamationPoint = new Image(exclamationPointURL);
        this.shape = new Rectangle(posX, posY, width, height);
        SpriteSheet spriteSheet = new SpriteSheet(imageURL, 24, 32);
        this.upStop = spriteSheet.getSprite(1, 0).getSubImage(4, 8, 16, 23);
        this.downStop = spriteSheet.getSprite(1, 2).getSubImage(4, 8, 16, 23);
        this.rightStop = spriteSheet.getSprite(1, 1).getSubImage(4, 8, 16, 23);
        this.leftStop = spriteSheet.getSprite(1, 3).getSubImage(4, 8, 16, 23);
        this.upWalk = new Animation(new Image[]{spriteSheet.getSprite(0, 0).getSubImage(4, 8, 16, 23), spriteSheet.getSprite(1, 0).getSubImage(4, 8, 16, 23), spriteSheet.getSprite(2, 0).getSubImage(4, 8, 16, 23)}, duration);
        this.rightWalk = new Animation(new Image[]{spriteSheet.getSprite(0, 1).getSubImage(4, 8, 16, 23), spriteSheet.getSprite(1, 1).getSubImage(4, 8, 16, 23), spriteSheet.getSprite(2, 1).getSubImage(4, 8, 16, 23)}, duration);
        this.downWalk = new Animation(new Image[]{spriteSheet.getSprite(0, 2).getSubImage(4, 8, 16, 23), spriteSheet.getSprite(1, 2).getSubImage(4, 8, 16, 23), spriteSheet.getSprite(2, 2).getSubImage(4, 8, 16, 23)}, duration);
        this.leftWalk = new Animation(new Image[]{spriteSheet.getSprite(0, 3).getSubImage(4, 8, 16, 23), spriteSheet.getSprite(1, 3).getSubImage(4, 8, 16, 23), spriteSheet.getSprite(2, 3).getSubImage(4, 8, 16, 23)}, duration);
        this.state = state;

    }

    public void update(int command) {

        switch (command) {
            case CommandCode.DOWN:
                this.state = PlayerState.DOWN_WALK;
                this.posY = this.posY + this.pace;
                break;
            case CommandCode.UP:
                this.state = PlayerState.UP_WALK;
                this.posY = this.posY - this.pace;
                break;
            case CommandCode.RIGHT:
                this.state = PlayerState.RIGHT_WALK;
                this.posX = this.posX + this.pace;
                break;
            case CommandCode.LEFT:
                this.state = PlayerState.LEFT_WALK;
                this.posX = this.posX - this.pace;
                break;
            default:
                switch (this.state) {
                    case UP_WALK:
                        this.state = PlayerState.UP_STOP;
                        break;
                    case DOWN_WALK:
                        this.state = PlayerState.DOWN_STOP;
                        break;
                    case LEFT_WALK:
                        this.state = PlayerState.LEFT_STOP;
                        break;
                    case RIGHT_WALK:
                        this.state = PlayerState.RIGHT_STOP;
                        break;
                }
        }
        this.shape.setLocation(this.posX, this.posY);
    }

    public void draw() {

        switch (this.state) {
            case UP_WALK:
                this.upWalk.draw(this.posX, this.posY, this.width, this.height);
                break;
            case DOWN_WALK:
                this.downWalk.draw(this.posX, this.posY, this.width, this.height);
                break;
            case LEFT_WALK:
                this.leftWalk.draw(this.posX, this.posY, this.width, this.height);
                break;
            case RIGHT_WALK:
                this.rightWalk.draw(this.posX, this.posY, this.width, this.height);
                break;
            case UP_STOP:
                this.upStop.draw(this.posX, this.posY, this.width, this.height);
                break;
            case DOWN_STOP:
                this.downStop.draw(this.posX, this.posY, this.width, this.height);
                break;
            case LEFT_STOP:
                this.leftStop.draw(this.posX, this.posY, this.width, this.height);
                break;
            case RIGHT_STOP:
                this.rightStop.draw(this.posX+10, this.posY, this.width, this.height);
                break;
        }
        if(this.interact){
            
            this.exclamationPoint.draw(posX+20, posY-20,25,20);
                    
        }

    }

    public boolean isCollided(InteractiveObjectInterface element) {

        return this.shape.intersects(((Element)element).shape);

    }

    public Element checkCollision(ArrayList<Element> elements) {

        for (Element e : elements) {
            
            if (this.shape.intersects(((Element)e).shape)) {
                this.interact = true;
                return e;
            }
        }
        this.interact = false;
        return null;

    }

}
