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

public class Button extends Entity {

    private final String _PATHIMAGES = "assets/sprites/controls/";

    protected final Rectangle button;
    protected final Image buttonImage;
    protected final Image buttonImagePressed;
    protected final Image buttonImageLabel;
    protected boolean buttonPressed = false;
    protected boolean buttonReleased = false;
    protected final Vector2f position;
    protected final float width;
    protected final float height;
    protected Image renderImage;
    protected final int type;
    protected boolean value = true;

    public Button(ButtonType buttonType, float x, float y) {

        this.buttonImage = this.loadImage(buttonType.image);
        this.buttonImagePressed = this.loadImage(buttonType.imageInv);
        this.width = this.buttonImage.getWidth();
        this.height = this.buttonImage.getHeight();
        this.position = new Vector2f(x - this.width / 2, y - this.height / 2);
        this.button = new Rectangle(position.x, position.y, this.width, this.height);
        this.renderImage = this.buttonImage;
        this.type = buttonType.type;

        if (buttonType.type > 2) {
            this.buttonImageLabel = this.loadImage(buttonType.imageLab);

        } else {
            this.buttonImageLabel = null;
        }
    }

    // private utility function to load an image.
    private Image loadImage(String image) {
        try {
            return new Image(_PATHIMAGES + image);

        } catch (SlickException e) {
            System.out.println("Unsupported Image or not found.");
            return null;
        }
    }

    // This method handles the mouse click events:
    // it checks if a displayed button is clicked, in this
    // case the image changes
    public void update(GameContainer gc) {

        buttonReleased = false;
        if (button.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
            if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                buttonPressed = true;
                if (type == 0 || type == 3) {
                    renderImage = buttonImagePressed;
                }
            } else {
                if (buttonPressed) {
                    buttonPressed = false;
                    buttonReleased = true;
                    if (type == 1) {
                        this.SwapImage();
                    }
                    if (type == 0 || type == 3) {
                        renderImage = buttonImage;
                    }
                }
            }
        } else {
            buttonPressed = false;
            if (type == 0 || type == 3) {
                renderImage = buttonImage;
            }
        }

    }

    public void render(Graphics gr) {
        renderImage.draw(position.x, position.y, width, height);
        gr.setColor(Color.black);
        if (buttonImageLabel != null) {
            buttonImageLabel.draw(position.x + width / 2 - buttonImageLabel.getWidth() / 2, position.y + height);
        }

    }

    public boolean isClicked() {
        if (buttonReleased) {
            buttonReleased = false;
            return true;
        }
        return false;
    }

    // This function manages the change of image
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
