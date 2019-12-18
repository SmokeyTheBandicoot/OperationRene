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
import org.newdawn.slick.Color;
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

        super.init(gc,sbg,elementID,keysID);

        this.lucchetto = 1;
        
        this.box = new Rectangle(0, 0, 600, 600);
        this.box.setCenterX(OperationRene.WIDTH/2 - 100);
        this.box.setCenterY(OperationRene.HEIGHT/2 + 30);

        
        contagiro = new Image(PATH + "manopola.png").getScaledCopy(230, 230);
        
        //inizializza vettore immagini lock
        for (int i = 0; i < 5; i++) {
            lock[i] = new Image(PATH + "lock.png");
        }

        int x = (int) this.box.getCenterX();
        int y = (int) this.box.getCenterY();
        
        //freccia rotazione verso destra
        this.rotateright5 = new Rectangle(x + 100, this.box.getY() + 80, 120, 70);
        //freccia rotazione verso destra
        this.rotateleft5 = new Rectangle(x - 200, this.box.getY() + 80, 120, 70);
        //bottone enter
        this.enter = new RoundedRectangle(this.box.getCenterX()-50, this.box.getCenterY() - 135,120,140,20);
        //bottone cancel
        this.cancel = new Rectangle(x + 170, y + 40, 100, 30);

        fontMiniGame = new TrueTypeFont(new java.awt.Font("Arial Black", java.awt.Font.BOLD, 20), false);
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
        grphcs.drawImage(goniometro.getScaledCopy(300, 300), this.box.getCenterX() - 150, this.box.getCenterY() - 210);
        
        //disegno manopola
        contagiro.draw(this.box.getCenterX() - 110, this.box.getCenterY() - 180, new Color(1, 1, 1, 9f));
        
        //immagini lock
        for (Image lock1 : lock) {
            grphcs.drawImage(lock1.getScaledCopy(50, 50), lockX, lockY);
            lockX += 60;
        }
        
        //stampa numero di serie della cassaforte
        grphcs.setColor(Color.white);
        fontMiniGame.drawString(this.box.getCenterX() + 180, this.box.getY() + 30, " " + serial[0] + serial[1] + serial[2] + serial[3] + serial[4], Color.black);
        
        
        grphcs.setColor(Color.black);
        grphcs.draw(this.cancel);
        this.fontMiniGame.drawString(this.cancel.getX(), this.cancel.getY(), " Cancel ", Color.black);

         //bottone enter ( solo per vedere la posizione del bottone)
        //grphcs.setColor(Color.white);
        //grphcs.draw(enterbox);

        font.drawString(10, 50, "TIME REMAINING: " + this.timer.getTime(), Color.red);
    }

    @Override
    public void mouseReleased(int button, int x, int y) {

        super.mouseReleased(button, x, y);
        //click solution  
        if (this.enter.contains(x, y)) {

            if (check(serial[lucchetto - 1])) {
                checksolutionpari();
            } else {
                checksolutiondispari();
            }

        } else if (this.rotateleft5.contains(x, y)) {
            System.out.println("-5 gradi");
            rotate -= 5;
            rotation();
        } else if (this.rotateright5.contains(x, y)) {
            System.out.println("+5 gradi");
            rotate += 5;
            rotation();
        } else if (this.cancel.contains(x, y)) {
            try {
                this.reset();
                System.out.println("reset");
            } catch (SlickException ex) {
            }
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

    private void checksolutionpari() {

        switch (lucchetto) {

            case 1:
                //prima cifra pari
                if (contagiro.getRotation() == 35 || contagiro.getRotation() == -325) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;

                    try {
                        lock[0] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }
                }

            case 2:
                //seconda cifra pari
                if (contagiro.getRotation() == 310 || contagiro.getRotation() == -50) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;

                    try {
                        lock[1] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }
                }

            case 3:
                //terza cifra pari
                if ((contagiro.getRotation() == 290 || contagiro.getRotation() == -70)) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    try {
                        lock[2] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }

                }

            case 4:
                // quarta cifra pari
                if (contagiro.getRotation() == 180 || contagiro.getRotation() == -180) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    try {
                        lock[3] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }

                }

            case 5:
                //quinta cifra pari
                if (contagiro.getRotation() == 355 || contagiro.getRotation() == -5) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    try {
                        lock[4] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }

                }

        }//chiusura switch
        
        if (lucchetto == 6) { this.completed = true; }
        
    }//chiusura check solution pari

    public void checksolutiondispari() {


        switch (lucchetto) {

            case 1:
                //prima cifra dispari
                if (contagiro.getRotation() == -20 || contagiro.getRotation() == 340) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto++;
                    System.out.print(lucchetto);
                    try {
                        lock[0] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }
                }
                break;

            case 2:
                //seconda cifra dispari
                if (contagiro.getRotation() == -10 || contagiro.getRotation() == 350) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto ++;
                    try {
                        lock[1] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }
                }
                break;
            case 3:
                //terza cifra dispari
                if (contagiro.getRotation() == 25 || contagiro.getRotation() == -335) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto ++;
                    try {
                        lock[2] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }

                }

            case 4:
                //quarta cifra dispari
                if (contagiro.getRotation() == 310 || contagiro.getRotation() == -50) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto ++;
                    try {
                        lock[3] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }

                }
            case 5:
                //quinta cifra dispari
                if (contagiro.getRotation() == 340 || contagiro.getRotation() == -20) {
                    System.out.println("Esatto" + rotate + "-->" + contagiro.getRotation());
                    lucchetto ++;
                    try {
                        lock[4] = new Image(PATH + "unlock.png");
                    } catch (SlickException ex) {
                    }
                }

        }// chiusura switch
    }//chiusura classe

    
    public void reset() throws SlickException {

        for (int i = 0; i < 5; i++) {
            lock[i] = new Image(PATH + "lock.png");
        }

        rotate = 0;
        lucchetto = 1;
        rotation();

    }

}
