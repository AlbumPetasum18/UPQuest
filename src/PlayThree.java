import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class PlayThree extends BasicGameState {

    private Animation akira, up, down, left, right, stay, effects;
    private Image map, instruction;
    private boolean quit = false, start = true;
    private int[] duration = {200, 200, 200, 200, 200, 200, 200, 200, 200};
    private float charPosX = -135;
    private float charPosY = -55;
    private float shiftX = 310;
    private float shiftY = 150;
    private Music bgm;
    private Sound bump;

    public PlayThree(int state){

    }

    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        map = new Image("res/playthree/puzzle copy.png");
        instruction = new Image("res/playthree/mazeInstruct.png");

        Image[] walkUp = {new Image("res/wu1.png"), new Image("res/wu2.png"), new Image("res/wu3.png"), new Image("res/wu4.png"), new Image("res/wu5.png"), new Image("res/wu6.png"), new Image("res/wu7.png"), new Image("res/wu8.png"), new Image("res/wd9.png")};
        Image[] walkDown = {new Image("res/wd1.png"), new Image("res/wd2.png"), new Image("res/wd3.png"), new Image("res/wd4.png"), new Image("res/wd5.png"), new Image("res/wd6.png"), new Image("res/wd7.png"), new Image("res/wd8.png"), new Image("res/wd9.png")};
        Image[] walkLeft = {new Image("res/wl1.png"), new Image("res/wl2.png"), new Image("res/wl3.png"), new Image("res/wl4.png"), new Image("res/wl5.png"), new Image("res/wl6.png"), new Image("res/wl7.png"), new Image("res/wl8.png"), new Image("res/wl9.png")};
        Image[] walkRight = {new Image("res/wr1.png"), new Image("res/wr2.png"), new Image("res/wr3.png"), new Image("res/wr4.png"), new Image("res/wr5.png"), new Image("res/wr6.png"), new Image("res/wr7.png"), new Image("res/wr8.png"), new Image("res/wr9.png")};
        Image[] steady = {new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png")};
       // Image[] fireworks = {new Image("res/fireworks.png"), new Image("res/fireworks1.png"), new Image("res/fireworks2.png"), new Image("res/fireworks4.png"), new Image("res/fireworks5.png"), new Image("res/fireworks4.png"), new Image("res/fireworks2.png"), new Image("res/fireworks1.png"), new Image("res/fireworks.png")};
        up = new Animation(walkUp, duration, true);
        down = new Animation(walkDown, duration, true);
        left = new Animation(walkLeft, duration, true);
        right = new Animation(walkRight, duration, true);
        stay = new Animation(steady, duration, true);

        akira = stay;

        bgm = new Music("res/bgm.wav");
        bump = new Sound("res/bump.wav");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.draw(charPosX, charPosY, map.getWidth() + 650, map.getHeight() + 650);
        akira.draw(shiftX, shiftY, akira.getWidth(), akira.getHeight());

        //graphics.drawString("akira x: " + charPosX + "\nakira y: " + charPosY, 400, 20);

        if(start){
            instruction.draw(50, 10);
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
        Input input = gameContainer.getInput();

        akira = stay;

        if(input.isKeyDown(Input.KEY_Y)){
            start = false;
        }

        if(input.isKeyDown(Input.KEY_UP)) {
            akira = up;
            charPosY += i * .2f;
            if ((charPosY > -45) ||
                    ((charPosY < -65 && charPosY > -145) && (charPosX > -240 || (charPosX < -340 && charPosX > -480) || (charPosX < -575 && charPosX > -880))) ||
                    ((charPosY < -155 && charPosY > -235) && ((charPosX < -94 && charPosX > -320) || (charPosX < -415 && charPosX > -560) || (charPosX < -815 && charPosX > -880) || (charPosX < -735 && charPosX > -800) || (charPosX < -655 && charPosX > -720))) ||
                    ((charPosY < -245 && charPosY > -315)&& ((charPosX < -180 && charPosX > -320) || (charPosX < -335 && charPosX > -640) || (charPosX < -735) || (charPosX < -94 && charPosX > -163))) ||
                    ((charPosY <  -335 && charPosY > -410) && ((charPosX < -655) || (charPosX < -94 && charPosX > -163) || (charPosX < -255 && charPosX > -320) || (charPosX < -415 && charPosX > -480) || (charPosX < -498 && charPosX > -560))) ||
                    ((charPosY <  -425 && charPosY > -500) && ((charPosX < -180 && charPosX > -400) || (charPosX < -575 && charPosX > -880))) ||
                    ((charPosY < -515 && charPosY > -585) && ((charPosX > -480) || (charPosX < -575))) ||
                    ((charPosY < -605 && charPosY > -675) && ((charPosX < -100 && charPosX > -320) || (charPosX < -335 && charPosX > -560) || (charPosX < -655 && charPosX > -880))) ||
                    ((charPosY < -695 && charPosY > -765) && ((charPosX < -415 && charPosX > -640) || (charPosX < -815 && charPosX > -880) || (charPosX < -255 && charPosX > -320))) ||
                    ((charPosY < -785 && charPosY > -855) && ((charPosX < -498 && charPosX > -720) || (charPosX < -175 && charPosX > -240))) ||
                    ((charPosY <  -873 && charPosY > -945) && ((charPosX < -178 && charPosX > -400) || (charPosX < -417 && charPosX > -719) || (charPosX < -815 && charPosX > -880) || (charPosX < -94 && charPosX > -163)))) {
                charPosY -= i * .2f;   // horizontal barriers
                bump.play();
            }
        }
        if(input.isKeyDown(Input.KEY_DOWN)) {
            akira = down;
            charPosY -= i * .2f;
            if ((charPosY < -960) ||
                    ((charPosY < -65 && charPosY > -145) && (charPosX > -240 || (charPosX < -340 && charPosX > -480) || (charPosX < -575 && charPosX > -880))) ||
                    ((charPosY < -155 && charPosY > -235) && ((charPosX < -94 && charPosX > -320) || (charPosX < -415 && charPosX > -560) || (charPosX < -815 && charPosX > -880) || (charPosX < -735 && charPosX > -800) || (charPosX < -655 && charPosX > -720))) ||
                    ((charPosY < -245 && charPosY > -315)&& ((charPosX < -180 && charPosX > -320) || (charPosX < -335 && charPosX > -640) || (charPosX < -735) || (charPosX < -94 && charPosX > -163))) ||
                    ((charPosY <  -335 && charPosY > -410) && ((charPosX < -655) || (charPosX < -94 && charPosX > -163) || (charPosX < -255 && charPosX > -320) || (charPosX < -415 && charPosX > -480) || (charPosX < -498 && charPosX > -560))) ||
                    ((charPosY <  -425 && charPosY > -500) && ((charPosX < -180 && charPosX > -400) || (charPosX < -575 && charPosX > -880))) ||
                    ((charPosY < -515 && charPosY > -585) && ((charPosX > -480) || (charPosX < -575))) ||
                    ((charPosY < -605 && charPosY > -675) && ((charPosX < -100 && charPosX > -320) || (charPosX < -335 && charPosX > -560) || (charPosX < -655 && charPosX > -880))) ||
                    ((charPosY < -695 && charPosY > -765) && ((charPosX < -415 && charPosX > -640) || (charPosX < -815 && charPosX > -880) || (charPosX < -255 && charPosX > -320))) ||
                    ((charPosY < -785 && charPosY > -855) && ((charPosX < -498 && charPosX > -720) || (charPosX < -175 && charPosX > -240))) ||
                    ((charPosY <  -873 && charPosY > -945) && ((charPosX < -178 && charPosX > -400) || (charPosX < -415 && charPosX > -719) || (charPosX < -815 && charPosX > -880) || (charPosX < -94 && charPosX > -163)))) {
                charPosY += i * .2f;   // horizontal barriers
                bump.play();
            }
        }
        if(input.isKeyDown(Input.KEY_RIGHT)) {
            akira = right;
            charPosX -= i * .2f;
            if ((charPosX < -890)||
                    ((charPosX < -94 && charPosX > -163) && ((charPosY < -155 && charPosY > -315) || (charPosY < -335 && charPosY > -585) || (charPosY < -605 && charPosY > -945))) ||
                    ((charPosX < -175 && charPosX > -240) && ((charPosY < -245 && charPosY > -500) || (charPosY < -605 && charPosY > -855) || (charPosY <  -873 && charPosY > -945) || (charPosY < -65 && charPosY > -145))) ||
                    ((charPosX < -255 && charPosX > -320) && ((charPosY > -235) || (charPosY < -245 && charPosY > -410) || (charPosY < -515 && charPosY > -675) || (charPosY < -695 && charPosY > -945))) ||
                    ((charPosX < -335 && charPosX > -400) && ((charPosY < -605) || (charPosY < -65 && charPosY > -500))) ||
                    ((charPosX < -415 && charPosX > -480) && ((charPosY < -335 && charPosY > -585) || (charPosY < -695 && charPosY > -945) || (charPosY < -155 && charPosY > -235) || (charPosY < -65 && charPosY > -145))) ||
                    ((charPosX < -498 && charPosX > -560) && ((charPosY > -235) || (charPosY < -335 && charPosY > -675) || (charPosY < -785 && charPosY > -855))) ||
                    ((charPosX < -575 && charPosX > -640) && (charPosY < -65 && charPosY > -765)) ||
                    ((charPosX < -655 && charPosX > -720) && ((charPosY < -155 && charPosY > -410) || (charPosY < -873 && charPosY > -945) || (charPosY < -605 && charPosY > -855))) ||
                    ((charPosX < -735 && charPosX > -800) && ((charPosY < -605) || (charPosY < -155 && charPosY > -315))) ||
                    ((charPosX < -815 && charPosX > -880) && ((charPosY < -695 && charPosY > -945) || (charPosY < -605 && charPosY > -675) || (charPosY < -425 && charPosY > -500) || (charPosY < -65 && charPosY > -235)))) {
                charPosX += i * .2f;   // vertical barriers
                bump.play();
            }
        }
        if(input.isKeyDown(Input.KEY_LEFT)) {
            akira = left;
            charPosX += i * .2f;
            if ((charPosX > -85) ||
                    ((charPosX < -94 && charPosX > -163) && ((charPosY < -155 && charPosY > -315) || (charPosY < -335 && charPosY > -585) || (charPosY < -605 && charPosY > -945))) ||
                    ((charPosX < -175 && charPosX > -240) && ((charPosY < -245 && charPosY > -500) || (charPosY < -605 && charPosY > -855) || (charPosY <  -873 && charPosY > -945) || (charPosY < -65 && charPosY > -145))) ||
                    ((charPosX < -255 && charPosX > -320) && ((charPosY > -235) || (charPosY < -245 && charPosY > -410) || (charPosY < -515 && charPosY > -675) || (charPosY < -695 && charPosY > -945))) ||
                    ((charPosX < -335 && charPosX > -400) && ((charPosY < -605) || (charPosY < -65 && charPosY > -500))) ||
                    ((charPosX < -415 && charPosX > -480) && ((charPosY < -335 && charPosY > -585) || (charPosY < -695 && charPosY > -945) || (charPosY < -155 && charPosY > -235) || (charPosY < -65 && charPosY > -145))) ||
                    ((charPosX < -498 && charPosX > -560) && ((charPosY > -235) || (charPosY < -335 && charPosY > -675) || (charPosY < -785 && charPosY > -855))) ||
                    ((charPosX < -575 && charPosX > -640) && (charPosY < -65 && charPosY > -765)) ||
                    ((charPosX < -655 && charPosX > -720) && ((charPosY < -155 && charPosY > -410) || (charPosY < -873 && charPosY > -945) || (charPosY < -605 && charPosY > -855))) ||
                    ((charPosX < -735 && charPosX > -800) && ((charPosY < -605) || (charPosY < -155 && charPosY > -315))) ||
                    ((charPosX < -815 && charPosX > -880) && ((charPosY < -695 && charPosY > -945) || (charPosY < -605 && charPosY > -675) || (charPosY < -425 && charPosY > -500) || (charPosY < -65 && charPosY > -235)))) {
                charPosX -= i * .2f;   // vertical barriers
                bump.play();
            }
        }
      if (charPosX < -875 && (charPosY < -945)) {   // naana sa stairs up

          stateBasedGame.enterState(6, new FadeOutTransition(), new FadeInTransition());
      }

        if(input.isKeyDown(Input.KEY_ESCAPE)) {
            quit = true;
        }
        if(quit) {
            if(input.isKeyDown(Input.KEY_R)) {
                quit = false;
            }
            if(input.isKeyDown(Input.KEY_M)) {
                stateBasedGame.enterState(0);
            }
            if(input.isKeyDown(Input.KEY_Q)) {
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
        bgm.loop();
        bgm.setVolume(3.0f);
    }

    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame){
        try{
            super.leave(gameContainer, stateBasedGame);
        }catch (SlickException e){
            e.printStackTrace();
        }
        bgm.stop();
    }
}
