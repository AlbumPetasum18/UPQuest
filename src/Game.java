import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

    private static final String gamename = "UP Quest";
    private static final int menu = 0;
    private static final int play = 2;
    private static final int welcome = 1;
    private static final int playTwo = 3;
    private static final int playThree = 4;


    private Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new WelcomeScreen(welcome));
        this.addState(new PlayTwo(playTwo));
        this.addState(new PlayThree(playThree));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(welcome).init(gc, this);
        this.getState(playTwo).init(gc, this);
        this.getState(playThree).init(gc, this);
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
