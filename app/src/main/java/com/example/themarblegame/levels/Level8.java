package com.example.themarblegame.levels;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.Camera;
import com.example.themarblegame.main.Constants;
import com.example.themarblegame.main.InternalClock;
import com.example.themarblegame.main.LevelInterface;
import com.example.themarblegame.main.LevelTouchControls;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.PlayerProgression;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.CoinBrickBlockManager;
import com.example.themarblegame.managers.DamageBlockManager;
import com.example.themarblegame.managers.DoorWayManager;
import com.example.themarblegame.managers.EnemyManager;
import com.example.themarblegame.managers.ItemManager;
import com.example.themarblegame.managers.LaunchPadManager;
import com.example.themarblegame.managers.PlatformManager;
import com.example.themarblegame.managers.PortraitManager;
import com.example.themarblegame.managers.RedBlueBlockManager;
import com.example.themarblegame.obstacles.CoinBrickBlock;
import com.example.themarblegame.obstacles.DamageBlock;
import com.example.themarblegame.obstacles.DoorWay;
import com.example.themarblegame.obstacles.FinishLine;
import com.example.themarblegame.obstacles.Item;
import com.example.themarblegame.obstacles.LaunchPad;
import com.example.themarblegame.obstacles.Platform;
import com.example.themarblegame.obstacles.RedBlueBlock;

public class Level8 implements GameDesigner {
    private LevelTouchControls levelTouchControls;
    private Camera camera;
    private Player player;
    private FinishLine finishLine;

