package operationrene;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.TileBackground;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import operationrene.mapframework.LevelSerializer;
import operationrene.mapframework.LevelMap;

public class OperationRene extends Game {

    private AnimatedSprite man = null;
    private ColorBackground background = null;

    public void initResources() {

        //BufferedImage[] images = this.getImages("../../../sprites/prova2.png",3,4);
        // Paths.get(".").getParent().getParent().getParent().toString() + "sprites/prova.png");
        // System.out.println(FileSystems.getDefault().getPath("").toAbsolutePath().getParent() + "\\assets\\sprites\\prova.png");
        String str = FileSystems.getDefault().getPath("").toAbsolutePath().getParent() + "\\assets\\sprites\\prova.png";
        System.out.println(str);
        BufferedImage[] images = this.getImages("../../../../../assets/sprites/prova.jpg", 3, 4);
        man = new AnimatedSprite(images, 200, 200);
        man.setFrame(7);
        man.setLoopAnim(true);
        background = new ColorBackground(Color.LIGHT_GRAY);

    }

    public void update(long elapsedTime) {

        if (this.keyDown(KeyEvent.VK_A)){
            man.setAnimationFrame(9,11);
            man.setAnimate(true);
            man.move(-1,0);
        }
        else if (this.keyDown(KeyEvent.VK_D)){
            man.setAnimationFrame(3,5);
            man.setAnimate(true);
            man.move(1,0);
        }
        else if (this.keyDown(KeyEvent.VK_W)){
            man.setAnimationFrame(0,2);
            man.setAnimate(true);
            man.move(0,-1);
        }
        else if (this.keyDown(KeyEvent.VK_S)){
            man.setAnimationFrame(6,8);
            man.setAnimate(true);
            man.move(0,1);
        }
        else{
            int i = man.getFrame();
            if (i<3){//W
                man.setAnimate(false);
                man.setFrame(1);
            }
            else if (i<6){//D
                man.setAnimate(false);
                man.setFrame(4);
            }
            else if (i<9){//S
                man.setAnimate(false);
                man.setFrame(7);
            }
            else{//A
                man.setAnimate(false);
                man.setFrame(10);
            }
        }

        background.update(elapsedTime);
        man.update(elapsedTime);
    }

    public void render(Graphics2D g) {
        background.render(g);
        man.render(g);
    }

    public static void main(String[] args) {
        
        LevelMap lm = LevelSerializer.loadLevel("assets/levels/level1.json");
        
        GameLoader gameLoader = new GameLoader();
        gameLoader.setup(new OperationRene(), new Dimension(640, 400), false);
        gameLoader.start();
    }
}