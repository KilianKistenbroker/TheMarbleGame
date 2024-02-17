package com.example.themarblegame.main;

import android.util.Log;
import android.view.MotionEvent;

import com.example.themarblegame.managers.PlatformManager;
import com.example.themarblegame.obstacles.Platform;

public class LevelTouchControls extends LevelInterface {
    private static final String TAG = "msg";
    public static boolean down = false;
    private float y1, y2, x1, x2, x0, y0;
    static final int MIN_DISTANCE = Scale.UNIT/2;
    private JumpPower jumpPower = new JumpPower();
    private float yPress;
//    public static float POSITION = 0;
//    public static float SCROLL = 0;
    public float SCALE_ANIM_1 = 0;
    public float SCALE_ANIM_2 = 0;

    private static boolean counterStartTimer = false;
    private static boolean moved = false;
    private boolean ignoreScrollDown1 = false;
    private boolean ignoreScrollDown2 = false;
    private boolean ignoreScrollUp1 = false;
    private boolean ignoreScrollUp2 = false;

    //this is for areYouSureMenuState
    private int onLevelSavedState = 0;
//    public static int POST_SCROLL;

    public void setAllStatesToOne() {
        HomeInterface.mailMenuState = 1;
        HomeInterface.settingsMenuState = 1;
        HomeInterface.coinsMenuState = 1;
        HomeInterface.livesMenuState = 1;
        HomeInterface.mailSlot1State = 1;
        HomeInterface.mailSlot2State = 1;
        HomeInterface.mailSlot3State = 1;
        HomeInterface.mailSlot4State = 1;
        HomeInterface.settingsHowToPlayState = 1;
        HomeInterface.settingsLanguageState = 1;
        areYouSureMenuState = 1;
    }

    public void pauseScreen(Player player) {
        InternalClock.pause();
        pauseMenuSelected = true;
        pauseMenuState = 1;
        jumpPower.cancel();
        ignoreScroll = true;
        if (player.playerPhysics.getDirection() == -1)
            player.setCurrentSkin(getMyCurrentSkinBank().get(0));
        else
            player.setCurrentSkin(getMyCurrentSkinBank().get(3));
    }

    public void resumeScreen() {
        InternalClock.resume();
        ignoreScroll = false;
    }

