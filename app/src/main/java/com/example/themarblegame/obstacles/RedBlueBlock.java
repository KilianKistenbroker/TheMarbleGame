package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.themarblegame.main.LevelTouchControls;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.PlatformManager;
import com.example.themarblegame.managers.RedBlueBlockManager;

public class RedBlueBlock {
    private static boolean counter = true;
    public static boolean isRed = true;
    private Rect switchBlock;
    private Rect redBlock;
    private Rect blueBlock;
    private int l = 1;
    private int t = 1;
    private int r = 1;
    private int b = 1;

    //ceiling == 1 will put button on ceiling
    public RedBlueBlock(int startX, int startY, int ceiling) {
        if (ceiling == 1)
            this.switchBlock = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, Scale.UNIT*startX + Scale.UNIT, Scale.GROUND-Scale.UNIT*startY + (Scale.UNIT / 2));
        else
            this.switchBlock = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY + (Scale.UNIT/2), Scale.UNIT*startX + Scale.UNIT, Scale.GROUND-Scale.UNIT*startY + (Scale.UNIT));
    }

    public RedBlueBlock(String color, int startX, int startY, int endX, int height) {
        //assign block
        if (color.equalsIgnoreCase("red"))
            this.redBlock = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, endX, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT*height);
        else
            this.blueBlock = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, endX, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT*height);
    }

    public RedBlueBlock(String color, int startX, int startY, int endX, int height, int l, int t, int r, int b) {
        //assign block
        if (color.equalsIgnoreCase("red"))
            this.redBlock = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, endX, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT*height);
        else
            this.blueBlock = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, endX, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT*height);
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;
    }

    public static boolean checkSwitch(Player player) {
        for (int i = 0; i < RedBlueBlockManager.getSwitchBlocks().size(); i++)
            if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getSwitchBlocks().get(i).switchBlock)){
                return false;
        }
        return true;
    }

    public static void switchBlocksCollision(Player player) {
        //switch collision here
        for (int i = 0; i < RedBlueBlockManager.getSwitchBlocks().size(); i++) {
            if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getSwitchBlocks().get(i).switchBlock) && counter) {
                counter = false;
                isRed = !isRed;
            } else if (checkSwitch(player))
                counter = true;
        }

            // red and blue block collision
            if (isRed) {
                for (int i = 0; i < RedBlueBlockManager.getRedBlocks().size(); i++) {

                    //do redBlock collision
                    if (RedBlueBlockManager.getRedBlocks().get(i).b == 1) {
                        if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getRedBlocks().get(i).redBlock) && player.getBounds().bottom > RedBlueBlockManager.getRedBlocks().get(i).redBlock.bottom) {
                            if (player.playerPhysics.getYVelocity() < 0) { //climbing
                                player.playerPhysics.setYVelocity(0);
                                player.playerPhysics.setY((RedBlueBlockManager.getRedBlocks().get(i).redBlock.bottom + player.getCurrentSkin().getHeight()));
                            }
                        }
                    }
                    if (RedBlueBlockManager.getRedBlocks().get(i).r == 1) {
                        if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getRedBlocks().get(i).redBlock) && player.getBounds().right - (Scale.UNIT / 1.35) > RedBlueBlockManager.getRedBlocks().get(i).redBlock.right) {
                            if (player.playerPhysics.getXVelocity() < 0) { //going left
                                player.reverse();
                                player.playerPhysics.setX((RedBlueBlockManager.getRedBlocks().get(i).redBlock.right));
                                if (player.isArmsAttached() && LevelTouchControls.down) {
                                    player.setUsingArms(true);
                                    player.setBalloonInUse(false);
                                    player.minusJumpCount();
                                    player.grabbingLimit = RedBlueBlockManager.getRedBlocks().get(i).redBlock.bottom;
                                }
                            }
                        }
                    }
                    if (RedBlueBlockManager.getRedBlocks().get(i).l == 1) {
                        if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getRedBlocks().get(i).redBlock) && player.getBounds().left + (Scale.UNIT / 1.35) < RedBlueBlockManager.getRedBlocks().get(i).redBlock.left) {
                            if (player.playerPhysics.getXVelocity() > 0) { //right
                                player.reverse();
                                player.playerPhysics.setX((RedBlueBlockManager.getRedBlocks().get(i).redBlock.left - player.getCurrentSkin().getWidth()));
                                if (player.isArmsAttached() && LevelTouchControls.down) {
                                    player.setUsingArms(true);
                                    player.setBalloonInUse(false);
                                    player.minusJumpCount();
                                    player.grabbingLimit = RedBlueBlockManager.getRedBlocks().get(i).redBlock.bottom;
                                }
                            }
                        }
                    }
                    if (RedBlueBlockManager.getRedBlocks().get(i).t == 1) {
                        if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getRedBlocks().get(i).redBlock) && player.getBounds().top < RedBlueBlockManager.getRedBlocks().get(i).redBlock.top) {
                            if (player.playerPhysics.getYVelocity() > 0) { //falling
                                player.playerPhysics.setY((RedBlueBlockManager.getRedBlocks().get(i).redBlock.top));
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
                    }
                     else
                        player.setGrounded(false);

                    if (player.isUsingArms() && player.getBounds().top > player.grabbingLimit)
                        player.setUsingArms(false);
                }

            } else {
                for (int i = 0; i < RedBlueBlockManager.getBlueBlocks().size(); i++) {
                    //do blueBlock collision
                    if (RedBlueBlockManager.getBlueBlocks().get(i).b == 1) {
                        if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getBlueBlocks().get(i).blueBlock) && player.getBounds().bottom > RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.bottom) {
                            if (player.playerPhysics.getYVelocity() < 0) { //climbing
                                player.playerPhysics.setYVelocity(0);
                                player.playerPhysics.setY((RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.bottom + player.getCurrentSkin().getHeight()));
                            }
                        }
                    }
                    if (RedBlueBlockManager.getBlueBlocks().get(i).r == 1) {
                        if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getBlueBlocks().get(i).blueBlock) && player.getBounds().right - (Scale.UNIT/1.35) > RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.right) {
                            if (player.playerPhysics.getXVelocity() < 0) { //left
                                player.reverse();
                                player.playerPhysics.setX((RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.right));
                                if (player.isArmsAttached() && LevelTouchControls.down) {
                                    player.setUsingArms(true);
                                    player.setBalloonInUse(false);
                                    player.minusJumpCount();
                                    player.grabbingLimit = RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.bottom;
                                }
                            }
                        }
                    }
                    if (RedBlueBlockManager.getBlueBlocks().get(i).l == 1) {
                        if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getBlueBlocks().get(i).blueBlock) && player.getBounds().left  + (Scale.UNIT/1.35) < RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.left){
                            if (player.playerPhysics.getXVelocity() > 0){ //right
                                player.reverse();
                                player.playerPhysics.setX((RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.left - player.getCurrentSkin().getWidth()));
                                if (player.isArmsAttached() && LevelTouchControls.down) {
                                    player.setUsingArms(true);
                                    player.setBalloonInUse(false);
                                    player.minusJumpCount();
                                    player.grabbingLimit = RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.bottom;
                                }
                            }
                        }
                    }
                    if (RedBlueBlockManager.getBlueBlocks().get(i).t == 1) {
                        if (Rect.intersects(player.getBounds(), RedBlueBlockManager.getBlueBlocks().get(i).blueBlock) && player.getBounds().top < RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.top) {
                            if (player.playerPhysics.getYVelocity() > 0) { //falling
                                player.playerPhysics.setY((RedBlueBlockManager.getBlueBlocks().get(i).blueBlock.top));
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
                    }
                    else
                        player.setGrounded(false);

                    if (player.isUsingArms() && player.getBounds().top > player.grabbingLimit)
                        player.setUsingArms(false);
                }
            }
    }
    Paint switchPaint = new Paint();
    Paint redPaint = new Paint();
    Paint bluePaint = new Paint();

    public void drawSwitch(Canvas canvas) {
        switchPaint.setColor(Color.DKGRAY);
        canvas.drawRect(switchBlock, switchPaint);
    }

    public void drawRed(Canvas canvas) {
        if (isRed)
            redPaint.setColor(Color.RED);
        else
            redPaint.setColor(Color.LTGRAY);
        canvas.drawRect(redBlock, redPaint);
    }

    public void drawBlue(Canvas canvas) {
        if (isRed)
            bluePaint.setColor(Color.LTGRAY);
        else
            bluePaint.setColor(Color.BLUE);
        canvas.drawRect(blueBlock, bluePaint);
    }
}
