package operationrene.minigame;

import java.util.ArrayList;
import java.util.Collections;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Random;
import operationrene.OperationRene;
import operationrene.core.StateID;


public class MemoryGame extends MinigameState{
    
    private Rectangle box = null;
    private Rectangle screen = null;
    private Rectangle[] buttons = null;
    private Rectangle[] steps = null;
    
    private TrueTypeFont fontScreen = null;
    private TrueTypeFont fontButtons = null;
    
    //livello di gioco
    private int level;
    //array contenente il valore dei tasti
    private ArrayList<Integer> buttonsValue = null;
    //array contenente i valore apparsi in screen ad ogni livello
    private int[] screenValues = null;
    //array contenente le posizioni dei tasti cliccati ad ogni livello
    private int[] positions = null;
    
    private int solution;
       

    @Override
    public int getID() {
        return StateID.MEMORY_ID;
    }

    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg, int elementID, ArrayList<Integer> keysID) throws SlickException {
        super.init(gc, sbg, elementID, keysID);

        //livello
        this.level = 0;
        
        this.screenValues = new int[5];
        this.positions = new int[5];
        this.buttonsValue = this.getRandomIndices(5);
           
        //box
        this.box = new Rectangle(0,0,600,600);
        this.box.setCenterX(OperationRene.WIDTH/2);
        this.box.setCenterY(OperationRene.HEIGHT/2);
        
        int boxCenterX = (int)this.box.getCenterX();
        int boxCenterY = (int)this.box.getCenterY();
      
        
        //screen
        this.screen = new Rectangle(boxCenterX-280, boxCenterY-170,400,250);
        fontButtons =new TrueTypeFont(new java.awt.Font("Arial Black",java.awt.Font.BOLD,40),false);
        fontScreen =new TrueTypeFont(new java.awt.Font("Arial Black",java.awt.Font.BOLD,65),false);
        
        //screen number
        this.generateLevel();
        
        //creo i bottoni
        this.buttons = new Rectangle[4];
        for (int i=0; i<4; i++){
            this.buttons[i] = new Rectangle(0,0,85,130);
        }
        this.buttons[0].setLocation(boxCenterX-280, boxCenterY+100);
        this.buttons[1].setLocation(boxCenterX-175, boxCenterY+100);
        this.buttons[2].setLocation(boxCenterX-70, boxCenterY+100);
        this.buttons[3].setLocation(boxCenterX+35, boxCenterY+100);
        
        //indicatori livello
        this.steps = new Rectangle[5];
        for (int i=0; i<5; i++){
            this.steps[i] = new Rectangle(0,0,116,50);
        }
        this.steps[0].setLocation(boxCenterX+160,boxCenterY+180);
        this.steps[1].setLocation(boxCenterX+160,boxCenterY+110);
        this.steps[2].setLocation(boxCenterX+160,boxCenterY+40);
        this.steps[3].setLocation(boxCenterX+160,boxCenterY-30);
        this.steps[4].setLocation(boxCenterX+160,boxCenterY-100);
        
    }

    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        if(!this.completed){
        
            //box
            grphcs.setColor(Color.lightGray);
            grphcs.fill(box);

            //schermo
            grphcs.setColor(Color.darkGray);
            grphcs.fill(screen);
            System.out.println(this.level+","+this.completed);
            //testo schermo
            fontScreen.drawString(this.screen.getCenterX()-30, this.screen.getCenterY()-60, ""+this.screenValues[this.level-1],Color.pink);

            //tasti
            grphcs.setColor(Color.white);
            for(int i=0; i<4; i++){
                grphcs.fill(this.buttons[i]);
                fontButtons.drawString(this.buttons[i].getCenterX()-13, this.buttons[i].getCenterY()-30, ""+this.buttonsValue.get(i),Color.black);
            }

            //indicatori livello
            grphcs.drawRect(923, 310, 125, 340);
            grphcs.setColor(Color.yellow);
            for(int i=0; i<this.level; i++){
                grphcs.fill(this.steps[i]);
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
    
    //restituisce la posizione del bottone con label x
    private int positionButtonX(int x){
        
        int pos = -1;
        for(int i=0; i<4; i++){
            if(this.buttonsValue.get(i) == x){
                 pos = i;
                 break;
            }
        }
        return pos;
    }
    
    
    //calcola la soluzione in base al livello ed al numero in screen
    private void computeSolution(int level){
        switch(level){
            
            case 1:
                if((this.screenValues[0] == 1)||(this.screenValues[0] == 2)){
                    solution = 1;
                }
                else if(this.screenValues[0] == 3){
                    solution = 2;
                }
                else{
                    solution = 3;
                }
                break;
                
            case 2:
                
                if(this.screenValues[1] == 1){
                    solution = this.positionButtonX(4);
                }
                else if((this.screenValues[1] == 2)||(this.screenValues[1] == 4)){
                    solution = this.positions[0];
                }
                else{
                    solution = 0;
                }
                break;
                
            case 3:
                
                if(this.screenValues[2] == 1){
                    solution = this.positionButtonX(this.screenValues[0]);
                }
                else if(this.screenValues[2] == 2){
                    solution = this.positionButtonX(this.screenValues[1]);
                }
                else if(this.screenValues[2] == 3){
                    solution = 2;//controllare
                }
                else{
                    solution = this.positionButtonX(4);
                }
                break;
                
            case 4:
                
                if(this.screenValues[3] == 1){
                    solution = this.positions[0];
                }
                else if(this.screenValues[3] == 2){
                    solution = 0;
                }
                else if(this.screenValues[3] == 3){
                    solution = this.positions[1];
                }
                else{
                    solution = this.positions[2];
                }
                break;
                
            case 5:
                
                if(this.screenValues[4] == 1){
                    solution = this.positionButtonX(this.screenValues[0]);
                }
                else if(this.screenValues[4] == 2){
                    solution = this.positionButtonX(this.screenValues[1]);
                }
                else if(this.screenValues[4] == 3){
                    solution = this.positionButtonX(this.screenValues[3]);
                }
                else{
                    solution = this.positionButtonX(this.screenValues[2]);
                }
                break;
        }
    }
    
    
    //resetta tutte le strutture per iniziare nuovamente dal livello 1
    private void resetAll(){
        //FORSE INUTILE
        for(int i=0; i<this.level; i++){
            this.positions[i] = -1;
        }
        this.level = 0;
        this.generateLevel();
    }
    
    
    //genera un nuovo livello
    private void generateLevel(){
        this.level ++;

        if(this.level < 6){
            this.screenValues[this.level-1] = new Random().nextInt(4)+1;
        }else{

            this.completed = true;
            
        }
    }
    
    @Override
    public void  mouseReleased(int button, int x, int y){
                
        super.mouseReleased(button,x,y);
        int i = 0;
        boolean flag = false;
        this.computeSolution(this.level);
        
        while((i<4)&&(!flag)){
            if(this.buttons[i].contains(x,y)){
                flag = true;
                if(i == this.solution){
                    
                    this.positions[this.level-1] = i;
                    this.generateLevel();
                    
                }
                else{
                    this.resetAll();
                }
            }
            i++;
        }
        
    }
    
    
}
