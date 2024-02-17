package com.example.themarblegame.obstacles;

import android.graphics.Rect;
import com.example.themarblegame.main.Constants;
import com.example.themarblegame.main.Scale;

public class RainDrops {
    private Rect rainDrop;
    private int ySpeed;
    private int x;
    private int y;
    private int sizeX;
    private int sizeY;

    public RainDrops(){
        x = (int) (Math.random() * Constants.SCREEN_WIDTH);
        ySpeed = (int) (Scale.UNIT/4.5 + (Math.random() * Scale.UNIT/2.5));
        y = (int) (-Constants.SCREEN_HEIGHT*1.5 + (Math.random() * -Constants.SCREEN_HEIGHT));
        if (ySpeed > Scale.UNIT/3){
            sizeX = Scale.UNIT/20;
            sizeY = Scale.UNIT/2;
        } else if (ySpeed > Scale.UNIT/4) {
            sizeX = Scale.UNIT/23;
            sizeY = (int) (Scale.UNIT/2.25);
        } else {
            sizeX = Scale.UNIT/26;
            sizeY = (int) (Scale.UNIT/2.5);
        }
        rainDrop = new Rect(x, y, x+sizeX, y+sizeY);
    }

    public Rect getRainDrop() {
        return rainDrop;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}