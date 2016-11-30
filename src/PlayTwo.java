import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class PlayTwo extends BasicGameState{

    private Animation akira, up, down, left, right, stay, complete;
    private Image map, spider1, spider2, spider3, spider4, spider5, spider6, spider7, gameover, playagain;

    private boolean quit = false;
    private boolean gameOver = false;

    private int[] duration = {200, 200, 200, 200, 200, 200, 200, 200, 200};
    private int rightLimit = 620, leftLimit = -30;
    private int upLimit = 245, downLimit = 200;
    private int limitspider = 230;
    private int mousePosX, mousePosY;

    private float shiftX = -10;
    private float shiftY = 200;
    private float web1 = 350, web2 = 251, web3 = -30, web4 = 380, web5 = 300, web6 = -1, web7 = 350;
    private float speed1, speed2, speed3, speed4, speed5, speed6, speed7;
    private float rectwidth = 0.5f;

    private Music back;
    private Sound collide, gov;

    private Input input;


    public PlayTwo(int state){

    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        map = new Image("res/fanglevel.png");
        spider1 = new Image("res/spid1.png");
        spider2 = new Image("res/spid2.png");
        spider3 = new Image("res/spid3.png");
        spider4 = new Image("res/spid4.png");
        spider5 = new Image("res/spid5.png");
        spider6 = new Image("res/spid6.png");
        spider7 = new Image("res/spid7.png");
        gameover = new Image("res/gameOver.png");
        playagain = new Image("res/playAgain.png");

        back = new Music("res/backtunnel.ogg");
        collide = new Sound("res/collide.ogg");
        gov = new Sound("res/gov.ogg");

        Image[] walkUp = {new Image("res/wu1.png"), new Image("res/wu2.png"), new Image("res/wu3.png"), new Image("res/wu4.png"), new Image("res/wu5.png"), new Image("res/wu6.png"), new Image("res/wu7.png"), new Image("res/wu8.png"), new Image("res/wd9.png")};
        Image[] walkDown = {new Image("res/wd1.png"), new Image("res/wd2.png"), new Image("res/wd3.png"), new Image("res/wd4.png"), new Image("res/wd5.png"), new Image("res/wd6.png"), new Image("res/wd7.png"), new Image("res/wd8.png"), new Image("res/wd9.png")};
        Image[] walkLeft = {new Image("res/wl1.png"), new Image("res/wl2.png"), new Image("res/wl3.png"), new Image("res/wl4.png"), new Image("res/wl5.png"), new Image("res/wl6.png"), new Image("res/wl7.png"), new Image("res/wl8.png"), new Image("res/wl9.png")};
        Image[] walkRight = {new Image("res/wr1.png"), new Image("res/wr2.png"), new Image("res/wr3.png"), new Image("res/wr4.png"), new Image("res/wr5.png"), new Image("res/wr6.png"), new Image("res/wr7.png"), new Image("res/wr8.png"), new Image("res/wr9.png")};
        Image[] steady = {new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png")};
        Image[] nextLevel = {new Image("res/nextLevel.png"), new Image("res/nextLevel1.png"), new Image("res/nextLevel2.png"), new Image("res/nextLevel3.png"), new Image("res/nextLevel4.png"), new Image("res/nextLevel.png"), new Image("res/nextLevel1.png"), new Image("res/nextLevel2.png"), new Image("res/nextLevel3.png")};
        up = new Animation(walkUp, duration, true);
        down = new Animation(walkDown, duration, true);
        left = new Animation(walkLeft, duration, true);
        right = new Animation(walkRight, duration, true);
        stay = new Animation(steady, duration, true);
        complete = new Animation(nextLevel, duration, true);

        akira = stay;

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        input = gameContainer.getInput();

        map.draw(0, 0, map.getWidth(), map.getHeight());

        //graphics.drawString("Akira X: " + shiftX + "\nAkira Y: " + shiftY, 400, 25);
        akira.draw(shiftX, shiftY, akira.getWidth(), akira.getHeight());


        graphics.drawRect(leftLimit + 90, 0, rectwidth, web1+10);
        spider1.draw(leftLimit + 68, web1, spider1.getWidth(), spider1.getHeight());

        graphics.drawRect(leftLimit + 170, 0, rectwidth, web2+10);
        spider2.draw(leftLimit + 146, web2, spider2.getWidth(), spider2.getHeight());

        graphics.drawRect(leftLimit + 250, 0, rectwidth, web3+10);
        spider3.draw(leftLimit + 227, web3, spider3.getWidth(), spider3.getHeight());

        graphics.drawRect(leftLimit + 330, 0, rectwidth, web4+10);
        spider4.draw(leftLimit + 310, web4, spider4.getWidth(), spider4.getHeight());

        graphics.drawRect(leftLimit + 410, 0, rectwidth, web5+10);
        spider5.draw(leftLimit + 390, web5, spider5.getWidth(), spider5.getHeight());

        graphics.drawRect(leftLimit + 490, 0, rectwidth, web6+10);
        spider6.draw(leftLimit + 470, web6, spider6.getWidth(), spider6.getHeight());

        graphics.drawRect(leftLimit + 570, 0, rectwidth, web7+10);
        spider7.draw(leftLimit + 545, web7, spider7.getWidth(), spider7.getHeight());

        if(!gameOver){

            if((shiftX > leftLimit + 45 && shiftX < leftLimit + 100) && (web1 > shiftY - 25)){
                collide.play();
                gameOver = true;
            }
            if((shiftX > leftLimit + 125 && shiftX < leftLimit + 180) && (web2 > shiftY - 25)){
                collide.play();
                gameOver = true;
            }
            if((shiftX > leftLimit + 205 && shiftX < leftLimit + 260) && (web3 > shiftY - 25)){
                collide.play();
                gameOver = true;
            }
            if((shiftX > leftLimit + 285 && shiftX < leftLimit + 340) && (web4 > shiftY - 25)){
                collide.play();
                gameOver = true;
            }
            if((shiftX > leftLimit + 365 && shiftX < leftLimit + 420) && (web5 > shiftY - 25)){
                collide.play();
                gameOver = true;
            }
            if((shiftX > leftLimit + 445 && shiftX < leftLimit + 500) && (web6 > shiftY - 25)){
                collide.play();
                gameOver = true;
            }
            if((shiftX > leftLimit + 525 && shiftX < leftLimit + 580) && (web7 > shiftY - 25)){
                collide.play();
                gameOver = true;
            }

        }
        else{
            back.stop();
            gameover.draw(80, 20, 500, 351);
            web1 = (float) (web1 - 1.3);
            web2 = (float) (web2 - 1.3);
            web3 = (float) (web3 - 1.3);
            web4 = (float) (web4 - 1.3);
            web5 = (float) (web5 - 1.3);
            web6 = (float) (web6 - 1.3);
            web7 = (float) (web7 - 1.3);
            // TRY AGAIN
            playagain.draw(250, 250);
        }

        if(shiftX > leftLimit + 620 && !gameOver){
             // INSERT NEXT LEVEL
            complete.draw(60, 10);
            if(input.isKeyDown(Input.KEY_ENTER)) {
                stateBasedGame.enterState(0);
            }
        }

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


        akira = stay;

        if(web1 < 0){
            speed1 = -.35f;
        }
        if(web2 < 0){
            speed2 = -.35f;
        }
        if(web3 < 0){
            speed3 = -.35f;
        }
        if(web4 < 0){
            speed4 = -.35f;
        }
        if(web5 < 0){
            speed5 = -.35f;
        }
        if(web6 < 0){
            speed6 = -.35f;
        }
        if(web7 < 0){
            speed7 = -.35f;
        }

        if(web1 > limitspider){
            speed1 = .35f;
        }
        if(web2 > limitspider){
            speed2 = .35f;
        }
        if(web3 > limitspider){
            speed3 = .35f;
        }
        if(web4 > limitspider ){
            speed4 = .35f;
        }
        if(web5 > limitspider ){
            speed5 = .35f;
        }
        if(web6 > limitspider ){
            speed6 = .35f;
        }
        if(web7 > limitspider ){
            speed7 = .35f;
        }


        web1 -= i * speed1;
        web2 -= i * speed2;
        web3 -= i * speed3;
        web4 -= i * speed4;
        web5 -= i * speed5;
        web6 -= i * speed6;
        web7 -= i * speed7;

        if(input.isKeyDown(Input.KEY_UP)){
            akira = up;
            shiftY -= i * .1f;
            if(shiftY < upLimit){
                shiftY += i * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_DOWN)){
            akira = down;
            shiftY += i * .1f;
            if(shiftY > downLimit){
                shiftY -= i * .1f;
            }
            if(web1 > downLimit+20){
                web1 -= i * .1f;
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

        mousePosX = Mouse.getX();
        mousePosY = Mouse.getY();

        if((mousePosX > 250 && mousePosX < 250+playagain.getWidth()) && (mousePosY > 80 && mousePosY < 110)){
            if(Mouse.isButtonDown(0)) {
               gameOver = false;
                shiftX = -10;
                shiftY = 200;
                back.play();
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
                stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
            }
            if(input.isKeyDown(Input.KEY_Q)){
                System.exit(0);
            }
        }
    }

    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame){
        try{
            super.enter(gameContainer, stateBasedGame);
        }catch (SlickException e){
            e.printStackTrace();
        }
        back.loop();
        back.setVolume(1.0f);
    }

    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame){
        try{
            super.leave(gameContainer, stateBasedGame);
        }catch (SlickException e){
            e.printStackTrace();
        }
        back.stop();
    }
}
