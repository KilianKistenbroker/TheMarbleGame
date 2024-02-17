package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.Camera;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.DoorWayManager;

public class DoorWay implements GameDesigner {
    private Rect doorWay1, doorWay2;
    private static boolean counter = true;
    private int a1, a2;

    public DoorWay(int x1, int y1, int a1, int x2, int y2, int a2) {
        this.doorWay1 = new Rect(Scale.UNIT*x1, Scale.GROUND-Scale.UNIT*y1, Scale.UNIT*x1 + Scale.UNIT, Scale.GROUND-Scale.UNIT*y1 + (2*Scale.UNIT));
        this.doorWay2 = new Rect(Scale.UNIT*x2, Scale.GROUND-Scale.UNIT*y2, Scale.UNIT*x2 + Scale.UNIT, Scale.GROUND-Scale.UNIT*y2 + (2*Scale.UNIT));
        this.a1 = a1;
        this.a2 = a2;
    }

    public Rect getDoorWay1() {
        return doorWay1;
    }

    public Rect getDoorWay2() {
        return doorWay2;
    }

    public boolean isCounter() {
        return counter;
    }

    public static boolean checkCounter(Player player) {
        for (int i = 0; i < DoorWayManager.getDoorWays().size(); i++) {
            if (Rect.intersects(player.getBounds(), DoorWayManager.getDoorWays().get(i).doorWay1))
                return false;
            if (Rect.intersects(player.getBounds(), DoorWayManager.getDoorWays().get(i).doorWay2))
                return false;
        }
        return true;
    }

    //write a boolean function similar to switch check
    //camera needs to center
    public static void doorWayCollision(Player player) {
        for (int i = 0; i < DoorWayManager.getDoorWays().size(); i++) {
            if (Rect.intersects(player.getBounds(), DoorWayManager.getDoorWays().get(i).doorWay1) && counter) {
                player.setUsingArms(false);
                player.playerPhysics.setX((DoorWayManager.getDoorWays().get(i).doorWay2.left));
                player.playerPhysics.setY((DoorWayManager.getDoorWays().get(i).doorWay2.top + (Scale.UNIT)));
                if (player.isBalloonInUse())
                    player.playerPhysics.setYVelocity(player.playerPhysics.getGRAVITY()/2);
                else
                    player.playerPhysics.setYVelocity(0);

//                if (Camera.CURRENT_AREA != DoorWayManager.getDoorWays().get(i).a2)
                Camera.changeAreaTo(DoorWayManager.getDoorWays().get(i).a2);
                counter = false;
            }
            else if (Rect.intersects(player.getBounds(), DoorWayManager.getDoorWays().get(i).doorWay2) && counter){
                player.setUsingArms(false);
                player.playerPhysics.setX((DoorWayManager.getDoorWays().get(i).doorWay1.left));
                player.playerPhysics.setY((DoorWayManager.getDoorWays().get(i).doorWay1.top + (Scale.UNIT)));
                if (player.isBalloonInUse())
                    player.playerPhysics.setYVelocity(player.playerPhysics.getGRAVITY()/2);
                else
                    player.playerPhysics.setYVelocity(0);
//                if (Camera.CURRENT_AREA != DoorWayManager.getDoorWays().get(i).a1)
                Camera.changeAreaTo(DoorWayManager.getDoorWays().get(i).a1);
                counter = false;
            }
            else if (checkCounter(player))
                counter = true;
        }
    }

    Paint paint = new Paint();

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(doorWay1, paint);
        canvas.drawRect(doorWay2, paint);
    }

    @Override
    public void update() {

    }
}