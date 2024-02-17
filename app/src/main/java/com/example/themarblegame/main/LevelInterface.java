package com.example.themarblegame.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.themarblegame.interfaces.GameDesigner;

public class LevelInterface extends PlayerBank implements GameDesigner {

    HomeInterface homeInterface = new HomeInterface();
    public static float POSITION = 0;
    public static float SCROLL = 0;
    public static PhysicsTimer levelPhysicsTimer = new PhysicsTimer();

    public static boolean ignoreScroll = false;
    public boolean runningSecondDarkScreen = false;

    private static final DrawText drawBonus1 = new DrawText();
    private static final DrawText drawBonus2 = new DrawText();
    private static final DrawText drawBonus3 = new DrawText();
    private static final DrawText drawCoinsEarned = new DrawText();

    private static final String TAG = "";
    private static String power1 = "Balloons: ";
    private static String power2 = "Rain Dance: ";
    private static String power3 = "Gorilla Arms: ";
    private static String tempPwr = "";

    private static String power1Num;
    private static String power2Num;
    private static String power3Num;

    private String pause = "Pause";
    private final DrawText drawText = new DrawText();

    public static boolean gameOver1 = false;
    public static boolean gameOver2 = false;
    public static int gameOverState = 0;
    private final Physics[] gameOverPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    public static boolean levelComplete = false;
    public static int levelCompleteState = 0;
    private final Physics[] levelCompletePhysics = {
            new Physics(),
            new Physics(),
            new Physics(),

            //stuff inside the main menu
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    public static boolean pauseMenuSelected = false;
    public static int pauseMenuState = 0;
    private final Physics[] pauseMenuPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    public static boolean powerMenuSelected = false;
    public static int powerMenuState = 0;
    private final Physics[] powerMenuPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    public static boolean isStartMenu = false;
    public static int startMenuState = 0;
    private final Physics[] startMenuPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    public static boolean isOutOfLives = false;
    public static int outOfLivesMenuState = 0;
    private final Physics[] outOfLivesPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    public static boolean isAreYouSure = false;
    public static int areYouSureMenuState = 0;
    private final Physics[] areYouSurePhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    private static final Rect pauseMenu = new Rect(0, (Constants.SCREEN_HEIGHT - (3 * Scale.UNIT)), (int) (Constants.SCREEN_WIDTH/2 - Scale.UNIT*.25), (Constants.SCREEN_HEIGHT + (6 * Scale.UNIT)));
    private static final Rect powerMenu = new Rect((int) (Constants.SCREEN_WIDTH/2 + Scale.UNIT*.25), (Constants.SCREEN_HEIGHT - (3 * Scale.UNIT)), Constants.SCREEN_WIDTH, (Constants.SCREEN_HEIGHT + (6 * Scale.UNIT)));

    private static final Rect pauseButton = new Rect((int) (Scale.UNIT*.25), (int) (Constants.SCREEN_HEIGHT - (Scale.UNIT*2.5)), (int) (Constants.SCREEN_WIDTH/2 - Scale.UNIT*.5), (int) (Constants.SCREEN_HEIGHT - Scale.UNIT*0.5));
    private static final Rect restartButton = new Rect((int) (Scale.UNIT*.25), (Constants.SCREEN_HEIGHT), (int) (Constants.SCREEN_WIDTH/2 - Scale.UNIT*.5), (Constants.SCREEN_HEIGHT + Scale.UNIT*2));
    private static final Rect quitButton = new Rect((int) (Scale.UNIT*.25), (int) (Constants.SCREEN_HEIGHT + (Scale.UNIT*2.5)), (int) (Constants.SCREEN_WIDTH/2 - Scale.UNIT*.5), (int) (Constants.SCREEN_HEIGHT + 4.5));

    private static final Rect power1Button = new Rect((int) (Constants.SCREEN_WIDTH/2 + Scale.UNIT*.5), (int) (Constants.SCREEN_HEIGHT - (Scale.UNIT*2.5)), (int) (Constants.SCREEN_WIDTH - Scale.UNIT*.25), (int) (Constants.SCREEN_HEIGHT - Scale.UNIT*0.5));
    private static final Rect power2Button = new Rect((int) (Constants.SCREEN_WIDTH/2 + Scale.UNIT*.5), (Constants.SCREEN_HEIGHT), (int) (Constants.SCREEN_WIDTH - Scale.UNIT*.25), (Constants.SCREEN_HEIGHT + Scale.UNIT*2));
    private static final Rect power3Button = new Rect((int) (Constants.SCREEN_WIDTH/2 + Scale.UNIT*.5), (int) (Constants.SCREEN_HEIGHT + (Scale.UNIT*2.5)), (int) (Constants.SCREEN_WIDTH - Scale.UNIT*.25), (int) (Constants.SCREEN_HEIGHT + 4.5));

    private static final Rect levelCompleteMenu = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6)+Scale.UNIT*14);
    //this one will be deleted soon
