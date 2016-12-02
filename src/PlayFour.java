import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.Font;
import java.util.Random;

public class PlayFour extends BasicGameState{

    private Animation Dattack,wake,sleep,die;
    private Animation Guards,Boom, lgate, Entrance, Entry, Oracle, Cryst, Akira;
    private Animation movingUp, movingDown, movingLeft, movingRight, steady, Ogres,Attack;
    private Animation bfairy;
    private Animation incompletePortal,transporter,azul,violeta,madyenta,dilaw;

    private Image iceLevel, hbarrier, vbarrier, tablet,score,inventory;
    private Image blue,red,vi,yel, base;
    private Image Scroll;
    private UnicodeFont uFont;

    private boolean quit = false;
    private int move = 1, message = 0,ormess =0, bumi = 1,enter =0, closed = 1;

    //for question and ans portion
    private int question = 0, wrongans = 0, ans = 0,corans= 0, displayScroll = 0, questionState = 0,ansState= 0,checkState =0,fairy = 0, fairyTalk = 0,qpause = 0;
    private int boomer1 = 0,boomer2 = 0,boomer3 = 0,boomer4 = 0,boomer5 = 0,boomer6 = 0,boomer7 = 0,boomer8 = 0,boomer9 = 0;
    private int qstopper = 0;//gives the chance if the ans is wrong
    private boolean  check = false;//checking ans

    private int putcrystal = 0,crystalclue = 0,crysans = 0,cans = 0,cans1= 0,cans2= 0, cans3= 0,cans4=0,incompleteport = 0; // near the end part. time to put crystals
    private int fblue = 0, fyel = 0, fvi = 0, fred= 0; // if the crystals are found
    private int dblue = 0, dyel =0, dvi = 0, dred =0,wrongcrys = 0,dlights = 0;//displaying the crystals
    private int points = 0;//scoring

    private int[] duration = {200, 300, 200, 200, 300, 200, 200, 300, 200};
    private int[] duration2 = {500, 500, 500, 500, 500};
    private int[] duration3 = {975,575,275};
    private float x = -530;
    private float y = -200;
    private float shiftX = x + 827;
    private float shiftY = y + 377;

    private Music Peace;
    private Sound positive,negative,charge,bumiM;

    public PlayFour(int state){}

    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        //setMusic
        charge = new Sound("res/jingle03.ogg");
        positive = new Sound("res/posa.ogg"); //music & sounds
        negative = new Sound("res/nega.ogg");
        Peace = new Music("res/peace.wav");

        //setFOnt
        Font font = new Font("Serif",Font.BOLD,20);
        uFont = new UnicodeFont(font, font.getSize(),font.isBold(),font.isItalic());
        iceLevel = new Image("res/playfour/ICElevel.png"); // the background and below are the char animation
        hbarrier = new Image("res/playfour/hbaricresized.png");
        tablet = new Image("res/playfour/Picture2.png");
        vbarrier = new Image("res/playfour/vbaricresized.png");

        //questions
        Scroll = new Image("res/playfour/scroll12.png");

        //objects
        blue = new Image("res/playfour/bluecrystal.png");
        red = new Image("res/playfour/redcrystal.png");
        vi = new Image("res/playfour/violetcrystal.png");
        yel = new Image("res/playfour/yellowcrystal.png");
        base = new Image("res/playfour/crystalbase.png");

        //scoreboard
        score = new Image("res/playfour/scoreboard.png");
        inventory = new Image("res/playfour/inventory.png");
        Image[] crystal = {new Image("res/playfour/cry1.png"), new Image("res/playfour/cry2.png"), new Image("res/playfour/cry3.png"), new Image("res/playfour/cry2.png"), new Image("res/playfour/cry1.png")};
        Image[] oracle = {new Image("res/playfour/o1.png"), new Image("res/playfour/o2.png"), new Image("res/playfour/o3.png"), new Image("res/playfour/o4.png"), new Image("res/playfour/o5.png"), new Image("res/playfour/o6.png"), new Image("res/playfour/o7.png"), new Image("res/playfour/o2.png"), new Image("res/playfour/o1.png")};

