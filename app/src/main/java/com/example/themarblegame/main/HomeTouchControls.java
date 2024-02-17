package com.example.themarblegame.main;

import android.util.Log;
import android.view.MotionEvent;

import com.example.themarblegame.levels.Level1;
import com.example.themarblegame.levels.Level10;
import com.example.themarblegame.levels.Level2;
import com.example.themarblegame.levels.Level3;
import com.example.themarblegame.levels.Level4;
import com.example.themarblegame.levels.Level5;
import com.example.themarblegame.levels.Level6;
import com.example.themarblegame.levels.Level7;
import com.example.themarblegame.levels.Level8;
import com.example.themarblegame.levels.Level9;

import java.util.Locale;

public class HomeTouchControls extends HomeInterface {
    private static final String TAG = "msg";
    private float y1, y2, y3, x1, x2;
    static final int MIN_DISTANCE = Scale.UNIT;
    private float yPress;
//    public static float POSITION = 0;
    private boolean counterReCenter = false;
    private boolean counterStartTimer = false;
    private boolean ignoreScrollDown1 = false;
    private boolean ignoreScrollDown2 = false;
    private boolean ignoreScrollUp1 = false;
    private boolean ignoreScrollUp2 = false;
    private boolean moved = false;

//    public static float SCROLL = 0;
//    public boolean isReCentered = true;

    public void setAllStatesToOne() {
        mailMenuState = 1;
        settingsMenuState = 1;
        shopMenuState = 1;
        coinsMenuState = 1;
        livesMenuState = 1;
        shopSkinsMenuState = 1;
        myStuffMenuState = 1;
        mailSlot1State = 1;
        mailSlot2State = 1;
        mailSlot3State = 1;
        mailSlot4State = 1;
        settingsHowToPlayState = 1;
        settingsLanguageState = 1;
    }

    public static Level1 level1;
    public static Level2 level2;
    public static Level3 level3;
    public static Level4 level4;
    public static Level5 level5;
    public static Level6 level6;
    public static Level7 level7;
    public static Level8 level8;
    public static Level9 level9;
    public static Level10 level10;

    //ADD BUTTONS TO ARRAY AND IMPLEMENT PROXIMITY SEARCH METHOD WHEN EXECUTING THIS
    public static void checkOnLevelDimButtons(float x1, float y1) {
        //MENUS THAT APPEAR ON BOTH HOME INTERFACE AND LEVEL INTERFACE
        //long pointers
        //check main header buttons
        if (y1 < getMainHeader().bottom && y1 > getMainHeader().top) {
            if (x1 < getMailIcon().right && x1 > getMailIcon().left && y1 < getMailIcon().bottom && y1 > getMailIcon().top)
                longPointer.set(getMailIcon().left, getMailIcon().top, getMailIcon().right, getMailIcon().bottom);
            else if (x1 < getSettingsIcon().right && x1 > getSettingsIcon().left && y1 < getSettingsIcon().bottom && y1 > getSettingsIcon().top)
                longPointer.set(getSettingsIcon().left, getSettingsIcon().top, getSettingsIcon().right, getSettingsIcon().bottom);
            else if (x1 < getLivesLeftTimer().right && x1 > getLivesLeftIcon().left && y1 < getLivesLeftIcon().bottom && y1 > 0)
                longPointer.set(getLivesLeftIcon().left, getLivesLeftIcon().top, getLivesLeftIcon().right, getLivesLeftIcon().bottom);
            else if (x1 < getCoinsIcon2().right && x1 > getCoinsIcon1().left && y1 < getCoinsIcon1().bottom && y1 > 0)
                longPointer.set(getCoinsIcon1().left, getCoinsIcon1().top, getCoinsIcon1().right, getCoinsIcon1().bottom);
        }
        //if user drags finger away from button
        else if (!isAnyMenu())
            longPointer.setEmpty();

        //short pointers
        else if (isAnyMenu() && !isShopSelected()) {
            if (x1 < getExitMenu().right && x1 > getExitMenu().left && y1 < getExitMenu().bottom && y1 > getExitMenu().top)
                shortPointer.set(getExitMenu().left, getExitMenu().top, getExitMenu().right, getExitMenu().bottom);
            else if (x1 < getSettingsHowToPlay().right && x1 > getSettingsHowToPlay().left && y1 < getSettingsHowToPlay().bottom && y1 > getSettingsHowToPlay().top)
                shortPointer.set(getSettingsHowToPlay().left, getSettingsHowToPlay().top, getSettingsHowToPlay().right, getSettingsHowToPlay().bottom);
            else if (x1 < getSettingsLanguage().right && x1 > getSettingsLanguage().left && y1 < getSettingsLanguage().bottom && y1 > getSettingsLanguage().top)
                shortPointer.set(getSettingsLanguage().left, getSettingsLanguage().top, getSettingsLanguage().right, getSettingsLanguage().bottom);
            else if (x1 < getSettingsBackButton().right && x1 > getSettingsBackButton().left && y1 < getSettingsBackButton().bottom && y1 > getSettingsBackButton().top)
                shortPointer.set(getSettingsBackButton().left, getSettingsBackButton().top, getSettingsBackButton().right, getSettingsBackButton().bottom);
            else if (x1 < getSettingsLanguageChinese().right && x1 > getSettingsLanguageChinese().left && y1 < getSettingsLanguageChinese().bottom && y1 > getSettingsLanguageChinese().top)
                shortPointer.set(getSettingsLanguageChinese().left, getSettingsLanguageChinese().top, getSettingsLanguageChinese().right, getSettingsLanguageChinese().bottom);
            else if (x1 < getMailSlot1().right && x1 > getMailSlot1().left && y1 < getMailSlot1().bottom && y1 > getMailSlot1().top)
                shortPointer.set(getMailSlot1().left, getMailSlot1().top, getMailSlot1().right, getMailSlot1().bottom);
            else if (x1 < getMailSlot2().right && x1 > getMailSlot2().left && y1 < getMailSlot2().bottom && y1 > getMailSlot2().top)
                shortPointer.set(getMailSlot2().left, getMailSlot2().top, getMailSlot2().right, getMailSlot2().bottom);
            else if (x1 < getMailSlot3().right && x1 > getMailSlot3().left && y1 < getMailSlot3().bottom && y1 > getMailSlot3().top)
                shortPointer.set(getMailSlot3().left, getMailSlot3().top, getMailSlot3().right, getMailSlot3().bottom);
            else if (x1 < getMailSlot4().right && x1 > getMailSlot4().left && y1 < getMailSlot4().bottom && y1 > getMailSlot4().top)
                shortPointer.set(getMailSlot4().left, getMailSlot4().top, getMailSlot4().right, getMailSlot4().bottom);
            else if (x1 < getMailSlotBackButton().right && x1 > getMailSlotBackButton().left && y1 < getMailSlotBackButton().bottom && y1 > getMailSlotBackButton().top)
                shortPointer.set(getMailSlotBackButton().left, getMailSlotBackButton().top, getMailSlotBackButton().right, getMailSlotBackButton().bottom);
            else if (x1 < getLivesConfirmShowAd().right && x1 > getLivesConfirmShowAd().left && y1 < getLivesConfirmShowAd().bottom && y1 > getLivesConfirmShowAd().top)
                shortPointer.set(getLivesConfirmShowAd().left, getLivesConfirmShowAd().top, getLivesConfirmShowAd().right, getLivesConfirmShowAd().bottom);
            else if (x1 < getLivesCancelShowAd().right && x1 > getLivesCancelShowAd().left && y1 < getLivesCancelShowAd().bottom && y1 > getLivesCancelShowAd().top)
                shortPointer.set(getLivesCancelShowAd().left, getLivesCancelShowAd().top, getLivesCancelShowAd().right, getLivesCancelShowAd().bottom);
            else if (x1 < getCoinsConfirmShowAd().right && x1 > getCoinsConfirmShowAd().left && y1 < getCoinsConfirmShowAd().bottom && y1 > getCoinsConfirmShowAd().top)
                shortPointer.set(getCoinsConfirmShowAd().left, getCoinsConfirmShowAd().top, getCoinsConfirmShowAd().right, getCoinsConfirmShowAd().bottom);
            else if (x1 < getCoinsCancelShowAd().right && x1 > getCoinsCancelShowAd().left && y1 < getCoinsCancelShowAd().bottom && y1 > getCoinsCancelShowAd().top)
                shortPointer.set(getCoinsCancelShowAd().left, getCoinsCancelShowAd().top, getCoinsCancelShowAd().right, getCoinsCancelShowAd().bottom);
            else
                shortPointer.setEmpty();
        }

    }

