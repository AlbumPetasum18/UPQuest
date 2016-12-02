import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Ending extends BasicGameState{


    private Image bg, akira;
    private Animation bird, flyleft, flyright, win;
    private float shiftX, shiftY, birdPosX = -15, birdPosY = 30, speedBirdx, speedBirdy, leftlimitBird = -13, rightlimitBird = 600;
    private Music back;

    private int[] durationBird = {80, 80, 80, 80, 80, 80, 80, 80, 80};
    private int[] duration = {200, 200, 200, 200, 200, 200, 200, 200, 200,};

    private boolean quit = false;

    public Ending(int state){}

    @Override
    public int getID() {
        return 8;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        Image[] LEFT = {new Image("res/playfive/fl1.png"), new Image("res/playfive/fl2.png"), new Image("res/playfive/fl3.png"), new Image("res/playfive/fl4.png"), new Image("res/playfive/fl5.png"), new Image("res/playfive/fl6.png"), new Image("res/playfive/fl7.png"), new Image("res/playfive/fl8.png"), new Image("res/playfive/fl8.png")};
        Image[] RIGHT = {new Image("res/playfive/fr1.png"), new Image("res/playfive/fr2.png"), new Image("res/playfive/fr3.png"), new Image("res/playfive/fr4.png"), new Image("res/playfive/fr5.png"), new Image("res/playfive/fr6.png"), new Image("res/playfive/fr7.png"), new Image("res/playfive/fr8.png"), new Image("res/playfive/fr8.png")};
        Image[] congrats = {new Image("res/congrats1.png"), new Image("res/congrats2.png"), new Image("res/congrats3.png"), new Image("res/congrats4.png"), new Image("res/congrats5.png"), new Image("res/congrats6.png"), new Image("res/congrats7.png"), new Image("res/congrats8.png"), new Image("res/congrats9.png")};

        flyleft = new Animation(LEFT, durationBird, true);
        flyright = new Animation (RIGHT, durationBird, true);
        win = new Animation(congrats, duration, true);
        bg = new Image("res/menu/ImpelDown.png");
        akira = new Image("res/s1.png");
        back = new Music("res/win.ogg");
        bird = flyleft;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        bg.draw(0, 0, 640, 360);
        win.draw(5, 20);
        akira.draw(shiftX, shiftY);
        bird.draw(birdPosX, birdPosY);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        Input input = gameContainer.getInput();

        if(birdPosX < leftlimitBird){
            speedBirdx = -.2f;
            bird = flyleft;
        }
        if(birdPosX > rightlimitBird){
            speedBirdx = .2f;
            bird = flyright;
        }

        birdPosX -= i * speedBirdx;

        if(birdPosY < 35){
            speedBirdy = -.2f;
        }
        if(birdPosY > 170){
            speedBirdy = .2f;
        }

        birdPosY -= i * speedBirdy;
        if(bird == flyleft) {
            shiftX = birdPosX + 25;
            shiftY = birdPosY - 10;
        }
        else{
            shiftX = birdPosX + 20;
            shiftY = birdPosY - 10;
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