    public Level8() {
        //essentials
        camera = new Camera();
        levelTouchControls = new LevelTouchControls();
        player = new Player();
        finishLine = new FinishLine(0);
        InternalClock.setMaxTime(300);
        InternalClock.start();

        //AREA0

        //-beginning

        //--platforms
        new PlatformManager(0, 0, Constants.SCREEN_WIDTH, 12,0,1,0,0); //ground
        new PlatformManager(9, 2, Scale.UNIT*10, 2);
        new PlatformManager(5, 2, Scale.UNIT*7, 2);
        new PlatformManager(2, 2, Scale.UNIT*3, 2);
        new PlatformManager(4, 7, Scale.UNIT*5, 1);
        new PlatformManager(1, 11, Scale.UNIT*2, 1);
        new PlatformManager(5, 13, Scale.UNIT*7, 7);
        new PlatformManager(3, 14, Scale.UNIT*7, 1);
        new PlatformManager(10, 14, Scale.UNIT*12, 1);
        new PlatformManager(10, 22, Scale.UNIT*11, 5);
        new PlatformManager(0, 22, Scale.UNIT*10, 3);
        new PlatformManager(13, 22, Constants.SCREEN_WIDTH, 1);
        new PlatformManager(8, 25, Scale.UNIT*10, 3);
        new PlatformManager(5, 24, Scale.UNIT*6, 2);
        new PlatformManager(2, 24, Scale.UNIT*3, 2);
        new PlatformManager(4, 31, Scale.UNIT*7, 3);
        new PlatformManager(0, 31, Scale.UNIT, 1);
        new PlatformManager(0, 36, Scale.UNIT, 1);
        new PlatformManager(9, 31, Scale.UNIT*10, 1);
        new PlatformManager(13, 32, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(11, 36, Scale.UNIT*12, 1);

        //middle
        new PlatformManager(9, 40, Scale.UNIT*11, 5);
        new PlatformManager(9, 44, Constants.SCREEN_WIDTH, 4);
        new PlatformManager(11, 51, Constants.SCREEN_WIDTH, 4);
        new PlatformManager(8, 51, Scale.UNIT*9, 1);
        new PlatformManager(12, 58, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(9, 57, Scale.UNIT*10, 1);
        new PlatformManager(0, 57, Scale.UNIT*2, 1);
        new PlatformManager(12, 70, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(4, 69, Scale.UNIT*5, 1);
        new PlatformManager(0, 71, Scale.UNIT*3, 3);
        new PlatformManager(9, 85, Scale.UNIT*11, 3);
        new PlatformManager(13, 85, Constants.SCREEN_WIDTH, 1);

        //redBlueBlocks
        new RedBlueBlockManager("blue", 2, 42, Scale.UNIT*3, 1);
        new RedBlueBlockManager("blue", 6, 43, Scale.UNIT*7, 1);
        new RedBlueBlockManager("red", 0, 72, Scale.UNIT*3, 1);
        new RedBlueBlockManager("red", 5, 75, Scale.UNIT*6, 1);
        new RedBlueBlockManager("red", 9, 76, Scale.UNIT*10, 1);
        new RedBlueBlockManager("red", 12, 79, Constants.SCREEN_WIDTH, 1);
//        new RedBlueBlockManager("red", 9, 85, Scale.UNIT*11, 2);
        new RedBlueBlockManager("red", 5, 84, Scale.UNIT*6, 1);

        //coinBrickBlocks
        new CoinBrickBlockManager("brick", 5, 7, 31, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 8, 31, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 10, 31, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 11, 31, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 12, 31, 0, 1, 0, 1);

        new CoinBrickBlockManager("brick", 1, 1, 42);
        new CoinBrickBlockManager("brick", 1, 0, 42);
        new CoinBrickBlockManager("brick", 1, 0, 41);
        new CoinBrickBlockManager("brick", 1, 0, 40);
        new CoinBrickBlockManager("brick", 1, 0, 39);
        new CoinBrickBlockManager("brick", 1, 0, 38);
        new CoinBrickBlockManager("brick", 1, 0, 37);
//        new CoinBrickBlockManager("brick", 1, 0, 36);

        //switches
        new RedBlueBlockManager(0, 32, 0);
        new CoinBrickBlockManager(11, 37, 0);
        new RedBlueBlockManager(4, 70, 0);

        //items
        new ItemManager("static parachute", 1, 13, 59);

        //doorway
        new DoorWayManager(0, 59, 0, 13, 72, 0);

        //damageBlocks
        new DamageBlockManager("poison ivy",0, 1, Scale.UNIT*2, 1);
        new DamageBlockManager("poison ivy",3, 1, Scale.UNIT*5, 1);
        new DamageBlockManager("poison ivy",7, 1, Scale.UNIT*9, 1);
        new DamageBlockManager("poison ivy",0, 23, Scale.UNIT*2, 1);
        new DamageBlockManager("poison ivy",3, 23, Scale.UNIT*5, 1);
        new DamageBlockManager("poison ivy",6, 23, Scale.UNIT*8, 1);
    }

    public void receivedTouch(MotionEvent event) {
        levelTouchControls.onTouchEvent(player, event);
    }

    public Camera getCamera(){
        return camera;
    }

    @Override
    public void draw(Canvas canvas) { //order matters here

        //camera
        camera.camera(player);
        canvas.translate(0, camera.getTranslateY());

        //background stuff
        player.rainPressed(canvas);
        finishLine.draw(canvas);

        for (int i = 0; i < PortraitManager.getPortraits().size(); i++) {
            PortraitManager.getPortraits().get(i).draw(canvas);
        }
        for (int i = 0; i < DoorWayManager.getDoorWays().size(); i++) {
            DoorWayManager.getDoorWays().get(i).draw(canvas);
        }
        for (int i = 0; i < ItemManager.getItems().size(); i++) {
            ItemManager.getItems().get(i).draw(canvas);
        }
        for (int i = 0; i < LaunchPadManager.getLaunchPads().size(); i++) {
            LaunchPadManager.getLaunchPads().get(i).draw(canvas);
        }
        for (int i = 0; i < DamageBlockManager.getDamageBlocks().size(); i++) {
            DamageBlockManager.getDamageBlocks().get(i).draw(canvas);
        }
//        for (int i = 0; i < OneWayManager.getOneWays().size(); i++) {
//            OneWayManager.getOneWays().get(i).draw(canvas);
//        }
        for (int i = 0; i < PlatformManager.getPlatforms().size(); i++) {
            PlatformManager.getPlatforms().get(i).draw(canvas);
        }
        for (int i = 0; i < RedBlueBlockManager.getSwitchBlocks().size(); i++) {
            RedBlueBlockManager.getSwitchBlocks().get(i).drawSwitch(canvas);
        }
        for (int i = 0; i < RedBlueBlockManager.getRedBlocks().size(); i++) {
            RedBlueBlockManager.getRedBlocks().get(i).drawRed(canvas);
        }
        for (int i = 0; i < RedBlueBlockManager.getBlueBlocks().size(); i++) {
            RedBlueBlockManager.getBlueBlocks().get(i).drawBlue(canvas);
        }
        for (int i = 0; i < CoinBrickBlockManager.getSwitchBlocks().size(); i++) {
            CoinBrickBlockManager.getSwitchBlocks().get(i).drawSwitch(canvas);
        }
        for (int i = 0; i < CoinBrickBlockManager.getCoinBrickBlocks().size(); i++) {
            CoinBrickBlockManager.getCoinBrickBlocks().get(i).drawBlock(canvas);
        }

        EnemyManager.drawRect(canvas, player);
        player.draw(canvas);

        //this keeps UI display constant
        canvas.translate(0, -camera.getTranslateY());

        player.drawHealth(canvas);

    }

    @Override
    public void update() {
        player.update();
        finishLine.finishLineCollision(player);
        if (LevelInterface.levelComplete) {
            PlayerProgression.setLevel8Complete();
            PlayerProgression.setLevel8CompleteState(1);
        }
        EnemyManager.enemyCollision(player);
        EnemyManager.mediumLaserCollision(player);
        EnemyManager.laserCollision(player);
        EnemyManager.selfCollision();

//        OneWay.oneWayCollision(playerPhysics);
        DoorWay.doorWayCollision(player);
        LaunchPad.launchPadCollision(player);
        DamageBlock.prickleCollision(player);
        Platform.platformCollision(player);
        RedBlueBlock.switchBlocksCollision(player);
        CoinBrickBlock.coinBrickBlockCollision(player);
        Item.itemCollision(player);
    }
}
