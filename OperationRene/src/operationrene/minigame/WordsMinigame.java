/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.minigame;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.util.HashMap;
import operationrene.OperationRene;
import static operationrene.OperationRene.font;
import operationrene.core.StateID;
import operationrene.minigame.MinigameState;
import org.newdawn.slick.geom.Rectangle;
import operationrene.utils.RandomUtils;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;

/**
 *
 * @author Amministratore
 */
public class WordsMinigame extends MinigameState {

    private ArrayList<String> displayWords ;
    private ArrayList<String> buttonWords;
    private ArrayList<String> selectedWords;
    private final String PATH = "assets/sprites/minigames/words/";
    private HashMap<String, String> buttonCorrelation;
    private HashMap<String, Integer> displayCorrelation ;
    private int resultWordsMinigame;
    Rectangle box= null;
    String dispWord;
    Rectangle display;
    Rectangle[] rec;
    Circle circle;

    public WordsMinigame(int difficulty) {
        super(difficulty);
    }

    @Override
    public int getID() {
        return StateID.WORDS_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg, int elementID, ArrayList<Integer> keysID) throws SlickException {

        super.init(gc,sbg,elementID,keysID);
        
        this.box = new Rectangle(0,0,600,600);
        this.box.setCenterX(OperationRene.WIDTH/2-100);
        this.box.setCenterY(OperationRene.HEIGHT/2+30);
        rec = new Rectangle[6];
        resultWordsMinigame = 0;
        displayWords = new ArrayList<>(28);
        buttonWords = new ArrayList<>(28);
        selectedWords = new ArrayList<>(6);
        buttonCorrelation = new HashMap<>();
        displayCorrelation = new HashMap<>();
        
        // display words array initialization
        displayWords.add("YES");
        displayWords.add("FIRST");
        displayWords.add("DISPLAY");
        displayWords.add("OKAY");
        displayWords.add("SAYS");
        displayWords.add("NOTHING");
        displayWords.add(" ");
        displayWords.add("BLANK");
        displayWords.add("NO");
        displayWords.add("LED");
        displayWords.add("LEAD");
        displayWords.add("READ");
        displayWords.add("RED");
        displayWords.add("REED");
        displayWords.add("LEED");
        displayWords.add("HOLD ON");
        displayWords.add("YOU");
        displayWords.add("YOU ARE");
        displayWords.add("YOUR");
        displayWords.add("YOU'RE");
        displayWords.add("UR");
        displayWords.add("THERE");
        displayWords.add("THEY'RE");
        displayWords.add("THEIR");
        displayWords.add("THEY ARE");
        displayWords.add("SEE");
        displayWords.add("C");
        displayWords.add("CEE");

        // button words array initialization
        buttonWords.add("READY");
        buttonWords.add("FIRST");
        buttonWords.add("NO");
        buttonWords.add("BLANK");
        buttonWords.add("NOTHING");
        buttonWords.add("YES");
        buttonWords.add("WHAT");
        buttonWords.add("UHHH");
        buttonWords.add("LEFT");
        buttonWords.add("RIGHT");
        buttonWords.add("MIDDLE");
        buttonWords.add("OKAY");
        buttonWords.add("WAIT");
        buttonWords.add("PRESS");
        buttonWords.add("YOU");
        buttonWords.add("YOU ARE");
        buttonWords.add("YOUR");
        buttonWords.add("YOU'RE");
        buttonWords.add("UR");
        buttonWords.add("U");
        buttonWords.add("UH HUH");
        buttonWords.add("UH UH");
        buttonWords.add("WHAT?");
        buttonWords.add("DONE");
        buttonWords.add("NEXT");
        buttonWords.add("HOLD");
        buttonWords.add("SURE");
        buttonWords.add("LIKE");

        // correlation between display words and button positions
        displayCorrelation.put(displayWords.get(0), 1);
        displayCorrelation.put(displayWords.get(1), 3);
        displayCorrelation.put(displayWords.get(2), 5);
        displayCorrelation.put(displayWords.get(3), 3);
        displayCorrelation.put(displayWords.get(4), 5);
        displayCorrelation.put(displayWords.get(5), 1);
        displayCorrelation.put(displayWords.get(6), 2);
        displayCorrelation.put(displayWords.get(7), 4);
        displayCorrelation.put(displayWords.get(8), 5);
        displayCorrelation.put(displayWords.get(9), 1);
        displayCorrelation.put(displayWords.get(10), 5);
        displayCorrelation.put(displayWords.get(11), 4);
        displayCorrelation.put(displayWords.get(12), 4);
        displayCorrelation.put(displayWords.get(13), 2);
        displayCorrelation.put(displayWords.get(14), 2);
        displayCorrelation.put(displayWords.get(15), 5);
        displayCorrelation.put(displayWords.get(16), 4);
        displayCorrelation.put(displayWords.get(17), 5);
        displayCorrelation.put(displayWords.get(18), 4);
        displayCorrelation.put(displayWords.get(19), 4);
        displayCorrelation.put(displayWords.get(20), 0);
        displayCorrelation.put(displayWords.get(21), 5);
        displayCorrelation.put(displayWords.get(22), 2);
        displayCorrelation.put(displayWords.get(23), 4);
        displayCorrelation.put(displayWords.get(24), 1);
        displayCorrelation.put(displayWords.get(25), 5);
        displayCorrelation.put(displayWords.get(26), 3);
        displayCorrelation.put(displayWords.get(27), 5);

        // correlation among button words
        buttonCorrelation.put(buttonWords.get(0), buttonWords.get(5));
        buttonCorrelation.put(buttonWords.get(1), buttonWords.get(8));
        buttonCorrelation.put(buttonWords.get(2), buttonWords.get(3));
        buttonCorrelation.put(buttonWords.get(3), buttonWords.get(12));
        buttonCorrelation.put(buttonWords.get(4), buttonWords.get(7));
        buttonCorrelation.put(buttonWords.get(5), buttonWords.get(11));
        buttonCorrelation.put(buttonWords.get(6), buttonWords.get(7));
        buttonCorrelation.put(buttonWords.get(7), buttonWords.get(0));
        buttonCorrelation.put(buttonWords.get(8), buttonWords.get(9));
        buttonCorrelation.put(buttonWords.get(9), buttonWords.get(5));
        buttonCorrelation.put(buttonWords.get(10), buttonWords.get(3));
        buttonCorrelation.put(buttonWords.get(11), buttonWords.get(10));
        buttonCorrelation.put(buttonWords.get(12), buttonWords.get(7));
        buttonCorrelation.put(buttonWords.get(13), buttonWords.get(9));
        buttonCorrelation.put(buttonWords.get(14), buttonWords.get(26));
        buttonCorrelation.put(buttonWords.get(15), buttonWords.get(16));
        buttonCorrelation.put(buttonWords.get(16), buttonWords.get(21));
        buttonCorrelation.put(buttonWords.get(17), buttonWords.get(14));
        buttonCorrelation.put(buttonWords.get(18), buttonWords.get(23));
        buttonCorrelation.put(buttonWords.get(19), buttonWords.get(20));
        buttonCorrelation.put(buttonWords.get(20), buttonWords.get(20));
        buttonCorrelation.put(buttonWords.get(21), buttonWords.get(18));
        buttonCorrelation.put(buttonWords.get(22), buttonWords.get(14));
        buttonCorrelation.put(buttonWords.get(23), buttonWords.get(26));
        buttonCorrelation.put(buttonWords.get(24), buttonWords.get(22));
        buttonCorrelation.put(buttonWords.get(25), buttonWords.get(15));
        buttonCorrelation.put(buttonWords.get(26), buttonWords.get(15));
        buttonCorrelation.put(buttonWords.get(27), buttonWords.get(17));

        int recY = (int) (this.box.getY()+270);
        int recX = (int) (this.box.getCenterX());
        
        display = new Rectangle(this.box.getCenterX()-180,this.box.getY()+80, 350, 70);

        for (int i = 0; i < 3; i++) {
            rec[i]=new Rectangle(recX-180,recY,174,69);
            rec[i+3]=new Rectangle(recX+10,recY,174,69);
           // rec[i] = new Rectangle(65, recY, 174, 69);
            //rec[i + 3] = new Rectangle(241, recY, 174, 69);
            recY += 71;
        }

        // random display word
        dispWord = displayWords.get(new Random().nextInt(28));
        randomizeButtonWords();

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

       Image img = new Image( PATH + "metalback.jpg");
       g.drawImage(img.getScaledCopy(600, 600), this.box.getX(), this.box.getY());

        g.setColor(Color.black);

        // display
        g.fill(display);

        // words' slots
        for (Rectangle rec1 : rec) {
            g.fill(rec1);
        }

        // led
       g.fillOval(this.box.getCenterX()+200, this.box.getY()+80, 70, 70);

        if (resultWordsMinigame == 1) {
            g.setColor(Color.green);
            g.fillOval(this.box.getCenterX()+205, this.box.getY()+85, 60, 60);
        }

        // random display word
        g.setColor(Color.white);
        int height = g.getFont().getHeight(dispWord);
        int width = g.getFont().getWidth(dispWord);
        g.drawString(dispWord, display.getCenterX() - width / 2, display.getCenterY() - height / 2);

        // random button words
        int heightWord;
        int widthWord;

        for (int i = 0; i < 6; i++) {
            heightWord = g.getFont().getHeight(selectedWords.get(i));
            widthWord = g.getFont().getWidth(selectedWords.get(i));
            g.drawString(selectedWords.get(i), rec[i].getCenterX() - widthWord / 2, rec[i].getCenterY() - heightWord / 2);
        }
        
         font.drawString(10, 50, "TIME REMAINING: " + this.timer.getTime(), Color.red);


    }

