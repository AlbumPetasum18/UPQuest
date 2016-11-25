import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class CongratsScreen extends BasicGameState {

    Image congrats;
    Music win;

    boolean quit = false;

    int mousePosX, mousePosY;

    public CongratsScreen(int state){

    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        congrats = new Image("res/congrats.png");

        win = new Music("res/win.ogg");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        congrats.draw(0, 0);

        //graphics.drawString("Akira X: " + mousePosX + "\nAkira Y: " + mousePosY, 400, 25);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        mousePosX = Mouse.getX();
        mousePosY = Mouse.getY();

        if((mousePosX > 275 && mousePosX < 365) && (mousePosY > 125 && mousePosY < 175)){
            if(Mouse.isButtonDown(0)) {
                stateBasedGame.enterState(5, new FadeOutTransition(), new FadeInTransition());
            }
        }

        if(input.isKeyDown(Input.KEY_ESCAPE)){
            quit = true;
        }
        if(quit == true){
            if(input.isKeyDown(Input.KEY_R)){
                quit = false;
            }
            if(input.isKeyDown(Input.KEY_M)){
                stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
            }
            if(input.isKeyDown(Input.KEY_Q)){
                System.exit(0);
            }
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        win.play();
    }

    public void leave(GameContainer gc, StateBasedGame sbg){
        try{
            super.leave(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        win.stop();
    }
}
