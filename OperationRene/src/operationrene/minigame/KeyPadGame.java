package operationrene.minigame;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import static operationrene.OperationRene.CURRENT_TIME;
import static operationrene.OperationRene.MAXIMUM_TIME;
import static operationrene.OperationRene.REMAINING_TIME;
import static operationrene.core.ExplorationGame.GAMEOVER;
import operationrene.core.ReneGame;
import operationrene.core.StateID;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class KeyPadGame extends BasicGameState {
    
    private Rectangle box = null;
    private RoundedRectangle enterButton = null;
    private RoundedRectangle deleteButton = null;
    private Rectangle[] padButtons = null;
    private RoundedRectangle[] padLeds = null;
    private Color[] padColorLeds = null;
    private Image[] padImages = null;
    private ArrayList<Integer> padValues = null;
    private String solution = null;
    private String playerInput =null;
    public static int resultKeyPad = 0;
    private static int time = 0;
    private final String PATH = "assets/sprites/minigames/keypad/";
     
    
    @Override
    public int getID() {
        return StateID.KEYPAD_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        
        int[] column; 
        
        switch((new Random()).nextInt(6)){
            
            case 0:
                column = new int[] {1,2,3,4,5,6,7};
                break;
            case 1:
                column = new int[] {8,1,7,9,10,6,11};

                break;
            case 2:
                column = new int[] {12,13,9,14,15,3,10};
                break;
            case 3:
                column = new int[] {16,17,18,5,14,11,19};
                break;
            case 4:
                column = new int[] {20,19,18,21,17,22,23};
                break;
            case 5:
                column = new int[] {16,8,24,25,20,26,27};
                break;
            default:
                column = null;
            
        }
        
        padValues = getRandomIndices(7);
        
        this.box = new Rectangle(0,0,600,600);
        this.box.setCenterX(ReneGame.WIDTH/2);
        this.box.setCenterY(ReneGame.HEIGHT/2);
        
        this.enterButton = new RoundedRectangle(this.box.getCenterX()-160,this.box.getY()+50,140,70,10);
        this.deleteButton = new RoundedRectangle(this.box.getCenterX()+20,this.box.getY()+50,140,70,10);
        
        this.padButtons = new Rectangle[4];
        this.padLeds = new RoundedRectangle[4];
        this.padColorLeds = new Color[4];
        this.padImages = new Image[4];
        for (int i = 0;i<4;i++){
            
            this.padButtons[i] = new Rectangle(0,0, 170,170);
            this.padLeds[i] = new RoundedRectangle (0,0,150,6,2);
            this.padImages[i] = new Image(PATH+"S"+column[padValues.get(i)]+".png").getScaledCopy(170,170);
            this.padColorLeds[i] = Color.gray;
            
        }
        
        int centerX = (int) this.box.getCenterX();
        int centerY = (int) this.box.getCenterY()+60;
        
        this.padButtons[0].setLocation(centerX-180, centerY-180);
        this.padButtons[1].setLocation(centerX+10, centerY-180);
        this.padButtons[2].setLocation(centerX-180, centerY+10);
        this.padButtons[3].setLocation(centerX+10, centerY+10);
        
        for (int i = 0;i<4;i++){
            
            this.padLeds[i].setLocation(this.padButtons[i].getX()+10,this.padButtons[i].getY()+5);
            
        }
        
        List<Integer> sortedPadValues = ((ArrayList<Integer>)padValues.clone()).subList(0,4);
        Collections.sort(sortedPadValues);
        solution =sortedPadValues.get(0).toString()+sortedPadValues.get(1).toString()+sortedPadValues.get(2).toString()+sortedPadValues.get(3).toString();
        playerInput = "";
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
       
        grphcs.setColor(Color.lightGray);
        grphcs.fill(box);
        
        Image backgroud = new Image(PATH+"metallo.jpg");
        grphcs.drawImage(backgroud.getScaledCopy(600, 600), this.box.getX(), this.box.getY());
        grphcs.setColor(Color.green);
        grphcs.fill(this.enterButton);
        grphcs.setColor(Color.red);
        grphcs.fill(this.deleteButton);
        TrueTypeFont f =new TrueTypeFont(new Font("Arial Black",Font.BOLD,28),false);
        f.drawString(this.enterButton.getX()+10, this.enterButton.getY()+12, "ENTER",Color.black);
        f.drawString(this.deleteButton.getX()+5, this.deleteButton.getY()+12, "DELETE",Color.black);
        
        for(int i=0;i<4;i++){
            grphcs.setColor(Color.white);
            grphcs.fill(this.padButtons[i]);
            grphcs.setColor(this.padColorLeds[i]);
            grphcs.fill(this.padLeds[i]);
            grphcs.drawImage(this.padImages[i], this.padButtons[i].getX(),this.padButtons[i].getY());
        }
        grphcs.setColor(Color.red);
        grphcs.drawString("TIME REMAINING: " + REMAINING_TIME, 10, 50);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        CURRENT_TIME +=delta;
        REMAINING_TIME = (MAXIMUM_TIME - CURRENT_TIME/1000);
        
        if(REMAINING_TIME < 0 ){
            JOptionPane.showMessageDialog(null, 
                              "HAI PERSO. TEMPO SCADUTO.\nSEI STATO CATTURATO POLLASTRO.", 
                              "TEMPO SCADUTO", 
                              JOptionPane.WARNING_MESSAGE);
            GAMEOVER = true;
            sbg.enterState(0);
            }
        
        if (resultKeyPad ==1)
            sbg.enterState(StateID.EXPLORATION_ID);
    }
    
    @Override
    public void  mouseReleased(int button, int x, int y){
                
        super.mouseReleased(button,x,y);
        
        if(this.enterButton.contains(x,y)){
         
            if(this.solution.compareTo(this.playerInput)==0){
            
                System.out.println("corretto");
                resultKeyPad = 1;
                
            }else{
                
                this.resetInput();//TEMPO DA DIMINUIREEEEE
                System.out.println("Sbagliato!");
                CURRENT_TIME += 5000;
                
                
            }
            
        }else if(this.deleteButton.contains(x,y)){
            
            this.resetInput();
            
        }else{
            int i = 0;
            boolean flag = true;
            while(i<4 && flag){
                if(this.padButtons[i].contains(x,y)){
            
                    flag = false;
                    
                    if(this.padColorLeds[i]==Color.gray){
                        
                        this.padColorLeds[i]=Color.green;
                        this.playerInput = this.playerInput+this.padValues.get(i);
                        
                    }
                
                }
                i++;
            }
            
        }
        
    }
    
    private ArrayList<Integer> getRandomIndices(int max){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=1; i < max; i++){
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        return list;
    }
    
    private void resetInput(){
        
        for(int i = 0; i<4;i++){
            this.padColorLeds[i]=Color.gray;
        } 
        this.playerInput = "";
        
    }
    
}