    public void randomizeButtonWords() {

        ArrayList<String> temp = buttonWords;
        int j;
        int index;

        for (Rectangle rec1 : rec) {
            j = RandomUtils.genRandomInt(0, temp.size() - 1);
            selectedWords.add(temp.get(j));
            temp.remove(j);
        }

        // controls if the correlated word is not present in the button pad
        String wordToSearch = selectedWords.get(displayCorrelation.get(dispWord));
        if (temp.contains(buttonCorrelation.get(wordToSearch))) {
            String wordToAdd = temp.get(temp.indexOf(buttonCorrelation.get(wordToSearch)));

            do {
                index = RandomUtils.genRandomInt(0, 5);
            } while (index == displayCorrelation.get(dispWord));

            selectedWords.set(index, wordToAdd);
        }
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);

        for (int i = 0; i < 6; i++) {
            if (this.rec[i].contains(x, y)) {
                if (findSolution() == i) {
                    resultWordsMinigame = 1;
                    System.out.println("Corretto");
                    this.completed = true;
                } else {
                    this.errorDone();
                    System.out.println("Sbagliato!");
                    resultWordsMinigame = -1;
                    // RIDURRE IL TEMPO
                }
                break;
            }
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        if (resultWordsMinigame == -1) {
            this.init(gc, sbg, this.elementID,this.keysID);
        }
    }

    private int findSolution() {

        int index = displayCorrelation.get(dispWord);
        String wordSolution = buttonCorrelation.get(selectedWords.get(index));

        return selectedWords.indexOf(wordSolution);
    }
}

