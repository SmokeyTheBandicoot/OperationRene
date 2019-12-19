package operationrene.minigame;

import java.util.ArrayList;
import operationrene.core.StateID;
import operationrene.minigame.MinigameState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;


public class SimonSays extends MinigameState {
    
    
    public SimonSays(int difficulty) {
        super(difficulty);
    }
    
    private Rectangle box = null;
    private Rectangle[] buttons = new Rectangle[4];
    private Circle rflash = new Circle(590,45,25);
    
    private Image[] images = new Image[4];
    
    private Rectangle[] errors = new Rectangle[2];
    private Rectangle errorspace;
    private int serialnumber;
    private color[][][] btpress = new color[2][3][4];
    private int strike = 0;
    private color colorflash;
    int buttonpressed = -1;
    int currentColor = -1; 
    int iteration;
    int endgame;

    @Override
    public int getID() {
        return StateID.SIMONSAYS_ID;
    }

    
     public enum color{
        BLUE(1),
        YELLOW(3),
        GREEN(2),
        RED(0);
        public Integer num;
        private color(int n){
            this.num = n;
        } 
      public static color getcolor(int n){
      switch(n){
                case 0:
                    return RED;
                case 1:
                    return BLUE;
                case 2:
                    return GREEN;
                default:
                    return YELLOW;
            }
   }
        
    }
     public Color getColor(int n){
            switch(n){
                case 0:
                    return Color.red;
                case 1:
                    return Color.blue;
                case 2:
                    return Color.green;
                default:
                    return Color.yellow;
            }
        }
     

    @Override
    public void init(GameContainer gc, StateBasedGame sbg, int elementID, ArrayList<Integer> keysID) throws SlickException {
        this.box = new Rectangle(0,0,600,600);
        this.box.setCenterX(1536/4);
        this.box.setCenterY(832/4);
        int centerX = (int) this.box.getCenterX();
        int centerY = (int) this.box.getCenterY()+60;
        this.errors[0]= new Rectangle(60,215,80,35);
        this.errors[1]= new Rectangle(60,170,80,35);
        this.buttons[color.BLUE.num]=new Rectangle(centerX-180, centerY-180,150,150);
        this.buttons[color.GREEN.num]=new Rectangle(centerX+10, centerY-180,150,150);
        this.buttons[color.RED.num]=new Rectangle(centerX-180, centerY+10,150,150);
        this.buttons[color.YELLOW.num]=new Rectangle(centerX+10, centerY+10,150,150);
        this.images[0]= new Image("LensRED.png");
        this.images[1]= new Image("LensBLUE.png");
        this.images[2]= new Image("LensGREEN.png");
        this.images[3]= new Image("LensYELLOW.png");;
        
        this.errorspace = new Rectangle(50,160,100,100);
        iteration = 0;
        endgame = 4;
        btpress[0]=new color[][]{
            {color.BLUE,color.RED,color.YELLOW,color.GREEN},
            {color.YELLOW,color.GREEN,color.BLUE,color.RED},
            {color.GREEN,color.RED,color.YELLOW,color.BLUE}
        };
        btpress[1]=new color[][]{
            {color.BLUE,color.YELLOW,color.GREEN,color.RED},
            {color.RED,color.BLUE,color.YELLOW,color.GREEN},
            {color.YELLOW,color.GREEN,color.BLUE,color.RED}
        };
        this.serialnumber = ((int)(Math.random()*10))%2;
        this.currentColor=((int)(Math.random()*10))%4;
        System.out.println("seriale "+serialnumber);
        this.strike=0;
    
    }
    public void update(GameContainer gc, int i) throws SlickException {
      
    }
    
   
    
    public void  mouseReleased(int button, int x, int y){
        super.mouseReleased(button,x,y);
        for(int i=0;i<4;i++){
            if (buttons[i].contains(x, y)){
                if(color.getcolor(i) == btpress[serialnumber][strike][this.currentColor]){
                    this.currentColor=((int)(Math.random()*10))%4;
                    System.out.println("corretto");
                    iteration++;
                } else {
                    strike++;
                    System.out.println("errore");
                    this.currentColor=((int)(Math.random()*10))%4;
                    iteration=0;
                    endgame +=4;
                }
        }
        }
        if (iteration >= endgame){
            System.exit(0);
            //gioco finito superato
        }
    }
    
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        for(int i=0;i<4;i++){
            grphcs.setColor(this.getColor(i));
            grphcs.fill(this.buttons[i]);
            }
        grphcs.drawImage(images[currentColor], 500,0);
        grphcs.setColor(Color.gray);
        grphcs.fill(errorspace);
        if (strike <=2){
            for (int i=0;i<strike;i++){
                grphcs.setColor(Color.yellow);
                grphcs.fill(errors[i]);
            }
        }else{
            System.exit(1);
            //perso gioco finito
        }
    }

}
   