//    private static final Rect levelCompleteDisplay = new Rect((-Scale.UNIT*10), (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT/2), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*11);
    private static final Rect levelCompleteContinue = new Rect((-Scale.UNIT*10), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11.5), 0, (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*13.5));
    //stuff inside the level complete menu
    private static final Rect levelCompleteScoreIcon = new Rect((int) (-Scale.UNIT*2.5), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT * 1.5)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*2.75));
    private static final Rect levelCompleteScore = new Rect((int) (-Scale.UNIT*2.25), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT * 1.5)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*2.75));
    private static final Rect levelCompleteHealthIcon = new Rect((int) (-Scale.UNIT*2.5), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*1.5)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*2.75));
    private static final Rect levelCompleteHealth = new Rect((-Scale.UNIT*2), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*1.5)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*2.75));

    private static final Rect levelCompleteItemsCollectedIcon = new Rect((int) (-Scale.UNIT*2.5), ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*3)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4.25));
    private static final Rect levelCompleteItemsCollected = new Rect((int) (-Scale.UNIT*2.25), ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*3)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4.25));
    private static final Rect levelCompleteTimeIcon = new Rect((int) (-Scale.UNIT*2.5), ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*3)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4.25));
    private static final Rect levelCompleteTime = new Rect((-Scale.UNIT*2), ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*3)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4.25));

    private static final Rect levelCompleteBonus1Icon = new Rect((int) (-Scale.UNIT*2.75), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*4.75)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*6));
    private static final Rect levelCompleteBonus1 = new Rect((int) (-Scale.UNIT*3), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*4.75)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*6));
    private static final Rect levelCompleteBonus2Icon = new Rect((int) (-Scale.UNIT*2.75), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*6.25)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*7.5));
    private static final Rect levelCompleteBonus2 = new Rect((int) (-Scale.UNIT*3), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*6.25)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*7.5));
    private static final Rect levelCompleteBonus3Icon = new Rect((int) (-Scale.UNIT*2.75), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*7.75)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*9));
    private static final Rect levelCompleteBonus3 = new Rect((int) (-Scale.UNIT*3), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*7.75)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*9));
    private static final Rect levelCompleteCoinsEarnedIcon = new Rect((int) (-Scale.UNIT*2.75), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*9.5)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*11));
    private static final Rect levelCompleteCoinsEarned = new Rect((int) (-Scale.UNIT*3), (int) ((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*9.5)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*11));
    private static final Rect levelCompleteTitleCard = new Rect(-Scale.UNIT*6, (Constants.SCREEN_HEIGHT/6) - (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT);



    private static final Rect gameOverMenu = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6)+Scale.UNIT*14);
