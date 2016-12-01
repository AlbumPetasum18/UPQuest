import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.Random;

public class PlayFive extends BasicGameState {

    private Animation map, akira, up, down, left, right, stay, flyleft, flyright, bird, fromtunnel, danger;
    private Image tunnel, gameover, levelComp, playagain;
    private Music background;
    private Sound bubbles, fall, GOV;
    private boolean quit = false;
    private int[] duration = {200, 200, 200, 200, 200, 200, 200, 200, 200};
    private int[] durationBird = {80, 80, 80, 80, 80, 80, 80, 80, 80};
    private int[] durationLava = {300, 300, 300, 300, 300, 300, 300, 300, 300};
    private int rightLimit = 620, leftLimit = -30;
    private int upLimit = 140, downLimit = 216;
    private float shiftX = 300;
    private float shiftY = -50;
    private float birdPosY;
    private float birdPosX = -400;
    private float speedBirdx, speedBirdy, leftlimitBird = -13, rightlimitBird = 600;
    private boolean onGround = false, dangerZone = false, win = false;
    private boolean startgame = false;
    private Random Y;

    public PlayFive(int state){}

    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        tunnel = new Image("res/playfive/tunnel.png");
        playagain = new Image("res/playAgain.png");
       /* levelComp = new Image("gstar.png");
        gameover = new Image("GOV.png");*/

        background = new Music("res/musicPlayThree.ogg");
        bubbles = new Sound("res/bub.ogg");
        fall = new Sound("res/fall.wav");
        GOV = new Sound("res/gov.ogg");


