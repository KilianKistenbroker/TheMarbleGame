package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.themarblegame.main.LevelTouchControls;
import com.example.themarblegame.main.Physics;
import com.example.themarblegame.main.PlayerBank;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.CoinBrickBlockManager;
import com.example.themarblegame.managers.PlatformManager;

public class CoinBrickBlock {
    //might have to move coin creation here
    public Rect block;
    private Rect switchBlock;
    private String type;
    private Physics physics = new Physics();
//    public boolean animCounter = false;
    public boolean anim = false;
    public static boolean isCoin = true;
    private static boolean counter = true;
    private int value;
    private int l = 1;
    private int t = 1;
    private int r = 1;
    private int b = 1;

    public CoinBrickBlock(int startX, int startY, int ceiling) {
        if (ceiling == 1)
            this.switchBlock = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, Scale.UNIT*startX + Scale.UNIT, Scale.GROUND-Scale.UNIT*startY + (Scale.UNIT / 2));
        else
            this.switchBlock = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY + (Scale.UNIT / 2), Scale.UNIT*startX + Scale.UNIT, Scale.GROUND-Scale.UNIT*startY + (Scale.UNIT));
    }

    public CoinBrickBlock(String type, int value, int startX, int startY) {
        this.block = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, Scale.UNIT*startX + Scale.UNIT, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT);
        this.type = type;
        this.value = value;
        physics.setX(Scale.UNIT*startX);
        physics.setXVelocity(physics.getNormieJump());
        physics.setYVelocity(physics.getNormieJump());
    }

    public CoinBrickBlock(String type, int value, int startX, int startY, int l, int t, int r, int b) {
        this.block = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, Scale.UNIT*startX + Scale.UNIT, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT);
        this.type = type;
        this.value = value;
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;
        physics.setX(Scale.UNIT*startX);
        physics.setXVelocity(physics.getNormieJump());
        physics.setYVelocity(physics.getNormieJump());
    }

    public static boolean checkSwitch(Player player) {
        for (int i = 0; i < CoinBrickBlockManager.getSwitchBlocks().size(); i++)
            if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getSwitchBlocks().get(i).switchBlock)){
                return false;
            }
        return true;
    }

    public void coinTravel(Rect block){
        if (!block.isEmpty()) {
            if (physics.getX() < Scale.UNIT)
                physics.altLeftCornerTravelAnim(block, Scale.UNIT);
            else
                physics.leftCornerTravelAnim(block, Scale.UNIT);
        }
    }

    public static void coinBrickBlockCollision(Player player) {

        for (int i = 0; i < CoinBrickBlockManager.getSwitchBlocks().size(); i++) {
            if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getSwitchBlocks().get(i).switchBlock) && counter) {
                counter = false;
                isCoin = !isCoin;
            } else if (checkSwitch(player)) {
                counter = true;
            }
        }

        for (int i = 0; i < CoinBrickBlockManager.getCoinBrickBlocks().size(); i++) {
            if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).anim) {
                CoinBrickBlockManager.getCoinBrickBlocks().get(i).coinTravel(CoinBrickBlockManager.getCoinBrickBlocks().get(i).block);
                if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.isEmpty()){
                    PlayerBank.incrementCoinsCollected();
                    PlayerBank.setScore(CoinBrickBlockManager.getCoinBrickBlocks().get(i).value);
                    player.setStatus("Coin " + " +" + CoinBrickBlockManager.getCoinBrickBlocks().get(i).value);
                    CoinBrickBlockManager.getCoinBrickBlocks().get(i).anim = false;
                }
            }

            if (isCoin) {
                //run coin collision
                if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).type.equalsIgnoreCase("coin")){
                    //run og coin collision
                    if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && !CoinBrickBlockManager.getCoinBrickBlocks().get(i).anim){
//                        PlayerAssets.setScore(CoinBrickBlockManager.getCoinBrickBlocks().get(i).value);
//                        CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.setEmpty();
//                        player.setStatus("Coin " + " +" + CoinBrickBlockManager.getCoinBrickBlocks().get(i).value);
                        CoinBrickBlockManager.getCoinBrickBlocks().get(i).anim = true;
                    }
                } else {
                    //run og brick collision
                    if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).b == 1) {
                        if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && player.getBounds().bottom > CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.bottom) {
                            if (player.playerPhysics.getYVelocity() < 0) { //climbing
                                player.playerPhysics.setYVelocity(0);
                                player.playerPhysics.setY((CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.bottom + player.getCurrentSkin().getHeight()));
                            }
                        }
                    }
                    if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).r == 1) {
                        if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && player.getBounds().right - (Scale.UNIT/1.35) > CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.right) {
                            if (player.playerPhysics.getXVelocity() < 0) { //going left
                                player.reverse();
                                player.playerPhysics.setX((CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.right));
                                if (player.isArmsAttached() && LevelTouchControls.down) {
                                    player.setUsingArms(true);
                                    player.setBalloonInUse(false);
                                    player.minusJumpCount();
                                    player.grabbingLimit = CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.bottom;
                                }
                            }
                        }
                    }
                    if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).l == 1) {
                        if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && player.getBounds().left  + (Scale.UNIT/1.35) < CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.left){
                            if (player.playerPhysics.getXVelocity() > 0){ //going right
                                player.reverse();
                                player.playerPhysics.setX((CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.left - player.getCurrentSkin().getWidth()));
                                if (player.isArmsAttached() && LevelTouchControls.down) {
                                    player.setUsingArms(true);
                                    player.setBalloonInUse(false);
                                    player.minusJumpCount();
                                    player.grabbingLimit = CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.bottom;
                                }
                            }
                        }
                    }
                    if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).t == 1) {
                        if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && player.getBounds().top < CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.top) {
                            if (player.playerPhysics.getYVelocity() > 0) { //falling
                                player.playerPhysics.setY((CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.top));
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
                if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).type.equalsIgnoreCase("brick")){
                    //run coin collision
                    if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && !CoinBrickBlockManager.getCoinBrickBlocks().get(i).anim){
//                        PlayerAssets.setScore(CoinBrickBlockManager.getCoinBrickBlocks().get(i).value);
//                        CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.setEmpty();
//                        player.setStatus("Coin " + " +" + CoinBrickBlockManager.getCoinBrickBlocks().get(i).value);
                        CoinBrickBlockManager.getCoinBrickBlocks().get(i).anim = true;
                    }
                } else {
                    //run brick collision
                    if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).b == 1) {
                        if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && player.getBounds().bottom > CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.bottom) {
                            if (player.playerPhysics.getYVelocity() < 0) { //climbing
                                player.playerPhysics.setYVelocity(0);
                                player.playerPhysics.setY((CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.bottom + player.getCurrentSkin().getHeight()));
                            }
                        }
                    }
                    if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).r == 1) {
                        if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && player.getBounds().right - (Scale.UNIT/1.35) > CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.right) {
                            if (player.playerPhysics.getXVelocity() < 0) { //going left
                                player.reverse();
                                player.playerPhysics.setX((CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.right));
                                if (player.isArmsAttached() && LevelTouchControls.down) {
                                    player.setUsingArms(true);
                                    player.setBalloonInUse(false);
                                    player.minusJumpCount();
                                    player.grabbingLimit = CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.bottom;
                                }
                            }
                        }
                    }
                    if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).l == 1) {
                        if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && player.getBounds().left  + (Scale.UNIT/1.35) < CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.left){
                            if (player.playerPhysics.getXVelocity() > 0){ //going right
                                player.reverse();
                                player.playerPhysics.setX((CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.left - player.getCurrentSkin().getWidth()));
                                if (player.isArmsAttached() && LevelTouchControls.down) {
                                    player.setUsingArms(true);
                                    player.setBalloonInUse(false);
                                    player.minusJumpCount();
                                    player.grabbingLimit = CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.bottom;
                                }
                            }
                        }
                    }
                    if (CoinBrickBlockManager.getCoinBrickBlocks().get(i).t == 1) {
                        if (Rect.intersects(player.getBounds(), CoinBrickBlockManager.getCoinBrickBlocks().get(i).block) && player.getBounds().top < CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.top) {
                            if (player.playerPhysics.getYVelocity() > 0) { //falling
                                player.playerPhysics.setY((CoinBrickBlockManager.getCoinBrickBlocks().get(i).block.top));
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
    }

    Paint paint = new Paint();
    public void drawSwitch (Canvas canvas) {
        //draw switch

        paint.setColor(Color.DKGRAY);
        canvas.drawRect(switchBlock, paint);
    }

    Paint blockPaint = new Paint();
    public void drawBlock (Canvas canvas) {
        //draw conditional block
        if (isCoin) {
            if (type.equalsIgnoreCase("coin")) {
                blockPaint.setColor(Color.YELLOW);
            } else {
                blockPaint.setColor(Color.BLACK);
            }
        } else {
            if (type.equalsIgnoreCase("brick")) {
                blockPaint.setColor(Color.YELLOW);
            } else {
                blockPaint.setColor(Color.BLACK);
            }
        }
        canvas.drawRect(block, blockPaint);
    }
}