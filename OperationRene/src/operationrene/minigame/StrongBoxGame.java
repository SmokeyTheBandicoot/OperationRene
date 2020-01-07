package operationrene.minigame;

import java.util.ArrayList;
import operationrene.core.StateID;
import operationrene.OperationRene;
import static operationrene.OperationRene.font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import operationrene.core.SoundEffect;
import operationrene.core.SoundEngine;
import static operationrene.gui.PauseWindow.setPauseInstance;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;

public class StrongBoxGame extends MinigameState {

    
      
    private Rectangle box = null;
    private Rectangle rotateleft5 = null;
    private Rectangle rotateright5 = null;
    private RoundedRectangle enter = null;
    private Rectangle cancel = null;
    private Image[] lock = new Image[5];
    private Image contagiro = null;
    private int[] serial = new int[5];
    private int rotate;
    private int lucchetto;

    private final String PATH = "assets/sprites/minigames/strongbox/";
    private TrueTypeFont fontMiniGame;

    public StrongBoxGame(int difficulty) {
        super(difficulty);
    }

    @Override
    public int getID() {

        return StateID.STRONGBOX_ID;

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg, int elementID, ArrayList<Integer> keysID) throws SlickException {

        super.init(gc, sbg, elementID, keysID);

        
        this.lucchetto = 1;

        this.box = new Rectangle(0, 0, 600, 600);
        this.box.setCenterX(OperationRene.WIDTH / 2 - 100);
        this.box.setCenterY(OperationRene.HEIGHT / 2 + 30);

        contagiro = new Image(PATH + "manopola.png").getScaledCopy(230, 230);

        //inizializza vettore immagini lock
        for (int i = 0; i < 5; i++) {
            lock[i] = new Image(PATH + "lock.png");
        }

        int x = (int) this.box.getCenterX();
        int y = (int) this.box.getCenterY();

        //freccia rotazione verso destra
        this.rotateright5 = new Rectangle(x + 150, this.box.getY() + 210, 120, 70);
        //freccia rotazione verso destra
        this.rotateleft5 = new Rectangle(x - 290, this.box.getY() + 210, 120, 70);
        //bottone enter
        this.enter = new RoundedRectangle(this.box.getCenterX() - 60, this.box.getCenterY() - 135, 120, 140, 20);
        //bottone cancel
        this.cancel = new Rectangle(x + 170, y + 40, 100, 30);

        fontMiniGame = new TrueTypeFont(new java.awt.Font("Arial Black", java.awt.Font.BOLD, 22), false);
        //numero di seria della cassaforte
        serial = generateSerial();

    }// chisura metodo init

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        int lockX = (int) this.box.getCenterX() - 150;
        int lockY = (int) this.box.getCenterY() + 200;

        Image background = new Image(PATH + "background.jpg");
        grphcs.drawImage(background.getScaledCopy(600, 600), this.box.getX(), this.box.getY());

        // immagine freccia sinistra
        Image aleft = new Image(PATH + "leftarrow.png");
        grphcs.drawImage(aleft.getScaledCopy(150, 60), this.rotateleft5.getX(), this.rotateleft5.getY());

        //immagine freccia destra
        Image aright = new Image(PATH + "rightarrow.png");
        grphcs.drawImage(aright.getScaledCopy(150, 60), this.rotateright5.getX(), this.rotateright5.getY());

        //immagine goniometro
        Image goniometro = new Image(PATH + "goniometro.png");
        //cambviato position x da 150 a 165
        grphcs.drawImage(goniometro.getScaledCopy(320, 320), this.box.getCenterX() - 165, this.box.getCenterY() - 220);

        //disegno manopola
        contagiro.draw(this.box.getCenterX() - 120, this.box.getCenterY() - 175, new Color(1, 1, 1, 9f));

        //immagini lock
        for (Image lock1 : lock) {
            grphcs.drawImage(lock1.getScaledCopy(50, 50), lockX, lockY);
            lockX += 60;
        }

        //stampa numero di serie della cassaforte
        grphcs.setColor(Color.white);
        fontMiniGame.drawString(this.box.getCenterX() + 180, this.box.getY() + 30, " " + serial[0] + serial[1] + serial[2] + serial[3] + serial[4], Color.black);

