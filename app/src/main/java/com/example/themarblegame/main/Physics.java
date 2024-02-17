package com.example.themarblegame.main;

import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;

import com.example.themarblegame.managers.PortraitManager;

import java.util.Timer;
import java.util.TimerTask;

public class Physics {
    private final float GRAVITY = (((float) Constants.SCREEN_WIDTH / 600f));
    private final float TERMINAL_VELOCITY = GRAVITY * 100;
    private final float normieJump = (-Constants.SCREEN_WIDTH/(60f));
    private float yVelocity = 0;
    private float xVelocity = 0;
    private float x = 0;
    private float y = 0;
    private int direction = -1;
    public boolean isMoving = false;

    //getters
    public float getGRAVITY() {
        return GRAVITY;
    }

    public float getTERMINAL_VELOCITY() {
        return TERMINAL_VELOCITY;
    }

    public float getNormieJump() {
        return normieJump;
    }

    public float getYVelocity() {
        return yVelocity;
    }

    public float getXVelocity() {
        return xVelocity;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    //setters


    public void setYVelocity(float yVelocity) {
        this.yVelocity = (yVelocity);
    }

    public void setXVelocity(float xVelocity) {
        this.xVelocity = (xVelocity);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void gravity() {
        y += yVelocity;
        yVelocity += (GRAVITY/1.15);
        if (yVelocity >= TERMINAL_VELOCITY/5)
            yVelocity = (TERMINAL_VELOCITY/5);
    }

    public void falling(Rect rect, int height) {
        if (!rect.isEmpty()) {
            rect.top += yVelocity;
            rect.bottom = rect.top + height;
            yVelocity += (GRAVITY);
            if (yVelocity >= TERMINAL_VELOCITY)
                yVelocity = TERMINAL_VELOCITY;
            if (rect.top > Constants.SCREEN_HEIGHT - Camera.getTranslateY()){
                rect.setEmpty();
            }
        }
    }

    public void driftLeft(Rect rect, int width, float speed) {
        if (rect.right < 0) {
            rect.left = Constants.SCREEN_WIDTH;
            rect.right = rect.left + width;
        }
        rect.right -= speed;
        rect.left = rect.right - width;

        //will add int var horizontal screen mode

    }

    public void expandRect(Rect rect, String target, int limit, int velocity) {
        isMoving = true;
        switch (target) {
            case "left":
                if (rect.left > limit)
                    rect.left -= velocity;
                else {
                    rect.left = limit;
                    isMoving = false;
                }
                break;
            case "top":
                if (rect.top > limit)
                    rect.top -= velocity;
                else {
                    rect.top = limit;
                    isMoving = false;
                }
                break;
            case "right":
                if (rect.right < limit)
                    rect.right += velocity;
                else {
                    rect.right = limit;
                    isMoving = false;
                }
                break;
            case "bottom":
                if (rect.bottom < limit)
                    rect.bottom += velocity;
                else {
                    rect.bottom = limit;
                    isMoving = false;
                }
                break;
        }
    }

    int bottom = 0;
    public void travelToCartAnimation(Rect rect) {
        if (bottom == (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4)
            bottom += Scale.UNIT/1.25;
        if (rect.bottom < (int)(Constants.SCREEN_HEIGHT/6 + Scale.UNIT*16.25 + bottom)/2) {
            rect.bottom += yVelocity;
            yVelocity += GRAVITY * 6.25;
        } else if (rect.bottom < (int)((Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*16.25))) {
            if (yVelocity > Scale.UNIT/2f)
                yVelocity -= GRAVITY * 8;
             else
                yVelocity = (Scale.UNIT/2f);
            rect.bottom += yVelocity;
        } else {
            rect.bottom = (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*16.25);
        }
        rect.top = rect.bottom - Scale.UNIT*2;
    }

    public void swipeRightReset(Rect rect) {
        if (rect.left < Constants.SCREEN_WIDTH) {
            rect.left += xVelocity;
            rect.right = rect.left + Scale.UNIT*2;
            xVelocity += GRAVITY*4;
        } else {
            PortraitManager.reset();
            xVelocity = 0;
        }
    }

    public void swipeLeftReset(Rect rect) {
        if (rect.right > 0) {
            rect.right -= xVelocity;
            rect.left = rect.right - Scale.UNIT*2;
            xVelocity += GRAVITY*4;
        } else {
            PortraitManager.reset();
            xVelocity = 0;
        }
    }

    public void leftCornerTravelAnim(Rect rect, int height) {
        if (rect.top > (int) ((Constants.SCREEN_HEIGHT/20f) - Camera.getTranslateY() +  Scale.UNIT)) {
            rect.top -= yVelocity;
        } else {
            rect.top = (int) ((Constants.SCREEN_HEIGHT/20f) - Camera.getTranslateY() + Scale.UNIT);
        }
        rect.bottom = rect.top + height;
        if (rect.left > Scale.UNIT) {
            rect.left -= xVelocity;
        } else {
            rect.left = Scale.UNIT;
        }
        rect.right = rect.left + height;
        if (rect.top == (int) ((Constants.SCREEN_HEIGHT/20f) - Camera.getTranslateY() + Scale.UNIT) && rect.left == Scale.UNIT) {
            rect.setEmpty();
        }
        yVelocity += (GRAVITY);
        xVelocity += (GRAVITY/2);
        if (rect.top < (int) ((Constants.SCREEN_HEIGHT/2f) - Camera.getTranslateY())){
            xVelocity += (GRAVITY/2);
        }
        if (rect.left < (Constants.SCREEN_WIDTH/2f) + (Constants.SCREEN_WIDTH*Camera.CURRENT_AREA)) {
            yVelocity += (GRAVITY);
        }
        if (yVelocity >= TERMINAL_VELOCITY/4)
            yVelocity = TERMINAL_VELOCITY/4;
        if (xVelocity >= TERMINAL_VELOCITY/4)
            xVelocity = TERMINAL_VELOCITY/4;
    }


    public void altLeftCornerTravelAnim(Rect rect, int height) {
        if (rect.top > (int) ((Constants.SCREEN_HEIGHT/20f) - Camera.getTranslateY() +  Scale.UNIT)) {
            rect.top -= yVelocity;

        } else {
            rect.top = (int) ((Constants.SCREEN_HEIGHT/20f) - Camera.getTranslateY() + Scale.UNIT);
        }
        rect.bottom = rect.top + height;
        if (rect.left < Scale.UNIT) {
            rect.left += xVelocity;
        } else {
            rect.left = Scale.UNIT;
        }
        rect.right = rect.left + height;
        if (rect.top == (int) ((Constants.SCREEN_HEIGHT/20f) - Camera.getTranslateY() + Scale.UNIT) && rect.left == Scale.UNIT) {
            rect.setEmpty();
        }
        yVelocity += (GRAVITY);
        xVelocity += (GRAVITY/2);
        if (yVelocity >= TERMINAL_VELOCITY/4)
            yVelocity = TERMINAL_VELOCITY/4;
        if (xVelocity >= TERMINAL_VELOCITY/4)
            xVelocity = TERMINAL_VELOCITY/4;
    }


    public void swipeLeftToMiddle(Rect rect, int width, int limit) {
        isMoving = true;
        if (rect.right < limit/2) {
            rect.right += xVelocity;
            rect.left = rect.right - width;
            xVelocity += GRAVITY*6;
        } else if (rect.right < limit) {
            if (xVelocity > Scale.UNIT/2f)
                xVelocity -= GRAVITY*6.75;
            else
                xVelocity = Scale.UNIT/2f;
            rect.right += xVelocity;
            rect.left = rect.right - width;
        } else {
            rect.right = limit;
            rect.left = rect.right - width;
            xVelocity = 0;
            isMoving = false;
        }
    }

    public void swipeMiddleToLeft(Rect rect, int width) {
        isMoving = true;
        if (rect.right > 0) {
            rect.right -= xVelocity;
            rect.left = rect.right - width;
            xVelocity += GRAVITY*4;
        } else {
            rect.right = 0;
            rect.left = rect.right - width;
            xVelocity = 0;
            isMoving = false;
        }
    }

    public void swipeRightToMiddle(Rect rect, int width, int limit) {
        isMoving = true;
        if (rect.left > (Constants.SCREEN_WIDTH + limit) /2) {
            rect.left -= xVelocity;
            rect.right = rect.left + width;
            xVelocity += GRAVITY*6;
        } else if (rect.left > limit) {
            if (xVelocity > Scale.UNIT/2f)
                xVelocity -= GRAVITY*6.75;
            else
                xVelocity = Scale.UNIT/2f;
            rect.left -= xVelocity;
            rect.right = rect.left + width;
        } else {
            rect.left = limit;
            rect.right = rect.left + width;
            xVelocity = 0;
            isMoving = false;
        }
    }

    public void swipeMiddleToRight(Rect rect, int width) {
        isMoving = true;
        if (rect.left < Constants.SCREEN_WIDTH) {
            rect.left += xVelocity;
            rect.right = rect.left + width;
            xVelocity += GRAVITY*4;
        } else {
            rect.left = Constants.SCREEN_WIDTH;
            rect.right = rect.left + width;
            xVelocity = 0;
            isMoving = false;
        }
    }

    //CUSTOM ACCELERATION
    public void swipeLeftToMiddle(Rect rect, int width, int limit, double acceleration) {
        isMoving = true;
        if (rect.right < limit/2) {
            rect.right += xVelocity;
            rect.left = rect.right - width;
            xVelocity += acceleration;
        } else if (rect.right < limit) {
            if (xVelocity > Scale.UNIT/20f)
                xVelocity -= acceleration*1.25f;
            else
                xVelocity = Scale.UNIT/20f;
            rect.right += xVelocity;
            rect.left = rect.right - width;
        }
        else {
            rect.right = limit;
            rect.left = rect.right - width;
            xVelocity = 0;
            isMoving = false;
        }
    }

    public void swipeMiddleToLeft(Rect rect, int width, int acceleration) {
        isMoving = true;
        if (rect.right > 0) {
            rect.right -= xVelocity;
            rect.left = rect.right - width;
            xVelocity += acceleration;
        } else {
            rect.right = 0;
            rect.left = rect.right - width;
            xVelocity = 0;
            isMoving = false;
        }
    }

    public void swipeRightToMiddle(Rect rect, int width, int limit, double acceleration) {
        isMoving = true;
        if (rect.left > (Constants.SCREEN_WIDTH + limit) /2) {
            rect.left -= xVelocity;
            rect.right = rect.left + width;
            xVelocity += acceleration;
        } else if (rect.left > limit) {
            if (xVelocity > Scale.UNIT/80f)
                xVelocity -= acceleration*1.2f;
            else
                xVelocity = Scale.UNIT/80f;
            rect.left -= xVelocity;
            rect.right = rect.left + width;
        } else {
            rect.left = limit;
            rect.right = rect.left + width;
            xVelocity = 0;
            isMoving = false;
        }
    }

    public void swipeMiddleToRight(Rect rect, int width, int acceleration) {
        isMoving = true;
        if (rect.left < Constants.SCREEN_WIDTH) {
            rect.left += xVelocity;
            rect.right = rect.left + width;
            xVelocity += acceleration;
        } else {
            rect.left = Constants.SCREEN_WIDTH;
            rect.right = rect.left + width;
            xVelocity = 0;
            isMoving = false;
        }
    }

    public void swipeUp(Rect rect, int height, int limit) {
        isMoving = true;
        if (rect.top > limit) {
            rect.top -= Constants.SCREEN_WIDTH/24f;
            rect.bottom = rect.top + height;
        } else {
            rect.top = limit;
            rect.bottom = rect.top + height;
            isMoving = false;
        }
    }

    public void swipeUp(Rect rect, int height, int limit, int speed) {
        isMoving = true;
        if (rect.top > limit) {
            rect.top -= speed;
            rect.bottom = rect.top + height;
        } else {
            rect.top = limit;
            rect.bottom = rect.top + height;
            isMoving = false;
        }
    }

    public void swipeDown(Rect rect, int height, int limit) {
        isMoving = true;
        if (rect.bottom < limit) {
            rect.bottom += Constants.SCREEN_WIDTH/24f;
            rect.top = rect.bottom - height;
        } else {
            rect.bottom = limit;
            rect.top = rect.bottom - height;
            isMoving = false;
        }
    }

    public void updatePosition() {
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }

    public void updatePosition(int limit) {
        if (xVelocity > limit)
            xVelocity = limit;
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }

//    public void ricochet(float yVelocity, float xVelocity){
//        if (PlayerAssets.isRainInUse()){
//            //change to set gameOver true in UI
//            if (yVelocity > -normieJump *3.25){
//                yVelocity = (yVelocity * -1) / 4f;
//                xVelocity *= 1.15;
//            } else if (yVelocity > -normieJump *2.75) {
//                yVelocity = (yVelocity * -1) / 4f;
//                xVelocity *= 1.15;
//            } else if (yVelocity > -normieJump*2.25) {
//                yVelocity = (yVelocity * -1) / 4f;
//                xVelocity *= 1.25;
//            }
//            else if (yVelocity > -normieJump/3){
//                yVelocity = (yVelocity * -1) / 6f;
//                xVelocity *= 1.5;
//            }
//            else {
//                yVelocity = 0;
//                xVelocity = 0;
//            }
//        } else {
//            if (yVelocity > -normieJump *3.25){
//                yVelocity = (yVelocity * -1) / 4f;
//            } else if (yVelocity > -normieJump *2.75) {
//                yVelocity = (yVelocity * -1) / 4f;
//            } else if (yVelocity > -normieJump*2.25) {
//                yVelocity = (yVelocity * -1) / 4f;
//            }
//            else if (yVelocity > -normieJump/3){
//                yVelocity = (yVelocity * -1) / 6f;
//            }
//            else {
//                yVelocity = 0;
//                xVelocity = 0;
//            }
//        }
//    }

    public void reverse() {
        this.direction *= -1;
        this.xVelocity *= -1;
    }
}