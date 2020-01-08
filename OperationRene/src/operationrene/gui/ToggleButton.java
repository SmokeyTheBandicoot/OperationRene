/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import static operationrene.gui.PlayWindow.difficulty;

//this class is used to display the difficulty button
public class ToggleButton extends Button {

    public ToggleButton(ButtonType buttonType, float x, float y) {
        super(buttonType, x, y);
    }

    @Override
    public void update(GameContainer gc) {

        buttonReleased = false;
        if (button.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
            if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                buttonPressed = true;
            } else {
                if (buttonPressed) {
                    buttonPressed = false;
                    buttonReleased = true;

                    this.SwapImage();

                }
            }
        } else {
            buttonPressed = false;

        }
    }

    //Override the parent's method to manage 3 images and set the difficulty variable.
    @Override
    public void SwapImage() {

        if (renderImage == buttonImage) {
            renderImage = buttonImagePressed;
            difficulty = 1;

        } else if (renderImage == buttonImagePressed) {
            renderImage = buttonImageLabel;
            difficulty = 2;
        } else if (renderImage == buttonImageLabel) {
            renderImage = buttonImage;
            difficulty = 0;
        }

    }

    @Override
    public void render(Graphics gr) {

        gr.setColor(Color.black);
        renderImage.draw(position.x, position.y, width, height);

    }

}
