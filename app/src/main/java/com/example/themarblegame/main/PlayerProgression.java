package com.example.themarblegame.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class PlayerProgression extends PlayerBank {
    private static final String TAG = "";

    /*
    this class is in charge of what
    level the player is currently at,
    how many stars a player has
    earned at a particular level,
    and whether a level is locked
    or not locked.
     */

    //---------------MAIN ATTRIBUTES-------------//
    //this is for player travel animation
    private int currentLevel = 0;
    private static final Rect playerProgressPosition = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND + Scale.UNIT*1.25), (Scale.UNIT*11), (int) (Scale.GROUND + Scale.UNIT*2.75));
    private static int playerProgressState = 0;
    private static int playerTargetPath = 1;
    private static final Physics[] playerProgressPhysics = {
            new Physics()
    };

    public static Rect getPlayerProgressPosition() {return playerProgressPosition;}

    private static boolean counter1 = false;
    private static boolean counter2 = false;
    private static boolean counter3 = false;
    private static boolean counter4 = false;
    private static boolean counter5 = false;
    private static boolean counter6 = false;
    private static boolean counter7 = false;
    private static boolean counter8 = false;
    private static boolean counter9 = false;
    private static boolean counter10 = false;

    private static int coinsCollected;

    //put locked icon over position on progress bar. make swipe to right for level complete. locked icon will mirror size of stars.
    private static final Rect progressBar = new Rect((int) (Scale.UNIT*10.1), (Scale.GROUND - Scale.UNIT*25), (int) (Scale.UNIT*10.4), (Scale.GROUND + Scale.UNIT*2));

    private static final Rect position0 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND + Scale.UNIT*1.25), (Scale.UNIT*11), (int) (Scale.GROUND + Scale.UNIT*2.75));
    private static final Rect position1 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND - Scale.UNIT*1.75), (Scale.UNIT*11), (int) (Scale.GROUND - Scale.UNIT*.25));
    private static final Rect position2 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND - Scale.UNIT*4.75), (Scale.UNIT*11), (int) (Scale.GROUND - Scale.UNIT*3.25));
    private static final Rect position3 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND - Scale.UNIT*7.75), (Scale.UNIT*11), (int) (Scale.GROUND - Scale.UNIT*6.25));
    private static final Rect position4 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND - Scale.UNIT*10.75), (Scale.UNIT*11), (int) (Scale.GROUND - Scale.UNIT*9.25));
    private static final Rect position5 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND - Scale.UNIT*13.75), (Scale.UNIT*11), (int) (Scale.GROUND - Scale.UNIT*12.25));
    private static final Rect position6 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND - Scale.UNIT*16.75), (Scale.UNIT*11), (int) (Scale.GROUND - Scale.UNIT*15.25));
    private static final Rect position7 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND - Scale.UNIT*19.75), (Scale.UNIT*11), (int) (Scale.GROUND - Scale.UNIT*18.25));
    private static final Rect position8 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND - Scale.UNIT*22.75), (Scale.UNIT*11), (int) (Scale.GROUND - Scale.UNIT*21.25));
    private static final Rect position9 = new Rect((int) (Scale.UNIT*9.5), (int) (Scale.GROUND - Scale.UNIT*25.75), (Scale.UNIT*11), (int) (Scale.GROUND - Scale.UNIT*24.25));

    private static final Rect lockedIcon1 = new Rect((int)(Scale.UNIT*9.75), (int)(Scale.GROUND - Scale.UNIT*1.5), (int) (Scale.UNIT*10.75), (int) (Scale.GROUND - Scale.UNIT*.5));
    private static final Rect lockedIcon2 = new Rect((int)(Scale.UNIT*9.75), (int)(Scale.GROUND - Scale.UNIT*4.5), (int) (Scale.UNIT*10.75), (int) (Scale.GROUND - Scale.UNIT*3.5));
    private static final Rect lockedIcon3 = new Rect((int)(Scale.UNIT*9.75), (int)(Scale.GROUND - Scale.UNIT*7.5), (int) (Scale.UNIT*10.75), (int) (Scale.GROUND - Scale.UNIT*6.5));
    private static final Rect lockedIcon4 = new Rect((int)(Scale.UNIT*9.75), (int)(Scale.GROUND - Scale.UNIT*10.5), (int) (Scale.UNIT*10.75), (int) (Scale.GROUND - Scale.UNIT*9.5));
    private static final Rect lockedIcon5 = new Rect((int)(Scale.UNIT*9.75), (int)(Scale.GROUND - Scale.UNIT*13.5), (int) (Scale.UNIT*10.75), (int) (Scale.GROUND - Scale.UNIT*12.5));
    private static final Rect lockedIcon6 = new Rect((int)(Scale.UNIT*9.75), (int)(Scale.GROUND - Scale.UNIT*16.5), (int) (Scale.UNIT*10.75), (int) (Scale.GROUND - Scale.UNIT*15.5));
    private static final Rect lockedIcon7 = new Rect((int)(Scale.UNIT*9.75), (int)(Scale.GROUND - Scale.UNIT*19.5), (int) (Scale.UNIT*10.75), (int) (Scale.GROUND - Scale.UNIT*18.5));
    private static final Rect lockedIcon8 = new Rect((int)(Scale.UNIT*9.75), (int)(Scale.GROUND - Scale.UNIT*22.5), (int) (Scale.UNIT*10.75), (int) (Scale.GROUND - Scale.UNIT*21.5));
    private static final Rect lockedIcon9 = new Rect((int)(Scale.UNIT*9.75), (int)(Scale.GROUND - Scale.UNIT*25.5), (int) (Scale.UNIT*10.75), (int) (Scale.GROUND - Scale.UNIT*24.5));

    public static void setCoinsCollected(int coinsCollected) {
        PlayerProgression.coinsCollected = coinsCollected;
    }

    //---------------LEVEL ATTRIBUTES------------//

    //level 1
    private static boolean level1Complete = false; //this will also lock above levels
    private static int level1CompleteState = 0; //this will update the function to bring stars on screen
    private static int level1StarsEarned = 0; //this will determine how many stars travel across screen on level complete.
    private static final Rect level1Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND + Scale.UNIT*1.5), 0, (int)(Scale.GROUND + Scale.UNIT*2.5));
    private static final Rect level1Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND + Scale.UNIT*1.5), 0, (int)(Scale.GROUND + Scale.UNIT*2.5));
    private static final Rect level1Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND + Scale.UNIT*1.5), 0, (int)(Scale.GROUND + Scale.UNIT*2.5));
    private static final Physics[] level1StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //level 2
    private static boolean level2Complete = false;
    private static int level2CompleteState = 0;
    private static int level2StarsEarned = 0;
    private static final Rect level2Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*1.5), 0, (int)(Scale.GROUND - Scale.UNIT*.5));
    private static final Rect level2Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*1.5), 0, (int)(Scale.GROUND - Scale.UNIT*.5));
    private static final Rect level2Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*1.5), 0, (int)(Scale.GROUND - Scale.UNIT*.5));
    private static final Physics[] level2StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //level 3
    private static boolean level3Complete = false;
    private static int level3CompleteState = 0;
    private static int level3StarsEarned = 0;
    private static final Rect level3Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*4.5), 0, (int)(Scale.GROUND - Scale.UNIT*3.5));
    private static final Rect level3Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*4.5), 0, (int)(Scale.GROUND - Scale.UNIT*3.5));
    private static final Rect level3Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*4.5), 0, (int)(Scale.GROUND - Scale.UNIT*3.5));
    private static final Physics[] level3StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //level 4
    private static boolean level4Complete = false;
    private static int level4CompleteState = 0;
    private static int level4StarsEarned = 0;
    private static final Rect level4Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*7.5), 0, (int)(Scale.GROUND - Scale.UNIT*6.5));
    private static final Rect level4Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*7.5), 0, (int)(Scale.GROUND - Scale.UNIT*6.5));
    private static final Rect level4Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*7.5), 0, (int)(Scale.GROUND - Scale.UNIT*6.5));
    private static final Physics[] level4StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //level 5
    private static boolean level5Complete = false;
    private static int level5CompleteState = 0;
    private static int level5StarsEarned = 0;
    private static final Rect level5Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*10.5), 0, (int)(Scale.GROUND - Scale.UNIT*9.5));
    private static final Rect level5Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*10.5), 0, (int)(Scale.GROUND - Scale.UNIT*9.5));
    private static final Rect level5Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*10.5), 0, (int)(Scale.GROUND - Scale.UNIT*9.5));
    private static final Physics[] level5StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //level 6
    private static boolean level6Complete = false;
    private static int level6CompleteState = 0;
    private static int level6StarsEarned = 0;
    private static final Rect level6Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*13.5), 0, (int)(Scale.GROUND - Scale.UNIT*12.5));
    private static final Rect level6Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*13.5), 0, (int)(Scale.GROUND - Scale.UNIT*12.5));
    private static final Rect level6Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*13.5), 0, (int)(Scale.GROUND - Scale.UNIT*12.5));
    private static final Physics[] level6StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //level 7
    private static boolean level7Complete = false;
    private static int level7CompleteState = 0;
    private static int level7StarsEarned = 0;
    private static final Rect level7Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*16.5), 0, (int)(Scale.GROUND - Scale.UNIT*15.5));
    private static final Rect level7Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*16.5), 0, (int)(Scale.GROUND - Scale.UNIT*15.5));
    private static final Rect level7Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*16.5), 0, (int)(Scale.GROUND - Scale.UNIT*15.5));
    private static final Physics[] level7StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //level 8
    private static boolean level8Complete = false;
    private static int level8CompleteState = 0;
    private static int level8StarsEarned = 0;
    private static final Rect level8Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*19.5), 0, (int)(Scale.GROUND - Scale.UNIT*18.5));
    private static final Rect level8Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*19.5), 0, (int)(Scale.GROUND - Scale.UNIT*18.5));
    private static final Rect level8Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*19.5), 0, (int)(Scale.GROUND - Scale.UNIT*18.5));
    private static final Physics[] level8StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //level 9
    private static boolean level9Complete = false;
    private static int level9CompleteState = 0;
    private static int level9StarsEarned = 0;
    private static final Rect level9Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*22.5), 0, (int)(Scale.GROUND - Scale.UNIT*21.5));
    private static final Rect level9Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*22.5), 0, (int)(Scale.GROUND - Scale.UNIT*21.5));
    private static final Rect level9Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*22.5), 0, (int)(Scale.GROUND - Scale.UNIT*21.5));
    private static final Physics[] level9StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //level 10
    private static boolean level10Complete = false;
    private static int level10CompleteState = 0;
    private static int level10StarsEarned = 0;
    private static final Rect level10Star1 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*25.5), 0, (int)(Scale.GROUND - Scale.UNIT*24.5));
    private static final Rect level10Star2 = new Rect((int)-(Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*25.5), 0, (int)(Scale.GROUND - Scale.UNIT*24.5));
    private static final Rect level10Star3 = new Rect((int)(-Scale.UNIT*1.5), (int)(Scale.GROUND-Scale.UNIT*25.5), 0, (int)(Scale.GROUND - Scale.UNIT*24.5));
    private static final Physics[] level10StarPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };





    //-----------BOOLEAN GETTERS AND SETTERS-----------//
    //these will check if level is locked
    public static boolean isLevel1Complete() {return level1Complete;}
    public static boolean isLevel2Complete() {return level2Complete;}
    public static boolean isLevel3Complete() {return level3Complete;}
    public static boolean isLevel4Complete() {return level4Complete;}
    public static boolean isLevel5Complete() {return level5Complete;}
    public static boolean isLevel6Complete() {return level6Complete;}
    public static boolean isLevel7Complete() {return level7Complete;}
    public static boolean isLevel8Complete() {return level8Complete;}
    public static boolean isLevel9Complete() {return level9Complete;}
    public static boolean isLevel10Complete() {return level10Complete;}

    //these will unlock the next level. once true always true.
    public static void setLevel1Complete() {level1Complete = true;}
    public static void setLevel2Complete() {level2Complete = true;}
    public static void setLevel3Complete() {level3Complete = true;}
    public static void setLevel4Complete() {level4Complete = true;}
    public static void setLevel5Complete() {level5Complete = true;}
    public static void setLevel6Complete() {level6Complete = true;}
    public static void setLevel7Complete() {level7Complete = true;}
    public static void setLevel8Complete() {level8Complete = true;}
    public static void setLevel9Complete() {level9Complete = true;}
    public static void setLevel10Complete() {level10Complete = true;}

    //-----------LEVEL COMPLETE  STATE GETTERS AND SETTERS----------//
    public static int getLevel1completeState() {return level1CompleteState;}
    public static int getLevel2completeState() {return level2CompleteState;}
    public static int getLevel3completeState() {return level3CompleteState;}
    public static int getLevel4completeState() {return level4CompleteState;}
    public static int getLevel5completeState() {return level5CompleteState;}
    public static int getLevel6completeState() {return level6CompleteState;}
    public static int getLevel7completeState() {return level7CompleteState;}
    public static int getLevel8completeState() {return level8CompleteState;}
    public static int getLevel9completeState() {return level9CompleteState;}
    public static int getLevel10completeState() {return level10CompleteState;}

    public static void setLevel1CompleteState(int state) {level1CompleteState = state;}
    public static void setLevel2CompleteState(int state) {level2CompleteState = state;}
    public static void setLevel3CompleteState(int state) {level3CompleteState = state;}
    public static void setLevel4CompleteState(int state) {level4CompleteState = state;}
    public static void setLevel5CompleteState(int state) {level5CompleteState = state;}
    public static void setLevel6CompleteState(int state) {level6CompleteState = state;}
    public static void setLevel7CompleteState(int state) {level7CompleteState = state;}
    public static void setLevel8CompleteState(int state) {level8CompleteState = state;}
    public static void setLevel9CompleteState(int state) {level9CompleteState = state;}
    public static void setLevel10CompleteState(int state) {level10CompleteState = state;}

    //-----------STARS EARNED GETTERS AND SETTERS----------//
    public static int getLevel1StarsEarned() {return level1StarsEarned;}
    public static int getLevel2StarsEarned() {return level2StarsEarned;}
    public static int getLevel3StarsEarned() {return level3StarsEarned;}
    public static int getLevel4StarsEarned() {return level4StarsEarned;}
    public static int getLevel5StarsEarned() {return level5StarsEarned;}
    public static int getLevel6StarsEarned() {return level6StarsEarned;}
    public static int getLevel7StarsEarned() {return level7StarsEarned;}
    public static int getLevel8StarsEarned() {return level8StarsEarned;}
    public static int getLevel9StarsEarned() {return level9StarsEarned;}
    public static int getLevel10StarsEarned() {return level10StarsEarned;}

    public static void setLevel1StarsEarned(int starsEarned) {level1StarsEarned = starsEarned;}
    public static void setLevel2StarsEarned(int starsEarned) {level2StarsEarned = starsEarned;}
    public static void setLevel3StarsEarned(int starsEarned) {level3StarsEarned = starsEarned;}
    public static void setLevel4StarsEarned(int starsEarned) {level4StarsEarned = starsEarned;}
    public static void setLevel5StarsEarned(int starsEarned) {level5StarsEarned = starsEarned;}
    public static void setLevel6StarsEarned(int starsEarned) {level6StarsEarned = starsEarned;}
    public static void setLevel7StarsEarned(int starsEarned) {level7StarsEarned = starsEarned;}
    public static void setLevel8StarsEarned(int starsEarned) {level8StarsEarned = starsEarned;}
    public static void setLevel9StarsEarned(int starsEarned) {level9StarsEarned = starsEarned;}
    public static void setLevel10StarsEarned(int starsEarned) {level10StarsEarned = starsEarned;}

    //-----------MENU UP FUNCTION (EXCEPT IT IS FOR STARS)---------//
    //we will set up 3, 2, and 1 star conditions in menu up function
    //this will also have the locked icon animation for level completion
    public static void playerProgressingUp() {
        if (playerProgressState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            switch (playerTargetPath) {
                case 1:
                    if (playerProgressPosition.top > position1.top)
                        playerProgressPhysics[0].swipeUp(playerProgressPosition, (int) (Scale.UNIT*1.5), position1.top, Scale.UNIT/45);
                    break;
                case 2:
                    if (playerProgressPosition.top > position2.top)
                        playerProgressPhysics[0].swipeUp(playerProgressPosition, (int) (Scale.UNIT*1.5), position2.top, Scale.UNIT/45);
                    break;
                case 3:
                    if (playerProgressPosition.top > position3.top)
                        playerProgressPhysics[0].swipeUp(playerProgressPosition, (int) (Scale.UNIT*1.5), position3.top, Scale.UNIT/45);
                    break;
                case 4:
                    if (playerProgressPosition.top > position4.top)
                        playerProgressPhysics[0].swipeUp(playerProgressPosition, (int) (Scale.UNIT*1.5), position4.top, Scale.UNIT/45);
                    break;
                case 5:
                    if (playerProgressPosition.top > position5.top)
                        playerProgressPhysics[0].swipeUp(playerProgressPosition, (int) (Scale.UNIT*1.5), position5.top, Scale.UNIT/45);
                    break;
                case 6:
                    if (playerProgressPosition.top > position6.top)
                        playerProgressPhysics[0].swipeUp(playerProgressPosition, (int) (Scale.UNIT*1.5), position6.top, Scale.UNIT/45);
                    break;
                case 7:
                    if (playerProgressPosition.top > position7.top)
                        playerProgressPhysics[0].swipeUp(playerProgressPosition, (int) (Scale.UNIT*1.5), position7.top, Scale.UNIT/45);
                    break;
                case 8:
                    if (playerProgressPosition.top > position8.top)
                        playerProgressPhysics[0].swipeUp(playerProgressPosition, (int) (Scale.UNIT*1.5), position8.top, Scale.UNIT/45);
                    break;
                case 9:
                    if (playerProgressPosition.top > position9.top)
                        playerProgressPhysics[0].swipeUp(playerProgressPosition, (int) (Scale.UNIT*1.5), position9.top, Scale.UNIT/45);
                    break;
            }
            for (Physics playerProgressPhysic : playerProgressPhysics) {
                if (playerProgressPhysic.isMoving)
                    break;
                else
                    playerProgressState = 0;
            }
        }
    }

    public static void level1StarsUp() {
        if (level1CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level1Complete = true) {
                if (!counter1) {
                    playerProgressState = 1;
                    playerTargetPath = 1;

                    if (coinsCollected > 20 && level1StarsEarned < 3)
                        level1StarsEarned = 3;
                    else if (coinsCollected > 10 && level1StarsEarned < 2)
                        level1StarsEarned = 2;
                    else if (level1StarsEarned < 1)
                        level1StarsEarned = 1;
                    counter1 = true;
                    coinsCollected = 0;
                }

                //unlocks next level
                //pass in unlocked bitmap here.
                level1StarPhysics[3].swipeMiddleToRight(lockedIcon1, (Scale.UNIT), Scale.UNIT/45);

                switch (level1StarsEarned) {
                    case 1:
                        level1StarPhysics[0].swipeLeftToMiddle(level1Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level1StarPhysics[0].swipeLeftToMiddle(level1Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level1StarPhysics[1].swipeLeftToMiddle(level1Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level1StarPhysics[0].swipeLeftToMiddle(level1Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level1StarPhysics[1].swipeLeftToMiddle(level1Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level1StarPhysics[2].swipeLeftToMiddle(level1Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;
                }
            } else {
                level1StarPhysics[0].swipeMiddleToLeft(level1Star1, (Scale.UNIT));
                level1StarPhysics[1].swipeMiddleToLeft(level1Star2, (Scale.UNIT));
                level1StarPhysics[2].swipeMiddleToLeft(level1Star3, (Scale.UNIT));
            }
            for (int i = 0; i < level1StarPhysics.length ; i++) {
                if (level1StarPhysics[i].isMoving || playerProgressPhysics[0].isMoving)
                    break;
                else if (!level1StarPhysics[i].isMoving && i == level1StarPhysics.length-1 && !playerProgressPhysics[0].isMoving) {
                    level1CompleteState = 0;
                    counter1 = false;
                }
            }
        }
    }

    public static void level2StarsUp() {
        if (level2CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level2Complete = true) {

                // this is dummy code. will change later on level design phase.
                if (!counter2) {
                    playerProgressState = 1;
                    playerTargetPath = 2;

                    if (coinsCollected > 20 && level2StarsEarned < 3)
                        level2StarsEarned = 3;
                    else if (coinsCollected > 10 && level2StarsEarned < 2)
                        level2StarsEarned = 2;
                    else if (level2StarsEarned < 1)
                        level2StarsEarned = 1;
                    counter2 = true;
                    coinsCollected = 0;
                }

                level2StarPhysics[3].swipeMiddleToRight(lockedIcon2, (Scale.UNIT), Scale.UNIT/45);

                switch (level2StarsEarned) {
                    case 1:
                        level2StarPhysics[0].swipeLeftToMiddle(level2Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level2StarPhysics[0].swipeLeftToMiddle(level2Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level2StarPhysics[1].swipeLeftToMiddle(level2Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level2StarPhysics[0].swipeLeftToMiddle(level2Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level2StarPhysics[1].swipeLeftToMiddle(level2Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level2StarPhysics[2].swipeLeftToMiddle(level2Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;

                }
            } else {
                level2StarPhysics[0].swipeMiddleToLeft(level2Star1, (Scale.UNIT));
                level2StarPhysics[1].swipeMiddleToLeft(level2Star2, (Scale.UNIT));
                level2StarPhysics[2].swipeMiddleToLeft(level2Star3, (Scale.UNIT));
            }
            for (int i = 0; i < level2StarPhysics.length ; i++) {
                if (level2StarPhysics[i].isMoving || playerProgressPhysics[0].isMoving)
                    break;
                else if (!level2StarPhysics[i].isMoving && i == level2StarPhysics.length-1 && !playerProgressPhysics[0].isMoving) {
                    level2CompleteState = 0;
                    counter2 = false;
                }
            }
        }
    }

    public static void level3StarsUp() {
        if (level3CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level3Complete = true) {

                // this is dummy code. will change later on level design phase.
                if (!counter3) {
                    playerProgressState = 1;
                    playerTargetPath = 3;

                    if (coinsCollected > 20 && level3StarsEarned < 3)
                        level3StarsEarned = 3;
                    else if (coinsCollected > 10 && level3StarsEarned < 2)
                        level3StarsEarned = 2;
                    else if (level3StarsEarned < 1)
                        level3StarsEarned = 1;
                    counter3 = true;
                    coinsCollected = 0;
                }

                level3StarPhysics[3].swipeMiddleToRight(lockedIcon3, (Scale.UNIT), Scale.UNIT/45);

                switch (level3StarsEarned) {
                    case 1:
                        level3StarPhysics[0].swipeLeftToMiddle(level3Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level3StarPhysics[0].swipeLeftToMiddle(level3Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level3StarPhysics[1].swipeLeftToMiddle(level3Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level3StarPhysics[0].swipeLeftToMiddle(level3Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level3StarPhysics[1].swipeLeftToMiddle(level3Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level3StarPhysics[2].swipeLeftToMiddle(level3Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;

                }
            } else {
                level3StarPhysics[0].swipeMiddleToLeft(level3Star1, (Scale.UNIT));
                level3StarPhysics[1].swipeMiddleToLeft(level3Star2, (Scale.UNIT));
                level3StarPhysics[2].swipeMiddleToLeft(level3Star3, (Scale.UNIT));
            }
            for (int i = 0; i < level3StarPhysics.length ; i++) {
                if (level3StarPhysics[i].isMoving)
                    break;
                else if (!level3StarPhysics[i].isMoving && i == level3StarPhysics.length-1) {
                    level3CompleteState = 0;
                    counter3 = false;
                }
            }
        }
    }

    public static void level4StarsUp() {
        if (level4CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level4Complete = true) {

                // this is dummy code. will change later on level design phase.
                if (!counter4) {
                    playerProgressState = 1;
                    playerTargetPath = 4;

                    if (coinsCollected > 20 && level4StarsEarned < 3)
                        level4StarsEarned = 3;
                    else if (coinsCollected > 10 && level4StarsEarned < 2)
                        level4StarsEarned = 2;
                    else if (level4StarsEarned < 1)
                        level4StarsEarned = 1;
                    counter4 = true;
                    coinsCollected = 0;
                }

                level4StarPhysics[3].swipeMiddleToRight(lockedIcon4, (Scale.UNIT), Scale.UNIT/45);

                switch (level4StarsEarned) {
                    case 1:
                        level4StarPhysics[0].swipeLeftToMiddle(level4Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level4StarPhysics[0].swipeLeftToMiddle(level4Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level4StarPhysics[1].swipeLeftToMiddle(level4Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level4StarPhysics[0].swipeLeftToMiddle(level4Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level4StarPhysics[1].swipeLeftToMiddle(level4Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level4StarPhysics[2].swipeLeftToMiddle(level4Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;

                }
            } else {
                level4StarPhysics[0].swipeMiddleToLeft(level4Star1,(Scale.UNIT));
                level4StarPhysics[1].swipeMiddleToLeft(level4Star2,(Scale.UNIT));
                level4StarPhysics[2].swipeMiddleToLeft(level4Star3,(Scale.UNIT));
            }
            for (int i = 0; i < level4StarPhysics.length ; i++) {
                if (level4StarPhysics[i].isMoving)
                    break;
                else if (!level4StarPhysics[i].isMoving && i == level4StarPhysics.length-1) {
                    level4CompleteState = 0;
                    counter4 = false;
                }
            }
        }
    }

    public static void level5StarsUp() {
        if (level5CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level5Complete = true) {

                // this is dummy code. will change later on level design phase.
                if (!counter5) {
                    playerProgressState = 1;
                    playerTargetPath = 5;

                    if (coinsCollected > 20 && level5StarsEarned < 3)
                        level5StarsEarned = 3;
                    else if (coinsCollected > 10 && level5StarsEarned < 2)
                        level5StarsEarned = 2;
                    else if (level5StarsEarned < 1)
                        level5StarsEarned = 1;
                    counter5 = true;
                    coinsCollected = 0;
                }

                level5StarPhysics[3].swipeMiddleToRight(lockedIcon5, (Scale.UNIT), Scale.UNIT/45);

                switch (level5StarsEarned) {
                    case 1:
                        level5StarPhysics[0].swipeLeftToMiddle(level5Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level5StarPhysics[0].swipeLeftToMiddle(level5Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level5StarPhysics[1].swipeLeftToMiddle(level5Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level5StarPhysics[0].swipeLeftToMiddle(level5Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level5StarPhysics[1].swipeLeftToMiddle(level5Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level5StarPhysics[2].swipeLeftToMiddle(level5Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;

                }
            } else {
                level5StarPhysics[0].swipeMiddleToLeft(level5Star1, (Scale.UNIT));
                level5StarPhysics[1].swipeMiddleToLeft(level5Star2, (Scale.UNIT));
                level5StarPhysics[2].swipeMiddleToLeft(level5Star3, (Scale.UNIT));
            }
            for (int i = 0; i < level5StarPhysics.length ; i++) {
                if (level5StarPhysics[i].isMoving)
                    break;
                else if (!level5StarPhysics[i].isMoving && i == level5StarPhysics.length-1) {
                    level5CompleteState = 0;
                    counter5 = false;
                }
            }
        }
    }

    public static void level6StarsUp() {
        if (level6CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level6Complete = true) {

                // this is dummy code. will change later on level design phase.
                if (!counter6) {
                    playerProgressState = 1;
                    playerTargetPath = 6;

                    if (coinsCollected > 20 && level6StarsEarned < 3)
                        level6StarsEarned = 3;
                    else if (coinsCollected > 10 && level6StarsEarned < 2)
                        level6StarsEarned = 2;
                    else if (level6StarsEarned < 1)
                        level6StarsEarned = 1;
                    counter6 = true;
                    coinsCollected = 0;
                }

                level6StarPhysics[3].swipeMiddleToRight(lockedIcon6, (Scale.UNIT), Scale.UNIT/45);

                switch (level6StarsEarned) {
                    case 1:
                        level6StarPhysics[0].swipeLeftToMiddle(level6Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level6StarPhysics[0].swipeLeftToMiddle(level6Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level6StarPhysics[1].swipeLeftToMiddle(level6Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level6StarPhysics[0].swipeLeftToMiddle(level6Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level6StarPhysics[1].swipeLeftToMiddle(level6Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level6StarPhysics[2].swipeLeftToMiddle(level6Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;

                }
            } else {
                level6StarPhysics[0].swipeMiddleToLeft(level6Star1, (int) (Scale.UNIT));
                level6StarPhysics[1].swipeMiddleToLeft(level6Star2, (int) (Scale.UNIT));
                level6StarPhysics[2].swipeMiddleToLeft(level6Star3, (int) (Scale.UNIT));
            }
            for (int i = 0; i < level6StarPhysics.length ; i++) {
                if (level6StarPhysics[i].isMoving)
                    break;
                else if (!level6StarPhysics[i].isMoving && i == level6StarPhysics.length-1) {
                    level6CompleteState = 0;
                    counter6 = false;
                }
            }
        }
    }

    public static void level7StarsUp() {
        if (level7CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level7Complete = true) {

                // this is dummy code. will change later on level design phase.
                if (!counter7) {
                    playerProgressState = 1;
                    playerTargetPath = 7;

                    if (coinsCollected > 20 && level7StarsEarned < 3)
                        level7StarsEarned = 3;
                    else if (coinsCollected > 10 && level7StarsEarned < 2)
                        level7StarsEarned = 2;
                    else if (level7StarsEarned < 1)
                        level7StarsEarned = 1;
                    counter7 = true;
                    coinsCollected = 0;
                }

                level7StarPhysics[3].swipeMiddleToRight(lockedIcon7, (Scale.UNIT), Scale.UNIT/45);

                switch (level7StarsEarned) {
                    case 1:
                        level7StarPhysics[0].swipeLeftToMiddle(level7Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level7StarPhysics[0].swipeLeftToMiddle(level7Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level7StarPhysics[1].swipeLeftToMiddle(level7Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level7StarPhysics[0].swipeLeftToMiddle(level7Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level7StarPhysics[1].swipeLeftToMiddle(level7Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level7StarPhysics[2].swipeLeftToMiddle(level7Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;

                }
            } else {
                level7StarPhysics[0].swipeMiddleToLeft(level7Star1, (int) (Scale.UNIT));
                level7StarPhysics[1].swipeMiddleToLeft(level7Star2, (int) (Scale.UNIT));
                level7StarPhysics[2].swipeMiddleToLeft(level7Star3, (int) (Scale.UNIT));
            }
            for (int i = 0; i < level7StarPhysics.length ; i++) {
                if (level7StarPhysics[i].isMoving)
                    break;
                else if (!level7StarPhysics[i].isMoving && i == level7StarPhysics.length-1) {
                    level7CompleteState = 0;
                    counter7 = false;
                }
            }
        }
    }

    public static void level8StarsUp() {
        if (level8CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level8Complete = true) {

                // this is dummy code. will change later on level design phase.
                if (!counter8) {
                    playerProgressState = 1;
                    playerTargetPath = 8;

                    if (coinsCollected > 20 && level8StarsEarned < 3)
                        level8StarsEarned = 3;
                    else if (coinsCollected > 10 && level8StarsEarned < 2)
                        level8StarsEarned = 2;
                    else if (level8StarsEarned < 1)
                        level8StarsEarned = 1;
                    counter8 = true;
                    coinsCollected = 0;
                }

                level8StarPhysics[3].swipeMiddleToRight(lockedIcon8, (Scale.UNIT), Scale.UNIT/45);

                switch (level8StarsEarned) {
                    case 1:
                        level8StarPhysics[0].swipeLeftToMiddle(level8Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level8StarPhysics[0].swipeLeftToMiddle(level8Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level8StarPhysics[1].swipeLeftToMiddle(level8Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level8StarPhysics[0].swipeLeftToMiddle(level8Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level8StarPhysics[1].swipeLeftToMiddle(level8Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level8StarPhysics[2].swipeLeftToMiddle(level8Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;

                }
            } else {
                level8StarPhysics[0].swipeMiddleToLeft(level8Star1, (int) (Scale.UNIT));
                level8StarPhysics[1].swipeMiddleToLeft(level8Star2, (int) (Scale.UNIT));
                level8StarPhysics[2].swipeMiddleToLeft(level8Star3, (int) (Scale.UNIT));
            }
            for (int i = 0; i < level8StarPhysics.length ; i++) {
                if (level8StarPhysics[i].isMoving)
                    break;
                else if (!level8StarPhysics[i].isMoving && i == level8StarPhysics.length-1) {
                    level8CompleteState = 0;
                    counter8 = false;
                }
            }
        }
    }

    public static void level9StarsUp() {
        if (level9CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level9Complete = true) {

                // this is dummy code. will change later on level design phase.
                if (!counter9) {
                    playerProgressState = 1;
                    playerTargetPath = 9;

                    if (coinsCollected > 20 && level9StarsEarned < 3)
                        level9StarsEarned = 3;
                    else if (coinsCollected > 10 && level9StarsEarned < 2)
                        level9StarsEarned = 2;
                    else if (level9StarsEarned < 1)
                        level9StarsEarned = 1;
                    counter9 = true;
                    coinsCollected = 0;
                }

                level9StarPhysics[3].swipeMiddleToRight(lockedIcon9, (Scale.UNIT), Scale.UNIT/45);

                switch (level9StarsEarned) {
                    case 1:
                        level9StarPhysics[0].swipeLeftToMiddle(level9Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level9StarPhysics[0].swipeLeftToMiddle(level9Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level9StarPhysics[1].swipeLeftToMiddle(level9Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level9StarPhysics[0].swipeLeftToMiddle(level9Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level9StarPhysics[1].swipeLeftToMiddle(level9Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level9StarPhysics[2].swipeLeftToMiddle(level9Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;

                }
            } else {
                level9StarPhysics[0].swipeMiddleToLeft(level9Star1, (int) (Scale.UNIT));
                level9StarPhysics[1].swipeMiddleToLeft(level9Star2, (int) (Scale.UNIT));
                level9StarPhysics[2].swipeMiddleToLeft(level9Star3, (int) (Scale.UNIT));
            }
            for (int i = 0; i < level9StarPhysics.length ; i++) {
                if (level9StarPhysics[i].isMoving)
                    break;
                else if (!level9StarPhysics[i].isMoving && i == level9StarPhysics.length-1) {
                    level9CompleteState = 0;
                    counter9 = false;
                }
            }
        }
    }

    public static void level10StarsUp() {
        if (level10CompleteState == 1 && HomeInterface.loadingScreen1State == 0 && HomeInterface.loadingScreen2State == 0) {
            Log.e(TAG, "level1StarsUp: RUNNING");
            if (level10Complete = true) {

                // this is dummy code. will change later on level design phase.
                if (!counter10) {
                    if (coinsCollected > 20 && level10StarsEarned < 3)
                        level10StarsEarned = 3;
                    else if (coinsCollected > 10 && level10StarsEarned < 2)
                        level10StarsEarned = 2;
                    else if (level10StarsEarned < 1)
                        level10StarsEarned = 1;
                    counter10 = true;
                    coinsCollected = 0;
                }

                //no unlock method here yet. will update for next patch probably.

                switch (level10StarsEarned) {
                    case 1:
                        level10StarPhysics[0].swipeLeftToMiddle(level10Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        break;
                    case 2:
                        level10StarPhysics[0].swipeLeftToMiddle(level10Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level10StarPhysics[1].swipeLeftToMiddle(level10Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        break;
                    case 3:
                        level10StarPhysics[0].swipeLeftToMiddle(level10Star1, (Scale.UNIT), (int) (Scale.UNIT*4.5), Scale.UNIT/45f);
                        level10StarPhysics[1].swipeLeftToMiddle(level10Star2, (Scale.UNIT), (int) (Scale.UNIT*3.25), Scale.UNIT/45f);
                        level10StarPhysics[2].swipeLeftToMiddle(level10Star3, (Scale.UNIT), (Scale.UNIT*2), Scale.UNIT/45f);
                        break;

                }
            } else {
                level10StarPhysics[0].swipeMiddleToLeft(level10Star1, (int) (Scale.UNIT));
                level10StarPhysics[1].swipeMiddleToLeft(level10Star2, (int) (Scale.UNIT));
                level10StarPhysics[2].swipeMiddleToLeft(level10Star3, (int) (Scale.UNIT));
            }
            for (int i = 0; i < level10StarPhysics.length ; i++) {
                if (level10StarPhysics[i].isMoving)
                    break;
                else if (!level10StarPhysics[i].isMoving && i == level10StarPhysics.length-1) {
                    level10CompleteState = 0;
                    counter10 = false;
                }
            }
        }
    }

    //this will be called on surface created since states are not saved in persistence.
    public static void setAllStatesTo1() {
        level1CompleteState = 1;
        level2CompleteState = 1;
        level3CompleteState = 1;
        level4CompleteState = 1;
        level5CompleteState = 1;
        level6CompleteState = 1;
        level7CompleteState = 1;
        level8CompleteState = 1;
        level9CompleteState = 1;
        level10CompleteState = 1;
    }


    static Paint containerPaint = new Paint();
    static Paint starPaint = new Paint();
    static Paint progressPaint = new Paint();
    static Paint lockedPaint = new Paint();

    public static void drawProgress(Canvas canvas) {
        containerPaint.setColor(Color.BLACK);
        starPaint.setColor(Color.YELLOW);
        progressPaint.setColor(Color.YELLOW);
        lockedPaint.setColor(Color.LTGRAY);

        canvas.drawRect(progressBar, progressPaint);

        canvas.drawRect(position0, progressPaint);
        canvas.drawRect(position1, progressPaint);
        canvas.drawRect(position2, progressPaint);
        canvas.drawRect(position3, progressPaint);
        canvas.drawRect(position4, progressPaint);
        canvas.drawRect(position5, progressPaint);
        canvas.drawRect(position6, progressPaint);
        canvas.drawRect(position7, progressPaint);
        canvas.drawRect(position8, progressPaint);
        canvas.drawRect(position9, progressPaint);

        canvas.drawRect(lockedIcon1, lockedPaint);
        canvas.drawRect(lockedIcon2, lockedPaint);
        canvas.drawRect(lockedIcon3, lockedPaint);
        canvas.drawRect(lockedIcon4, lockedPaint);
        canvas.drawRect(lockedIcon5, lockedPaint);
        canvas.drawRect(lockedIcon6, lockedPaint);
        canvas.drawRect(lockedIcon7, lockedPaint);
        canvas.drawRect(lockedIcon8, lockedPaint);
        canvas.drawRect(lockedIcon9, lockedPaint);

//        canvas.drawRect(playerProgressPosition, containerPaint);
        canvas.drawBitmap(getMySkinCollection().get(0).get(4), null, playerProgressPosition, null);

        canvas.drawRect(level1Star1, starPaint);
        canvas.drawRect(level1Star2, starPaint);
        canvas.drawRect(level1Star3, starPaint);

        canvas.drawRect(level2Star1, starPaint);
        canvas.drawRect(level2Star2, starPaint);
        canvas.drawRect(level2Star3, starPaint);

        canvas.drawRect(level3Star1, starPaint);
        canvas.drawRect(level3Star2, starPaint);
        canvas.drawRect(level3Star3, starPaint);

        canvas.drawRect(level4Star1, starPaint);
        canvas.drawRect(level4Star2, starPaint);
        canvas.drawRect(level4Star3, starPaint);

        canvas.drawRect(level5Star1, starPaint);
        canvas.drawRect(level5Star2, starPaint);
        canvas.drawRect(level5Star3, starPaint);

        canvas.drawRect(level6Star1, starPaint);
        canvas.drawRect(level6Star2, starPaint);
        canvas.drawRect(level6Star3, starPaint);

        canvas.drawRect(level7Star1, starPaint);
        canvas.drawRect(level7Star2, starPaint);
        canvas.drawRect(level7Star3, starPaint);

        canvas.drawRect(level8Star1, starPaint);
        canvas.drawRect(level8Star2, starPaint);
        canvas.drawRect(level8Star3, starPaint);

        canvas.drawRect(level9Star1, starPaint);
        canvas.drawRect(level9Star2, starPaint);
        canvas.drawRect(level9Star3, starPaint);

        canvas.drawRect(level10Star1, starPaint);
        canvas.drawRect(level10Star2, starPaint);
        canvas.drawRect(level10Star3, starPaint);
    }

    public static void updateProgress() {
        playerProgressingUp();
        level1StarsUp();
        level2StarsUp();
        level3StarsUp();
        level4StarsUp();
        level5StarsUp();
        level6StarsUp();
        level7StarsUp();
        level8StarsUp();
        level9StarsUp();
        level10StarsUp();
    }
}
