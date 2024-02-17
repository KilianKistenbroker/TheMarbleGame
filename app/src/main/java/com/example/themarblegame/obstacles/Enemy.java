package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.themarblegame.main.Constants;
import com.example.themarblegame.main.PlayerBank;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.EnemyManager;

public class Enemy {
    private static final String TAG = "msg";
    private String name;
    private Rect enemy;
    private Laser laser;
    private boolean fall = false;

    public Enemy(String name, int x, int y) {
        this.name = name;
        EnemyManager enemyManager = new EnemyManager();
        enemyManager.populateEnemy(this.enemy = new Rect(Scale.UNIT*x, Scale.GROUND-Scale.UNIT*y, Scale.UNIT*x + Scale.UNIT, Scale.GROUND-Scale.UNIT*y + Scale.UNIT));
        if (name.equalsIgnoreCase("medium"))
            enemyManager.populateMediumLaser(this.laser = new Laser(name, x, y, y + Scale.UNIT));
        else
            enemyManager.populateLaser(this.laser = new Laser(name, x, y, y + Scale.UNIT));
    }

    public Enemy() {
    }

    public Rect getEnemy() {
        return enemy;
    }

    public String getName() {
        return name;
    }

    public Laser getLaser() {
        return laser;
    }

    public boolean isFall() {
        return fall;
    }

    public void setFall(boolean fall) {
        this.fall = fall;
    }


    //falling logic
    float yVelocity = -Constants.SCREEN_WIDTH / 80f;
    float GRAVITY = ((float) Constants.SCREEN_WIDTH / 600f);
    float TERMINAL_VELOCITY = GRAVITY * 100;
    public void gravity() {
        enemy.top += yVelocity;
        enemy.bottom += yVelocity;
        yVelocity += (GRAVITY);
        if (yVelocity >= TERMINAL_VELOCITY)
            yVelocity = TERMINAL_VELOCITY;
    }

    public void falling(){
        if (fall && !enemy.isEmpty()) {
            gravity();
            if (enemy.top > Constants.SCREEN_HEIGHT){
                enemy.setEmpty();
            }
        }
    }



    public void yMovement(Player player, int yVelocity) {
        if (!fall){
            if (PlayerBank.isRainInUse()){
                if (player.getBounds().top > enemy.top + (9*Scale.UNIT)) {
                    EnemyManager.setYDirection(1);
                    enemy.top += (yVelocity/2);
                    enemy.bottom = enemy.top + Scale.UNIT;
                    if (enemy.top + (9*Scale.UNIT) >= player.getBounds().top){
                        enemy.top = player.getBounds().top - (9*Scale.UNIT);
                        enemy.bottom = enemy.top + Scale.UNIT;
                    }
                } else if (player.getBounds().top < enemy.top + (9*Scale.UNIT)) {
                    EnemyManager.setYDirection(-1);
                    enemy.top -= (yVelocity/2);
                    enemy.bottom = enemy.top + Scale.UNIT;
                    if (enemy.top + (9*Scale.UNIT) <= player.getBounds().top){
                        enemy.top = player.getBounds().top - (9*Scale.UNIT);
                        enemy.bottom = enemy.top + Scale.UNIT;
                    }
                }
            } else {
                if (player.getBounds().top > enemy.top + (9*Scale.UNIT)) {
                    EnemyManager.setYDirection(1);
                    enemy.top += yVelocity;
                    enemy.bottom = enemy.top + Scale.UNIT;
                    if (enemy.top + (9*Scale.UNIT) >= player.getBounds().top){
                        enemy.top = player.getBounds().top - (9*Scale.UNIT);
                        enemy.bottom = enemy.top + Scale.UNIT;
                    }
                } else if (player.getBounds().top < enemy.top + (9*Scale.UNIT)) {
                    EnemyManager.setYDirection(-1);
                    enemy.top -= yVelocity;
                    enemy.bottom = enemy.top + Scale.UNIT;
                    if (enemy.top + (9*Scale.UNIT) <= player.getBounds().top){
                        enemy.top = player.getBounds().top - (9*Scale.UNIT);
                        enemy.bottom = enemy.top + Scale.UNIT;
                    }
                }
            }

        }
    }

    public void xMovement(Player player, int xVelocity) {
        if (player.getBounds().left < enemy.left) {
            EnemyManager.setXDirection(-1);
            enemy.left -= xVelocity;
            enemy.right = enemy.left + Scale.UNIT;
            if (enemy.left <= player.getBounds().left){
                enemy.left = player.getBounds().left;
                enemy.right = enemy.left + Scale.UNIT;
            }
        } else if (player.getBounds().left > enemy.left){
            EnemyManager.setXDirection(1);
            enemy.left += xVelocity;
            enemy.right = enemy.left + Scale.UNIT;
            if (enemy.left >= player.getBounds().left){
                enemy.left = player.getBounds().left;
                enemy.right = enemy.left + Scale.UNIT;
            }
        }
    }