        //crystal lights
        Image[] light1 = {new Image("res/playfour/azul3.png"), new Image("res/playfour/azul2.png"), new Image("res/playfour/azul.png")};
        Image[] light2 = {new Image("res/playfour/violeta3.png"), new Image("res/playfour/violeta2.png"), new Image("res/playfour/violeta.png")};
        Image[] light3 = {new Image("res/playfour/madyenta3.png"), new Image("res/playfour/madyenta2.png"), new Image("res/playfour/madyenta.png")};
        Image[] light4 = {new Image("res/playfour/dilaw3.png"), new Image("res/playfour/dilaw2.png"), new Image("res/playfour/dilaw.png")};

        //for the main character
        Image[] walkDown = {new Image("res/wd1.png"), new Image("res/wd2.png"), new Image("res/wd3.png"), new Image("res/wd4.png"), new Image("res/wd5.png"), new Image("res/wd6.png"), new Image("res/wd7.png"), new Image("res/wd8.png"), new Image("res/wd9.png")};
        Image[] walkUp = {new Image("res/wu1.png"), new Image("res/wu2.png"), new Image("res/wu3.png"), new Image("res/wu4.png"), new Image("res/wu5.png"), new Image("res/wu6.png"), new Image("res/wu7.png"), new Image("res/wu8.png"), new Image("res/wu9.png")};
        Image[] walkRight = {new Image("res/wr1.png"), new Image("res/wr2.png"), new Image("res/wr3.png"), new Image("res/wr4.png"), new Image("res/wr5.png"), new Image("res/wr6.png"), new Image("res/wr7.png"), new Image("res/wr8.png"), new Image("res/wr9.png")};
        Image[] walkLeft = {new Image("res/wl1.png"), new Image("res/wl2.png"), new Image("res/wl3.png"), new Image("res/wl4.png"), new Image("res/wl5.png"), new Image("res/wl6.png"), new Image("res/wl7.png"), new Image("res/wl8.png"), new Image("res/wl9.png")};
        Image[] stay = {new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s2.png"), new Image("res/s2.png"), new Image("res/s3.png"), new Image("res/s4.png"), new Image("res/s5.png"), new Image("res/s6.png")};
        Image[] attk = {new Image("res/playfour/a1.png"), new Image("res/playfour/a2.png"), new Image("res/playfour/a3.png")};

        //ogre prisoners
        Image[] ogre = {new Image("res/playfour/og1.png"), new Image("res/playfour/og2.png"), new Image("res/playfour/og3.png"), new Image("res/playfour/og4.png"), new Image("res/playfour/og5.png"), new Image("res/playfour/og6.png"), new Image("res/playfour/og7.png"), new Image("res/playfour/og8.png"), new Image("res/playfour/og9.png")};
        Image[] lg = {new Image("res/playfour/vl1.png"), new Image("res/playfour/vl2.png"), new Image("res/playfour/vl1.png"), new Image("res/playfour/vl2.png"), new Image("res/playfour/vl1.png")};

        //Bomberfairies
        Image[] bb =  {new Image("res/playfour/bf1.png"), new Image("res/playfour/bf2.png"), new Image("res/playfour/bf3.png"), new Image("res/playfour/bf4.png"), new Image("res/playfour/bf5.png"), new Image("res/playfour/bf6.png"), new Image("res/playfour/bf7.png"), new Image("res/playfour/bf8.png"), new Image("res/playfour/bf1.png")};

        //use explosion for the vanishing effect.
        //portals
        Image[] trans = {new Image("res/playfour/trans3.png"), new Image("res/playfour/trans4.png"), new Image("res/playfour/trans5.png"), new Image("res/playfour/trans6.png"), new Image("res/playfour/trans7.png"), new Image("res/playfour/trans8.png"), new Image("res/playfour/trans9.png"), new Image("res/playfour/trans8.png"), new Image("res/playfour/trans6.png")};
        Image[] incom = {new Image("res/playfour/2ndp1.png"), new Image("res/playfour/2ndp2.png"), new Image("res/playfour/2ndp3.png"), new Image("res/playfour/2ndp4.png"), new Image("res/playfour/2ndp5.png"), new Image("res/playfour/2ndp4.png"), new Image("res/playfour/2ndp3.png"), new Image("res/playfour/2ndp2.png"), new Image("res/playfour/2ndp1.png")};
        Image[] portal = {new Image("res/playfour/teleporter.png"), new Image("res/playfour/te2.png"), new Image("res/playfour/te3.png"), new Image("res/playfour/teleporter.png"), new Image("res/playfour/te2.png")};
        Image[] circo = {new Image("res/playfour/circle2.png"), new Image("res/playfour/circle2.png"), new Image("res/playfour/circle2.png"), new Image("res/playfour/circle2.png"), new Image("res/playfour/circle2.png")};
        Image[] bom = {new Image("res/playfour/e1.png"), new Image("res/playfour/e2.png"), new Image("res/playfour/e3.png"), new Image("res/playfour/e4.png"), new Image("res/playfour/e5.png"), new Image("res/playfour/e6.png"), new Image("res/playfour/e7.png"), new Image("res/playfour/e8.png"), new Image("res/playfour/e9.png")};
        Image[] dragattack = {new Image("res/playfour/at1.png"), new Image("res/playfour/at2.png"), new Image("res/playfour/at3.png")};

        //Animations
        Dattack = new Animation(dragattack,duration3,true);
        transporter = new Animation(trans,duration,true);
        incompletePortal = new Animation(incom,duration,true);
        Entry = new Animation(circo, duration2, true);
        Entrance = new Animation(portal, duration2, true); //the beginning of the level
        Boom = new Animation(bom,duration, true);
        Oracle = new Animation(oracle, duration, true);
        Cryst = new Animation(crystal, duration2, true);

        //main char
        movingUp = new Animation(walkUp, duration, true);
        movingDown = new Animation(walkDown, duration, true);
        movingLeft = new Animation(walkLeft, duration, true);
        movingRight = new Animation(walkRight, duration, true);
        steady = new Animation(stay, duration, true);
        Attack = new Animation(attk, duration3,true); //Akira's attack animation
        Akira = movingDown;

        //crystals
        azul = new Animation(light1,duration3,true);
        violeta = new Animation(light2,duration3,true);
        madyenta = new Animation(light3,duration3,true);
        dilaw = new Animation(light4,duration3,true);

        //bfairies animation
        bfairy = new Animation(bb, duration, true);

        //ogre
        Ogres = new Animation(ogre, duration, true);
        lgate = new Animation(lg, duration2, true);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        iceLevel.draw(x, y);//g.drawImage(iceCircle,0,0);
        if(fblue == 0) blue.draw(x + 860, y + 800);
        if(fred  == 0) red.draw(x+995,y + 420);
        if(fyel == 0)  yel.draw(x+1500,y+800);
        if(fvi == 0)  vi.draw(x+1275,y+420);
        if(incompleteport ==1)
            incompletePortal.draw(x+1350,y+500);

        //boomfairies
        bfairy.draw(x+720, y+650);

        //  bfairy.draw(x+1000, y + 500);
        //  bfairy.draw(x +1170, y+610);
        bfairy.draw(x+1270,y+530);
        bfairy.draw(x+1400,y+640);

        base.draw(x + 1300,y + 420);
        base.draw(x + 1350,y + 420);
        base.draw(x + 1400,y + 420);
        base.draw(x + 1450,y + 420);
        Oracle.draw(x + 600, y + 420);
        Cryst.draw(x + 647, y + 420);

        //ogre prisoners
        Ogres.draw(x + 580, y + 580);
        Ogres.draw(x + 530, y + 650);
        Ogres.draw(x + 620, y + 650);
        Ogres.draw(x + 850, y + 570);
        Ogres.draw(x + 830, y + 660);
        Ogres.draw(x + 900, y + 570);
        Dattack.draw(x+1100,y+750);
        Dattack.draw(x+1100,y+450); Dattack.draw(x+1077,y+430);
        Dattack.draw(x+1100,y+450);Dattack.draw(x+1100,y+400);
        azul.draw(x+580,y+760); violeta.draw(x+540,y+740); madyenta.draw(x+650,y+700); dilaw.draw(x+850,y+670);
        azul.draw(x+580,y+500); violeta.draw(x+850,y+500); madyenta.draw(x+1000,y+400); dilaw.draw(x+1400,y+750);
        if(closed == 1) {
            lgate.draw(x + 650, y + 657);
            lgate.draw(x + 750, y + 657);
            lgate.draw(x+1040,y+775);
            lgate.draw(x+1040,y+730);
            lgate.draw(x+1170,y+410);
            lgate.draw(x+1170,y+450);
            lgate.draw(x+1170,y+491);
        }

        //portals
        if(bumi == 1){
            Boom.draw(x + 763, y + 270);
            graphics.setColor(Color.black);
            graphics.drawString(" WELCOME TO LEVEL 5 \n THE FLOOR OF FROZEN SPELLS\n (press arrow down)",250, 220); // the character entrance;
        }
        graphics.setColor(Color.white);
        if(bumi == 0) {
            Entry.draw(x + 777, y + 277);
            Entrance.draw(x + 793, y + 292);
            Akira.draw(shiftX, shiftY);}

        //graphics.drawString();("Akira X: " + x + "\nAkira Y: " + y, 400, 120);

        //the lightning and crystals
        if(dlights == 1){
            transporter.draw(x+1150,y+460);
            azul.draw(x+1397,y+400);
            violeta.draw(x+1446,y+400);
            madyenta.draw(x+1302,y+400);
            dilaw.draw(x+1330,y+370);
        }

        //messages...messages...messages...messages...messages
        if(message > 0 || fairy > 0)
            tablet.draw(330,0);
        if(message == 1) {
            graphics.drawString("Go to the oracle.\nShe helps everybody", 350, 35);
        }
        if(message == 2) {
            if(ormess == 1)
                graphics.drawString("Oracle: Greetings Stranger! \n", 350, 25);
            else if(ormess == 2)
                graphics.drawString("Oracle: oh! I know who \nyou really are.", 350, 25);
            else if(ormess == 3)
                graphics.drawString("Akira: Please! Tell me where\nis the next portal!", 350, 25);
            else if(ormess == 4)
                graphics.drawString("Oracle: It is on the\nnortheastern part...But", 350, 25);
            else if(ormess == 5)
                graphics.drawString("Oracle: before the gate opens\nyou need the four crystals", 350, 25);
            else if(ormess == 6)
                graphics.drawString("Akira: Then what happens?", 350, 25);
            else if(ormess == 7)
                graphics.drawString("Oracle: See for yourself\nhuehue Good Luck!", 350, 25);
            graphics.setColor(Color.black);
            graphics.drawString("Press 1 to 7 consecutively",350, 95);
            graphics.setColor(Color.white);
        }
        if(message == 3)
            graphics.drawString("Look! \nthe blue crystal is here!", 350, 35);
        if(message == 5|| message == 6 || message == 7 || message == 8)
            graphics.drawString("Press <enter> to\nget the crystal", 350, 35);
        if(message == 10){
            tablet.draw(330,0);
            graphics.drawString("Unknown: Place the crystals\nin their proper places.", 350, 35);
        }

        //questions of fairies area
        if(fairy == 1){
            if(fairyTalk == 1)
                graphics.drawString("Guard: Halt! This ice guard\nwon't let you pass.",350,25);
            if(fairyTalk == 2)
                graphics.drawString("Akira: I need to go to the\ncrystal bases!",350,25);
            if(fairyTalk == 3)
                graphics.drawString("Guard: Then were all set. \nEveryone gives passwords\n before passing.",350,25);
            if(fairyTalk == 4)
                graphics.drawString("Akira: Uh... What password\n",350,25);
            if(fairyTalk == 5)
                graphics.drawString("Guard: In this floor, the saying\n    -Knowledge is power-\nis applied literally! ",350,25);
            if(fairyTalk == 6)
                graphics.drawString("Guard: Everything can be fixed\nwith just one CORRECT answer!!\n<press Enter>",350,25);
            graphics.setColor(Color.black);
            graphics.drawString("Press 1 to 6 consecutively",350,95);
            graphics.setColor(Color.white);
        }
        if(fairy > 1)
            if(fairyTalk == 1 && boomer3 == 0 && boomer4 == 0 && boomer5 == 0&& boomer8 == 0)
                graphics.drawString("Guard: Halt! This ice guard\nwon't let you pass.",350,25);

        // graphics.drawString();("queststate and quest\n"+questionState+"\n"+question+"\n"+boomer1+"\n"+boomer2,220,25);
        if(questionState == 1 || question >0 ) {
            graphics.setColor(Color.black);
            if(question > 0)
                Scroll.draw(320,120);
            if(question == 26){
                graphics.drawString("What sets up the \npossibility of an avalanche?\n1.Snow drift\n2.Snow pack\n3.Snow slab\n4.Snow sled", 370, 164);
            }
            if(question == 1){
                graphics.drawString("What sets up the \npossibility of an avalanche?\n1.Snow drift\n2.Snow pack\n3.Snow slab\n4.Snow sled",370,164);
            }
            if(question == 2){
                graphics.drawString("Recorded as the greatest\nsnowfall in 1999 (US)\n1.Mt. St.Helens\n2.Mt. McKinley\n3.Mt. Baker\n4.Mt.Moriah",370,164);
            }
            if(question == 3){
                graphics.drawString("Where would you find\nthe snow belt?\n1.Canada\n2.United States\n3.Scandinavia\n4.Arctic region",370,164);
            }
            if(question == 4){
                graphics.drawString("Who manipulates snow\nfor recreational use?\n1.snow man\n2.snow groomer\n3. sled riders\n4.skaters",370,164);
            }
            if(question == 5){
                graphics.drawString("Which of these can\ndetermine how deep snow\nis?\n1.pistol shot\n2.rifle shot\n3.shotgun shot\n4.armalite shot",370,164);
            }
            if(question == 6){
                graphics.drawString("How much snow is\nequivalent to an\ninch of rainfall?\n1.6 inches\n2.13 inches\n3.20 inches\n4.22 inches",370,164);
            }
            if(question == 7){
                graphics.drawString("What shape are \nsnowflakes?\n1.Pentagons\n2.Hexagons\n3.Octagons,\n4.Heptagon",370,164);
            }
            if(question == 8){
                graphics.drawString("What size was the world's\nlargest recorded snowflake?\n1.38 cm\n2.38 inches\n3.38 mm\n4.38 dm",370,164);
            }
            if(question == 9){
                graphics.drawString("When does snow make\nnoise?\n1.when it is freshly fallen\n2.the temperature is low\n3.when it's about to melt\n4.during snow fall ",370,164);
            }
            if(question == 10){
                graphics.drawString("What sort of snow turns\ninto a glacier?\n1.melted snow\n2.partially melted snow\n3.not melting snow\n4.ice bergs",370,164);
            }
            if(question == 11){
                graphics.drawString("How tall was the world's\nlargest snowman?\n1.113ft 7in\n2.122ft 1in\n3.131ft 4in\n4.150ft 25in",370,164);
            }
            if(question == 12){
                graphics.drawString("What was the name of the\n world's second largest\nsnowman?\n1.Angus\n2.Frost\n3.Olaf\n4.Tyrannus",370,164);
            }
            graphics.setColor(Color.white);
        }
        if( putcrystal == 1  ){
            graphics.setColor(Color.black);
            Scroll.draw(390,180);
            graphics.drawString("Press 1 for red\n      2 for yellow\n      3 for blue\n      4 for violet",430,217);
            if(crystalclue == 1)
                graphics.drawString("\n\n\n\n\n...WAR",430,210);
            if(crystalclue == 2)
                graphics.drawString("\n\n\n\n\n...Happiness",430,210);
            if(crystalclue == 3)
                graphics.drawString("\n\n\n\n\n...Peace",430,210);
            if(crystalclue == 4)
                graphics.drawString("\n\n\n\n\nThe last in the bow",430,210);
            graphics.setColor(Color.white);
        }

        if(dlights == 1){
            graphics.setColor(Color.black);
            Scroll.draw(390,180);
            graphics.drawString("Press <enter> to go\nto the next level",430,210);
            graphics.setColor(Color.white);
        }

        if(dred == 1) red.draw(x + 1326, y + 383);
        if(dyel == 1)yel.draw(x + 1376, y + 383);
        if(dblue == 1)blue.draw(x + 1426, y + 383);
        if(dvi == 1)vi.draw(x + 1476, y + 383);

        inventory.draw(-60,230);
        if(fblue == 1 && dblue != 1) blue.draw(25,290);
        if(fred == 1 && dred != 1) red.draw(65,290);
        if(fyel == 1 && dyel != 1) yel.draw(105,290);
        if(fvi == 1 && dvi != 1) vi.draw(150,290);

        //score.draw(-5,10);
        //  graphics.drawString();("Score\n\t "+ points,22,22);

        // quit button
        if (quit) {
            graphics.drawString("Resume(R)", 250, 100);
            graphics.drawString("Main Menu(M)", 250, 150);
            graphics.drawString("Quit(Q)", 250, 200);
            if (!quit) {
                graphics.clear();
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        fairy = 0;
        putcrystal = 0;
        incompleteport = 0;
        Boom.update(i);
        Akira = steady;  // Akira's animation if not moving.

        Input input = gameContainer.getInput();
        if (input.isKeyDown(input.KEY_UP)) {
            bumi = 0;
            Akira = movingUp;
            //  if(questionState == 1){move = 0;}
            if(y < -196 && y> -263 && x < -997 && x > -1200){move = 0;}
            if (y >= -200){ move = 0;}
            if (y >= -522 && y <= -502 && x <= -460 && x >= -631){ move = 0; }
            if (y >= -538 && y <= -518 && x <= -735 && x >= -883){ move = 0; }
            if (y >= -372 && y <= -352 && x <= -738 && x >= -902){ move = 0; }
            if (y >= -506 && y <= -470 && x <= -965 && x >= -1007){move = 0; }
            if (y > -460 && y < -400 && x < -967 && x > -1200 && boomer8 != 1){  move = 0;}
            if (y <= -300 && y >= -349 && x <= -910 && x >= -970 && boomer5 != 1 ){ move = 0;}//if (y <= -330 && y >= -345 && x <= -630 && x >= -730 && boomer3 != 1 ){ move = 0;}
            else if(move == 1)
                y += i * .15f;
            move = 1;
        }
        if (input.isKeyDown(input.KEY_DOWN)) {
            bumi = 0;
            Akira = movingDown;
            if (y <= -607){ move = 0;}
            if (y <= -307 && y >= -340 && x <= -450 && x >= -595){ move = 0;}
            if (y <= -316 && y >= -336 && x <= -762 && x >= -902){ move = 0;}
            if (y <= -481 && y >= -490 && x <= -739 && x >= -910){ move = 0;}
            if (y <= -500 && y >= -520 && x <= -920 && x >= -970 && boomer6 != 1 ){ move = 0;}
            if(x < -390 && x > -452 && y < -415 && y > -425 && boomer1 != 1  ){  move = 0;}
            else if(move == 1)
                y -= i * .15f;
            move = 1;
        }
        if (input.isKeyDown(input.KEY_LEFT)) {
            bumi = 0;
            Akira = movingLeft;
            if (x >= -405) { move = 0;}
            if (x >= -640 && x <= -630 && y <= -190 && y >= -518) { move = 0;}
            if (x >= -770 && x <= -750 && y <= -190 && y >= -325) { move = 0;}
            if (x >= -928 && x <= -900 && y <= -485 && y >= -620) { move = 0;}
            if (x >= -1006 && x <= -996 && y <= -190 && y >= -477) { move = 0;}
            if (x >= -925 && x <= -900 && y <= -327 && y >= -361) { move = 0;}
            if (x >= -900 && x <= -850 && y <= -190 && y >= -320) { move = 0;}
            else if(move == 1)
                x += i * .15f;
            move = 1;
        }
        if (input.isKeyDown(input.KEY_RIGHT)) {
            bumi = 0;
            Akira = movingRight;
            if (x <= -1180){ move = 0;}
            if (x <= -591 && x >= -611 && y < -190 && y > -517){ move = 0;}
            if (x <= -438 && x >= -458 && y < -315 && y > -521){ move = 0;}
            if (x <= -875 && x >= -895 && y < -532) { move = 0;}
            if (x <= -717 && x >= -737 && y < -480 && y > -517){move=0;}
            if (x <= -723 && x >= -743 && y < -190 && y > -357){move=0;}
            if (x <= -965 && x >= -1003 && y < -190 && y > -505){move=0;}
            if (x <= -745 && x >= -800 && y < -525 && y > -612){move=0;}
            //if (y < -360 && y >= -500 && x <= -815 && x >= -830 && boomer4 != 1 ){ move = 0;}
            else if(move == 1)
                x -= i * .15f;
            move = 1;
        }
        else if(x<-490 && x > -560 && y <-560 && y >-650){message = 5;}
        else if(x<-630&& x >-730 && y <-190&& y>-270){message = 6;}
        else if(x<-900&& x >-970 && y <-190&& y>-270){message = 7;}
        else if(x<-1110&& x >-1300 && y <-546&& y>-620){message = 8;}
        else if(x < -463 && x > -591 && y < - 190 && y > -307){ message = 1; }  //first message.
        else if(x <= -400 && x >= -430 && y < - 200 && y > -267){message = 2;}  //oracle
        else if(x < -390 && x > -452 && y <-410 && y > -425 && boomer1 != 1){fairy = 1;}
        // else if(y > -365 && y < -345 && x < -630 && x > -730 && boomer3 != 1){fairy = 3;}
        //else if(y > -470 && y < -400 && x < -815 && x > -830 && boomer4 != 1){ fairy = 4;}
        else if(y > -380 && y < -320 && x < -916 && x > -971 && boomer5 != 1){fairy = 5;}
        else if(y > -570 && y < -470 && x < -916 && x > -971 && boomer6 != 1){fairy = 6;}
        else if(y > -470 && y < -400 && x < -967 && x > -1200 && boomer8 != 1){fairy = 8;}
        else if(x <-400 && x > -440 && y < -480 && y > -512){message = 3;boomer1 = 0;}//crystal found//dragon spawn
        else if(x <-997&&x>-1200&&y<-255&&y>-275){putcrystal = 1;message = 10;incompleteport = 1;}
        else message = 0;

        //oracle's message;
        if(fairy == 3 && ans == 2)
            boomer3 = 1;
        else boomer3 = 0;
        if(message == 2){
            if (input.isKeyDown(input.KEY_1))
                ormess = 1;
            if (input.isKeyDown(input.KEY_2))
                ormess = 2;
            if (input.isKeyDown(input.KEY_3))
                ormess = 3;
            if (input.isKeyDown(input.KEY_4))
                ormess = 4;
            if (input.isKeyDown(input.KEY_5))
                ormess = 5;
            if (input.isKeyDown(input.KEY_6))
                ormess = 6;
            if (input.isKeyDown(input.KEY_7))
                ormess = 7;
            if(!(ormess >= 1 && ormess <= 7))
                ormess = 1;
        }
        if(message == 5){
            if(input.isKeyDown(input.KEY_ENTER))
                fblue = 1;
        }
        if(message == 6){
            if(input.isKeyDown(input.KEY_ENTER))
                fred = 1;
        }
        if(message == 7){
            if(input.isKeyDown(input.KEY_ENTER))
                fvi = 1;
        }
        if(message == 8){
            if(input.isKeyDown(input.KEY_ENTER))
                fyel = 1;
        }
        if(fairy == 1){
            if(input.isKeyDown(input.KEY_1))
                fairyTalk = 1;
            if(input.isKeyDown(input.KEY_2))
                fairyTalk = 2;
            if(input.isKeyDown(input.KEY_3))
                fairyTalk = 3;
            if(input.isKeyDown(input.KEY_4))
                fairyTalk = 4;
            if(input.isKeyDown(input.KEY_5))
                fairyTalk = 5;
            if(input.isKeyDown(input.KEY_6)) {fairyTalk = 6;}
            if(input.isKeyDown(input.KEY_ENTER)){questionState = 1;question = 1;}
            if(!(fairyTalk >= 1&& fairyTalk <=6))
                fairyTalk = 1;
        }
        if(fairy > 1){questionState = 1;
            fairyTalk = 1;
            if(fairy == 3){question = 3;}
            if(fairy == 4){question = 4;}
            if(fairy == 5){question = 5;}
            if(fairy == 6){question = 6;}
            if(fairy == 7){question = 7;}
            if(fairy == 8){question = 8;}
        }
        if(questionState == 1){
            ansState = 1;
            //qpause = 0;
        }

        //questions' answers
        if(question == 1){corans =  3;}
        if(question == 2){corans =  3;}
        if(question == 3){corans =  2;}
        if(question == 4){corans =  2;}
        if(question == 5){corans =  1;}
        if(question == 6){corans =  2;}
        if(question == 7){corans =  2;}
        if(question == 8){corans =  1;}
        if(question == 9){corans =  3;}
        if(question == 10){corans =  3;}
        if(question == 11){corans =  2;}
        if(question == 12){corans =  1;}
        if(question == 13){corans =  1;}
        if(question == 14){corans =  2;}
        if(question == 15){corans =  3;}
        if(question == 16){corans =  2;}
        if(question == 17){corans =  2;}

//player answers
        if(ansState == 1){qstopper = 0;
            if(input.isKeyDown(input.KEY_1)) {
                ans = 1; if(ans == corans){points = points + 5;}else points = points - 5;
            }
            if(input.isKeyDown(input.KEY_2)) {
                ans = 2; if(ans == corans){points = points + 5;}else points = points - 5;
            }
            if(input.isKeyDown(input.KEY_3)) {
                ans = 3; if(ans == corans){points = points + 5;}else points = points - 5;
            }
            if(input.isKeyDown(input.KEY_4)) {
                ans = 4; if(ans == corans){points = points + 5;}else points = points - 5;
            }
        }
        else ans = 0;
        if(ans != 0) {
            check = checker(ans, corans);
            if (check == true) {
                if(fairy == 1){boomer1 = 1;fairy = 0;}
                if(fairy == 2){boomer2 = 1;}
                if(fairy == 3){boomer3 = 1;}
                if(fairy == 4){boomer4 = 1;}
                if(fairy == 5){boomer5 = 1;}
                if(fairy == 6){boomer6 = 1;}
                if(fairy == 7){boomer7 = 1;}
                if(fairy == 8){boomer8 = 1;}
                if(fairy == 9){boomer9 = 1;}
                questionState = 0;question = 0;
            }
        }

        //the last part
        if(putcrystal == 1 && fblue == 1 && fred == 1 && fyel == 1 && fvi ==1 ){
            displayScroll = 1;
            if(x<-1000&&x>-1040&&y<-255&&y>-275){
                crystalclue = 1; crysans = 1;
                if (input.isKeyDown(input.KEY_1)) {
                    cans1 = 1; wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_2)) {
                    cans1 = 2; wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_3)) {
                    cans1 = 3; wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_4)) {
                    cans1 = 4; wrongcrys = 0;
                }
                if(cans1 == 1){
                    dred = 1;
                }
                if(cans1 > 0 && cans1 != 1){
                    wrongcrys = 1;  }
            }
            if(x<-1040&&x>-1092&&y<-255&&y>-275){
                crystalclue = 2; crysans = 2;
                if (input.isKeyDown(input.KEY_1)) {
                    cans2 = 1; wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_2)) {
                    cans2 = 2; wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_3)) {
                    cans2 = 3;wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_4)) {
                    cans2 = 4;wrongcrys = 0;
                }
                if(cans2 == 2){
                    positive.play();
                    dyel  = 1;
                }
                if(cans1 > 0 && cans2 != 2)
                    wrongcrys = 1;
            }
            if(x<-1092&&x>-1143&&y<-255&&y>-275){
                crystalclue = 3; crysans = 3;
                if (input.isKeyDown(input.KEY_1)) {
                    cans3 = 1;wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_2)) {
                    cans3 = 2;wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_3)) {
                    cans3 = 3;wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_4)) {
                    cans3 = 4;wrongcrys = 0;
                }
                if(cans3 == 3){
                    positive.play();
                    dblue = 1;
                }
                if(cans1 > 0 && cans3 != 3)
                    wrongcrys = 1;
            }
            if(x<-1143&&x>-1180&&y<-255&&y>-275){
                crystalclue = 4; crysans = 4;
                if (input.isKeyDown(input.KEY_1)) {
                    cans4 = 1;wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_2)) {
                    cans4 = 2;wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_3)) {
                    cans4 = 3;wrongcrys = 0;
                }
                if (input.isKeyDown(input.KEY_4)) {
                    cans4 = 4;wrongcrys = 0;
                }
                if(cans4 == 4){
                    positive.play();
                    dvi = 1;
                }
                if(cans1 > 0 && cans4 != 4)
                    wrongcrys = 1;
            }
        }

        //displays the crystals if correct and gives a message if wrong.
        if(dred  == 1 && dyel == 1 && dblue == 1 && dvi == 1){
            Peace.stop();
            charge.play();
            dlights = 1;
            if(input.isKeyDown(input.KEY_ENTER))
                charge.stop();
                stateBasedGame.enterState(7, new FadeOutTransition(), new FadeInTransition());
        }

        //the player quits
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

    public boolean checker(int ans, int corans){
        return ans == corans;
    }
    public int questionGenerator(){
        Random rand = new Random();
        return rand.nextInt(25)+1;
    }

    public void enter(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        Peace.loop();
        Peace.setVolume(7.0f);
    }
    public void leave(GameContainer gc, StateBasedGame sbg){
        try{
            super.leave(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        Peace.stop();
    }
}
