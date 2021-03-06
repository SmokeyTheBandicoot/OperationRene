package operationrene.minigame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import operationrene.OperationRene;
import static operationrene.OperationRene.font;
import operationrene.sound.SoundEffect;
import operationrene.sound.SoundEngine;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import operationrene.StateID;
import static operationrene.gui.PauseWindow.setPauseInstance;
import org.newdawn.slick.Input;

public class WiresGame extends MinigameState{

    private final class ColorID {

        private ColorID(){}

        public static final int RED= 0;
        public static final  int WHITE=1;
        public static final int BLUE=2;
        public static final int YELLOW=3;
        public static final int BLACK=4;
    }    
    
    private Color[] arrayColors={Color.red,Color.white,Color.blue,Color.yellow,Color.black};
    private ArrayList<Integer>[] colorPosition = null;
    private Line [] arrayline=null;
    private Color [] lineColor =null;
    private ArrayList<Integer> deletedLines = null;
    private int solution;
    private Image circuit = null;
    private Image screw = null;
     
    private final String PATH = "assets/sprites/minigames/wires/";
    
    
    public WiresGame(int difficulty) {
        super(difficulty);
    }
    
    
    @Override
    public int getID() {
        return StateID.WIRES_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg, int elementID, ArrayList<Integer> keysID) throws SlickException {
        
        super.init(gc, sbg, elementID, keysID);

        this.circuit = (new Image(PATH+"/circuit.jpg")).getScaledCopy(640, 350);
        
        Random rand = new Random();
        
        int num =rand.nextInt(4)+3;
        
        Integer[] yPositions = new Integer[num];
        for (int i=0; i<num; i++){ 
            yPositions[i]=(OperationRene.HEIGHT/2-90)+(180*i/(num-1));
        }
        
        List<Integer> yList = Arrays.asList(yPositions);
        Collections.shuffle(yList);
        yList.toArray(yPositions);
        
        this.arrayline = new Line[num];
        this.lineColor=new Color[num];
        this.colorPosition = new ArrayList[arrayColors.length];
        for(int i=0;i<arrayColors.length;i++){
           this.colorPosition[i] = new ArrayList<Integer>();
        }
        
        for (int i=0; i<num; i++){ 
           
            this.arrayline[i]=new Line(OperationRene.WIDTH/2-280,(OperationRene.HEIGHT/2-90)+(180*i/(num-1)),OperationRene.WIDTH/2+280,yPositions[i]);
            int c=rand.nextInt(this.arrayColors.length);
            lineColor[i]=arrayColors[c];
            this.colorPosition[c].add(i);
            
       }     
        
       this.checkSolution();
       this.deletedLines = new ArrayList<Integer>();
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        grphcs.drawImage(this.circuit,OperationRene.WIDTH/2-(this.circuit.getWidth()/2),OperationRene.HEIGHT/2-(this.circuit.getHeight()/2));
        
        grphcs.setColor(Color.gray);
        grphcs.setLineWidth(1);
        grphcs.fillRect(this.arrayline[0].getX1()-7, this.arrayline[0].getY1()-20, 20, 220);
        grphcs.fillRect(this.arrayline[0].getX2()-10, this.arrayline[0].getY1()-20, 20, 220);
        grphcs.setLineWidth(10);
        grphcs.drawRect(OperationRene.WIDTH/2-(this.circuit.getWidth()/2), OperationRene.HEIGHT/2-(this.circuit.getHeight()/2), this.circuit.getWidth(), this.circuit.getHeight());
        grphcs.setLineWidth(10);
        for(int i = this.arrayline.length-1; i>-1;i--){
            if(!this.deletedLines.contains(i)){
                grphcs.setColor(this.lineColor[i]);
                grphcs.draw(this.arrayline[i]);
            }
        }
        
        font.drawString(10, 50, "TIME REMAINING: " + this.timer.getTime(), Color.red);
        
    }
    
    private void checkSolution(){ 
   
        switch(arrayline.length){
            
            case 3:
                if(this.colorPosition[ColorID.RED].size() == 0 ) { 
                    this.solution=1;
                }else if(this.lineColor[this.arrayline.length-1] == Color.white){
                    this.solution=2;
                }else if(this.colorPosition[ColorID.BLUE].size()>1){
                    this.solution=this.colorPosition[ColorID.BLUE].get(this.colorPosition[ColorID.BLUE].size()-1);
                }else{
                    this.solution=2;
                }
                break;
            case 4:
                if(this.colorPosition[ColorID.RED].size() > 1 ) { 
                    this.solution=this.colorPosition[ColorID.RED].get(this.colorPosition[ColorID.RED].size()-1);
                }else if((this.lineColor[this.arrayline.length-1] == Color.yellow)&&(this.colorPosition[ColorID.RED].size() == 0)){
                    this.solution=0;
                }else if(this.colorPosition[ColorID.BLUE].size() == 1){
                    this.solution=0;
                }else if(this.colorPosition[ColorID.YELLOW].size()>1){
                    this.solution=3;
                }else{
                    this.solution=1;
                }
                break;
            case 5:
                if(this.lineColor[this.arrayline.length-1] == Color.black){
                    this.solution = 3;
                }else if((this.colorPosition[ColorID.RED].size()==1)&&(this.colorPosition[ColorID.YELLOW].size()>1)){
                    this.solution = 0;
                }else if(this.colorPosition[ColorID.BLACK].size()==0){
                    this.solution = 1;
                }else{
                    this.solution = 0;
                }
                break;
            case 6:
                if(this.colorPosition[ColorID.YELLOW].size()==0){
                    this.solution = 2;
                }else if((this.colorPosition[ColorID.YELLOW].size()==1)&&(this.colorPosition[ColorID.WHITE].size()>1)){
                    this.solution = 3;
                }else if(this.colorPosition[ColorID.RED].size()==0){
                    this.solution = 5;
                }else{
                    this.solution = 3;
                }
                break;
                
        }
        
    }
    
    @Override
    public void  mouseReleased(int button, int x, int y){
                
        super.mouseReleased(button,x,y);
        
        int i = 0;
        boolean flag = true;
        
        Rectangle cube = new Rectangle(x-4,y-4,8,8);
        
        while(i<this.arrayline.length && flag){
            
            if(!this.deletedLines.contains(i)){
                if(this.arrayline[i].intersects(cube)){

                    flag = false;
                    if(i==this.solution){
                        
                        SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_WIRE_CUT);
                        this.completed = true;
                        
                    }else{
                        
                        SoundEngine.getIstance().playSoundEffect(SoundEffect.SOUND_WIRE_CUT);
                        this.errorDone();
                        this.deletedLines.add(i);
                        
                    }

                }
            }
            
            i++;
            
        }
        
    }
    
}
