import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class IntroMessage extends BasicGameState{

    private Animation intro;
    private Music back;

    private int[] duration = {200, 200, 200};

    private boolean quit = false;

    public IntroMessage(int state){}

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Image[] message = {new Image("res/intromessage/intro.png"), new Image("res/intromessage/intro1.png"), new Image("res/intromessage/intro2.png")};

        intro = new Animation(message, duration, true);

        back = new Music("res/back.ogg");

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        intro.draw(50, 10);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        if(input.isKeyDown(Input.KEY_ENTER)){
            stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }

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

    }

    public void enter(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        back.loop();
        back.setVolume(3.0f);
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
