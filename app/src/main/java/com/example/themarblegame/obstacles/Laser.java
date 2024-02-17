package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.LevelInterface;
import com.example.themarblegame.main.Scale;

public class Laser implements GameDesigner {
    private String name;
    private Rect laser1;
    private Rect laser2;
    private Rect laser3;
    private Rect laser4;
    private Rect laser5;
    private Rect laser6;
    public Laser(String name, int x, int y, int bottom) {
        this.name = name;
        if (this.name.equalsIgnoreCase("medium")){
            this.laser1 = new Rect(Scale.UNIT*x, Scale.GROUND-Scale.UNIT*y + (Scale.UNIT/2), Scale.UNIT*x + (Scale.UNIT/15), bottom);
            this.laser2 = new Rect((Scale.UNIT*x + Scale.UNIT) - (Scale.UNIT/15), Scale.GROUND-Scale.UNIT*y + (Scale.UNIT/2), Scale.UNIT*x + Scale.UNIT, bottom);
            this.laser3 = new Rect(Scale.UNIT*x, Scale.GROUND-Scale.UNIT*y + (Scale.UNIT/2), Scale.UNIT*x + (Scale.UNIT/15), bottom);
            this.laser5 = new Rect(Scale.UNIT*x, Scale.GROUND-Scale.UNIT*y + (Scale.UNIT/2), Scale.UNIT*x + (Scale.UNIT/15), bottom);
            this.laser4 = new Rect((Scale.UNIT*x + Scale.UNIT) - (Scale.UNIT/15), Scale.GROUND-Scale.UNIT*y + (Scale.UNIT/2), Scale.UNIT*x + Scale.UNIT, bottom);
            this.laser6 = new Rect((Scale.UNIT*x + Scale.UNIT) - (Scale.UNIT/15), Scale.GROUND-Scale.UNIT*y + (Scale.UNIT/2), Scale.UNIT*x + Scale.UNIT, bottom);
        } else {
            this.laser1 = new Rect(Scale.UNIT*x, Scale.GROUND-Scale.UNIT*y + (Scale.UNIT/2), Scale.UNIT*x + (Scale.UNIT/15), bottom);
            this.laser2 = new Rect((Scale.UNIT*x + Scale.UNIT) - (Scale.UNIT/15), Scale.GROUND-Scale.UNIT*y + (Scale.UNIT/2), Scale.UNIT*x + Scale.UNIT, bottom);
        }
    }

    public Rect getLaser1() {
        return laser1;
    }

    public Rect getLaser2() {
        return laser2;
    }

    public Rect getLaser3() {
        return laser3;
    }

    public Rect getLaser4() {
        return laser4;
    }

    public Rect getLaser5() {
        return laser5;
    }

    public Rect getLaser6() {
        return laser6;
    }

    public void slowSingleLaser() {
        if (!LevelInterface.pauseMenuSelected){
            laser1.top += 5;
            laser1.bottom += 5;
            laser2.top += 5;
            laser2.bottom += 5;
        }
    }

    public void fastDoubleLaser() {
        if (!LevelInterface.pauseMenuSelected){
            laser1.top += 20;
            laser1.bottom += 20;
            laser2.top += 20;
            laser2.bottom += 20;
        }
    }

    public void setCounter(boolean counter) {
        this.counter = counter;
    }

    public void setCounter1(boolean counter1) {
        this.counter1 = counter1;
    }

    boolean counter = false;
    boolean counter1 = false;
    public void mediumMultiLaser(Rect enemy) {
        if (!LevelInterface.pauseMenuSelected){
            laser1.top += 4;
            laser1.bottom += 4;
            laser2.top += 4;
            laser2.bottom += 4;

            if (!counter) {
                laser3.left = enemy.left;
                laser3.right = laser3.left + Scale.UNIT/15;
                laser3.top = enemy.top + Scale.UNIT/2;
                laser3.bottom = enemy.bottom;

                laser4.right = enemy.right;
                laser4.left = laser4.right - Scale.UNIT/15;
                laser4.top = enemy.top + Scale.UNIT/2;
                laser4.bottom = enemy.bottom;
            }
            if (!counter1) {
                laser5.left = enemy.left;
                laser5.right = laser5.left + Scale.UNIT/15;
                laser5.top = enemy.top + Scale.UNIT/2;
                laser5.bottom = enemy.bottom;

                laser6.right = enemy.right;
                laser6.left = laser6.right - Scale.UNIT/15;
                laser6.top = enemy.top + Scale.UNIT/2;
                laser6.bottom = enemy.bottom;
            }
            if (counter) {
                laser3.top += 4;
                laser3.bottom += 4;
                laser4.top += 4;
                laser4.bottom += 4;
            }
            if (counter1) {
                laser5.top += 4;
                laser5.bottom += 4;
                laser6.top += 4;
                laser6.bottom += 4;
            }
            if (laser1.top > enemy.bottom + (Scale.UNIT) && laser2.top > enemy.bottom + (Scale.UNIT)){
                counter = true;
            }
            if (laser3.top > enemy.bottom + (Scale.UNIT) && laser4.top > enemy.bottom + (Scale.UNIT)) {
                counter1 = true;
            }
        }
    }

    Paint paint = new Paint();

    public void draw(Canvas canvas, Laser laser) {

        paint.setColor(Color.RED);
        if (laser.name.equalsIgnoreCase("medium")){
            canvas.drawRect(laser1, paint);
            canvas.drawRect(laser2, paint);
            canvas.drawRect(laser3, paint);
            canvas.drawRect(laser4, paint);
            canvas.drawRect(laser5, paint);
            canvas.drawRect(laser6, paint);
        } else {
            canvas.drawRect(laser1, paint);
            canvas.drawRect(laser2, paint);
        }

    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void update() {

    }
}