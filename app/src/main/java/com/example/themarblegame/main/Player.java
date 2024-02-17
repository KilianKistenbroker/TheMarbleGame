package com.example.themarblegame.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.themarblegame.interfaces.GameDesigner;

public class Player extends PlayerBank implements GameDesigner {

    /*
    this class mostly tracks the
    physical nature of the player
    while in game view.
     */

    private static final String TAG = "msg";
    private SoundEffects soundEffects = new SoundEffects();
    private static float jumpPower;
    private int jumpCount = 0;
    private boolean grounded;
    private boolean newGround = true;
    private boolean poisoned;
    private String status;
    private boolean displayStatus = false;
    public Physics playerPhysics;
    public Physics balloonPhysics;
//    private int health;
    private static int staticHealth;
    private Rect balloon = new Rect();
    public DrawText animations = new DrawText();

    //        physics.setX(Constants.SCREEN_WIDTH - (getCurrentMarble().getWidth() *2));
    //        physics.setY(Scale.GROUND);

    //constructor
    public Player() {
        playerPhysics = new Physics();
        balloonPhysics = new Physics();
        setCurrentSkinBank();
        setCurrentSkin(getMyCurrentSkinBank().get(0));
        playerPhysics.setX(Constants.SCREEN_WIDTH - (getCurrentSkin().getWidth() *2));
        playerPhysics.setY(Scale.GROUND);
        health = 100;
        balloon.setEmpty();
    }

    public void setStaticHealth() {
        staticHealth = health;
        Log.e(TAG, "setStaticHealth: "+staticHealth);
    }

    public static int getStaticHealth() {return staticHealth;}

//    public void setHealth(int health) {PlayerAssets.health += health;
//    }

     public void setGrounded(boolean grounded) {
        this.grounded = grounded;
     }

     public void setNewGround (boolean newGround) {
        this.newGround = newGround;
     }

     public boolean isNewGround() {
        return newGround;
     }

     public boolean isPoisoned() {
        return poisoned;
     }

     public void setPoisoned (boolean poisoned) {
        this.poisoned = poisoned;
     }

    //power-up physics
    //parachute power up
    public void useParachute() {
        if (balloon.isEmpty()) {
            setBalloonInUse(true);
//        yVelocity = GRAVITY / 2;
            balloonPhysics.setYVelocity(balloonPhysics.getNormieJump()/2);
            playerPhysics.setYVelocity(playerPhysics.getGRAVITY()/2);
            addBalloon(-1);
            jumpCount--;
        }
    }


    public void useArms() {
        if (isUsingArms()) {
            playerPhysics.setYVelocity(playerPhysics.getGRAVITY()/2);
            playerPhysics.setXVelocity(0);
        }
    }

    //reverse power up is infinite
    public void reverse() {
        playerPhysics.reverse();

        if (playerPhysics.getDirection() == -1) {
            if (LevelTouchControls.down)
                setCurrentSkin(Bitmap.createScaledBitmap(getMyCurrentSkinBank().get(1), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true));
            else
                setCurrentSkin(Bitmap.createScaledBitmap(getMyCurrentSkinBank().get(0), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true));
        } else {
            if (LevelTouchControls.down)
                setCurrentSkin(Bitmap.createScaledBitmap(getMyCurrentSkinBank().get(2), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true));
            else
                setCurrentSkin(Bitmap.createScaledBitmap(getMyCurrentSkinBank().get(3), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true));
        }
    }

    public void setStatus(String str) {
        this.status = str;
        this.displayStatus = true;
    }

    //positional conditionals
    public boolean isAirBorn() {
        return playerPhysics.getY() < Scale.GROUND;
    }

    public boolean isGrounded() {
        return playerPhysics.getY() > Scale.GROUND;
    }

    public void onJump(float yVelocity, float xVelocity) {
        // this is for scaling purposes (will delete later)
        Log.e(TAG, "onJump: SCREEN_HEIGHT: " + Constants.SCREEN_HEIGHT + "SCREEN_WIDTH: " + Constants.SCREEN_WIDTH);
//        soundEffects.jumpSound();
        if (poisoned) {
            health -= 2;
        }
//        this.yVelocity = yVelocity;
//        this.xVelocity = xVelocity;
        playerPhysics.setYVelocity(yVelocity);
        playerPhysics.setXVelocity(xVelocity);
        displayStatus = false;
        jumpCount++;
    }

