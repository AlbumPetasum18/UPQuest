import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

    private static final String gamename = "UP Quest";
    private static final int menu = 0;
    private static final int intro = 1;
    private static final int intro2 = 2;
    private static final int welcome = 3;
    private static final int play = 4;
    private static final int playTwo = 5;
    private static final int playThree = 6;
    private static final int playFour = 7;
    private static final int playFive = 8;
    private static final int ending = 9;


    private Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new WelcomeScreen(welcome));
        this.addState(new PlayTwo(playTwo));
        this.addState(new PlayThree(playThree));
        this.addState(new PlayFour(playFour));
        this.addState(new PlayFive(playFive));
        this.addState(new Ending(ending));
        this.addState(new IntroMessage(intro));
        this.addState(new IntroMessage2(intro2));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(welcome).init(gc, this);
        this.getState(playTwo).init(gc, this);
        this.getState(playThree).init(gc, this);
        this.getState(playFour).init(gc, this);
        this.getState(playFive).init(gc, this);
        this.getState(ending).init(gc, this);
        this.getState(intro).init(gc, this);
        this.getState(intro2).init(gc, this);

        gc.setShowFPS(false);
        this.enterState(menu);
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try{
            appgc = new AppGameContainer(new Game(gamename));
            appgc.setDisplayMode(640, 360, false);
            appgc.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }

}