    public void onTouchEvent(Player player, MotionEvent event) {
        int action = event.getAction();
        if (!HomeInterface.isIsLoading1() && HomeInterface.loadingScreen1State == 0) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:

                    x1 = (float) (event.getX()/Constants.DIVISOR);
                    y1 = (float) (event.getY()/Constants.DIVISOR);

                    if (pauseMenuSelected && !ignoreScroll) {
                        levelPhysicsTimer.velocity = 0;

                        if (moved) {
                            POSITION = -SCROLL;
                            moved = false;
                        }
                    }

                    //run timer here

                    this.yPress = (float) (event.getY()/Constants.DIVISOR);
                    if (!pauseMenuSelected && !powerMenuSelected && !gameOver1 && !levelComplete && !isStartMenu){
                        down = true;
                        if (player.playerPhysics.getDirection() == -1)
                            player.setCurrentSkin(getMyCurrentSkinBank().get(1));
                        else
                            player.setCurrentSkin(getMyCurrentSkinBank().get(2));
                        jumpPower = new JumpPower();
                        jumpPower.start();
                    }

                    HomeTouchControls.checkOnLevelDimButtons(x1, y1);
                    break;

                case MotionEvent.ACTION_MOVE:

                    //because x1 and y1 is used for something else
                    x0 = (float) (event.getX()/Constants.DIVISOR);
                    y0 = (float) (event.getY()/Constants.DIVISOR);

                    //implement movement here
                    if (pauseMenuSelected && !ignoreScroll) {
                        moved = true;
                        if (!levelPhysicsTimer.timer1Running && !levelPhysicsTimer.timer2Running) {
//                    PhysicsTimer.startTimer1();
                            levelPhysicsTimer.timer1Running = true;
                            levelPhysicsTimer.timer2Running = true;
                        }

                        if (!ignoreScrollDown1 && !ignoreScrollUp1) {
                            SCROLL = (float) (event.getY()/Constants.DIVISOR - player.playerPhysics.getY() + (player.playerPhysics.getY() - yPress) - POSITION);
                            if (SCROLL <  -Scale.UNIT*5) {
                                POSITION = Scale.UNIT*5;
                                SCROLL = -Scale.UNIT*5;
                                ignoreScrollDown1 = true;
                            }

                            //height of finish line is 85
                            if (SCROLL > -(Constants.SCREEN_HEIGHT/3f - Scale.UNIT*85)) {
                                POSITION = (Constants.SCREEN_HEIGHT/3f  - Scale.UNIT*85);
                                SCROLL = -(Constants.SCREEN_HEIGHT/3f - Scale.UNIT*85);
                                ignoreScrollUp1 = true;
                            }

                        } else {
                            yPress = (float) (event.getY()/Constants.DIVISOR);
                            ignoreScrollDown1 = false;
                            ignoreScrollDown2 = true;

                            ignoreScrollUp1 = false;
                            ignoreScrollUp2 = true;
                        }
                    }

                    HomeTouchControls.checkOnLevelDimButtons(x0, y0);
                    break;

                case MotionEvent.ACTION_UP:
                    HomeInterface.shortPointer.setEmpty();

                    x2 = (float) (event.getX()/Constants.DIVISOR);
                    y2 = (float) (event.getY()/Constants.DIVISOR);

                    //IF LEVEL IS IN START MENU MODE
                    if (isStartMenu) {

                        //START LEVEL PRESSED
                        if (x2 < getStartMenuStart().right && x2 > getStartMenuStart().left && y2 < getStartMenuStart().bottom && y2 > getStartMenuStart().top) {
                            InternalClock.resume();
                            ignoreScroll = false;
                            isStartMenu = false;
                            startMenuState = 1;

                            //QUIT LEVEL PRESSED IN START MENU
                        } else if (x2 < getStartMenuQuit().right && x2 > getStartMenuQuit().left && y2 < getStartMenuQuit().bottom && y2 > getStartMenuQuit().top) {
                            isStartMenu = false;
                            startMenuState = 1;
                            ignoreScroll = false;
                            HomeInterface.setIsLoading1(true);
                            HomeInterface.loadingScreen1State = 2;
                        }

                        //NORMAL GAME MODE
                    } else if (!gameOver1 && !levelComplete) {
                        down = false;
                        float deltaY = y1 - y2;
                        float deltaX = x2 - x1;

                        if (pauseMenuSelected && !ignoreScroll) {
                            if (moved) {
                                levelPhysicsTimer.calcVelocity(2);
                            }
                            levelPhysicsTimer.cancel();

                            if (!ignoreScrollDown2 && !ignoreScrollUp2)
                                POSITION =  (POSITION + deltaY);
                            else {
                                POSITION = -SCROLL;
                                ignoreScrollDown2 = false;
                                ignoreScrollUp2 = false;
                            }
                        }

                        //this order will ignore all other button options if true
                        if (isAreYouSure) {
                            //extra exit menu here
                            if (x2 < HomeInterface.getExitMenu().right && x2 > HomeInterface.getExitMenu().left && y2 < HomeInterface.getExitMenu().bottom && y2 > HomeInterface.getExitMenu().top) {
                                isAreYouSure = false;
                                areYouSureMenuState = onLevelSavedState;
                                onLevelSavedState = 0;
                                ignoreScroll = false;
                                break;
                            }

                            //CONFIRM ARE YOU SURE
                            else if (y2 > getAreYouSureConfirm().top && y2 < getAreYouSureConfirm().bottom && x2 < getAreYouSureConfirm().right && x2 > getAreYouSureConfirm().left) {
                                if (onLevelSavedState == 2)
                                    InternalClock.resume();
                                isAreYouSure = false;
                                areYouSureMenuState = onLevelSavedState;
                                onLevelSavedState = 0;
                                HomeInterface.setIsLoading1(true);
                                ignoreScroll = false;
                                break;

                                //CANCEL ARE YOU SURE
                            } else if (y2 > getAreYouSureCancel().top && y2 < getAreYouSureCancel().bottom && x2 < getAreYouSureCancel().right && x2 > getAreYouSureCancel().left) {
                                isAreYouSure = false;
                                areYouSureMenuState = onLevelSavedState;
                                onLevelSavedState = 0;
                                ignoreScroll = false;
                                break;
                            }
                        }

                        //get main header buttons
                        else if (y2 > HomeInterface.getMainHeader().top && y2 < HomeInterface.getMainHeader().bottom) {

                            //settings selected
                            if (y2 > HomeInterface.getSettingsIcon().top && y2 < HomeInterface.getSettingsIcon().bottom && x2 > HomeInterface.getSettingsIcon().left && x2 < HomeInterface.getSettingsIcon().right) {
                                Log.e(TAG, "onTouchEvent: settings pressed");
                                if (!HomeInterface.isSettingsSelected()) {
                                    pauseScreen(player);
                                    HomeInterface.setCoinsSelected(false);
                                    HomeInterface.setLivesSelected(false);
                                    HomeInterface.setMailSelected(false);
                                    HomeInterface.setMailSlot1Selected(false);
                                    HomeInterface.setMailSlot2Selected(false);
                                    HomeInterface.setMailSlot3Selected(false);
                                    HomeInterface.setMailSlot4Selected(false);
                                    HomeInterface.setSettingsHowToPlaySelected(false);
                                    HomeInterface.setSettingsLanguageSelected(false);
                                    HomeInterface.setSettingsSelected(true);
                                } else {
                                    resumeScreen();
                                    HomeInterface.setSettingsSelected(false);
                                    HomeInterface.setSettingsHowToPlaySelected(false);
                                    HomeInterface.setSettingsLanguageSelected(false);
                                    HomeInterface.longPointer.setEmpty();
                                }
                                setAllStatesToOne();
                                break;

                                //mail pressed
                            } else if (y2 > HomeInterface.getMailIcon().top && y2 < HomeInterface.getMailIcon().bottom && x2 > HomeInterface.getMailIcon().left && x2 < HomeInterface.getMailIcon().right) {
                                Log.e(TAG, "onTouchEvent: mail pressed");
                                if (!HomeInterface.isMailSelected()) {
                                    pauseScreen(player);
                                    HomeInterface.setCoinsSelected(false);
                                    HomeInterface.setLivesSelected(false);
                                    HomeInterface.setSettingsSelected(false);
                                    HomeInterface.setSettingsHowToPlaySelected(false);
                                    HomeInterface.setSettingsLanguageSelected(false);
                                    HomeInterface.setMailSlot1Selected(false);
                                    HomeInterface.setMailSlot2Selected(false);
                                    HomeInterface.setMailSlot3Selected(false);
                                    HomeInterface.setMailSlot4Selected(false);
                                    HomeInterface.setMailSelected(true);
                                } else {
                                    resumeScreen();
                                    HomeInterface.setMailSelected(false);
                                    HomeInterface.setMailSlot1Selected(false);
                                    HomeInterface.setMailSlot2Selected(false);
                                    HomeInterface.setMailSlot3Selected(false);
                                    HomeInterface.setMailSlot4Selected(false);
                                    HomeInterface.longPointer.setEmpty();
                                }
                                setAllStatesToOne();
                                break;

                                //lives selected
                            } else if (y2 > HomeInterface.getLivesLeftIcon().top && y2 < HomeInterface.getLivesLeftIcon().bottom && x2 > HomeInterface.getLivesLeftIcon().left && x2 < HomeInterface.getLivesLeftIcon().right) {
                                Log.e(TAG, "onTouchEvent: lives pressed");
                                if (!HomeInterface.isLivesSelected()) {
                                    pauseScreen(player);
                                    HomeInterface.setSettingsSelected(false);
                                    HomeInterface.setSettingsHowToPlaySelected(false);
                                    HomeInterface.setSettingsLanguageSelected(false);
                                    HomeInterface.setCoinsSelected(false);
                                    HomeInterface.setMailSelected(false);
                                    HomeInterface.setMailSlot1Selected(false);
                                    HomeInterface.setMailSlot2Selected(false);
                                    HomeInterface.setMailSlot3Selected(false);
                                    HomeInterface.setMailSlot4Selected(false);
                                    HomeInterface.setLivesSelected(true);
                                } else {
                                    resumeScreen();
                                    HomeInterface.setLivesSelected(false);
                                    HomeInterface.longPointer.setEmpty();
                                }
                                setAllStatesToOne();
                                break;

                                //coins selected
                            } else if (y2 > 0 && y2 < HomeInterface.getCoinsIcon1().bottom && x2 > HomeInterface.getCoinsIcon1().left && x2 < HomeInterface.getCoinsIcon2().right) {
                                Log.e(TAG, "onTouchEvent: coins pressed");
                                if (!HomeInterface.isCoinsSelected()) {
                                    pauseScreen(player);
                                    HomeInterface.setLivesSelected(false);
                                    HomeInterface.setSettingsSelected(false);
                                    HomeInterface.setSettingsHowToPlaySelected(false);
                                    HomeInterface.setSettingsLanguageSelected(false);
                                    HomeInterface.setMailSelected(false);
                                    HomeInterface.setMailSlot1Selected(false);
                                    HomeInterface.setMailSlot2Selected(false);
                                    HomeInterface.setMailSlot3Selected(false);
                                    HomeInterface.setMailSlot4Selected(false);
                                    HomeInterface.setCoinsSelected(true);
                                } else {
                                    resumeScreen();
                                    HomeInterface.setCoinsSelected(false);
                                    HomeInterface.longPointer.setEmpty();
                                }
                                setAllStatesToOne();
                                break;
                            }
                        }

                        //inside buttons
                        else if (HomeInterface.isAnyMenu()) {
                            if (x2 < HomeInterface.getExitMenu().right && x2 > HomeInterface.getExitMenu().left && y2 < HomeInterface.getExitMenu().bottom && y2 > HomeInterface.getExitMenu().top) {
                                Log.e(TAG, "onTouchEvent: exitMenu pressed");
                                resumeScreen();
                                HomeInterface.setCoinsSelected(false);
                                HomeInterface.setLivesSelected(false);
                                HomeInterface.setSettingsSelected(false);
                                HomeInterface.setSettingsHowToPlaySelected(false);
                                HomeInterface.setSettingsLanguageSelected(false);
                                HomeInterface.setMailSelected(false);
                                HomeInterface.setMailSlot1Selected(false);
                                HomeInterface.setMailSlot2Selected(false);
                                HomeInterface.setMailSlot3Selected(false);
                                HomeInterface.setMailSlot4Selected(false);
                                isAreYouSure = false;
                                setAllStatesToOne();
                                HomeInterface.longPointer.setEmpty();
                                break;


                            }

                            //inside menus MAIL
                            else if (HomeInterface.isMailSelected()) {
                                if (x2 < HomeInterface.getMailSlot1().right && x2 > HomeInterface.getMailSlot1().left && y2 < HomeInterface.getMailSlot1().bottom && y2 > HomeInterface.getMailSlot1().top) {
                                    HomeInterface.setMailSlot1Selected(true);
                                    HomeInterface.mailMenuState = 1;
                                    HomeInterface.mailSlot1State = 1;

                                } else if (x2 < HomeInterface.getMailSlotBackButton().right && x2 > HomeInterface.getMailSlotBackButton().left && y2 < HomeInterface.getMailSlotBackButton().bottom && y2 > HomeInterface.getMailSlotBackButton().top) {
                                    HomeInterface.setMailSlot1Selected(false);
                                    HomeInterface.mailMenuState = 1;
                                    HomeInterface.mailSlot1State = 1;
                                    HomeInterface.setMailSlot2Selected(false);
                                    HomeInterface.mailMenuState = 1;
                                    HomeInterface.mailSlot2State = 1;
                                    HomeInterface.setMailSlot3Selected(false);
                                    HomeInterface.mailMenuState = 1;
                                    HomeInterface.mailSlot3State = 1;
                                    HomeInterface.setMailSlot4Selected(false);
                                    HomeInterface.mailMenuState = 1;
                                    HomeInterface.mailSlot4State = 1;

                                } else if (x2 < HomeInterface.getMailSlot2().right && x2 > HomeInterface.getMailSlot2().left && y2 < HomeInterface.getMailSlot2().bottom && y2 > HomeInterface.getMailSlot2().top) {
                                    HomeInterface.setMailSlot2Selected(true);
                                    HomeInterface.mailMenuState = 1;
                                    HomeInterface.mailSlot2State = 1;

                                } else if (x2 < HomeInterface.getMailSlot3().right && x2 > HomeInterface.getMailSlot3().left && y2 < HomeInterface.getMailSlot3().bottom && y2 > HomeInterface.getMailSlot3().top) {
                                    HomeInterface.setMailSlot3Selected(true);
                                    HomeInterface.mailMenuState = 1;
                                    HomeInterface.mailSlot3State = 1;

                                } else if (x2 < HomeInterface.getMailSlot4().right && x2 > HomeInterface.getMailSlot4().left && y2 < HomeInterface.getMailSlot4().bottom && y2 > HomeInterface.getMailSlot4().top) {
                                    HomeInterface.setMailSlot4Selected(true);
                                    HomeInterface.mailMenuState = 1;
                                    HomeInterface.mailSlot4State = 1;

                                }

                                //inside menus SETTINGS
                            } else if (HomeInterface.isSettingsSelected()) {

                                //SETTINGS HOW TO PLAY SELECTED
                                if (x2 < HomeInterface.getSettingsHowToPlay().right && x2 > HomeInterface.getSettingsHowToPlay().left && y2 < HomeInterface.getSettingsHowToPlay().bottom && y2 > HomeInterface.getSettingsHowToPlay().top) {
                                    HomeInterface.setSettingsHowToPlaySelected(true);
                                    HomeInterface.settingsMenuState = 1;
                                    HomeInterface.settingsHowToPlayState = 1;

                                } else if (x2 < HomeInterface.getSettingsBackButton().right && x2 > HomeInterface.getSettingsBackButton().left && y2 < HomeInterface.getSettingsBackButton().bottom && y2 > HomeInterface.getSettingsBackButton().top) {
                                    HomeInterface.setSettingsHowToPlaySelected(false);
                                    HomeInterface.settingsMenuState = 1;
                                    HomeInterface.settingsHowToPlayState = 1;
                                    HomeInterface.setSettingsLanguageSelected(false);
                                    HomeInterface.settingsMenuState = 1;
                                    HomeInterface.settingsLanguageState = 1;

                                    //SETTINGS LANGUAGE SELECTED
                                } else if (x2 < HomeInterface.getSettingsLanguage().right && x2 > HomeInterface.getSettingsLanguage().left && y2 < HomeInterface.getSettingsLanguage().bottom && y2 > HomeInterface.getSettingsLanguage().top) {
                                    HomeInterface.setSettingsLanguageSelected(true);
                                    HomeInterface.settingsMenuState = 1;
                                    HomeInterface.settingsLanguageState = 1;

                                }

                                //inside menu LIVES
                            } else if (HomeInterface.isLivesSelected()) {

                                //watch ad is selected
                                if (x2 < HomeInterface.getLivesConfirmShowAd().right && x2 > HomeInterface.getLivesConfirmShowAd().left && y2 < HomeInterface.getLivesConfirmShowAd().bottom && y2 > HomeInterface.getLivesConfirmShowAd().top) {
                                    if (getLives() < 5)
                                        setLives(1);
                                    HomeInterface.setIsOutOfLives(false);
                                    HomeInterface.setLivesSelected(false);
                                    HomeInterface.livesMenuState = 1;
                                    break;
                                } else if (x2 < HomeInterface.getLivesCancelShowAd().right && x2 > HomeInterface.getLivesCancelShowAd().left && y2 < HomeInterface.getLivesCancelShowAd().bottom && y2 > HomeInterface.getLivesCancelShowAd().top) {
                                    HomeInterface.setLivesSelected(false);
                                    HomeInterface.livesMenuState = 1;
                                    break;
                                }

                                //COINS MENU INSIDE BUTTONS
                            } else if (HomeInterface.isCoinsSelected()) {

                                //watch ad is selected
                                if (x2 < HomeInterface.getCoinsConfirmShowAd().right && x2 > HomeInterface.getCoinsConfirmShowAd().left && y2 < HomeInterface.getCoinsConfirmShowAd().bottom && y2 > HomeInterface.getCoinsConfirmShowAd().top) {
                                    addCoin(1000);
                                    HomeInterface.setCoinsSelected(false);
                                    HomeInterface.coinsMenuState = 1;
                                    break;
                                } else if (x2 < HomeInterface.getCoinsCancelShowAd().right && x2 > HomeInterface.getCoinsCancelShowAd().left && y2 < HomeInterface.getCoinsCancelShowAd().bottom && y2 > HomeInterface.getCoinsCancelShowAd().top) {
                                    HomeInterface.setCoinsSelected(false);
                                    HomeInterface.coinsMenuState = 1;
                                    break;
                                }
                            }

                            //touched outside of menu bounds
                            if (!(x2 < Scale.UNIT*12.5 && x2 > Scale.UNIT*1.5 && y2 < ((Constants.SCREEN_HEIGHT/6f) + Scale.UNIT*14)  && y2 > Constants.SCREEN_HEIGHT/6f)) {
                                Log.e(TAG, "onTouchEvent: Touched outside of bounds.");
                                resumeScreen();
                                HomeInterface.setCoinsSelected(false);
                                HomeInterface.setLivesSelected(false);
                                HomeInterface.setSettingsSelected(false);
                                HomeInterface.setSettingsHowToPlaySelected(false);
                                HomeInterface.setSettingsLanguageSelected(false);
                                HomeInterface.setMailSelected(false);
                                HomeInterface.setMailSlot1Selected(false);
                                HomeInterface.setMailSlot2Selected(false);
                                HomeInterface.setMailSlot3Selected(false);
                                HomeInterface.setMailSlot4Selected(false);
                                isAreYouSure = false;
                                setAllStatesToOne();
                                HomeInterface.longPointer.setEmpty();
                                break;
                            }
                        }

                        else if (y2 > getPower1Button().top && y2 < getPower3Button().bottom || y2 > getPauseButton().top && y2 < getQuitButton().bottom) {
                            //button right footer
                            if (y2 > getPower1Button().top && y2 < getPower1Button().bottom && x2 > getPower1Button().left && !powerMenuSelected) {
                                powerMenuSelected = true;
                                powerMenuState = 1;
                                jumpPower.cancel();

                                if (player.playerPhysics.getDirection() == -1)
                                    player.setCurrentSkin(getMyCurrentSkinBank().get(0));
                                else
                                    player.setCurrentSkin(getMyCurrentSkinBank().get(3));
                                break;

                                //Power 1 pressed
                            } else if (y2 > getPower1Button().top && y2 < getPower1Button().bottom && x2 > getPower1Button().left && powerMenuSelected) {
                                jumpPower.cancel();
                                powerMenuSelected = false;
                                powerMenuState = 1;
                                break;

                                //POWER 2 Pressed
                            } else if (y2 > getPower2Button().top && y2 < getPower2Button().bottom && x2 > getPower2Button().left) {
//                        getPausePowerMenus().cyclePower2(); //this is frontend
                                jumpPower.cancel();
                                powerMenuSelected = false;
                                powerMenuState = 1;
                                cycleString(2);
                                break;

                                //Power 3 pressed
                            } else if (y2 > getPower3Button().top && y2 < getPower3Button().bottom && x2 > getPower3Button().left) {
//                        getPausePowerMenus().cyclePower3(); //this is frontend
                                jumpPower.cancel();
                                powerMenuSelected = false;
                                powerMenuState = 1;
                                cycleString(3);
                                break;
                            }

                            //left footer
                            //pause button pressed
                            else if (y2 > getPauseButton().top && y2 < getPauseButton().bottom && x2 < getPauseButton().right && !pauseMenuSelected) {
                                InternalClock.pause();
                                pauseMenuSelected = true;
                                pauseMenuState = 1;
                                jumpPower.cancel();
                                if (player.playerPhysics.getDirection() == -1)
                                    player.setCurrentSkin(getMyCurrentSkinBank().get(0));
                                else
                                    player.setCurrentSkin(getMyCurrentSkinBank().get(3));
                                break;

                                //resume button pressed
                            } else if (y2 > getPauseButton().top && y2 < getPauseButton().bottom && x2 < getPauseButton().right && pauseMenuSelected) {
                                InternalClock.resume();
                                SceneManager.resetCamera();
                                pauseMenuSelected = false;
                                powerMenuSelected = false;
                                pauseMenuState = 1;
                                powerMenuState = 1;
                                SCROLL = 0;
                                yPress = 0;
                                POSITION = 0;
                                break;

                                //restart button pressed
                            } else if (y2 > getRestartButton().top && y2 < getRestartButton().bottom && x2 < getRestartButton().right) {
                                Log.e(TAG, "onTouchEvent: restart pressed");
                                if (!HomeInterface.getIsOutOfLives()) {
                                    ignoreScroll = true;
                                    isAreYouSure = true;
                                    areYouSureMenuState = 2;
                                    onLevelSavedState = 2;
                                } else {
                                    HomeInterface.setLivesSelected(true);
                                    HomeInterface.livesMenuState = 1;
                                }
//                        HomeInterface.loadingScreenState = 3;
                                break;

                                //quit button pressed
                            } else if (y2 > getQuitButton().top && y2 < getQuitButton().bottom && x2 < getQuitButton().right) {
                                Log.e(TAG, "onTouchEvent: quit pressed");
                                ignoreScroll = true;
                                isAreYouSure = true;
                                areYouSureMenuState = 1;
                                onLevelSavedState = 1;
                                break;
                            }
                        }

                        //physics controls
                        else {
                            if (powerMenuSelected) {
                                powerMenuSelected = false;
                                powerMenuState = 1;
                            }

                            else if (!pauseMenuSelected) {
                                if (deltaY > MIN_DISTANCE*2 && player.isBalloonSelected() && !player.isNewGround() && !player.isUsingArms() || deltaY < -MIN_DISTANCE*2  && player.isBalloonSelected() && !player.isNewGround() && !player.isUsingArms()) {
                                    // change to parachute in use to true to turn off gravity
                                    if (player.isBalloonInUse())
                                        player.setBalloonInUse(false);
                                    else if (player.isBalloonInStock() && !player.isBalloonInUse())
                                        player.useParachute();
                                    jumpPower.cancel();
                                } else if (deltaY > MIN_DISTANCE*2 && player.isArmsSelected() || deltaY < -MIN_DISTANCE*2 && player.isArmsSelected()) {
                                    jumpPower.cancel();
                                    if (isArmsAttached())
                                        setArmsAttached(false);
                                    else if (isArmsInStock() && !player.isArmsAttached())
                                        player.armsPressed();
                                } else if (deltaY > MIN_DISTANCE*2 && player.isRainSelected() && player.isRainInStock() && !PlayerBank.isRainInUse() || deltaY < -MIN_DISTANCE*2 && player.isRainSelected() && player.isRainInStock() && !PlayerBank.isRainInUse()) {
                                    jumpPower.cancel();
                                    player.setRainTrue();
                                }
                                //left or right swipe reverse used
                                else if (deltaX > MIN_DISTANCE || deltaX < -MIN_DISTANCE) {
                                    player.setUsingArms(false);
                                    player.grabbingLimit = Scale.GROUND;
                                    jumpPower.cancel();
                                    player.reverse();

                                    //normal tap
                                } else if (player.getJumpCount() < 2) {
                                    player.setBalloonInUse(false);
                                    player.setUsingArms(false);
                                    player.grabbingLimit = Scale.GROUND;
                                    jumpPower.cancel();
                                    player.onJump((-Constants.SCREEN_WIDTH / ((63f) - player.getJumpPower())), (player.playerPhysics.getDirection() * Constants.SCREEN_WIDTH / ((180f) - (2 * player.getJumpPower()))));
                                    player.setNewGround(false);
                                    if (powerMenuSelected)
                                        powerMenuSelected = false;
                                    Log.e(TAG, "receivedTouch: x = " + player.playerPhysics.getX());
                                    Log.e(TAG, "receivedTouch: y = " + player.playerPhysics.getY());
                                } else
                                    jumpPower.cancel();

                                if (player.playerPhysics.getDirection() == -1)
                                    player.setCurrentSkin(getMyCurrentSkinBank().get(0));
                                else
                                    player.setCurrentSkin(getMyCurrentSkinBank().get(3));
                            }
                        }


                    } else {
                        //game over or level complete is true
                        //gameOver quit selected
                        if (y2 > getGameOverQuit().top && y2 < getGameOverQuit().bottom && x2 < getGameOverQuit().right && x2 > getGameOverQuit().left) {
                            HomeInterface.setIsLoading1(true);
                            HomeInterface.loadingScreen1State = 2;

                            //game over restart selected
                        } else if (y2 > getGameOverRestart().top && y2 < getGameOverRestart().bottom && x2 < getGameOverRestart().right && x2 > getGameOverRestart().left) {
                            if (!HomeInterface.getIsOutOfLives()) {
                                HomeInterface.setIsLoading1(true);
                                HomeInterface.loadingScreen1State = 3;
                            } else {
                                gameOver2 = true;
                                gameOverState = 1;
                                HomeInterface.setLivesSelected(true);
                                HomeInterface.livesMenuState = 1;
                            }

                            //level complete continue selected
                        } else if (y2 > getLevelCompleteContinue().top && y2 < getLevelCompleteContinue().bottom && x2 < getLevelCompleteContinue().right && x2 > getLevelCompleteContinue().left) {
                            HomeInterface.setIsLoading1(true);
                            HomeInterface.loadingScreen1State = 2;
                            //lives menu up for restarting in game over state
                        } else if (HomeInterface.isLivesSelected()) {

                            //watch ad is selected
                            if (x2 < HomeInterface.getLivesConfirmShowAd().right && x2 > HomeInterface.getLivesConfirmShowAd().left && y2 < HomeInterface.getLivesConfirmShowAd().bottom && y2 > HomeInterface.getLivesConfirmShowAd().top) {
                                if (getLives() < 5)
                                    setLives(1);
                                HomeInterface.setIsOutOfLives(false);
                                HomeInterface.setLivesSelected(false);
                                HomeInterface.livesMenuState = 1;
                                if (gameOver1) {
                                    gameOver2 = false;
                                    gameOverState = 1;
                                }
                                break;
                            } else if (x2 < HomeInterface.getLivesCancelShowAd().right && x2 > HomeInterface.getLivesCancelShowAd().left && y2 < HomeInterface.getLivesCancelShowAd().bottom && y2 > HomeInterface.getLivesCancelShowAd().top) {
                                HomeInterface.setLivesSelected(false);
                                HomeInterface.livesMenuState = 1;
                                if (gameOver1) {
                                    gameOver2 = false;
                                    gameOverState = 1;
                                }
                                break;
                            }
                        }
                    }
                    break;
            }
        }
    }
}
