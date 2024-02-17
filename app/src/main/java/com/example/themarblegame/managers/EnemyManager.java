package com.example.themarblegame.managers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.Constants;
import com.example.themarblegame.main.LevelInterface;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.PlayerBank;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.obstacles.Enemy;
import com.example.themarblegame.obstacles.Laser;

import java.util.ArrayList;

public class EnemyManager implements GameDesigner {
    private static int xDirection;
    private static int yDirection;
    private static boolean pinned = false;
    private Enemy enemy;


    public EnemyManager(String name, int x, int y) {
        enemy = new Enemy(name, x, y);
        populateEnemyArrayList(enemy);
    }

    private static ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    private static ArrayList<Rect> enemies = new ArrayList<>();
    private static ArrayList<Laser> lasers = new ArrayList<>();
    private static ArrayList<Laser> mediumLasers = new ArrayList<>();

    public EnemyManager() {
    }

    public static void reset() {
        enemyArrayList.clear();
        enemies.clear();
        lasers.clear();
        mediumLasers.clear();
    }

    public void populateEnemy(Rect enemy) {
        enemies.add(enemy);
    }

    public void populateEnemyArrayList(Enemy enemy){
        enemyArrayList.add(enemy);
    }

    public void populateLaser(Laser laser) {
        lasers.add(laser);
    }
    public void populateMediumLaser(Laser laser) {
        mediumLasers.add(laser);
    }

    public static void setXDirection(int xDirection) {
        EnemyManager.xDirection = xDirection;
    }

    public static void setYDirection(int yDirection) {
        EnemyManager.yDirection = yDirection;
    }

    public static ArrayList<Enemy> getEnemyArrayList() {
        return enemyArrayList;
    }

    //this dont work right
    public static void enemyCollision(Player player) {
        for (int i = 0; i < enemyArrayList.size(); i++) {
            if (Rect.intersects(player.getBounds(), enemyArrayList.get(i).getEnemy())) {
                enemyArrayList.get(i).setFall(true);
            } enemyArrayList.get(i).falling();
        }
    }

    public static void laserCollision(Player player) {
        for (int i = 0; i < lasers.size(); i++) {
            if (Rect.intersects(player.getBounds(), lasers.get(i).getLaser1())) {
                lasers.get(i).getLaser1().set(0, 0, 0, 0);
                PlayerBank.setHealth(-10);

            } else if (Rect.intersects(player.getBounds(), lasers.get(i).getLaser2())) {
                lasers.get(i).getLaser2().set(0, 0, 0, 0);
                PlayerBank.setHealth(-10);
            }
        }
    }

    public static void mediumLaserCollision(Player player) {
        for (int i = 0; i < mediumLasers.size(); i++) {
            if (Rect.intersects(player.getBounds(), mediumLasers.get(i).getLaser1())) {
                mediumLasers.get(i).getLaser1().set(0, 0, 0, 0);
                PlayerBank.setHealth(-10);

            } else if (Rect.intersects(player.getBounds(), mediumLasers.get(i).getLaser2())) {
                mediumLasers.get(i).getLaser2().set(0, 0, 0, 0);
                PlayerBank.setHealth(-10);

            } else if (Rect.intersects(player.getBounds(), mediumLasers.get(i).getLaser3())) {
                mediumLasers.get(i).getLaser3().set(0, 0, 0, 0);
                PlayerBank.setHealth(-10);

            } else if (Rect.intersects(player.getBounds(), mediumLasers.get(i).getLaser4())) {
                mediumLasers.get(i).getLaser4().set(0, 0, 0, 0);
                PlayerBank.setHealth(-10);

            } else if (Rect.intersects(player.getBounds(), mediumLasers.get(i).getLaser5())) {
                mediumLasers.get(i).getLaser5().set(0, 0, 0, 0);
                PlayerBank.setHealth(-10);

            } else if (Rect.intersects(player.getBounds(), mediumLasers.get(i).getLaser6())) {
                mediumLasers.get(i).getLaser6().set(0, 0, 0, 0);
                PlayerBank.setHealth(-10);
            }
        }
    }

