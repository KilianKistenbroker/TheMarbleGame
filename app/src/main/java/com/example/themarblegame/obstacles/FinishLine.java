package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.LevelInterface;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.PlatformManager;

public class FinishLine implements GameDesigner {

    private Rect finishLine;

    public FinishLine(int left) {
        finishLine = new Rect(left, Scale.GROUND - Scale.UNIT*85, left + Scale.UNIT*3, Scale.GROUND - Scale.UNIT*84);
    }
    public FinishLine(int left, int top, int right, int bottom) {
        finishLine = new Rect(left, top, right, bottom);
    }

    public Rect getFinishLine() {
        return finishLine;
    }

    //collision logic goes here and triggers win screen UI
    public void finishLineCollision(Player player) {
        for (int i = 0; i < PlatformManager.getPlatforms().size(); i++) {
            if (Rect.intersects(player.getBounds(), finishLine) && player.getBounds().right - (Scale.UNIT/1.35) > finishLine.right) {
                if (player.playerPhysics.getXVelocity() < 0) { //left
                    player.reverse();
                    player.playerPhysics.setX((finishLine.right));
                }
            }
            if (Rect.intersects(player.getBounds(), finishLine) && player.getBounds().left  + (Scale.UNIT/1.35) < finishLine.left){
                if (player.playerPhysics.getXVelocity() > 0){ //right
                    player.reverse();
                    player.playerPhysics.setX((finishLine.left - player.getCurrentSkin().getWidth()));
                }
            }
            if (Rect.intersects(player.getBounds(), finishLine) && player.getBounds().top < finishLine.top) {
                if (player.playerPhysics.getYVelocity() > 0) { //falling
                    player.playerPhysics.setY((finishLine.top));
                    player.setGrounded(true);
                    player.setNewGround(true);
                    player.setJumpCount(0);
                    player.setBalloonInUse(false);
                    player.ricochetDamage();
                    if (!LevelInterface.levelComplete && !LevelInterface.gameOver1) {
                        LevelInterface.levelComplete = true;
                        LevelInterface.levelCompleteState = 1;
                    }
                }
            }
            if (Rect.intersects(player.getBounds(), finishLine) && player.getBounds().bottom > finishLine.bottom) {
                if (player.playerPhysics.getYVelocity() < 0) { //climbing
                    player.playerPhysics.setYVelocity(0);
                    player.playerPhysics.setY((finishLine.bottom + player.getCurrentSkin().getHeight()));
                }
            }
            else
                player.setGrounded(false);
        }
    }

    Paint paint = new Paint();

    @Override
    public void draw(Canvas canvas) {

        paint.setColor(Color.CYAN);
        canvas.drawRect(finishLine, paint);
    }

    @Override
    public void update() {

    }
}
