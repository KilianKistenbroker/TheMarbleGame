package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.Constants;
import com.example.themarblegame.main.LevelInterface;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.Scale;

public class Rain implements GameDesigner {
    private Rect rain;
    private int x;
    private int y = -10;
    private int i;
    private int ySpeed;
    private RainDrops[] rainDrops;
    private boolean counter = true;

    public Rain() {
        rainDrops = new RainDrops[200];
        populateRain();
    }

    public void populateRain() {
        for (i =0; i < 200; i ++){
            rainDrops[i] = new RainDrops();
        }
    }

    public void rainCollision(Player player, Rain rain) {
        for (int i = 0; i<200; i++){
            if (Rect.intersects(rain.rainDrops[i].getRainDrop(), player.getBounds()) && player.getHealth() < 100 && rain.isCounter()){
                player.setHealth(1);
                counter = false;
            }
            if (!(Rect.intersects(rain.rainDrops[i].getRainDrop(), player.getBounds())))
                rain.setCounter(true);
        }
    }

    public boolean isCounter() {
        return counter;
    }

    public void setCounter(boolean counter) {
        this.counter = counter;
    }

    public void fall() {
        if (!LevelInterface.pauseMenuSelected){
            rainDrops[i].getRainDrop().top +=  rainDrops[i].getYSpeed();
            rainDrops[i].getRainDrop().bottom +=  rainDrops[i].getYSpeed();
        }
    }

    Paint paint = new Paint();

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.rgb(175, 195, 204));
        for (i = 0; i < 200; i++){
            canvas.drawRect(rainDrops[i].getRainDrop(), paint);
            if (rainDrops[i].getRainDrop().top > Constants.SCREEN_HEIGHT){
                rainDrops[i].getRainDrop().top = ((int) (-Constants.SCREEN_HEIGHT*1.5 + (Math.random() * -Constants.SCREEN_HEIGHT)));
                if (rainDrops[i].getYSpeed() > Scale.UNIT/3)
                    rainDrops[i].getRainDrop().bottom = (rainDrops[i].getRainDrop().top + Scale.UNIT/2);
                else if (rainDrops[i].getYSpeed() > Scale.UNIT/4)
                    rainDrops[i].getRainDrop().bottom = (rainDrops[i].getRainDrop().top + (int) (Scale.UNIT/2.25));
                else
                    rainDrops[i].getRainDrop().bottom = (rainDrops[i].getRainDrop().top + (int) (Scale.UNIT/2.5));

            }
            fall();
        }
    }

    @Override
    public void update() {
    }
}