    public static void selfCollision() {
        for (int i = 0; i < enemies.size(); i++) {
            for (int c = 0; c < enemies.size(); c++) {
                if (i != c) {
                    //going left to right
                    if (Rect.intersects(enemies.get(i), enemies.get(c)) && enemies.get(i).right - (Scale.UNIT/5) < enemies.get(c).left && !pinned) {
                        if (xDirection == 1 ) {
                            enemies.get(i).right = enemies.get(c).left;
                            enemies.get(i).left = enemies.get(i).right - Scale.UNIT;
                            pinned = true;
                        }
                        //run logic here
                    }
                    //going right to left
                    if (Rect.intersects(enemies.get(i), enemies.get(c)) && enemies.get(i).left + (Scale.UNIT/5) > enemies.get(c).right && !pinned) {
                        if (xDirection == -1 ) {
                            enemies.get(i).left = enemies.get(c).right;
                            enemies.get(i).right = enemies.get(i).left + Scale.UNIT;
                            pinned = true;
                        }
                    }
                    if (Rect.intersects(enemies.get(i), enemies.get(c)) && enemies.get(i).bottom - (Scale.UNIT/5) < enemies.get(c).top && !pinned) {
                        if (yDirection == 1 ){
                            enemies.get(i).bottom = enemies.get(c).top;
                            enemies.get(i).top = enemies.get(i).bottom - Scale.UNIT;
                            pinned = true;
                        }
                    }
                    if (Rect.intersects(enemies.get(i), enemies.get(c)) && enemies.get(i).top + (Scale.UNIT/5) > enemies.get(c).bottom && !pinned) {
                        if (yDirection == -1 ) {
                            enemies.get(i).top = enemies.get(c).bottom;
                            enemies.get(i).bottom = enemies.get(i).top + Scale.UNIT;
                            pinned = true;
                        }
                    }
                    if (enemies.get(i).right != enemies.get(c).left && enemies.get(i).left != enemies.get(c).right && enemies.get(i).bottom != enemies.get(c).top && enemies.get(i).top != enemies.get(c).bottom)
                        pinned = false;
                }
            }
        }
    }

    public static void drawRect(Canvas canvas, Player player) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        for (int i = 0; i < enemyArrayList.size(); i++){
            switch (enemyArrayList.get(i).getName()) {
                case "slow":
                    if (!enemyArrayList.get(i).getEnemy().isEmpty()) {
                        enemyArrayList.get(i).getLaser().draw(canvas, enemyArrayList.get(i).getLaser());
                        canvas.drawRect(enemyArrayList.get(i).getEnemy(), paint);
                        enemyArrayList.get(i).resetSlowLaser(enemyArrayList.get(i).getLaser());
                        if (!LevelInterface.pauseMenuSelected){
                            enemyArrayList.get(i).xMovement(player, (int) (Constants.SCREEN_WIDTH / 360f));
                            enemyArrayList.get(i).yMovement(player, (int) (Constants.SCREEN_WIDTH / 180f));
                        }
                        enemyArrayList.get(i).getLaser().slowSingleLaser();
                    }
                    break;
                case "fast":
                    if (!enemyArrayList.get(i).getEnemy().isEmpty()) {
                        enemyArrayList.get(i).getLaser().draw(canvas, enemyArrayList.get(i).getLaser());
                        canvas.drawRect(enemyArrayList.get(i).getEnemy(), paint);
                        enemyArrayList.get(i).resetFastLaser(enemyArrayList.get(i).getLaser());
                        if (!LevelInterface.pauseMenuSelected){
                            enemyArrayList.get(i).xMovement(player, (int) (Constants.SCREEN_WIDTH / 400f));
                            enemyArrayList.get(i).yMovement(player, (int) (Constants.SCREEN_WIDTH / 200f));
                        }
                        enemyArrayList.get(i).getLaser().fastDoubleLaser();

                    }
                    break;
                case "medium":
                    //multi spread
                    //mediumMultiLaser
                    if (!enemyArrayList.get(i).getEnemy().isEmpty()) {
                        enemyArrayList.get(i).getLaser().draw(canvas, enemyArrayList.get(i).getLaser());
                        canvas.drawRect(enemyArrayList.get(i).getEnemy(), paint);
                        enemyArrayList.get(i).resetMediumLaser(enemyArrayList.get(i).getLaser());
                        if (!LevelInterface.pauseMenuSelected){
                            enemyArrayList.get(i).xMovement(player, (int) (Constants.SCREEN_WIDTH / 440f));
                            enemyArrayList.get(i).yMovement(player, (int) (Constants.SCREEN_WIDTH / 220f));
                        }
                        enemyArrayList.get(i).getLaser().mediumMultiLaser(enemyArrayList.get(i).getEnemy());
                    }
                    break;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {
    }
}
