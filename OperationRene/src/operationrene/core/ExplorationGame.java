package operationrene.core;


import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static operationrene.OperationRene.PATH_RESOURCES;

public class ExplorationGame extends BasicGameState {

    private Player player = null;
    private GameMap map = null;
    private InteractiveObject e = null;

    @Override
    public int getID() {
        return StateID.EXPLORATION_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        this.player = new Player(PATH_RESOURCES+"Rene.png",100,PlayerState.DOWN_STOP,ReneGame.width/2,ReneGame.height/2+(4*32),32,40,1);
        this.map = new GameMap(PATH_RESOURCES+"Livello1.tmx",ReneGame.width,ReneGame.height,0,0);
        this.e = new OggettoProva(200,200,50,50);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        this.map.draw();
        this.player.draw();
        g.setColor(Color.red);
        g.draw(new Rectangle(1,1,ReneGame.width-1,ReneGame.height-1));

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        Input input = container.getInput();

        if (input.isKeyDown(CommandCode.LEFT)){
            if(!this.map.checkCollision(this.player.posX-1,this.player.posY,this.player.width,this.player.height)){
                this.player.update(CommandCode.LEFT);
            }
        }
        else if (input.isKeyDown(CommandCode.RIGHT)){
            if(!this.map.checkCollision(this.player.posX+1,this.player.posY,this.player.width,this.player.height)){
                this.player.update(CommandCode.RIGHT);
            }
        }
        else if (input.isKeyDown(CommandCode.UP)){
            if(!this.map.checkCollision(this.player.posX,this.player.posY-1,this.player.width,this.player.height)){
                this.player.update(CommandCode.UP);
            }
        }
        else if (input.isKeyDown(CommandCode.DOWN)){
            if(!this.map.checkCollision(this.player.posX,this.player.posY+1,this.player.width,this.player.height)){
                this.player.update(CommandCode.DOWN);
            }
        }
        else if (input.isKeyDown(1)){
            game.enterState(2);
        }
        else{
            if(!this.map.checkCollision(this.player.posX,this.player.posY,this.player.width,this.player.height)){
                this.player.update(CommandCode.NONE);
            }
        }

        if (this.player.isCollided(this.e)&&input.isKeyDown(Input.KEY_E)){

            this.e.interact(container);

        }

    }

}
