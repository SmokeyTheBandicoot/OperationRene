package operationrene.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tests.xml.Entity;

/**
 *
 * @author Rickma
 */
public class Button extends Entity {


    private final String _PATHIMAGES = "C:\\Users\\**\\Documents\\NetBeansProjects\\Slick2D\\src\\extra\\Menu\\";

    //private String imageName;
    private final Rectangle button;
    private final Image buttonImage;
    private final Image buttonImagePressed;
    private boolean buttonPressed = false;
    private boolean buttonReleased = false;
    private final Vector2f position;
    private final float width;
    private final float height;
    private Image renderImage;
    private final int type;
    private boolean value = true;
    
    
    
    
    public Button(ButtonType buttonType, float x, float y) {
        this.buttonImage = this.loadImage(buttonType.image);
        this.buttonImagePressed = this.loadImage(buttonType.imageInv);
        this.width = this.buttonImage.getWidth();
        this.height = this.buttonImage.getHeight();
        this.position = new Vector2f(x - this.width / 2, y - this.height / 2);
        this.button = new Rectangle(position.x, position.y, this.width, this.height);
        this.renderImage = this.buttonImage;
        this.type = buttonType.type;
    }

    /*
    OLD CONSTRUCTOR
    
    public Button(String image, String image2, int type, float x, float y) {
        this.buttonImage = this.loadImage(image);
        this.buttonImagePressed = this.loadImage(image2);
        this.width = this.buttonImage.getWidth();
        this.height = this.buttonImage.getHeight();
        this.position = new Vector2f(x - this.width / 2, y - this.height / 2);
        this.button = new Rectangle(position.x, position.y, this.width, this.height);
        this.renderImage = this.buttonImage;
        this.type = type;
    }
*/

    private Image loadImage(String image) {
        try {
            return new Image(_PATHIMAGES + image);

        } catch (SlickException e) {
            System.out.println("Unsupported Image or not found.");
            return null;
        }
    }

    public void update(GameContainer gc) {

        buttonReleased = false;
        if (button.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
            if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                buttonPressed = true;
                if (type == 0) {
                    renderImage = buttonImagePressed;
                }
            } else {
                if (buttonPressed) {
                    buttonPressed = false;
                    buttonReleased = true;
                    if (type == 1) {
                        this.SwapImage();
                    }
                    if (type == 0) {
                        renderImage = buttonImage;
                    }
                }
            }
        } else {
            buttonPressed = false;
            if (type == 0) {
                renderImage = buttonImage;
            }
        }

    }

    public void render(Graphics gr) {
        renderImage.draw(position.x, position.y, width, height);

        gr.setColor(Color.black);

    }

    public boolean isClicked() {
        if (buttonReleased) {
            buttonReleased = false;
            return true;
        }
        return false;
    }

    public void SwapImage() {

        if (renderImage == buttonImage) {
            renderImage = buttonImagePressed;
            this.value = false;
        } else {
            renderImage = buttonImage;
            this.value = true;
        }

    }

    public boolean getValue() {
        return this.value;
    }

}
