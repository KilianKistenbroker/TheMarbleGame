package com.example.themarblegame.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.example.themarblegame.obstacles.Rain;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerBank{

    /*
    this class keeps track of what
    consumables the user currently
    has in their possession.
     */
    private static SkinBank skinBank = new SkinBank();

    private Bitmap currentSkin;
    private static ArrayList<Bitmap> myCurrentSkinBank = new ArrayList<>();

    //save last array in persistence class for saving skins
    private static ArrayList<ArrayList<android.graphics.Bitmap>> mySkinCollection = new ArrayList<>(Arrays.asList(skinBank.getSkin_a(), skinBank.getEmpty_b()));
    private static ArrayList<Bitmap> tempList;
    private static final String TAG = "";

    private static int skinBankSize = 1;
    public static int health = 100;
    private static int coin = 0;
    private static int score = 0;
    private static int lives = 5;

    //this is for calculating stars earned on level complete
    private static int coinsCollected = 0;
    private static double allItemsCollected = 0;
    private static double allCollectables = 0;
    private static int secondsRemaining = 0;

    private static double bonus1 = 0;
    private static double bonus2 = 0;
    private static double bonus3 = 0;

    //this is for adding to assets
    private static int coinsEarned = 0;
    private static int displayCoinsEarned = 0;

    public int getHealth() {
        return Math.max(health, 0);
    }
    public double getBonus1() {return Math.round(bonus1);}
    public double getBonus2() {return Math.round(bonus2);}
    public double getBonus3() {return Math.round(bonus3);}
    public int getDisplayCoinsEarned() {return displayCoinsEarned;}
    public int getCoinsEarned() {return coinsEarned;}
    public int getPercentOfItemsCollected() {return (int) (Math.round(100*(allItemsCollected/allCollectables)));}
    public void resetCoinsEarned() {coinsEarned = 0;}

    public void setBonus1() {
        bonus1 = score * (health/100f);
    }
    public void setBonus2() {
        bonus2 = score * (secondsRemaining/200f);
    }
    public void setBonus3() {
        bonus3 = score * (float)(allItemsCollected/allCollectables);
    }
    public void setSecondsRemaining() {
        secondsRemaining = InternalClock.getSecondsRemaining();
    }
    public void setDisplayCoinsEarned() {
        displayCoinsEarned = (int) (score + getBonus1() + getBonus2() + getBonus3());
    }
    public void setCoinsEarned() {
        coinsEarned = (int) (score + getBonus1() + getBonus2() + getBonus3());
    }
    public static void setHealth(int health) {
        if (!LevelInterface.levelComplete)
            PlayerBank.health += health;
    }

    //this will be called on level creation
    public static void setAllCollectables(int num) {
        allCollectables = num;
    }

    //will be called in the object class
    public static void incrementCoinsCollected() {
        coinsCollected++;
        allItemsCollected++;
    }

    public int getCoinsCollected() {return coinsCollected;}

//    public static void resetCoinsCollected() {coinsCollected = 0;}

    public void incrementSkinBankSize() {skinBankSize++;}

    public int getSkinBankSize() {return skinBankSize;}

    public int getScore() {
        return score;
    }
    //this is for setting coins + score on level completion
    public static void setScore(int num) {
        if (!LevelInterface.levelComplete && !LevelInterface.gameOver1)
            score += num;
    }

//    public static void resetScore() {
//        score = 0;
//    }

    public void resetAll () {
        score = 0;
        bonus1 = 0;
        bonus2 = 0;
        bonus3 = 0;
        coinsCollected = 0;
        secondsRemaining = 0;
        allItemsCollected = 0;
        displayCoinsEarned = 0;
    }

    public void addCoin(int num) {
        coin += num;
    }

    public static int getCoin() {
        return coin;
    }

    //this is for saving data
    public static void setSavedCoins(int coin) {
        PlayerBank.coin = coin;
    }

    public static int getLives() {
        return lives;
    }

    public static void setLives(int lives) {
        PlayerBank.lives += lives;
    }

    public static ArrayList<ArrayList<android.graphics.Bitmap>> getMySkinCollection() {
        return mySkinCollection;
    }

    public void swapInMySkinCollection(int selectedSkin) {

        tempList = new ArrayList<>();
        tempList.add(mySkinCollection.get(0).get(0));
        tempList.add(mySkinCollection.get(0).get(1));
        tempList.add(mySkinCollection.get(0).get(2));
        tempList.add(mySkinCollection.get(0).get(3));
        tempList.add(mySkinCollection.get(0).get(4));

        mySkinCollection.set(0, mySkinCollection.get(selectedSkin));
        mySkinCollection.set(selectedSkin, tempList);
    }

    public Bitmap getCurrentSkin() {
        return currentSkin;
    }

    public void setCurrentSkin(Bitmap currentSkin) {
        this.currentSkin = currentSkin;
    }

    public ArrayList<Bitmap> getMyCurrentSkinBank() {
        return myCurrentSkinBank;
    }

    public void setSelectedSkin(int selectedSkin) {
        if (skinBankSize-1 >= selectedSkin) {
            Log.e(TAG, "setSelectedSkin: " + skinBankSize);
            swapInMySkinCollection(selectedSkin);
        }
    }

    public void setCurrentSkinBank() {
        if (myCurrentSkinBank.isEmpty()) {
            myCurrentSkinBank.add(mySkinCollection.get(0).get(0));
            myCurrentSkinBank.add(mySkinCollection.get(0).get(1));
            myCurrentSkinBank.add(mySkinCollection.get(0).get(2));
            myCurrentSkinBank.add(mySkinCollection.get(0).get(3));
        } else {
            myCurrentSkinBank.set(0, mySkinCollection.get(0).get(0));
            myCurrentSkinBank.set(1, mySkinCollection.get(0).get(1));
            myCurrentSkinBank.set(2, mySkinCollection.get(0).get(2));
            myCurrentSkinBank.set(3, mySkinCollection.get(0).get(3));
        }
    }

    //all power ups are here
    //parachute power up
    //might make booleans static to reflect display
    private static int balloons = 0;
    //this will not be used
    private static boolean balloonSelected = true;
    private static boolean balloonInUse = false;
    public static int getBalloons() {
        return balloons;
    }
    public boolean isBalloonSelected() {
        return balloonSelected;
    }
    public void setBalloonSelected(boolean balloonSelected){
        balloonSelected = true;
    }
    public boolean isBalloonInStock() {
        return balloons > 0;
    }
    public boolean isBalloonInUse() {
        return balloonInUse;
    }
    public void setBalloonInUse(boolean balloonInUse) {
        PlayerBank.balloonInUse = balloonInUse;
    }
    // will integrate with objects collision class
    public static void addBalloon(int balloons) {
        PlayerBank.balloons += balloons;
    }

    //shell shock power up
    private static int arms = 1;
    private static boolean armsAttached = false;
    private static boolean armsSelected = false;
    private static boolean usingArms = false;

    public int grabbingLimit = Scale.GROUND;
    public void setUsingArms(boolean usingArms) {PlayerBank.usingArms = usingArms;}
    public boolean isUsingArms() {return usingArms;}
    public  boolean isArmsAttached() {
        return armsAttached;
    }
    public boolean isArmsInStock() {
        return arms > 0;
    }

    //will be used later for animation
    public  void setArmsAttached(boolean armsInUse) {
        PlayerBank.armsAttached = armsInUse;
    }

    public  boolean isArmsSelected() {
        return armsSelected;
    }

    public void setArmsSelected(boolean shellShockSelected) {
        PlayerBank.armsSelected = shellShockSelected;
    }

    public static int getArms() {
        return arms;
    }
    public static void addArms(int shellShock) {
        PlayerBank.arms += shellShock;
    }
    public void armsPressed() {
//        rainInUse = false;
        armsAttached = true;
        arms--;
//            shellShockInUse = true; this will be used for shock wave affect
//        for (int i = 0; i < EnemyManager.getEnemyArrayList().size(); i++){
//            EnemyManager.getEnemyArrayList().get(i).setFall(true);
//        }
    }

    //rain power up
    private Rain rain = new Rain();
    private static int rainPower = 1;
    private static boolean rainInUse = false;
    private static boolean rainSelected = false;

    public boolean isRainSelected() {
        return rainSelected;
    }

    public boolean isRainInStock() {
        return rainPower > 0;
    }

    public void setRainSelected(boolean rainSelected) {
        PlayerBank.rainSelected = rainSelected;
    }

    public static int getRainDance() {
        return rainPower;
    }

    public static void addRainPower(int rainPower) {
        PlayerBank.rainPower += rainPower;
    }

    public static boolean isRainInUse() {
        return rainInUse;
    }
    public void rainPressed(Canvas canvas) {
        if (rainInUse)
        rain.draw(canvas);
    }
    public void setRainTrue() {
        PlayerBank.rainInUse = true;
        rainPower--;
    }
    public void setRainFalse() {
        PlayerBank.rainInUse = false;
    }

    public Rain getRain(){
        return rain;
    }

    public static void cyclePower() {

    }
}