        Image[] back = {new Image("res/playfive/back1.png"), new Image("res/playfive/back2.png"), new Image("res/playfive/back3.png"), new Image("res/playfive/back4.png"), new Image("res/playfive/back1.png"), new Image("res/playfive/back2.png"), new Image("res/playfive/back3.png"), new Image("res/playfive/back4.png"), new Image("res/playfive/back1.png")};
       // Image[] walkUp = {new Image("res/wu1.png"), new Image("res/wu2.png"), new Image("res/wu3.png"), new Image("res/wu4.png"), new Image("res/wu5.png"), new Image("res/wu6.png"), new Image("res/wu7.png"), new Image("res/wu8.png"), new Image("res/wd9.png")};
        Image[] walkDown = {new Image("res/wd1.png"), new Image("res/wd2.png"), new Image("res/wd3.png"), new Image("res/wd4.png"), new Image("res/wd5.png"), new Image("res/wd6.png"), new Image("res/wd7.png"), new Image("res/wd8.png"), new Image("res/wd9.png")};
        Image[] walkLeft = {new Image("res/wl1.png"), new Image("res/wl2.png"), new Image("res/wl3.png"), new Image("res/wl4.png"), new Image("res/wl5.png"), new Image("res/wl6.png"), new Image("res/wl7.png"), new Image("res/wl8.png"), new Image("res/wl9.png")};
        Image[] walkRight = {new Image("res/wr1.png"), new Image("res/wr2.png"), new Image("res/wr3.png"), new Image("res/wr4.png"), new Image("res/wr5.png"), new Image("res/wr6.png"), new Image("res/wr7.png"), new Image("res/wr8.png"), new Image("res/wr9.png")};
        Image[] steady = {new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png")};
        Image[] fall = {new Image("res/s1.png"), new Image("res/s2.png"), new Image("res/s3.png"), new Image("res/s4.png"), new Image("res/s5.png"), new Image("res/s6.png"), new Image("res/s1.png"), new Image("res/s2.png"), new Image("res/s3.png")};
        Image[] LEFT = {new Image("res/playfive/fl1.png"), new Image("res/playfive/fl2.png"), new Image("res/playfive/fl3.png"), new Image("res/playfive/fl4.png"), new Image("res/playfive/fl5.png"), new Image("res/playfive/fl6.png"), new Image("res/playfive/fl7.png"), new Image("res/playfive/fl8.png"), new Image("res/playfive/fl8.png")};
        Image[] RIGHT = {new Image("res/playfive/fr1.png"), new Image("res/playfive/fr2.png"), new Image("res/playfive/fr3.png"), new Image("res/playfive/fr4.png"), new Image("res/playfive/fr5.png"), new Image("res/playfive/fr6.png"), new Image("res/playfive/fr7.png"), new Image("res/playfive/fr8.png"), new Image("res/playfive/fr8.png")};
        Image[] dangerZone = {new Image("res/playfive/dangerZone.png"), new Image("res/playfive/dangerZone1.png"), new Image("res/playfive/dangerZone2.png"), new Image("res/playfive/dangerZone3.png"), new Image("res/playfive/dangerZone4.png"), new Image("res/playfive/dangerZone.png"), new Image("res/playfive/dangerZone1.png"), new Image("res/playfive/dangerZone2.png"), new Image("res/playfive/dangerZone3.png")};

        map = new Animation(back, durationLava, true);
        down = new Animation(walkDown, duration, true);
        left = new Animation(walkLeft, duration, true);
        right = new Animation(walkRight, duration, true);
        stay = new Animation(steady, duration, true);
        flyleft = new Animation (LEFT, durationBird, true);
        flyright = new Animation (RIGHT, durationBird, true);
        fromtunnel = new Animation (fall, duration, true);
        danger = new Animation (dangerZone, duration, true);

        akira = stay;
        background.loop();
        background.setVolume(1.5f);
        Y = new Random();
        birdPosY = Y.nextInt(10) + -30;

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if(!startgame){
            //draw kato instuctions
        }
        map.draw(0, 0, map.getWidth(), map.getHeight());
        akira.draw(shiftX, shiftY, akira.getWidth(), akira.getHeight());
        tunnel.draw(265, 0, 100, 80);
        if(onGround){
            bird.draw(birdPosX, birdPosY, bird.getWidth() - 10, bird.getHeight() - 9);
        }

        if(dangerZone && shiftY > 215){
            GOV.loop();
            map.stop();
            background.stop();
            bubbles.stop();
            danger.draw(50,100);
            shiftY += 0.1f;
            //playagain.draw(250, 250);
        }
        else{
            bubbles.play(0.4f, 0.2f);
        }
        if(!onGround){
            fall.play();

        }
        if(win){
            if(bird == flyleft){
                rightlimitBird = -1000;
            }
            else{
                leftlimitBird = 2000;
            }
            shiftX = birdPosX +5;
            shiftY = birdPosY - 10;
            dangerZone = false;
        }

        bubbles.play(0.4f, 0.2f);

        if(quit){
            graphics.drawString("Resume (R)", 250, 100);
            graphics.drawString("Main Menu (M)", 250, 150);
            graphics.drawString("Quit Game (Q)", 250, 200);
            if(!quit){
                graphics.clear();
            }
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        akira = stay;

       /* if(){
            //////// mousepressed blah blah
            startgame = true;

        }*/
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

        if(shiftY < downLimit){
            akira = fromtunnel;
            shiftY += i * .3f;
            if(shiftY + 10 > downLimit){
                onGround = true;
            }
        }

        if(((birdPosX - shiftX > 0 && birdPosX - shiftX < 30) || (birdPosX - shiftX < -10 && birdPosX - shiftX > -30))  && (birdPosY - shiftY < -10 && birdPosY - shiftY > -15)){
            win = true;
        }

        if(shiftX < 165 || shiftX > 420){
            dangerZone = true;
        }

        if(input.isKeyDown(Input.KEY_UP)){

            akira = fromtunnel;
            if(shiftY > upLimit){
                shiftY -= i * 1.0f;
            }

        }
        if(input.isKeyDown(Input.KEY_RIGHT)){
            akira = right;
            shiftX += i * .1f;
            if(shiftX > rightLimit){
                shiftX -= i * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_LEFT)){
            akira = left;
            shiftX -= i * .1f;
            if(shiftX < leftLimit + 25){
                shiftX += i * .1f;
            }
        }


        if(input.isKeyDown(Input.KEY_ESCAPE)){
            quit = true;
        }
        if(quit){
            if(input.isKeyDown(Input.KEY_R)){
                quit = false;
            }
            if(input.isKeyDown(Input.KEY_M)){
                stateBasedGame.enterState(0);
            }
            if(input.isKeyDown(Input.KEY_Q)){
                System.exit(0);
            }
        }

    }
}
