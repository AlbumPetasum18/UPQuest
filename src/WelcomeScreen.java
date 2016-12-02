
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class WelcomeScreen extends BasicGameState{

    private Image welcome;
    private Music back;

    private boolean quit = false;

    private int mousePosX, mousePosY;

    public WelcomeScreen(int state){

    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        welcome = new Image("res/welcomescreen/welcome.png");

        back = new Music("res/back.ogg");

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        welcome.draw(0, 0);

       // graphics.drawString("Akira X: " + mousePosX + "\nAkira Y: " + mousePosY, 400, 25);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        Input input = gameContainer.getInput();

        if(input.isKeyDown(Input.KEY_ESCAPE)){
            quit = true;
        }
        if(quit){
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

        mousePosX = Mouse.getX();
        mousePosY = Mouse.getY();

        if((mousePosX > 275 && mousePosX < 365) && (mousePosY > 125 && mousePosY < 175)){
            if(Mouse.isButtonDown(0)) {
                stateBasedGame.enterState(3, new FadeOutTransition(), new FadeInTransition());
            }
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        back.loop();
        back.setVolume(2.0f);
    }

    public void leave(GameContainer gc, StateBasedGame sbg){
        try{
            super.leave(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        back.stop();
    }
}
