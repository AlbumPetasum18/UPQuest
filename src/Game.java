import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

    public static final String gamename = "UP Quest";
    public static final int menu = 0;
    public static final int play = 3;
    public static final int welcome = 1;
    public static final int escape = 2;
    public static final int congratsScreen = 4;
    public static final int playTwo = 5;

    public Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new WelcomeScreen(welcome));
        this.addState(new Escape(escape));
        this.addState(new CongratsScreen(congratsScreen));
        this.addState(new PlayTwo(playTwo));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(welcome).init(gc, this);
        this.getState(escape).init(gc, this);
        this.getState(congratsScreen).init(gc, this);
        this.getState(playTwo).init(gc, this);
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
