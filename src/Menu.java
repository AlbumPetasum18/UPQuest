import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Menu extends BasicGameState{

    Image bg, quit, play;
    Animation animateTitle;
    int xpos, ypos;
    int[] duration = {500, 500};
    Music back;
    Sound playgame;

    public Menu(int state){

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        bg = new Image("res/ImpelDown.png");
        quit = new Image("res/quitGame.png");
        play = new Image("res/playGame.png");
        back = new Music("res/back.ogg");
        playgame = new Sound("res/play.ogg");

        Image[] title = {new Image("res/title.png"), new Image("res/title2.png")};

        animateTitle = new Animation(title, duration, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        bg.draw(0, 0, 640, 360);
        animateTitle.draw(125, 3);
        play.draw(350, 200);
        quit.draw(360, 250);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        xpos = Mouse.getX();
        ypos = Mouse.getY();
        if((xpos > 370 && xpos < 585) && (ypos > 110 && ypos < 140)){
            if(Mouse.isButtonDown(0)) {
                playgame.play();
                sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
            }
        }
        if((xpos > 380 && xpos < 585) && (ypos > 60 && ypos < 90)){
            if(Mouse.isButtonDown(0)){
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

    @Override
    public int getID() {
        return 0;
    }


}