    public void resetFastLaser(Laser laser) {
        if (laser.getLaser1().top > Constants.SCREEN_HEIGHT + (4*Constants.SCREEN_WIDTH) || laser.getLaser2().top > Constants.SCREEN_HEIGHT + (4*Constants.SCREEN_WIDTH)) {
                laser.getLaser1().left = enemy.left;
                laser.getLaser1().top = enemy.top + (Scale.UNIT/2);
                laser.getLaser1().right = enemy.left + (Scale.UNIT/15);
                laser.getLaser1().bottom = enemy.bottom;

                laser.getLaser2().left = enemy.right - (Scale.UNIT/15);
                laser.getLaser2().top = enemy.top + (Scale.UNIT/2);
                laser.getLaser2().right = enemy.right;
                laser.getLaser2().bottom = enemy.bottom;
        }
    }

    public void resetSlowLaser(Laser laser) {
        if (laser.getLaser1().top > Constants.SCREEN_HEIGHT + (Constants.SCREEN_WIDTH) || laser.getLaser2().top > Constants.SCREEN_HEIGHT + (Constants.SCREEN_WIDTH)) {
            laser.getLaser1().left = enemy.left;
            laser.getLaser1().top = enemy.top + (Scale.UNIT/2);
            laser.getLaser1().right = enemy.left + (Scale.UNIT/15);
            laser.getLaser1().bottom = enemy.bottom;

            laser.getLaser2().left = enemy.right - (Scale.UNIT/15);
            laser.getLaser2().top = enemy.top + (Scale.UNIT/2);
            laser.getLaser2().right = enemy.right;
            laser.getLaser2().bottom = enemy.bottom;
        }
    }

    public void resetMediumLaser(Laser laser) {
        if (laser.getLaser5().top > Constants.SCREEN_HEIGHT || laser.getLaser6().top > Constants.SCREEN_HEIGHT) {
            laser.setCounter(false);
            laser.setCounter1(false);
            laser.getLaser1().left = enemy.left;
            laser.getLaser1().top = enemy.top + (Scale.UNIT/2);
            laser.getLaser1().right = enemy.left + (Scale.UNIT/15);
            laser.getLaser1().bottom = enemy.bottom;

            laser.getLaser2().left = enemy.right - (Scale.UNIT/15);
            laser.getLaser2().top = enemy.top + (Scale.UNIT/2);
            laser.getLaser2().right = enemy.right;
            laser.getLaser2().bottom = enemy.bottom;

            laser.getLaser3().left = enemy.left;
            laser.getLaser3().top = enemy.top + (Scale.UNIT/2);
            laser.getLaser3().right = enemy.left + (Scale.UNIT/15);
            laser.getLaser3().bottom = enemy.bottom;

            laser.getLaser4().left = enemy.right - (Scale.UNIT/15);
            laser.getLaser4().top = enemy.top + (Scale.UNIT/2);
            laser.getLaser4().right = enemy.right;
            laser.getLaser4().bottom = enemy.bottom;

            laser.getLaser5().left = enemy.left;
            laser.getLaser5().top = enemy.top + (Scale.UNIT/2);
            laser.getLaser5().right = enemy.left + (Scale.UNIT/15);
            laser.getLaser5().bottom = enemy.bottom;

            laser.getLaser6().left = enemy.right - (Scale.UNIT/15);
            laser.getLaser6().top = enemy.top + (Scale.UNIT/2);
            laser.getLaser6().right = enemy.right;
            laser.getLaser6().bottom = enemy.bottom;
        }
    }

    Paint paint = new Paint();

    public void draw(Canvas canvas, Player player) {

        paint.setColor(Color.GREEN);
        switch (name) {
            case "slow":
                if (!enemy.isEmpty()) {
                    laser.draw(canvas, laser);
                    canvas.drawRect(enemy, paint);
                    resetSlowLaser(laser);
                    xMovement(player, (int) (Constants.SCREEN_WIDTH / 360f));
                    yMovement(player, (int) (Constants.SCREEN_WIDTH / 180f));
                    laser.slowSingleLaser();
                }
                break;
            case "fast":
                if (!enemy.isEmpty()) {
                    laser.draw(canvas, laser);
                    canvas.drawRect(enemy, paint);
                    resetFastLaser(laser);
                    xMovement(player, (int) (Constants.SCREEN_WIDTH / 400f));
                    yMovement(player, (int) (Constants.SCREEN_WIDTH / 200f));
                    laser.fastDoubleLaser();
                }
                break;
            case "medium":
                //multi spread
                //mediumMultiLaser
                if (!enemy.isEmpty()) {
                    laser.draw(canvas, laser);
                    canvas.drawRect(enemy, paint);
                    resetMediumLaser(laser);
                    xMovement(player, (int) (Constants.SCREEN_WIDTH / 440f));
                    yMovement(player, (int) (Constants.SCREEN_WIDTH / 220f));
                    laser.mediumMultiLaser(enemy);
                }
                break;
        }
    }
}