       grphcs.setColor(Color.green);
       fontMiniGame.drawString(this.enter.getX()+10,this.enter.getY()+60,"ENTER",Color.green);
        
       font.drawString(10, 50, "TIME REMAINING: " + this.timer.getTime(), Color.red);
    }

    @Override
    public void mouseReleased(int button, int x, int y) {

        super.mouseReleased(button, x, y);
        //click solution  
        if (this.enter.contains(x, y)) {

            if (check(serial[lucchetto - 1])) {
                
                try {
                    checksolutionpari();
                } catch (SlickException ex) { }
            } else {
                
                try {
                    checksolutiondispari();
                } catch (SlickException ex) { }
              }

        } else if (this.rotateleft5.contains(x, y)) {
            SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_TICK);
            rotate -= 5;
            rotation();
        } else if (this.rotateright5.contains(x, y)) {
            SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_TICK);
            rotate += 5;
            rotation();
          } 
    }

    private int[] generateSerial() {
        Random rand = new Random();
        int[] array = new int[5];

        for (int i = 0; i < 5; i++) {
            array[i] = rand.nextInt(10);
        }
        return array;
    }

    private void rotation() {

        contagiro.setRotation(rotate);

    }

    private boolean check(int array) {

        return (array % 2) == 0;
    }

    private void checksolutionpari() throws SlickException {

        switch (lucchetto) {

            case 1:
                //prima cifra pari
                if (contagiro.getRotation() == 35 || contagiro.getRotation() == -325) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[0] = new Image(PATH + "unlock.png");
                } else { this.reset(); }
            break;

            case 2:
                //seconda cifra pari
                if (contagiro.getRotation() == 310 || contagiro.getRotation() == -50) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[1] = new Image(PATH + "unlock.png");
                } else { this.reset(); }
            break;

            case 3:
                //terza cifra pari
                if ((contagiro.getRotation() == 290 || contagiro.getRotation() == -70)) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[2] = new Image(PATH + "unlock.png");
                } else { this.reset(); }
            break;

            case 4:
                // quarta cifra pari
                if (contagiro.getRotation() == 180 || contagiro.getRotation() == -180) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[3] = new Image(PATH + "unlock.png");
                } else { this.reset(); }
            break;

            case 5:
                //quinta cifra pari
                if (contagiro.getRotation() == 355 || contagiro.getRotation() == -5) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[4] = new Image(PATH + "unlock.png");
                } else { this.reset(); }
            break;
    }//chiusura switch
        
        if (lucchetto == 6) {
            this.completed = true;
        }
    } //chiusura checksoultionpari

    //chiusura check solution pari
    public void checksolutiondispari() throws SlickException {

        switch (lucchetto) {

            case 1:

//prima cifra dispari
                if (contagiro.getRotation() == -20 || contagiro.getRotation() == 340) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[0] = new Image(PATH + "unlock.png");
                } else { this.reset(); }
            break;
            
            case 2:
                //seconda cifra dispari
                if (contagiro.getRotation() == -10 || contagiro.getRotation() == 350) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[1] = new Image(PATH + "unlock.png");
                } else { this.reset(); }
            break;
            
            case 3:
                //terza cifra dispari
                if (contagiro.getRotation() == 25 || contagiro.getRotation() == -335) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[2] = new Image(PATH + "unlock.png");
                } else { this.reset(); }
            break;

            case 4:
                //quarta cifra dispari
                if (contagiro.getRotation() == 310 || contagiro.getRotation() == -50) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[3] = new Image(PATH + "unlock.png");
                } else { this.reset(); }
            break;
            
            case 5:
                //quinta cifra dispari
                if (contagiro.getRotation() == 340 || contagiro.getRotation() == -20) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_UNLOCK);
                    lock[4] = new Image(PATH + "unlock.png");
                } else {this.reset(); }
            break;

        }// chiusura switch
         
        if (lucchetto == 6) {
               this.completed = true;
         }
    }

    public void reset() throws SlickException {
        
        SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_LOCK);
        this.errorDone();
        
        for (int i = 0; i < 5; i++) {
            lock[i] = new Image(PATH + "lock.png");
        }

        rotate = 0;
        lucchetto = 1;
        rotation();

    }
    

}//chiusura classe