    public void setJumpCount (int jumpCount) {
        this.jumpCount = jumpCount;
    }

    public void minusJumpCount() {jumpCount--;}

    public int getJumpCount() {
        return jumpCount;
    }

    public float getJumpPower() {
        return jumpPower;
    }

    public static void setJumpPower(int jumpPower) {
        Player.jumpPower = (jumpPower / 4f);
    }

    public void ricochetDamage(){
        if (isRainInUse()){
            if (playerPhysics.getYVelocity() > -playerPhysics.getNormieJump()/3){
                playerPhysics.setYVelocity((playerPhysics.getYVelocity() * -1) / 6f);
                if (Math.abs(playerPhysics.getXVelocity()) > Scale.UNIT/6f)
                    playerPhysics.setXVelocity(playerPhysics.getDirection()*Scale.UNIT/6f);
                else
                    playerPhysics.setXVelocity(playerPhysics.getXVelocity() * 1.5f);
            }
            else {
                playerPhysics.setYVelocity(0);
                playerPhysics.setXVelocity(0);
            }
        } else {
            if (playerPhysics.getYVelocity() > -playerPhysics.getNormieJump()/3){
                playerPhysics.setYVelocity((playerPhysics.getYVelocity() * -1) / 6f);
            }
            else {
                playerPhysics.setYVelocity(0);
                playerPhysics.setXVelocity(0);
            }
        }
    }

    //while holding down
    public void ricochetDownDamage(){
        if (isRainInUse()){
            if (playerPhysics.getYVelocity() > -playerPhysics.getNormieJump()/3){
                playerPhysics.setYVelocity((playerPhysics.getYVelocity() * -1) / 10f);
                if (Math.abs(playerPhysics.getXVelocity()) > Scale.UNIT/6f)
                    playerPhysics.setXVelocity(playerPhysics.getDirection()*Scale.UNIT/6f);
                else
                    playerPhysics.setXVelocity(playerPhysics.getXVelocity() * 1.5f);
            }
            else {
                playerPhysics.setYVelocity(0);
                playerPhysics.setXVelocity(0);
            }
        } else {
            if (playerPhysics.getYVelocity() > -playerPhysics.getNormieJump()/3){
                playerPhysics.setYVelocity((playerPhysics.getYVelocity() * -1) / 10f);
            }
            else {
                playerPhysics.setYVelocity(0);
                playerPhysics.setXVelocity(0);
            }
        }
    }

    //this is for collision. please let this work....
    public Rect getBounds() {
        float left = playerPhysics.getX();
        float top = playerPhysics.getY() - getCurrentSkin().getHeight() + playerPhysics.getYVelocity();
        float right = playerPhysics.getX() + getCurrentSkin().getWidth();
        float bottom = playerPhysics.getY() + (playerPhysics.getYVelocity()*2);

        return new Rect((int)left, (int)top, (int)right, (int)bottom);
    }

    public Rect shadow() {
        float left = playerPhysics.getX() - (playerPhysics.getXVelocity()*2);
        float top = playerPhysics.getY() - getCurrentSkin().getHeight() - (playerPhysics.getYVelocity()*2);
        float right = playerPhysics.getX() + getCurrentSkin().getWidth() - (playerPhysics.getXVelocity()*2);
        float bottom = playerPhysics.getY() - (playerPhysics.getYVelocity()*2);

        return new Rect((int)left, (int)top, (int)right, (int)bottom);
    }

    Rect rect = new Rect();
    Paint paint1 = new Paint();

    public void drawHealth(Canvas canvas){
        //do stuff

        paint1.setColor(Color.BLACK);
        paint1.setTextSize(Constants.SCREEN_WIDTH/25f);
        paint1.setTextAlign(Paint.Align.LEFT);
        animations.drawCenterX(canvas, paint1, "Health: " + getHealth(), rect, (Constants.SCREEN_HEIGHT / 20f) + (Constants.SCREEN_HEIGHT / 20f), 2f);
    }