    public void checkDimButton(float x1, float y1) {

        checkOnLevelDimButtons(x1, y1);

        //footer buttons
        if (y1 < getReCenter().bottom && y1 > getReCenter().top) {

            //long pointers
            if (x1 < getShop().right && x1 > getShop().left && y1 < getShop().bottom && y1 > getShop().top)
                longPointer.set(getShop().left, getShop().top, getShop().right, getShop().bottom);
            else if (x1 < getMyStuff().right && x1 > getMyStuff().left && y1 < getMyStuff().bottom && y1 > getMyStuff().top)
                longPointer.set(getMyStuff().left, getMyStuff().top, getMyStuff().right, getMyStuff().bottom);

            //short pointers
            if (x1 < getReCenter().right && x1 > getReCenter().left && y1 < getReCenter().bottom && y1 > getReCenter().top + Scale.UNIT)
                shortPointer.set(getReCenter().left, getReCenter().top, getReCenter().right, getReCenter().bottom);
            else
                shortPointer.setEmpty();

            //inside buttons for shop menu
        } else if (isShopSelected()) {
            if (x1 < getExitMenu().right && x1 > getExitMenu().left && y1 < getExitMenu().bottom && y1 > getExitMenu().top)
                shortPointer.set(getExitMenu().left, getExitMenu().top, getExitMenu().right, getExitMenu().bottom);
            else if (x1 < getShopBalloonsBuy().right && x1 > getShopBalloonsBuy().left && y1 < getShopBalloonsBuy().bottom && y1 > getShopBalloonsBuy().top)
                shortPointer.set(getShopBalloonsBuy().left, getShopBalloonsBuy().top, getShopBalloonsBuy().right, getShopBalloonsBuy().bottom);
            else if (x1 < getShopRainBuy().right && x1 > getShopRainBuy().left && y1 < getShopRainBuy().bottom && y1 > getShopRainBuy().top)
                shortPointer.set(getShopRainBuy().left, getShopRainBuy().top, getShopRainBuy().right, getShopRainBuy().bottom);
            else if (x1 < getShopShellShockBuy().right && x1 > getShopShellShockBuy().left && y1 < getShopShellShockBuy().bottom && y1 > getShopShellShockBuy().top)
                shortPointer.set(getShopShellShockBuy().left, getShopShellShockBuy().top, getShopShellShockBuy().right, getShopShellShockBuy().bottom);
            else if (x1 < getShopSkinItem1Buy().right && x1 > getShopSkinItem1Buy().left && y1 < getShopSkinItem1Buy().bottom && y1 > getShopSkinItem1Buy().top)
                shortPointer.set(getShopSkinItem1Buy().left, getShopSkinItem1Buy().top, getShopSkinItem1Buy().right, getShopSkinItem1Buy().bottom);
            else if (x1 < getShopSkins().right && x1 > getShopSkins().left && y1 < getShopSkins().bottom && y1 > getShopSkins().top)
                shortPointer.set(getShopSkins().left, getShopSkins().top, getShopSkins().right, getShopSkins().bottom);
            else if (x1 < getShopSkinsBackButton().right && x1 > getShopSkinsBackButton().left && y1 < getShopSkinsBackButton().bottom && y1 > getShopSkinsBackButton().top)
                shortPointer.set(getShopSkinsBackButton().left, getShopSkinsBackButton().top, getShopSkinsBackButton().right, getShopSkinsBackButton().bottom);
            else if (x1 < getShopConfirm().right && x1 > getShopConfirm().left && y1 < getShopConfirm().bottom && y1 > getShopConfirm().top)
                shortPointer.set(getShopConfirm().left, getShopConfirm().top, getShopConfirm().right, getShopConfirm().bottom);
            else if (x1 < getShopCancel().right && x1 > getShopCancel().left && y1 < getShopCancel().bottom && y1 > getShopCancel().top)
                shortPointer.set(getShopCancel().left, getShopCancel().top, getShopCancel().right, getShopCancel().bottom);
            else
                shortPointer.setEmpty();

            //level buttons
        } else if (!isAnyMenu() && x1 > getLevel1Button().left- Scale.UNIT/2f && x1 < getLevel1Button().right + Scale.UNIT/2f) {

            if (x1 < getLevel1Button().right && x1 > getLevel1Button().left && y1 < getLevel1Button().bottom + SCROLL && y1 > getLevel1Button().top + SCROLL)
                levelShortPointer.set(getLevel1Button().left, (int) (getLevel1Button().top), getLevel1Button().right, (int) (getLevel1Button().bottom));
            else if (x1 < getLevel2Button().right && x1 > getLevel2Button().left && y1 < getLevel2Button().bottom + SCROLL && y1 > getLevel2Button().top + SCROLL)
                levelShortPointer.set(getLevel2Button().left, (int) (getLevel2Button().top), getLevel2Button().right, (int) (getLevel2Button().bottom));
            else if (x1 < getLevel3Button().right && x1 > getLevel3Button().left && y1 < getLevel3Button().bottom + SCROLL && y1 > getLevel3Button().top + SCROLL)
                levelShortPointer.set(getLevel3Button().left, (int) (getLevel3Button().top), getLevel3Button().right, (int) (getLevel3Button().bottom));
            else if (x1 < getLevel4Button().right && x1 > getLevel4Button().left && y1 < getLevel4Button().bottom + SCROLL && y1 > getLevel4Button().top + SCROLL)
                levelShortPointer.set(getLevel4Button().left, (int) (getLevel4Button().top), getLevel4Button().right, (int) (getLevel4Button().bottom));
            else if (x1 < getLevel5Button().right && x1 > getLevel5Button().left && y1 < getLevel5Button().bottom + SCROLL && y1 > getLevel5Button().top + SCROLL)
                levelShortPointer.set(getLevel5Button().left, (int) (getLevel5Button().top), getLevel5Button().right, (int) (getLevel5Button().bottom));
            else if (x1 < getLevel6Button().right && x1 > getLevel6Button().left && y1 < getLevel6Button().bottom + SCROLL && y1 > getLevel6Button().top + SCROLL)
                levelShortPointer.set(getLevel6Button().left, (int) (getLevel6Button().top), getLevel6Button().right, (int) (getLevel6Button().bottom));
            else if (x1 < getLevel7Button().right && x1 > getLevel7Button().left && y1 < getLevel7Button().bottom + SCROLL && y1 > getLevel7Button().top + SCROLL)
                levelShortPointer.set(getLevel7Button().left, (int) (getLevel7Button().top), getLevel7Button().right, (int) (getLevel7Button().bottom));
            else if (x1 < getLevel8Button().right && x1 > getLevel8Button().left && y1 < getLevel8Button().bottom + SCROLL && y1 > getLevel8Button().top + SCROLL)
                levelShortPointer.set(getLevel8Button().left, (int) (getLevel8Button().top), getLevel8Button().right, (int) (getLevel8Button().bottom));
            else if (x1 < getLevel9Button().right && x1 > getLevel9Button().left && y1 < getLevel9Button().bottom + SCROLL && y1 > getLevel9Button().top + SCROLL)
                levelShortPointer.set(getLevel9Button().left, (int) (getLevel9Button().top), getLevel9Button().right, (int) (getLevel9Button().bottom));
            else if (x1 < getLevel10Button().right && x1 > getLevel10Button().left && y1 < getLevel10Button().bottom + SCROLL && y1 > getLevel10Button().top + SCROLL)
                levelShortPointer.set(getLevel10Button().left, (int) (getLevel10Button().top), getLevel10Button().right, (int) (getLevel10Button().bottom));
            else
                levelShortPointer.setEmpty();
        }
    }

