package com.example.themarblegame.main;

import android.graphics.Canvas;

import com.example.themarblegame.R;
import com.example.themarblegame.obstacles.Portrait;

public class Background {
     /*
    this class is in charge of making backgrounds move.
    this will be defined in game panel, and implemented
    in scene manager.
     */

    //will add more later
    private final Portrait clearDayStillGround;
    private final Portrait greenMountainBackground;
    private final Portrait greenMountainMidGround;

    public final Portrait cloud1;
    public final Portrait cloud2;
    public final Portrait cloud3;

    public final Physics cloud1Physics = new Physics();
    public final Physics cloud2Physics = new Physics();
    public final Physics cloud3Physics = new Physics();

    public Background() {
//        this.foreground = foreground;
        clearDayStillGround = new Portrait(0,0,Constants.SCREEN_WIDTH, Scale.UNIT*29, R.drawable.stillground, false);
        greenMountainBackground = new Portrait(0,Constants.SCREEN_HEIGHT - Scale.UNIT * 28,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, R.drawable.background, false);
        greenMountainMidGround = new Portrait(0,Constants.SCREEN_HEIGHT - Scale.UNIT * 28,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, R.drawable.midground, false);

        cloud1 = new Portrait(Scale.UNIT, Scale.GROUND-Scale.UNIT*13, Scale.UNIT * 7, Scale.GROUND-Scale.UNIT*10, R.drawable.cloud1, false);
        cloud2 = new Portrait(Scale.UNIT*7, Scale.GROUND-Scale.UNIT*12, Scale.UNIT * 13, Scale.GROUND-Scale.UNIT*9, R.drawable.cloud2, false);
        cloud3 = new Portrait(Scale.UNIT*4, (int) (Scale.GROUND-Scale.UNIT*11), Scale.UNIT * 7, (int) (Scale.GROUND-Scale.UNIT*8), R.drawable.cloud3, false);
    }

    public void drawBackground(Canvas canvas, String stillGround, String background, String midGround, float SCROLL) {
        //will adjust later

        switch (stillGround) {
            case "clear day":
                canvas.drawBitmap(clearDayStillGround.getScaledBitmap(), null, clearDayStillGround.getRect(), null);
                break;
        }

        switch (background) {
            case "green mountains":
                canvas.translate(0, SCROLL/10);
                canvas.drawBitmap(cloud1.getScaledBitmap(), null, cloud1.getRect(), null);
                canvas.drawBitmap(greenMountainBackground.getScaledBitmap(), null, greenMountainBackground.getRect(), null);
                canvas.drawBitmap(cloud2.getScaledBitmap(), null, cloud2.getRect(), null);
                canvas.drawBitmap(cloud3.getScaledBitmap(), null, cloud3.getRect(), null);
                canvas.translate(0, -SCROLL/10);
                break;
        }

        switch (midGround) {
            case "green mountains":
                canvas.translate(0, SCROLL/8);
                canvas.drawBitmap(greenMountainMidGround.getScaledBitmap(), null, greenMountainMidGround.getRect(), null);
                canvas.translate(0, -SCROLL/8);
                break;
        }
    }

    public void updateBackground() {
//        cloud1Physics.driftLeft(cloud1.getRect(), Scale.UNIT*5, Scale.UNIT/30f);
//        cloud2Physics.driftLeft(cloud2.getRect(), Scale.UNIT*5, Scale.UNIT/25f);
//        cloud3Physics.driftLeft(cloud3.getRect(), Scale.UNIT*2, Scale.UNIT/30f);
    }
}