    //final health bar will be hidden
    //for drawing center text. move to UI Class later.
    //interface

    Paint paint = new Paint();
    Paint shadowPaint = new Paint();
    Paint parachutePaint = new Paint();
    Paint statusPaint = new Paint();

    @Override
    public void draw(Canvas canvas) {
        //health display is temporary

        paint.setColor(Color.BLACK);
        shadowPaint.setColor(Color.BLACK);
        parachutePaint.setColor(Color.RED);
        canvas.drawRect(shadow(), shadowPaint);
        canvas.drawRect(balloon, parachutePaint);
        canvas.drawBitmap(getCurrentSkin(), playerPhysics.getX(), playerPhysics.getY() - getCurrentSkin().getWidth(), null);
        paint.setTextSize(Constants.SCREEN_WIDTH/25f);
        paint.setTextAlign(Paint.Align.LEFT);

        //for status display

        statusPaint.setColor(Color.DKGRAY);
        statusPaint.setTextSize(Constants.SCREEN_WIDTH/25f);
        statusPaint.setTextAlign(Paint.Align.LEFT);
//        canvas.drawText(String.valueOf(health), physics.getX(), physics.getY() - (3*Scale.UNIT/2f), paint);
//        animations.drawCenterX(canvas, paint, "Health: " + health, new Rect(), (Constants.SCREEN_HEIGHT / 25f) + (Constants.SCREEN_HEIGHT / 25f), 2f);
        if (displayStatus) {
            canvas.drawText(status, playerPhysics.getX(), playerPhysics.getY() - (3*Scale.UNIT/2f), statusPaint);
        }

        //make isOutOfLives false on incrementation function in watch ad or natural 1 hour wait time.
        if (health <= 0 && !LevelInterface.gameOver1){
            LevelInterface.gameOver1 = true;
            LevelInterface.gameOverState = 1;
            if (getLives() != 0)
                setLives(-1);
            if (getLives() <= 0)
                HomeInterface.setIsOutOfLives(true);
        }
    }

    @Override
    public void update() {

        playerPhysics.updatePosition();

        //normie collision
        if (playerPhysics.getX() < Constants.SCREEN_WIDTH*Camera.CURRENT_AREA) {
            playerPhysics.setX(Constants.SCREEN_WIDTH*Camera.CURRENT_AREA);
            reverse();

            //arms power
            if (isArmsAttached() && LevelTouchControls.down) {
                jumpCount--;
                setUsingArms(true);
                setBalloonInUse(false);
            }
        }

        if (playerPhysics.getX() > Constants.SCREEN_WIDTH + (Constants.SCREEN_WIDTH*Camera.CURRENT_AREA) - getCurrentSkin().getWidth()) {
            playerPhysics.setX(Constants.SCREEN_WIDTH + (Constants.SCREEN_WIDTH*Camera.CURRENT_AREA) - getCurrentSkin().getWidth());
            reverse();

            //arms power
            if (isArmsAttached() && LevelTouchControls.down) {
                jumpCount--;
                setUsingArms(true);
                setBalloonInUse(false);
            }

        }

        useArms();

        if (isGrounded()) {
            playerPhysics.setY(Scale.GROUND);
            jumpCount = 0;
            setBalloonInUse(false);
            newGround = true;
            ricochetDamage();
        }

        //is air born
        if (isAirBorn() && !isBalloonInUse() && !grounded) {
//            gravity();
            playerPhysics.gravity();
        }

        //this is for rain power up
        if (isRainInUse()){
            getRain().rainCollision(this, getRain());
        }

        if (isBalloonInUse()) {
            balloon.bottom = getBounds().top;
            balloon.top = balloon.bottom - (Scale.UNIT*2);
            balloon.left = getBounds().left - (Scale.UNIT/2);
            balloon.right = balloon.left + (Scale.UNIT*2);
        } else {
            balloonPhysics.falling(balloon, Scale.UNIT*2);
        }
    }
}