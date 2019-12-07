/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.gui;

/**
 *
 * @author Rickma
 */

/*
Enum class to list all available buttons.
*/

public enum ButtonType {
    PLAY("Play.PNG", "Play_pressed.PNG", 0),
    SETTINGS("Settings.PNG", "Settings_pressed.PNG", 0),
    EXIT("Exit.PNG", "Exit_pressed.PNG", 0),
    RETURN("Return.PNG", "Return_pressed.PNG", 0),
    CREDITS("Credits.PNG","Credits_pressed.PNG",0),
    MUSIC_SOUND("MusicSound_checked.PNG", "MusicSound.PNG", 1),
    EFFECT_SOUND("EffectSound_checked.PNG", "EffectSound.PNG", 1);
    

    public final String image;
    public final String imageInv;
    public final int type;
    
    // Type = 0 is a button that apply changes or change view screen. Type = 1 is a button that set on/off its value.

    private ButtonType(String image1, String image2, int type) {
        this.image = image1;
        this.imageInv = image2;
        this.type = type;
    }
}

