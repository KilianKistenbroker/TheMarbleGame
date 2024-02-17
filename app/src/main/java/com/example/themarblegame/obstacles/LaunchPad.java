package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.Constants;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.LaunchPadManager;

public class LaunchPad implements GameDesigner {
    private Rect launchpad;

    public LaunchPad(int x, int y) {
        this.launchpad = new Rect(Scale.UNIT*x, Scale.GROUND-Scale.UNIT*y, Scale.UNIT*x + Scale.UNIT, Scale.GROUND-Scale.UNIT*y + Scale.UNIT);
    }

    public static void launchPadCollision(Player player) {
        for (int i = 0; i < LaunchPadManager.getLaunchPads().size(); i++) {
            if (Rect.intersects(player.getBounds(), LaunchPadManager.getLaunchPads().get(i).launchpad)) {
                if (player.playerPhysics.getYVelocity() > 0) { //falling
//                    playerPhysics.setY(LaunchPadManager.getLaunchPads().get(i).launchpad.top);
//                    playerPhysics.setGrounded(true);
//                    playerPhysics.setNewGround(true);
                    player.setJumpCount(0);
                    player.setBalloonInUse(false);
                    player.setUsingArms(false);
                    player.onJump(-Constants.SCREEN_WIDTH / 25f, (player.playerPhysics.getDirection() * Constants.SCREEN_WIDTH / 180f));
                }
            }
        }
    }

    Paint paint = new Paint();

    @Override
    public void draw(Canvas canvas) {

        paint.setColor(Color.BLUE);
        canvas.drawRect(launchpad, paint);
    }

    @Override
    public void update() {

    }
}
