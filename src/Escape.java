import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Escape extends BasicGameState{

    Image bg;
    int[] duration = {200, 200, 200, 200, 200, 200, 200, 200, 200};
    Animation mm, up, down, left, right, stay;
    float shiftX = 480;
    float shiftY = 70;
    int rightLimit = 610, leftLimit = -15;
    int upLimit = 70, downLimit = 310;

    public Escape(int state){

    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        bg = new Image("res/escapeLevel.png");

        Image[] walkUp = {new Image("res/wu1.png"), new Image("res/wu2.png"), new Image("res/wu3.png"), new Image("res/wu4.png"), new Image("res/wu5.png"), new Image("res/wu6.png"), new Image("res/wu7.png"), new Image("res/wu8.png"), new Image("res/wd9.png")};
        Image[] walkDown = {new Image("res/wd1.png"), new Image("res/wd2.png"), new Image("res/wd3.png"), new Image("res/wd4.png"), new Image("res/wd5.png"), new Image("res/wd6.png"), new Image("res/wd7.png"), new Image("res/wd8.png"), new Image("res/wd9.png")};
        Image[] walkLeft = {new Image("res/wl1.png"), new Image("res/wl2.png"), new Image("res/wl3.png"), new Image("res/wl4.png"), new Image("res/wl5.png"), new Image("res/wl6.png"), new Image("res/wl7.png"), new Image("res/wl8.png"), new Image("res/wl9.png")};
        Image[] walkRight = {new Image("res/wr1.png"), new Image("res/wr2.png"), new Image("res/wr3.png"), new Image("res/wr4.png"), new Image("res/wr5.png"), new Image("res/wr6.png"), new Image("res/wr7.png"), new Image("res/wr8.png"), new Image("res/wr9.png")};
        Image[] steady = {new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png")};

        up = new Animation(walkUp, duration, true);
        down = new Animation(walkDown, duration, true);
        left = new Animation(walkLeft, duration, true);
        right = new Animation(walkRight, duration, true);
        stay = new Animation(steady, duration, true);

        mm = stay;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        bg.draw(0,0);
        mm.draw(shiftX, shiftY);
        graphics.drawString("Akira X: " + shiftX + "\nAkira Y: " + shiftY, 400, 25);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        mm = stay;

        if(input.isKeyDown(Input.KEY_UP)){
            mm = up;
            shiftY -= i * .1f;
            if(shiftY < upLimit){
                shiftY += i * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_DOWN)){
            mm = down;
            shiftY += i * .1f;
            if(shiftY > downLimit){
                shiftY -= i * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_RIGHT)){
            mm = right;
            shiftX += i * .1f;
            if(shiftX > rightLimit){
                shiftX -= i * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_LEFT)){
            mm = left;
            shiftX -= i * .1f;
            if(shiftX < leftLimit || (shiftX > 290 && shiftX < 320)){
                shiftX += i * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_ENTER)){
            stateBasedGame.enterState(4);
        }

    }
}