    public void onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (isInPlayState || playState == 1) {
            switch (action) {
                //i think i need these other cases to dim button when pressed
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    x1 = (float) (event.getX()/Constants.DIVISOR);
                    y1 = (float) (event.getY()/Constants.DIVISOR);
                    if (x1 < getPlayButton().right && x1 > getPlayButton().left && y1 < getPlayButton().bottom && y1 > getPlayButton().top) {
                        shortPointer.set(getPlayButton().left, getPlayButton().top, getPlayButton().right, getPlayButton().bottom);
                    } else
                        shortPointer.setEmpty();
                    break;
                case MotionEvent.ACTION_UP:
                    x1 = (float) (event.getX()/Constants.DIVISOR);
                    y1 = (float) (event.getY()/Constants.DIVISOR);
                    if (x1 < getPlayButton().right && x1 > getPlayButton().left && y1 < getPlayButton().bottom && y1 > getPlayButton().top) {
                        playState = 1;
                        setIsInPlayState(false);
                        shortPointer.setEmpty();
                        break;
                    }
                    break;
            }
        } else if (!isIsLoading1() && loadingScreen1State == 0){
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    indicatorState = 0;
                    homePhysicsTimer.velocity = 0;

                    x1 = (float) (event.getX()/Constants.DIVISOR);
                    y1 = (float) (event.getY()/Constants.DIVISOR);

                    if (!HomeInterface.isAnyMenu()) {
                        yPress = (float) (event.getY()/Constants.DIVISOR);
                    }

                    if (!counterReCenter && isReCentered) {
                        POSITION = -SCROLL;
                        counterReCenter = true;
                    }

                    if (moved) {
                        POSITION = -SCROLL;
                        moved = false;
                    }

                    isReCentered = false;

                    checkDimButton(x1, y1);
                    break;

                case MotionEvent.ACTION_MOVE:

                    //i think i need to keep ignore scroll down inside case action move.

                /*create position 2 variable here, where position 1 is just y1.
                have timer class running and link/record position alongside timer.
                take last second intervals w/ respective positions to determine velocity of swipe.
                 */
                    x1 = (float) (event.getX()/Constants.DIVISOR);
                    y1 = (float) (event.getY()/Constants.DIVISOR);

                    moved = true;
                    if (!homePhysicsTimer.timer1Running) {
//                    PhysicsTimer.startTimer1();
                        homePhysicsTimer.timer1Running = true;
                        homePhysicsTimer.timer2Running = true;
                    }


                    if (!HomeInterface.isAnyMenu() && !isReCentered) {

                        //it works!!!
                        if (!ignoreScrollDown1 && !ignoreScrollUp1) {
                            SCROLL = (float) (event.getY()/Constants.DIVISOR  - yPress - POSITION);
                            if (SCROLL <  0) {
                                POSITION = 0;
                                SCROLL = 0;
                                ignoreScrollDown1 = true;
                            }

                            if (SCROLL > Constants.SCREEN_HEIGHT/3f - getLevel10Button().top) {
                                POSITION = -(Constants.SCREEN_HEIGHT/3f - getLevel10Button().top);
                                SCROLL = Constants.SCREEN_HEIGHT/3f - getLevel10Button().top;
                                ignoreScrollUp1 = true;
                            }

                        } else {
                            yPress = (float) (event.getY()/Constants.DIVISOR);
                            ignoreScrollDown1 = false;
                            ignoreScrollDown2 = true;

                            ignoreScrollUp1 = false;
                            ignoreScrollUp2 = true;
                        }

                        //run ignore scroll up here

                    }

                    checkDimButton(x1, y1);
                    break;

                case MotionEvent.ACTION_UP:
                    shortPointer.setEmpty();
                    levelShortPointer.setEmpty();
                    counterReCenter = false;
                    if (moved) {
                        homePhysicsTimer.calcVelocity(1);
                    }

                    homePhysicsTimer.cancel();

                    x2 = (float) (event.getX()/Constants.DIVISOR);
                    y2 = (float) (event.getY()/Constants.DIVISOR - SCROLL); // for clicking on levels
                    y3 = (float) (event.getY()/Constants.DIVISOR); //for clicking on headers or footers
                    if (!HomeInterface.isAnyMenu()) {
                        float deltaY = y1 - y3;
                        if (!ignoreScrollDown2 && !ignoreScrollUp2)
                            POSITION =  (POSITION + deltaY);
                        else {
                            POSITION = -SCROLL;
                            ignoreScrollDown2 = false;
                            ignoreScrollUp2 = false;
                        }
                    }

                    if (y3 < getMainHeader().bottom && y3 > getMainHeader().top) {
                        //check main header buttons
                        //coin pressed
                        if (x2 < getCoinsIcon2().right && x2 > getCoinsIcon1().left && y3 < getCoinsIcon1().bottom && y3 > 0) {
                            Log.e(TAG, "onTouchEvent: coins pressed");
                            if (!HomeInterface.isCoinsSelected()) {
                                setShopSelected(false);
                                setLivesSelected(false);
                                setSettingsSelected(false);
                                setSettingsHowToPlaySelected(false);
                                setSettingsLanguageSelected(false);
                                setMyStuffSelected(false);
                                setShopSkinsSelected(false);
                                setMailSelected(false);
                                setMailSlot1Selected(false);
                                setMailSlot2Selected(false);
                                setMailSlot3Selected(false);
                                setMailSlot4Selected(false);
                                setCoinsSelected(true);
                            } else {
                                setCoinsSelected(false);
                                longPointer.setEmpty();
                            }
                            setAllStatesToOne();
                            break;

                            //lives pressed
                        }  else if (x2 < getLivesLeftTimer().right && x2 > getLivesLeftIcon().left && y3 < getLivesLeftIcon().bottom && y3 > 0) {
                            Log.e(TAG, "onTouchEvent: lives pressed");
                            if (!HomeInterface.isLivesSelected()) {
                                setShopSelected(false);
                                setSettingsSelected(false);
                                setSettingsHowToPlaySelected(false);
                                setSettingsLanguageSelected(false);
                                setMyStuffSelected(false);
                                setCoinsSelected(false);
                                setShopSkinsSelected(false);
                                setMailSelected(false);
                                setMailSlot1Selected(false);
                                setMailSlot2Selected(false);
                                setMailSlot3Selected(false);
                                setMailSlot4Selected(false);
                                setLivesSelected(true);
                            } else {
                                setLivesSelected(false);
                                longPointer.setEmpty();
                            }
                            setAllStatesToOne();
                            break;

                            //mail pressed
                        }  else if (x2 < getMailIcon().right && x2 > getMailIcon().left && y3 < getMailIcon().bottom && y3 > 0) {
                            Log.e(TAG, "onTouchEvent: mail pressed");
                            if (!HomeInterface.isMailSelected()) {
                                setShopSelected(false);
                                setMyStuffSelected(false);
                                setCoinsSelected(false);
                                setLivesSelected(false);
                                setSettingsSelected(false);
                                setSettingsHowToPlaySelected(false);
                                setSettingsLanguageSelected(false);
                                setShopSkinsSelected(false);
                                setMailSlot1Selected(false);
                                setMailSlot2Selected(false);
                                setMailSlot3Selected(false);
                                setMailSlot4Selected(false);
                                setMailSelected(true);
                            } else {
                                setMailSelected(false);
                                setMailSlot1Selected(false);
                                setMailSlot2Selected(false);
                                setMailSlot3Selected(false);
                                setMailSlot4Selected(false);
                                longPointer.setEmpty();
                            }
                            setAllStatesToOne();
                            break;

                            //settings pressed
                        } else if (x2 < getSettingsIcon().right && x2 > getSettingsIcon().left && y3 < getSettingsIcon().bottom && y3 > 0) {
                            Log.e(TAG, "onTouchEvent: settings pressed");
                            if (!HomeInterface.isSettingsSelected()) {
                                setShopSelected(false);
                                setMyStuffSelected(false);
                                setCoinsSelected(false);
                                setLivesSelected(false);
                                setShopSkinsSelected(false);
                                setMailSelected(false);
                                setMailSlot1Selected(false);
                                setMailSlot2Selected(false);
                                setMailSlot3Selected(false);
                                setMailSlot4Selected(false);
                                setSettingsHowToPlaySelected(false);
                                setSettingsLanguageSelected(false);
                                setSettingsSelected(true);
                            } else {
                                setSettingsSelected(false);
                                setSettingsHowToPlaySelected(false);
                                setSettingsLanguageSelected(false);
                                longPointer.setEmpty();
                            }
                            setAllStatesToOne();
                            break;
                        }
                    }

                    else if (y3 < getReCenter().bottom && y3 > getReCenter().top) {
                        //check footer buttons
                        //shop pressed
                        if (x2 < getShop().right && x2 > getShop().left && y3 < getShop().bottom && y3 > getShop().top) {
                            Log.e(TAG, "onTouchEvent: shop pressed");
                            if (!HomeInterface.isShopSelected()) {
                                setCoinsSelected(false);
                                setLivesSelected(false);
                                setSettingsSelected(false);
                                setSettingsHowToPlaySelected(false);
                                setSettingsLanguageSelected(false);
                                setMyStuffSelected(false);
                                setShopSkinsSelected(false);
                                setMailSelected(false);
                                setMailSlot1Selected(false);
                                setMailSlot2Selected(false);
                                setMailSlot3Selected(false);
                                setMailSlot4Selected(false);
                                setShopSelected(true);
                            } else {
                                setShopSelected(false);
                                setShopSkinsSelected(false);
                                longPointer.setEmpty();
                            }
                            setAllStatesToOne();
                            break;

                            //reCenter pressed
                        } else if (x2 < getReCenter().right && x2 > getReCenter().left && y3 < getReCenter().bottom && y3 > getReCenter().top) {
                            Log.e(TAG, "onTouchEvent: reCenter pressed");
                            Log.e(TAG, "setUsersLanguage: " + Locale.getDefault().getLanguage());
                            setShopSelected(false);
                            setMyStuffSelected(false);
                            setCoinsSelected(false);
                            setLivesSelected(false);
                            setSettingsSelected(false);
                            setSettingsHowToPlaySelected(false);
                            setSettingsLanguageSelected(false);
                            setShopSkinsSelected(false);
                            setMailSelected(false);
                            setMailSlot1Selected(false);
                            setMailSlot2Selected(false);
                            setMailSlot3Selected(false);
                            setMailSlot4Selected(false);
                            setAllStatesToOne();

                            longPointer.setEmpty();
                            isReCentered = true;

                            PlayerProgression.setLevel1Complete();
                            PlayerProgression.setLevel1CompleteState(1);

                            //will change this to recenter around player progression position
                            SCROLL = 0;
                            break;


                            //my stuff pressed
                        } else if (x2 < getMyStuff().right && x2 > getMyStuff().left && y3 < getMyStuff().bottom && y3 > getMyStuff().top) {
                            Log.e(TAG, "onTouchEvent: my stuff pressed");
                            if (!HomeInterface.isMyStuffSelected()) {
                                setShopSelected(false);
                                setCoinsSelected(false);
                                setLivesSelected(false);
                                setSettingsSelected(false);
                                setSettingsHowToPlaySelected(false);
                                setSettingsLanguageSelected(false);
                                setShopSkinsSelected(false);
                                setMailSelected(false);
                                setMailSlot1Selected(false);
                                setMailSlot2Selected(false);
                                setMailSlot3Selected(false);
                                setMailSlot4Selected(false);
                                setMyStuffSelected(true);
                            } else {
                                setMyStuffSelected(false);
                                longPointer.setEmpty();
                            }
                            setAllStatesToOne();
                            break;
                        }
                    }

                    else if (isAnyMenu()) {
                        //menu buttons
                        if (x2 < getExitMenu().right && x2 > getExitMenu().left && y3 < getExitMenu().bottom && y3 > getExitMenu().top) {
                            Log.e(TAG, "onTouchEvent: exitMenu pressed");
                            setShopSelected(false);
                            setMyStuffSelected(false);
                            setCoinsSelected(false);
                            setLivesSelected(false);
                            setSettingsSelected(false);
                            setSettingsHowToPlaySelected(false);
                            setSettingsLanguageSelected(false);
                            setShopSkinsSelected(false);
                            setMailSelected(false);
                            setMailSlot1Selected(false);
                            setMailSlot2Selected(false);
                            setMailSlot3Selected(false);
                            setMailSlot4Selected(false);
                            setAllStatesToOne();
                            longPointer.setEmpty();
                            break;
                        }

                        //INSIDE MENU BUTTONS
                        else if (isShopSelected()) {

                            //shop skins selected
                            if (x2 < getShopSkins().right && x2 > getShopSkins().left && y3 < getShopSkins().bottom && y3 > getShopSkins().top) {
                                Log.e(TAG, "onTouchEvent: skins button pressed");
                                setShopSkinsSelected(true);
                                shopMenuState = 1;
                                shopSkinsMenuState = 1;
                                break;

                                //back button selected
                            } else if (x2 < getShopSkinsBackButton().right && x2 > getShopSkinsBackButton().left && y3 < getShopSkinsBackButton().bottom && y3 > getShopSkinsBackButton().top) {
                                Log.e(TAG, "onTouchEvent: back button pressed");
                                setShopSkinsSelected(false);
                                shopMenuState = 1;
                                shopSkinsMenuState = 1;
                                break;

                                //shop balloon pressed
                            } else if (x2 < getShopBalloonsBuy().right && x2 > getShopBalloonsBuy().left && y3 < getShopBalloonsBuy().bottom && y3 > getShopBalloonsBuy().top) {
                                Log.e(TAG, "balloon button pressed");
//                                setCanAffordItem(true);
                                setCheckoutState(1);
                                shopMenuState = 1;
                                break;

                                //shop rain pressed
                            } else if (x2 < getShopRainBuy().right && x2 > getShopRainBuy().left && y3 < getShopRainBuy().bottom && y3 > getShopRainBuy().top) {
                                Log.e(TAG, "rain power button pressed");
//                                setCanAffordItem(true);
                                setCheckoutState(2);
                                shopMenuState = 1;
                                break;

                                //shop shell shock pressed
                            } else if (x2 < getShopShellShockBuy().right && x2 > getShopShellShockBuy().left && y3 < getShopShellShockBuy().bottom && y3 > getShopShellShockBuy().top) {
                                Log.e(TAG, "shell shock button pressed");
//                                setCanAffordItem(true);
                                setCheckoutState(3);
                                shopMenuState = 1;
                                break;

                                //confirm pressed
                            } else if (x2 < getShopConfirm().right && x2 > getShopConfirm().left && y3 < getShopConfirm().bottom && y3 > getShopConfirm().top) {
                                Log.e(TAG, "confirm pressed");
//                                setCanAffordItem(false);
                                setCheckoutState(-1);
                                shopMenuState = 1;
                                break;

                                //cancel pressed
                            } else if (x2 < getShopCancel().right && x2 > getShopCancel().left && y3 < getShopCancel().bottom && y3 > getShopCancel().top) {
                                Log.e(TAG, "cancel pressed");
//                                setCanAffordItem(false);

                                setCheckoutState(-2);
                                shopMenuState = 1;
                                break;

                                //buy skin1 pressed
                            } else if (x2 < getShopSkinItem1Buy().right && x2 > getShopSkinItem1Buy().left && y3 < getShopSkinItem1Buy().bottom && y3 > getShopSkinItem1Buy().top) {
//                                setCanAffordItem(true);
                                setCheckoutState(4);
                                shopMenuState = 1;
                                break;
                            }

                            //inside buttons for MY STUFF menu
                        } else if (isMyStuffSelected()) {
                            if (x2 < getSkinSlot1().right && x2 > getSkinSlot1().left && y3 < getSkinSlot1().bottom && y3 > getSkinSlot1().top) {
                                setSelectedSkin(1);
                                Log.e(TAG, "onTouchEvent: " + getMySkinCollection().size() );
                                Log.e(TAG, "onTouchEvent: " + getSkinBankSize());
                                break;
                            }
                            //inside buttons for MAIL Menu
                        } else if (isMailSelected()) {
                            if (x2 < getMailSlot1().right && x2 > getMailSlot1().left && y3 < getMailSlot1().bottom && y3 > getMailSlot1().top) {
                                setMailSlot1Selected(true);
                                mailMenuState = 1;
                                mailSlot1State = 1;
                                break;

                            } else if (x2 < getMailSlotBackButton().right && x2 > getMailSlotBackButton().left && y3 < getMailSlotBackButton().bottom && y3 > getMailSlotBackButton().top) {
                                setMailSlot1Selected(false);
                                mailMenuState = 1;
                                mailSlot1State = 1;
                                setMailSlot2Selected(false);
                                mailMenuState = 1;
                                mailSlot2State = 1;
                                setMailSlot3Selected(false);
                                mailMenuState = 1;
                                mailSlot3State = 1;
                                setMailSlot4Selected(false);
                                mailMenuState = 1;
                                mailSlot4State = 1;
                                break;

                            } else if (x2 < getMailSlot2().right && x2 > getMailSlot2().left && y3 < getMailSlot2().bottom && y3 > getMailSlot2().top) {
                                setMailSlot2Selected(true);
                                mailMenuState = 1;
                                mailSlot2State = 1;
                                break;

                            } else if (x2 < getMailSlot3().right && x2 > getMailSlot3().left && y3 < getMailSlot3().bottom && y3 > getMailSlot3().top) {
                                setMailSlot3Selected(true);
                                mailMenuState = 1;
                                mailSlot3State = 1;
                                break;

                            } else if (x2 < getMailSlot4().right && x2 > getMailSlot4().left && y3 < getMailSlot4().bottom && y3 > getMailSlot4().top) {
                                setMailSlot4Selected(true);
                                mailMenuState = 1;
                                mailSlot4State = 1;
                                break;

                            }

                            //SETTING SELECTED
                        } else if (isSettingsSelected()) {

                            //SETTINGS HOW TO PLAY SELECTED
                            if (x2 < getSettingsHowToPlay().right && x2 > getSettingsHowToPlay().left && y3 < getSettingsHowToPlay().bottom && y3 > getSettingsHowToPlay().top) {
                                setSettingsHowToPlaySelected(true);
                                settingsMenuState = 1;
                                settingsHowToPlayState = 1;
                                break;

                            } else if (x2 < getSettingsBackButton().right && x2 > getSettingsBackButton().left && y3 < getSettingsBackButton().bottom && y3 > getSettingsBackButton().top) {
                                setSettingsHowToPlaySelected(false);
                                settingsMenuState = 1;
                                settingsHowToPlayState = 1;
                                setSettingsLanguageSelected(false);
                                settingsMenuState = 1;
                                settingsLanguageState = 1;
                                break;

                                //SETTINGS LANGUAGE SELECTED
                            } else if (x2 < getSettingsLanguage().right && x2 > getSettingsLanguage().left && y3 < getSettingsLanguage().bottom && y3 > getSettingsLanguage().top) {
                                setSettingsLanguageSelected(true);
                                settingsMenuState = 1;
                                settingsLanguageState = 1;
                                break;

                            } else if (x2 < getSettingsLanguageChinese().right && x2 > getSettingsLanguageChinese().left && y3 < getSettingsLanguageChinese().bottom && y3 > getSettingsLanguageChinese().top) {
                                WordBank.manuallySetUserLanguage("fr");
                                WordBank.loadAllText();
                                setSettingsLanguageSelected(false);
                                settingsMenuState = 1;
                                settingsLanguageState = 1;
                                break;
                            }

                            //LIVES SELECTED INSIDE BUTTONS
                        } else if (isLivesSelected()) {

                            //watch ad is selected
                            if (x2 < getLivesConfirmShowAd().right && x2 > getLivesConfirmShowAd().left && y3 < getLivesConfirmShowAd().bottom && y3 > getLivesConfirmShowAd().top) {
                                if (getLives() < 5)
                                    setLives(1);
                                setIsOutOfLives(false);
                                setLivesSelected(false);
                                livesMenuState = 1;
                                longPointer.setEmpty();
                                break;
                            } else if (x2 < getLivesCancelShowAd().right && x2 > getLivesCancelShowAd().left && y3 < getLivesCancelShowAd().bottom && y3 > getLivesCancelShowAd().top) {
                                setLivesSelected(false);
                                livesMenuState = 1;
                                longPointer.setEmpty();
                                break;
                            }

                            //COINS MENU INSIDE BUTTONS
                        } else if (isCoinsSelected()) {

                            //watch ad is selected
                            if (x2 < getCoinsConfirmShowAd().right && x2 > getCoinsConfirmShowAd().left && y3 < getCoinsConfirmShowAd().bottom && y3 > getCoinsConfirmShowAd().top) {
                               addCoin(1000);
                                setCoinsSelected(false);
                                coinsMenuState = 1;
                                longPointer.setEmpty();
                                break;
                            } else if (x2 < getCoinsCancelShowAd().right && x2 > getCoinsCancelShowAd().left && y3 < getCoinsCancelShowAd().bottom && y3 > getCoinsCancelShowAd().top) {
                                setCoinsSelected(false);
                                coinsMenuState = 1;
                                longPointer.setEmpty();
                                break;
                            }
                        }

                        //touched outside of menu bounds
                        if (!(x2 < Scale.UNIT*12.5 && x2 > Scale.UNIT*1.5 && y3 < ((Constants.SCREEN_HEIGHT/6f) + Scale.UNIT*14)  && y3 > Constants.SCREEN_HEIGHT/6f)) {
                            Log.e(TAG, "onTouchEvent: Touched outside of bounds.");
                            setShopSelected(false);
                            setMyStuffSelected(false);
                            setCoinsSelected(false);
                            setLivesSelected(false);
                            setSettingsSelected(false);
                            setSettingsHowToPlaySelected(false);
                            setSettingsLanguageSelected(false);
                            setShopSkinsSelected(false);
                            setMailSelected(false);
                            setMailSlot1Selected(false);
                            setMailSlot2Selected(false);
                            setMailSlot3Selected(false);
                            setMailSlot4Selected(false);
                            setAllStatesToOne();
                            longPointer.setEmpty();
                            break;
                        }
                    }

                    else if (x2 < getLevel1Button().right + Scale.UNIT/2f && x2 > getLevel1Button().left - Scale.UNIT/2f){
                        //normal scrolling and level selection
                        //level1 pressed
                        if (x2 < getLevel1Button().right && x2 > getLevel1Button().left && y2 < getLevel1Button().bottom && y2 > getLevel1Button().top){
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives()) {
                                level1 = new Level1();
                                SceneManager.currentLevel = 1;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            } else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        } else if (x2 < getLevel2Button().right && x2 > getLevel2Button().left && y2 < getLevel2Button().bottom && y2 > getLevel2Button().top) {
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives() && PlayerProgression.isLevel1Complete()) {
                                level2 = new Level2();
                                SceneManager.currentLevel = 2;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            } else if (!getIsOutOfLives()) {
                                Log.e(TAG, "onTouchEvent: this level is locked");
                                indicatorState = 2;
                            }  else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        } else if (x2 < getLevel3Button().right && x2 > getLevel3Button().left && y2 < getLevel3Button().bottom && y2 > getLevel3Button().top) {
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives() && PlayerProgression.isLevel2Complete()) {
                                level3 = new Level3();
                                SceneManager.currentLevel = 3;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            } else if (!getIsOutOfLives()) {
                                Log.e(TAG, "onTouchEvent: this level is locked");
                                indicatorState = 3;
                            }  else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        } else if (x2 < getLevel4Button().right && x2 > getLevel4Button().left && y2 < getLevel4Button().bottom && y2 > getLevel4Button().top) {
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives() && PlayerProgression.isLevel3Complete()) {
                                level4 = new Level4();
                                SceneManager.currentLevel = 4;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            } else if (!getIsOutOfLives()) {
                                Log.e(TAG, "onTouchEvent: this level is locked");
                                indicatorState = 4;
                            }  else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        } else if (x2 < getLevel5Button().right && x2 > getLevel5Button().left && y2 < getLevel5Button().bottom && y2 > getLevel5Button().top) {
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives() && PlayerProgression.isLevel4Complete()) {
                                level5 = new Level5();
                                SceneManager.currentLevel = 5;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            } else if (!getIsOutOfLives()) {
                                Log.e(TAG, "onTouchEvent: this level is locked");
                                indicatorState = 5;
                            }  else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        } else if (x2 < getLevel6Button().right && x2 > getLevel6Button().left && y2 < getLevel6Button().bottom && y2 > getLevel6Button().top) {
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives() && PlayerProgression.isLevel5Complete()) {
                                level6 = new Level6();
                                SceneManager.currentLevel = 6;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            } else if (!getIsOutOfLives()) {
                                Log.e(TAG, "onTouchEvent: this level is locked");
                                indicatorState = 6;
                            }  else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        } else if (x2 < getLevel7Button().right && x2 > getLevel7Button().left && y2 < getLevel7Button().bottom && y2 > getLevel7Button().top) {
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives() && PlayerProgression.isLevel6Complete()) {
                                level7 = new Level7();
                                SceneManager.currentLevel = 7;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            } else if (!getIsOutOfLives()) {
                                Log.e(TAG, "onTouchEvent: this level is locked");
                                indicatorState = 7;
                            } else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        } else if (x2 < getLevel8Button().right && x2 > getLevel8Button().left && y2 < getLevel8Button().bottom && y2 > getLevel8Button().top) {
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives() && PlayerProgression.isLevel7Complete()) {
                                level8 = new Level8();
                                SceneManager.currentLevel = 8;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            }  else if (!getIsOutOfLives()) {
                                Log.e(TAG, "onTouchEvent: this level is locked");
                                indicatorState = 8;
                            } else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        } else if (x2 < getLevel9Button().right && x2 > getLevel9Button().left && y2 < getLevel9Button().bottom && y2 > getLevel9Button().top) {
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives() && PlayerProgression.isLevel8Complete()) {
                                level9 = new Level9();
                                SceneManager.currentLevel = 9;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            }  else if (!getIsOutOfLives()) {
                                Log.e(TAG, "onTouchEvent: this level is locked");
                                indicatorState = 9;
                            } else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        } else if (x2 < getLevel10Button().right && x2 > getLevel10Button().left && y2 < getLevel10Button().bottom && y2 > getLevel10Button().top) {
                            Log.e(TAG, "onTouchEvent: pressed");
                            if (!getIsOutOfLives() && PlayerProgression.isLevel9Complete()) {
                                level10 = new Level10();
                                SceneManager.currentLevel = 10;
                                LevelInterface.isStartMenu = true;
                                LevelInterface.startMenuState = 1;
                                setIsLoading1(true);
                                loadingScreen1State = 1;
                            } else if (!getIsOutOfLives()) {
                                Log.e(TAG, "onTouchEvent: this level is locked");
                                indicatorState = 10;
                            } else {
                                setLivesSelected(true);
                                livesMenuState = 1;
                            }
                        }
                    }
                    break;
            }
        }
    }
}