//    private static final Rect gameOverMenuDisplay = new Rect((-Scale.UNIT*10), (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT/2), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*11);
    private static final Rect gameOverTitleCard = new Rect(-Scale.UNIT*6, (Constants.SCREEN_HEIGHT/6) - (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT);
    private static final Rect gameOverRestart = new Rect((int) (-Scale.UNIT*5.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11.5), 0, (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*13.5));
    private static final Rect gameOverQuit = new Rect((int) (-Scale.UNIT*3.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11.5), 0, (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*13.5));

    private static final Rect startMenu = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6)+Scale.UNIT*14);
    private static final Rect startMenuDisplay = new Rect(Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT/2), Scale.UNIT*12, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*11);
    private static final Rect startMenuStart = new Rect(Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11.5), (int) (Scale.UNIT*6.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*13.5));
    private static final Rect startMenuQuit = new Rect((int) (Scale.UNIT*7.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11.5), Scale.UNIT*12, (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*13.5));

    private static final Rect outOfLivesMenu = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6)+Scale.UNIT*14);
    private static final Rect outOfLivesDisplay = new Rect(-Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT/2), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*11);
    private static final Rect outOfLivesWatchAd = new Rect((int) (-Scale.UNIT*5.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11.5), 0, (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*13.5));
    private static final Rect outOfLivesMenuQuit = new Rect((int) (-Scale.UNIT*3.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11.5), 0, (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*13.5));

    private static final Rect areYouSureMenu = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6)+Scale.UNIT*14);
    private static final Rect areYouSureDisplay = new Rect(-Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT/2), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*11);
    private static final Rect areYouSureConfirm = new Rect((int) (-Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11.5), 0, (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*13.5));
    private static final Rect areYouSureCancel = new Rect((int) (-Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11.5), 0, (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*13.5));

    //ARE YOU SURE GETTERS
    public Rect getAreYouSureConfirm() {return areYouSureConfirm;}
    public Rect getAreYouSureCancel() {return areYouSureCancel;}

    //START MENU GETTERS
    public Rect getStartMenuStart() {return startMenuStart;}
    public Rect getStartMenuQuit() {return startMenuQuit;}

    //RIGHT FOOTER BUTTON GETTERS
    public Rect getPower1Button() {return power1Button;}
    public Rect getPower2Button() {return power2Button;}
    public Rect getPower3Button() {return power3Button;}

    //LEFT FOOTER BUTTON GETTERS
    public Rect getPauseButton() {return pauseButton;}
    public Rect getRestartButton() {return restartButton;}
    public Rect getQuitButton() {return quitButton;}

    //GAME OVER GETTERS
    public Rect getGameOverQuit() {return gameOverQuit;}
    public Rect getGameOverRestart() {return gameOverRestart;}

    //LEVEL COMPLETE GETTER
    public Rect getLevelCompleteContinue() {return levelCompleteContinue;}

    public static void resetMenus() {
        pauseMenuSelected = false;
        powerMenuSelected = false;
        LevelInterface.gameOver1 = false;
        LevelInterface.levelComplete = false;

        pauseMenuState = 1;
        powerMenuState = 1;
        LevelInterface.gameOverState = 1;
        LevelInterface.levelCompleteState = 1;
    }

    public void pauseStatus() {
        if (pauseMenuSelected) {
            pause = "Resume";
        }
        if (!pauseMenuSelected) {
            pause = "Pause";
        }
    }

    public void cycleString(int selectedPower) {
        tempPwr = power1;
        switch (selectedPower) {
            case 2:
                power1 = power2;
                power2 = tempPwr;
                break;
            case 3:
                power1 = power3;
                power3 = tempPwr;
                break;
        }
        cycleBoolean(power1);
    }

    public void cycleNum() {
        switch (power1) {
            case "Balloons: ":
                power1Num = ""+getBalloons();
                break;
            case "Rain Dance: ":
                power1Num = ""+getRainDance();
                break;
            case "Gorilla Arms: ":
                power1Num = ""+ getArms();
                break;
        }

        switch (power2) {
            case "Balloons: ":
                power2Num = ""+getBalloons();
                break;
            case "Rain Dance: ":
                power2Num = ""+getRainDance();
                break;
            case "Gorilla Arms: ":
                power2Num = ""+ getArms();
                break;
        }

        switch (power3) {
            case "Balloons: ":
                power3Num = ""+getBalloons();
                break;
            case "Rain Dance: ":
                power3Num = ""+getRainDance();
                break;
            case "Gorilla Arms: ":
                power3Num = ""+ getArms();
                break;
        }
    }

    public void cycleBoolean(String selectedPower) {
        switch (selectedPower) {
            case "Balloons: ":
                setBalloonSelected(true);
                setRainSelected(false);
                setArmsSelected(false);
                break;
            case "Rain Dance: ":
                setRainSelected(true);
                setBalloonSelected(false);
                setArmsSelected(false);
                break;
            case "Gorilla Arms: ":
                setArmsSelected(true);
                setBalloonSelected(false);
                setRainSelected(false);
                break;
        }
    }

    boolean counter = true;
    public void areYouSureMenuUp() {

        //quit is pressed
        if (areYouSureMenuState == 1) {
            Log.e(TAG, "areYouSureMenuUp: RUNNING");
            if (isAreYouSure) {
                areYouSurePhysics[0].swipeLeftToMiddle(areYouSureMenu, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                areYouSurePhysics[1].swipeLeftToMiddle(areYouSureDisplay, Scale.UNIT*10, Scale.UNIT*12);
                areYouSurePhysics[2].swipeLeftToMiddle(areYouSureConfirm, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT * 12));
                areYouSurePhysics[3].swipeLeftToMiddle(areYouSureCancel, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*6.75));
            } else if (HomeInterface.isIsLoading1()){
                if (counter) {
                    HomeInterface.loadingScreen1State = 2;
                    counter = false;
                    if (getLives() != 0)
                        setLives(-1);
                    if (getLives() == 0)
                        HomeInterface.setIsOutOfLives(true);
                }
                areYouSurePhysics[0].swipeMiddleToLeft(areYouSureMenu, Scale.UNIT*11);
                areYouSurePhysics[1].swipeMiddleToLeft(areYouSureDisplay, Scale.UNIT*10, Scale.UNIT/15);
                areYouSurePhysics[2].swipeMiddleToLeft(areYouSureConfirm, (int) (Scale.UNIT*4.75),Scale.UNIT/15);
                areYouSurePhysics[3].swipeMiddleToLeft(areYouSureCancel, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
            } else {
                areYouSurePhysics[0].swipeMiddleToLeft(areYouSureMenu, Scale.UNIT*11);
                areYouSurePhysics[1].swipeMiddleToLeft(areYouSureDisplay, Scale.UNIT*10, Scale.UNIT/15);
                areYouSurePhysics[2].swipeMiddleToLeft(areYouSureConfirm, (int) (Scale.UNIT*4.75),Scale.UNIT/15);
                areYouSurePhysics[3].swipeMiddleToLeft(areYouSureCancel, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
            }
            for (int i = 0; i < areYouSurePhysics.length; i++) {
                if (areYouSurePhysics[i].isMoving)
                    break;
                else if (!areYouSurePhysics[i].isMoving && i == areYouSurePhysics.length-1) {
                    areYouSureMenuState = 0;
                    counter = true;
                }
            }
        }

        //restart is pressed
        else if (areYouSureMenuState == 2) {
            Log.e(TAG, "areYouSureMenuUp: RUNNING");
            if (isAreYouSure) {
                areYouSurePhysics[0].swipeLeftToMiddle(areYouSureMenu, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                areYouSurePhysics[1].swipeLeftToMiddle(areYouSureDisplay, Scale.UNIT*10, Scale.UNIT*12);
                areYouSurePhysics[2].swipeLeftToMiddle(areYouSureConfirm, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT * 12));
                areYouSurePhysics[3].swipeLeftToMiddle(areYouSureCancel, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*6.75));
            } else if (HomeInterface.isIsLoading1()) {
                if (counter) {
                    setLives(-1);
                    HomeInterface.loadingScreen1State = 3;
                    counter = false;
                    if (getLives() == 0)
                        HomeInterface.setIsOutOfLives(true);
                }
                areYouSurePhysics[0].swipeMiddleToLeft(areYouSureMenu, Scale.UNIT*11);
                areYouSurePhysics[1].swipeMiddleToLeft(areYouSureDisplay, Scale.UNIT*10, Scale.UNIT/15);
                areYouSurePhysics[2].swipeMiddleToLeft(areYouSureConfirm, (int) (Scale.UNIT*4.75),Scale.UNIT/15);
                areYouSurePhysics[3].swipeMiddleToLeft(areYouSureCancel, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
            } else {
                areYouSurePhysics[0].swipeMiddleToLeft(areYouSureMenu, Scale.UNIT*11);
                areYouSurePhysics[1].swipeMiddleToLeft(areYouSureDisplay, Scale.UNIT*10, Scale.UNIT/15);
                areYouSurePhysics[2].swipeMiddleToLeft(areYouSureConfirm, (int) (Scale.UNIT*4.75),Scale.UNIT/15);
                areYouSurePhysics[3].swipeMiddleToLeft(areYouSureCancel, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
            }
            for (int i = 0; i < areYouSurePhysics.length; i++) {
                if (areYouSurePhysics[i].isMoving)
                    break;
                else if (!areYouSurePhysics[i].isMoving && i == areYouSurePhysics.length-1) {
                    areYouSureMenuState = 0;
                    counter = true;
                }

            }
        }
    }

    public void startMenuUp() {
        if (startMenuState == 1) {
            Log.e(TAG, "startMenuUp: RUNNING");
            if (isStartMenu) {
                ignoreScroll = true;
                startMenuPhysics[0].swipeLeftToMiddle(startMenu, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                startMenuPhysics[1].swipeLeftToMiddle(startMenuDisplay, Scale.UNIT*10, Scale.UNIT*12);
                startMenuPhysics[2].swipeLeftToMiddle(startMenuStart, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*12));
                startMenuPhysics[3].swipeLeftToMiddle(startMenuQuit,  (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*6.75));
            } else {
                startMenuPhysics[0].swipeMiddleToLeft(startMenu, Scale.UNIT*11);
                startMenuPhysics[1].swipeMiddleToLeft(startMenuDisplay, Scale.UNIT*10, Scale.UNIT/15);
                startMenuPhysics[2].swipeMiddleToLeft(startMenuStart, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                startMenuPhysics[3].swipeMiddleToLeft(startMenuQuit, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
            }
            for (int i = 0; i < startMenuPhysics.length; i++) {
                if (startMenuPhysics[i].isMoving)
                    break;
                else if (!startMenuPhysics[i].isMoving && i == startMenuPhysics.length-1)
                    startMenuState = 0;
            }
        }
    }

    boolean counterGameOver = false;
    public void gameOverMenuUp() {
        if (gameOverState == 1) {
            Log.e(TAG, "gameOverMenuUp: RUNNING");
            if (gameOver1 && !gameOver2) {
                if (!counterGameOver) {
                    InternalClock.pause();
                    setSecondsRemaining();
                    setDisplayCoinsEarned();
                    setCoinsEarned();
                    addCoin(getCoinsEarned());
                    resetCoinsEarned();
                    ignoreScroll = true;
                    counterGameOver = true;
                }

                gameOverPhysics[0].swipeLeftToMiddle(gameOverMenu, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                gameOverPhysics[1].swipeLeftToMiddle(gameOverTitleCard, Scale.UNIT*6, Scale.UNIT*8,Scale.UNIT/30f);
                gameOverPhysics[2].swipeLeftToMiddle(gameOverRestart, (int) (Scale.UNIT*5.75), (int) (Scale.UNIT*12));
                gameOverPhysics[3].swipeLeftToMiddle(gameOverQuit, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*5.75));

                gameOverPhysics[4].swipeLeftToMiddle(levelCompleteScoreIcon, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*4.5));
                gameOverPhysics[5].swipeLeftToMiddle(levelCompleteScore, (int) (Scale.UNIT*2.25), (int) (Scale.UNIT*7));
                gameOverPhysics[6].swipeLeftToMiddle(levelCompleteHealthIcon, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*9.75));
                gameOverPhysics[7].swipeLeftToMiddle(levelCompleteHealth, (int) (Scale.UNIT*2), Scale.UNIT*12);

                gameOverPhysics[8].swipeLeftToMiddle(levelCompleteItemsCollectedIcon, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*4.5));
                gameOverPhysics[9].swipeLeftToMiddle(levelCompleteItemsCollected, (int) (Scale.UNIT*2.25), (int) (Scale.UNIT*7));
                gameOverPhysics[10].swipeLeftToMiddle(levelCompleteTimeIcon, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*9.75));
                gameOverPhysics[11].swipeLeftToMiddle(levelCompleteTime, Scale.UNIT*2, Scale.UNIT*12);

                gameOverPhysics[12].swipeLeftToMiddle(levelCompleteBonus1Icon, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*5.75));
                gameOverPhysics[13].swipeLeftToMiddle(levelCompleteBonus1, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*9.75));
                gameOverPhysics[14].swipeLeftToMiddle(levelCompleteBonus2Icon, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*5.75));
                gameOverPhysics[15].swipeLeftToMiddle(levelCompleteBonus2, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*9.75));
                gameOverPhysics[16].swipeLeftToMiddle(levelCompleteBonus3Icon, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*5.75));
                gameOverPhysics[17].swipeLeftToMiddle(levelCompleteBonus3, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*9.75));
                gameOverPhysics[18].swipeLeftToMiddle(levelCompleteCoinsEarnedIcon, (int) (Scale.UNIT*5), (int) (Scale.UNIT*7));
                gameOverPhysics[19].swipeLeftToMiddle(levelCompleteCoinsEarned, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*12));

            } else if (gameOver2) {

                gameOverPhysics[0].swipeMiddleToLeft(gameOverMenu, Scale.UNIT*11);
                gameOverPhysics[1].swipeMiddleToLeft(gameOverTitleCard, Scale.UNIT*6, Scale.UNIT/30);
                gameOverPhysics[2].swipeMiddleToLeft(gameOverRestart, (int) (Scale.UNIT*5.75), Scale.UNIT/15);
                gameOverPhysics[3].swipeMiddleToLeft(gameOverQuit, (int) (Scale.UNIT*3.75), Scale.UNIT/15);

                gameOverPhysics[4].swipeMiddleToLeft(levelCompleteScoreIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                gameOverPhysics[5].swipeMiddleToLeft(levelCompleteScore, (int) (Scale.UNIT*2.25), Scale.UNIT/15);
                gameOverPhysics[6].swipeMiddleToLeft(levelCompleteHealthIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                gameOverPhysics[7].swipeMiddleToLeft(levelCompleteHealth, (int) (Scale.UNIT*2), Scale.UNIT/15);

                gameOverPhysics[8].swipeMiddleToLeft(levelCompleteItemsCollectedIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                gameOverPhysics[9].swipeMiddleToLeft(levelCompleteItemsCollected, (int) (Scale.UNIT*2.25), Scale.UNIT/15);
                gameOverPhysics[10].swipeMiddleToLeft(levelCompleteTimeIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                gameOverPhysics[11].swipeMiddleToLeft(levelCompleteTime, Scale.UNIT*2, Scale.UNIT/15);

                gameOverPhysics[12].swipeMiddleToLeft(levelCompleteBonus1Icon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                gameOverPhysics[13].swipeMiddleToLeft(levelCompleteBonus1, Scale.UNIT*3, Scale.UNIT/15);
                gameOverPhysics[14].swipeMiddleToLeft(levelCompleteBonus2Icon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                gameOverPhysics[15].swipeMiddleToLeft(levelCompleteBonus2, Scale.UNIT*3, Scale.UNIT/15);
                gameOverPhysics[16].swipeMiddleToLeft(levelCompleteBonus3Icon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                gameOverPhysics[17].swipeMiddleToLeft(levelCompleteBonus3, Scale.UNIT*3, Scale.UNIT/15);
                gameOverPhysics[18].swipeMiddleToLeft(levelCompleteCoinsEarnedIcon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                gameOverPhysics[19].swipeMiddleToLeft(levelCompleteCoinsEarned, Scale.UNIT*4, Scale.UNIT/15);
            } else {

                gameOverPhysics[0].swipeMiddleToLeft(gameOverMenu, Scale.UNIT*11);
                gameOverPhysics[1].swipeMiddleToLeft(gameOverTitleCard, Scale.UNIT*6, Scale.UNIT/30);
                gameOverPhysics[2].swipeMiddleToLeft(gameOverRestart, (int) (Scale.UNIT*5.75), Scale.UNIT/15);
                gameOverPhysics[3].swipeMiddleToLeft(gameOverQuit, (int) (Scale.UNIT*3.75), Scale.UNIT/15);

                gameOverPhysics[4].swipeMiddleToLeft(levelCompleteScoreIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                gameOverPhysics[5].swipeMiddleToLeft(levelCompleteScore, (int) (Scale.UNIT*2.25), Scale.UNIT/15);
                gameOverPhysics[6].swipeMiddleToLeft(levelCompleteHealthIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                gameOverPhysics[7].swipeMiddleToLeft(levelCompleteHealth, (int) (Scale.UNIT*2), Scale.UNIT/15);

                gameOverPhysics[8].swipeMiddleToLeft(levelCompleteItemsCollectedIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                gameOverPhysics[9].swipeMiddleToLeft(levelCompleteItemsCollected, (int) (Scale.UNIT*2.25), Scale.UNIT/15);
                gameOverPhysics[10].swipeMiddleToLeft(levelCompleteTimeIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                gameOverPhysics[11].swipeMiddleToLeft(levelCompleteTime, Scale.UNIT*2, Scale.UNIT/15);

                gameOverPhysics[12].swipeMiddleToLeft(levelCompleteBonus1Icon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                gameOverPhysics[13].swipeMiddleToLeft(levelCompleteBonus1, Scale.UNIT*3, Scale.UNIT/15);
                gameOverPhysics[14].swipeMiddleToLeft(levelCompleteBonus2Icon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                gameOverPhysics[15].swipeMiddleToLeft(levelCompleteBonus2, Scale.UNIT*3, Scale.UNIT/15);
                gameOverPhysics[16].swipeMiddleToLeft(levelCompleteBonus3Icon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                gameOverPhysics[17].swipeMiddleToLeft(levelCompleteBonus3, Scale.UNIT*3, Scale.UNIT/15);
                gameOverPhysics[18].swipeMiddleToLeft(levelCompleteCoinsEarnedIcon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                gameOverPhysics[19].swipeMiddleToLeft(levelCompleteCoinsEarned, Scale.UNIT*4, Scale.UNIT/15);
            }
            for (int i = 0; i < gameOverPhysics.length; i++) {
                if (gameOverPhysics[i].isMoving)
                    break;
                else if (!gameOverPhysics[i].isMoving && i == gameOverPhysics.length-1) {
                    gameOverState = 0;
                    counterGameOver = false;
                }
            }
        }
    }

    boolean counterLevelComplete = false;
    public void levelCompleteMenuUp() {
        if (levelCompleteState == 1) {
            Log.e(TAG, "levelCompleteMenuUp: RUNNING");
            if (levelComplete) {
                if (!counterLevelComplete) {
                    InternalClock.pause();
                    setSecondsRemaining();
                    setBonus1();
                    setBonus2();
                    setBonus3();
                    setDisplayCoinsEarned();
                    setCoinsEarned();
                    addCoin(getCoinsEarned());
                    resetCoinsEarned();
                    // change methods from coins collected to check all star requirements
                    PlayerProgression.setCoinsCollected(getCoinsCollected());
                    ignoreScroll = true;
                    counterLevelComplete = true;
                }
                levelCompletePhysics[0].swipeLeftToMiddle(levelCompleteTitleCard, Scale.UNIT*6, Scale.UNIT*8,Scale.UNIT/30f);
                levelCompletePhysics[1].swipeLeftToMiddle(levelCompleteMenu, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                levelCompletePhysics[2].swipeLeftToMiddle(levelCompleteScoreIcon, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*4.5));
                levelCompletePhysics[3].swipeLeftToMiddle(levelCompleteScore, (int) (Scale.UNIT*2.25), (int) (Scale.UNIT*7));
                levelCompletePhysics[4].swipeLeftToMiddle(levelCompleteHealthIcon, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*9.75));
                levelCompletePhysics[5].swipeLeftToMiddle(levelCompleteHealth, (int) (Scale.UNIT*2), Scale.UNIT*12);
                levelCompletePhysics[6].swipeLeftToMiddle(levelCompleteContinue, Scale.UNIT*10, Scale.UNIT*12);

                levelCompletePhysics[7].swipeLeftToMiddle(levelCompleteItemsCollectedIcon, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*4.5));
                levelCompletePhysics[8].swipeLeftToMiddle(levelCompleteItemsCollected, (int) (Scale.UNIT*2.25), (int) (Scale.UNIT*7));
                levelCompletePhysics[9].swipeLeftToMiddle(levelCompleteTimeIcon, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*9.75));
                levelCompletePhysics[10].swipeLeftToMiddle(levelCompleteTime, Scale.UNIT*2, Scale.UNIT*12);

                levelCompletePhysics[11].swipeLeftToMiddle(levelCompleteBonus1Icon, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*5.75));
                levelCompletePhysics[12].swipeLeftToMiddle(levelCompleteBonus1, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*9.75));
                levelCompletePhysics[13].swipeLeftToMiddle(levelCompleteBonus2Icon, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*5.75));
                levelCompletePhysics[14].swipeLeftToMiddle(levelCompleteBonus2, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*9.75));
                levelCompletePhysics[15].swipeLeftToMiddle(levelCompleteBonus3Icon, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*5.75));
                levelCompletePhysics[16].swipeLeftToMiddle(levelCompleteBonus3, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*9.75));
                levelCompletePhysics[17].swipeLeftToMiddle(levelCompleteCoinsEarnedIcon, (int) (Scale.UNIT*5), (int) (Scale.UNIT*7));
                levelCompletePhysics[18].swipeLeftToMiddle(levelCompleteCoinsEarned, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*12));

            } else {
                levelCompletePhysics[0].swipeMiddleToLeft(levelCompleteTitleCard, Scale.UNIT*6, Scale.UNIT/30);
                levelCompletePhysics[1].swipeMiddleToLeft(levelCompleteMenu, Scale.UNIT*11);
                levelCompletePhysics[2].swipeMiddleToLeft(levelCompleteScoreIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                levelCompletePhysics[3].swipeMiddleToLeft(levelCompleteScore, (int) (Scale.UNIT*2.25), Scale.UNIT/15);
                levelCompletePhysics[4].swipeMiddleToLeft(levelCompleteHealthIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                levelCompletePhysics[5].swipeMiddleToLeft(levelCompleteHealth, (int) (Scale.UNIT*2), Scale.UNIT/15);
                levelCompletePhysics[6].swipeMiddleToLeft(levelCompleteContinue, Scale.UNIT*10, Scale.UNIT/15);

                levelCompletePhysics[7].swipeMiddleToLeft(levelCompleteItemsCollectedIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                levelCompletePhysics[8].swipeMiddleToLeft(levelCompleteItemsCollected, (int) (Scale.UNIT*2.25), Scale.UNIT/15);
                levelCompletePhysics[9].swipeMiddleToLeft(levelCompleteTimeIcon, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                levelCompletePhysics[10].swipeMiddleToLeft(levelCompleteTime, Scale.UNIT*2, Scale.UNIT/15);

                levelCompletePhysics[11].swipeMiddleToLeft(levelCompleteBonus1Icon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                levelCompletePhysics[12].swipeMiddleToLeft(levelCompleteBonus1, Scale.UNIT*3, Scale.UNIT/15);
                levelCompletePhysics[13].swipeMiddleToLeft(levelCompleteBonus2Icon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                levelCompletePhysics[14].swipeMiddleToLeft(levelCompleteBonus2, Scale.UNIT*3, Scale.UNIT/15);
                levelCompletePhysics[15].swipeMiddleToLeft(levelCompleteBonus3Icon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                levelCompletePhysics[16].swipeMiddleToLeft(levelCompleteBonus3, Scale.UNIT*3, Scale.UNIT/15);
                levelCompletePhysics[17].swipeMiddleToLeft(levelCompleteCoinsEarnedIcon, (int) (Scale.UNIT*2.75), Scale.UNIT/15);
                levelCompletePhysics[18].swipeMiddleToLeft(levelCompleteCoinsEarned, Scale.UNIT*4, Scale.UNIT/15);
            }
            for (int i = 0; i < levelCompletePhysics.length; i++) {
                if (levelCompletePhysics[i].isMoving)
                    break;
                else if (!levelCompletePhysics[i].isMoving && i == levelCompletePhysics.length-1) {
                    levelCompleteState = 0;
                    counterLevelComplete = false;
                }
            }
        }
    }

    public void pauseMenuUp() {
        if (pauseMenuState == 1) {
            Log.e(TAG, "PauseMenuUp: RUNNING");
            if (pauseMenuSelected) {
                pauseMenuPhysics[0].swipeUp(pauseMenu, Scale.UNIT*9, Constants.SCREEN_HEIGHT - Scale.UNIT*8);
                pauseMenuPhysics[1].swipeUp(pauseButton, Scale.UNIT*2, (int) (Constants.SCREEN_HEIGHT - Scale.UNIT*7.5));
                pauseMenuPhysics[2].swipeUp(restartButton, Scale.UNIT*2, (Constants.SCREEN_HEIGHT - Scale.UNIT*5));
                pauseMenuPhysics[3].swipeUp(quitButton, Scale.UNIT*2, (int) (Constants.SCREEN_HEIGHT - Scale.UNIT*2.5));
            } else {
                pauseMenuPhysics[0].swipeDown(pauseMenu, Scale.UNIT*9, (Constants.SCREEN_HEIGHT + Scale.UNIT*6));
                pauseMenuPhysics[1].swipeDown(pauseButton, Scale.UNIT*2, (int) (Constants.SCREEN_HEIGHT - Scale.UNIT*.5));
                pauseMenuPhysics[2].swipeDown(restartButton, Scale.UNIT*2, (Constants.SCREEN_HEIGHT + Scale.UNIT*2));
                pauseMenuPhysics[3].swipeDown(quitButton, Scale.UNIT*2, (int) (Constants.SCREEN_HEIGHT + Scale.UNIT*4.5));
            }
            for (int i = 0; i < pauseMenuPhysics.length; i++) {
                if (pauseMenuPhysics[i].isMoving)
                    break;
                else if (!pauseMenuPhysics[i].isMoving && i == pauseMenuPhysics.length-1)
                    pauseMenuState = 0;
            }
        }
    }

    public void powerMenuUp() {
        if (powerMenuState == 1) {
            Log.e(TAG, "powerMenuUp: RUNNING");
            if (powerMenuSelected) {
                powerMenuPhysics[0].swipeUp(powerMenu, Scale.UNIT*9, Constants.SCREEN_HEIGHT - Scale.UNIT*8);
                powerMenuPhysics[1].swipeUp(power1Button, Scale.UNIT*2, (int) (Constants.SCREEN_HEIGHT - Scale.UNIT*7.5));
                powerMenuPhysics[2].swipeUp(power2Button, Scale.UNIT*2, (Constants.SCREEN_HEIGHT - Scale.UNIT*5));
                powerMenuPhysics[3].swipeUp(power3Button, Scale.UNIT*2, (int) (Constants.SCREEN_HEIGHT - Scale.UNIT*2.5));
            } else {
                powerMenuPhysics[0].swipeDown(powerMenu, Scale.UNIT*9, (Constants.SCREEN_HEIGHT + Scale.UNIT*6));
                powerMenuPhysics[1].swipeDown(power1Button, Scale.UNIT*2, (int) (Constants.SCREEN_HEIGHT - Scale.UNIT*.5));
                powerMenuPhysics[2].swipeDown(power2Button, Scale.UNIT*2, (Constants.SCREEN_HEIGHT + Scale.UNIT*2));
                powerMenuPhysics[3].swipeDown(power3Button, Scale.UNIT*2, (int) (Constants.SCREEN_HEIGHT + Scale.UNIT*4.5));
            }
            for (int i = 0; i < powerMenuPhysics.length; i++) {
                if (powerMenuPhysics[i].isMoving)
                    break;
                else if (!powerMenuPhysics[i].isMoving && i == powerMenuPhysics.length-1)
                    powerMenuState = 0;
            }
        }
    }

    Paint backgroundPaint = new Paint();
    Paint iconPaint = new Paint();
    Paint textPaint = new Paint();
    Paint buttonPaint = new Paint();
    Rect rect = new Rect();

    @Override
    public void draw(Canvas canvas) {

        backgroundPaint.setColor(Color.GREEN);
        textPaint.setColor(Color.BLACK);
        iconPaint.setColor(Color.YELLOW);
        buttonPaint.setColor(Color.YELLOW);

        canvas.drawRect(pauseMenu, backgroundPaint); //bottom left footer
        canvas.drawRect(powerMenu, backgroundPaint);

        canvas.drawRect(pauseButton, buttonPaint);
        canvas.drawRect(restartButton, buttonPaint);
        canvas.drawRect(quitButton, buttonPaint);

        canvas.drawRect(power1Button, buttonPaint);
        canvas.drawRect(power2Button, buttonPaint);
        canvas.drawRect(power3Button, buttonPaint);

        //onMain header text
        textPaint.setTextSize(Constants.SCREEN_WIDTH/25f);
        textPaint.setTextAlign(Paint.Align.LEFT);

        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Score: " + getScore(), Constants.SCREEN_WIDTH / 20f, (Constants.SCREEN_HEIGHT / 20f) + (Constants.SCREEN_HEIGHT / 20f), textPaint);
        textPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Timer: " + (InternalClock.getSecondsRemaining()), Constants.SCREEN_WIDTH - (Constants.SCREEN_WIDTH / 20f), (Constants.SCREEN_HEIGHT / 20f) + (Constants.SCREEN_HEIGHT / 20f), textPaint);

        drawText.drawInCenterOfRect(canvas, textPaint, rect, pauseButton, pause);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, restartButton, "Restart");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, quitButton, "Quit");

        cycleNum();

        //have power num appear in separate corner rect like for my stuff in home interface.
        drawText.drawInCenterOfRect(canvas, textPaint, rect, power1Button, power1 + power1Num);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, power2Button, power2 + power2Num);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, power3Button, power3 + power3Num);


        if (!runningSecondDarkScreen)
            homeInterface.drawDarkBackground(canvas);
        homeInterface.drawOnLevelHeader(canvas);
        if (isAreYouSure || isStartMenu || areYouSureMenuState == 1 || areYouSureMenuState == 2 || startMenuState == 1 || gameOver1 || gameOver2 || levelComplete) {
            runningSecondDarkScreen = true;
            homeInterface.drawDarkBackground(canvas);
        } else {
            runningSecondDarkScreen = false;
        }

        //GAME OVER
        canvas.drawRect(gameOverMenu, backgroundPaint);
//        canvas.drawRect(gameOverMenuDisplay, buttonPaint);
        canvas.drawRect(gameOverTitleCard, buttonPaint);
        canvas.drawRect(gameOverRestart, buttonPaint);
        canvas.drawRect(gameOverQuit, buttonPaint);
//        drawText.drawInCenterOfRect(canvas, textPaint, rect, gameOverMenuDisplay, "Game Over!");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, gameOverRestart, "Restart");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, gameOverQuit, "Quit");

        //LEVEL COMPLETE
        canvas.drawRect(levelCompleteMenu, backgroundPaint);
        canvas.drawRect(levelCompleteContinue, buttonPaint);
        canvas.drawRect(levelCompleteTitleCard, buttonPaint);

        canvas.drawRect(levelCompleteHealth, buttonPaint);
        canvas.drawRect(levelCompleteHealthIcon, buttonPaint);
        canvas.drawRect(levelCompleteScore, buttonPaint);
        canvas.drawRect(levelCompleteScoreIcon, buttonPaint);
        canvas.drawRect(levelCompleteItemsCollectedIcon, buttonPaint);
        canvas.drawRect(levelCompleteItemsCollected, buttonPaint);
        canvas.drawRect(levelCompleteTimeIcon, buttonPaint);
        canvas.drawRect(levelCompleteTime, buttonPaint);
        canvas.drawRect(levelCompleteBonus1Icon, buttonPaint);
        canvas.drawRect(levelCompleteBonus1, buttonPaint);
        canvas.drawRect(levelCompleteBonus2Icon, buttonPaint);
        canvas.drawRect(levelCompleteBonus2, buttonPaint);
        canvas.drawRect(levelCompleteBonus3Icon, buttonPaint);
        canvas.drawRect(levelCompleteBonus3, buttonPaint);
        canvas.drawRect(levelCompleteCoinsEarnedIcon, buttonPaint);
        canvas.drawRect(levelCompleteCoinsEarned, buttonPaint);

        drawText.drawInCenterOfRect(canvas, homeInterface.headerTextPaint, rect, levelCompleteTitleCard, "Complete!");
        drawText.drawInCenterOfRect(canvas, homeInterface.headerTextPaint, rect, gameOverTitleCard, "Game Over!");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, levelCompleteScoreIcon, "Score:");
        drawText.drawAlignLeftInRect(canvas, textPaint, rect, levelCompleteScore, "" + getScore(), Scale.UNIT/2);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, levelCompleteHealthIcon, "HP:");
        drawText.drawAlignLeftInRect(canvas, textPaint, rect, levelCompleteHealth, "" + getHealth(), Scale.UNIT/2);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, levelCompleteItemsCollectedIcon, "IC:");
        drawText.drawAlignLeftInRect(canvas, textPaint, rect, levelCompleteItemsCollected, getPercentOfItemsCollected()+"%", Scale.UNIT/2);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, levelCompleteTimeIcon, "SR:");
        drawText.drawAlignLeftInRect(canvas, textPaint, rect, levelCompleteTime, ""+InternalClock.getSecondsRemaining(), Scale.UNIT/2);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, levelCompleteBonus1Icon, "Bonus I:");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, levelCompleteBonus2Icon, "Bonus II:");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, levelCompleteBonus3Icon, "Bonus III:"); //the below stuff will be calculated in player assets and assigned to var before being reset to 0
        drawText.drawAlignLeftInRect(canvas, textPaint, rect, levelCompleteBonus1, ""+drawBonus1.increaseNum(0, (int) getBonus1()), Scale.UNIT/2);
        drawText.drawAlignLeftInRect(canvas, textPaint, rect, levelCompleteBonus2, ""+drawBonus2.increaseNum(0, (int) getBonus2()), Scale.UNIT/2);
        drawText.drawAlignLeftInRect(canvas, textPaint, rect, levelCompleteBonus3, ""+drawBonus3.increaseNum(0, (int) getBonus3()), Scale.UNIT/2); //this will be items collected in percent, so it is like seconds left bonus
        drawText.drawInCenterOfRect(canvas, textPaint, rect, levelCompleteCoinsEarnedIcon, "Coins Earned:");
        drawText.drawAlignLeftInRect(canvas, textPaint, rect, levelCompleteCoinsEarned, ""+drawCoinsEarned.increaseNum(0, getDisplayCoinsEarned()), Scale.UNIT/2);

        drawText.drawInCenterOfRect(canvas, textPaint, rect, levelCompleteContinue, "Continue");

        //START MENU
        canvas.drawRect(startMenu, backgroundPaint);
        canvas.drawRect(startMenuDisplay, buttonPaint);
        canvas.drawRect(startMenuStart, buttonPaint);
        canvas.drawRect(startMenuQuit, buttonPaint);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, startMenuDisplay, "Display");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, startMenuStart, "Start");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, startMenuQuit, "Go Back");

        //OUT OF LIVES MENU
        canvas.drawRect(outOfLivesMenu, backgroundPaint);
        canvas.drawRect(outOfLivesDisplay, buttonPaint);
        canvas.drawRect(outOfLivesWatchAd, buttonPaint);
        canvas.drawRect(outOfLivesMenuQuit, buttonPaint);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, outOfLivesDisplay, "Display");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, outOfLivesWatchAd, "Watch Ad");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, outOfLivesMenuQuit, "Quit");

        //ARE YOU SURE MENU
        canvas.drawRect(areYouSureMenu, backgroundPaint);
        canvas.drawRect(areYouSureDisplay, buttonPaint);
        canvas.drawRect(areYouSureConfirm, buttonPaint);
        canvas.drawRect(areYouSureCancel, buttonPaint);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, areYouSureDisplay, "Display");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, areYouSureConfirm, "Confirm");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, areYouSureCancel, "Cancel");


        homeInterface.drawOnLevelMenus(canvas); // all relevant menus from home interface
        if (!(isStartMenu || startMenuState == 1)) {
            homeInterface.drawExitMenu(canvas);
        }
        homeInterface.drawLoadingScreen(canvas);

        //gameOver and levelComplete
        if (gameOver1){
//            PlayerAssets.resetScore();
            InternalClock.pause();
            addCoin(getCoinsEarned());
            resetCoinsEarned();
        }
        if (levelComplete) {
//            InternalClock.pause();
//            setSecondsRemaining();
//            addCoin(getCoinsEarned());
//            resetCoinsEarned();
//            PlayerProgression.setCoinsCollected(getCoinsCollected());
        }
    }

    public static void resetDrawables() {
        drawBonus1.resetIncreaseNum();
        drawBonus2.resetIncreaseNum();
        drawBonus3.resetIncreaseNum();
        drawCoinsEarned.resetIncreaseNum();
    }

    private boolean ignoreAutoScroll = false;
    public boolean counterOnLevelDecelerate = false;
    public int decelerationOnLevelState = 0;
    public void setOnLevelDecelerationDirection() {
        if (!counterOnLevelDecelerate) {
            if (levelPhysicsTimer.velocity > 0)
                decelerationOnLevelState = 1;
            else
                decelerationOnLevelState = -1;
        }
        counterOnLevelDecelerate = true;
    }

    @Override
    public void update() {
        if (pauseMenuSelected && !ignoreScroll) {


            if (!ignoreAutoScroll) {

                SCROLL += levelPhysicsTimer.velocity;
                if (levelPhysicsTimer.velocity != 0) {
                    setOnLevelDecelerationDirection();
                    switch (decelerationOnLevelState) {
                        case 1:
                            if (levelPhysicsTimer.velocity > 0)
                                levelPhysicsTimer.velocity -= (((float) Constants.SCREEN_WIDTH / 1000f));
                            else
                                levelPhysicsTimer.velocity = 0;
                            break;
                        case -1:
                            if (levelPhysicsTimer.velocity < 0)
                                levelPhysicsTimer.velocity += (((float) Constants.SCREEN_WIDTH / 1000f));
                            else
                                levelPhysicsTimer.velocity = 0;
                            break;
                    }
                } else
                    counterOnLevelDecelerate = false;

                if (SCROLL <  -Scale.UNIT*5) {
                    POSITION = Scale.UNIT*5;
                    SCROLL = -Scale.UNIT*5;
                    ignoreAutoScroll = true;
                }

                if (SCROLL > -(Constants.SCREEN_HEIGHT/3f - Scale.UNIT*85)) {
                    POSITION = (Constants.SCREEN_HEIGHT/3f  - Scale.UNIT*85);
                    SCROLL = -(Constants.SCREEN_HEIGHT/3f - Scale.UNIT*85);
                    ignoreAutoScroll = true;
                }
            } else
                ignoreAutoScroll = false;

        }

//        outOfLivesMenuUp();
        areYouSureMenuUp();
        startMenuUp();
        gameOverMenuUp();
        levelCompleteMenuUp();
        pauseStatus();
        pauseMenuUp();
        powerMenuUp();
        homeInterface.updateOnLevelMenus();
    }
}
