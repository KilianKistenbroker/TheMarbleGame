package com.example.themarblegame.obstacles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.themarblegame.main.Constants;
import com.example.themarblegame.main.LevelTouchControls;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.PlatformManager;

import java.util.ArrayList;


public class Platform implements GameDesigner{

    private static final String TAG = "";
    private Rect platform;
    private static boolean counter = true;
    private Bitmap platformImage;
    private int l= 1;
    private int t = 1;
    private int r = 1;
    private int b = 1;

//    public Platform (int startX, int startY, int endX, int height) {
//        this.platform = new Rect(startX, startY, endX, startY + height);
//    }

    //step 1: create the guts
    //step 2: no check bc it is too expensive
    //step 3: instead just swap Rect for Portraits and hardcode w/ booleans top, connected, etc.

    public static ArrayList<Rect> platformGuts = new ArrayList<Rect>();

    public Platform (int startX, int startY, int endX, int height, int platformImage) {
        this.platform = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, endX, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT*height);
//        this.platformImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), platformImage), );
    }

    public Platform (int startX, int startY, int endX, int height) {
        this.platform = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, endX, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT*height);
    }

    //for connected platforms, this will eliminate unnecessary collision checks.
    public Platform (int startX, int startY, int endX, int height, int l, int t, int r, int b) {
        this.platform = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, endX, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT*height);
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;

        int xIterator = 0;
        int yIterator = 0;
        for (int i = 0; i < calcWidth(platform) * height; i++) {
            if (xIterator == calcWidth(platform)) {
                yIterator++;
                xIterator = 0;
            }
            platformGuts.add(new Rect(platform.left + Scale.UNIT*xIterator, platform.top + Scale.UNIT*yIterator, platform.left + Scale.UNIT*xIterator + Scale.UNIT, platform.top + Scale.UNIT*yIterator + Scale.UNIT));
            xIterator++;
        }
    }

    private static int calcWidth(Rect rect) {
        int width;

        if (rect.right == Constants.SCREEN_WIDTH)
            width = (Scale.UNIT*14-rect.left)/Scale.UNIT;
        else
            width = (rect.right-rect.left)/Scale.UNIT;

        return width;
    }

    public static void platformCollision(Player player) {

        //this is to prevent grabbing after sliding too far down
        for (int i = 0; i < PlatformManager.getPlatforms().size(); i++) {
            if (PlatformManager.getPlatforms().get(i).b == 1) {
                if (Rect.intersects(player.getBounds(), PlatformManager.getPlatforms().get(i).platform) && player.getBounds().bottom > PlatformManager.getPlatforms().get(i).platform.bottom) {
                    if (player.playerPhysics.getYVelocity() < 0) { //climbing
                        player.playerPhysics.setYVelocity(0);
                        player.playerPhysics.setY((PlatformManager.getPlatforms().get(i).platform.bottom + player.getCurrentSkin().getHeight()));
                    }
                }
            }
            if (PlatformManager.getPlatforms().get(i).r == 1) {
                if (Rect.intersects(player.getBounds(), PlatformManager.getPlatforms().get(i).platform) && player.getBounds().right - (Scale.UNIT/1.35) > PlatformManager.getPlatforms().get(i).platform.right) {
                    if (player.playerPhysics.getXVelocity() < 0) { //going left
                        player.reverse();
                        player.playerPhysics.setX((PlatformManager.getPlatforms().get(i).platform.right));
                        if (player.isArmsAttached() && LevelTouchControls.down) {
                            player.setUsingArms(true);
                            player.setBalloonInUse(false);
                            player.minusJumpCount();
                            player.grabbingLimit = PlatformManager.getPlatforms().get(i).platform.bottom;
                        }
                    }
                }
            }
            if (PlatformManager.getPlatforms().get(i).l == 1) {
                if (Rect.intersects(player.getBounds(), PlatformManager.getPlatforms().get(i).platform) && player.getBounds().left  + (Scale.UNIT/1.35) < PlatformManager.getPlatforms().get(i).platform.left){
                    if (player.playerPhysics.getXVelocity() > 0){ //going right
                        player.reverse();
                        player.playerPhysics.setX((PlatformManager.getPlatforms().get(i).platform.left - player.getCurrentSkin().getWidth()));
                        if (player.isArmsAttached() && LevelTouchControls.down) {
                            player.setUsingArms(true);
                            player.setBalloonInUse(false);
                            player.minusJumpCount();
                            player.grabbingLimit = PlatformManager.getPlatforms().get(i).platform.bottom;
                        }
                    }
                }
            }
            if (PlatformManager.getPlatforms().get(i).t == 1) {
                if (Rect.intersects(player.getBounds(), PlatformManager.getPlatforms().get(i).platform)) {
                    if (player.playerPhysics.getYVelocity() < 0) {
                        counter = false;
                    }
                    else if ( player.getBounds().top < PlatformManager.getPlatforms().get(i).platform.top && player.playerPhysics.getYVelocity() > 0 && counter) { //falling
                        player.playerPhysics.setY((PlatformManager.getPlatforms().get(i).platform.top));
                        player.setGrounded(true);
                        player.setNewGround(true);
                        player.setJumpCount(0);
                        player.setBalloonInUse(false);
                        if (LevelTouchControls.down)
                            player.ricochetDownDamage();
                        else
                            player.ricochetDamage();
                    }
                }
                else if (!Rect.intersects(player.getBounds(), PlatformManager.getPlatforms().get(i).platform)) {
                    counter = true;
                }
            }
            else
                player.setGrounded(false);

            if (player.isUsingArms() && player.getBounds().top > player.grabbingLimit)
                player.setUsingArms(false);
        }
    }

    static Paint paintGuts = new Paint();
    Paint paint = new Paint();

    public static void drawGuts(Canvas canvas) {
        paintGuts.setColor(Color.DKGRAY);
        for (int i = 0; i < platformGuts.size(); i++) {
            canvas.drawRect(platformGuts.get(i), paintGuts);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.BLACK);

        canvas.drawRect(platform, paint);
//        canvas.drawBitmap(platformImage, null, platform, null);
    }

    @Override
    public void update() {
    }
}
