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

public class Level2 implements GameDesigner {
    private LevelTouchControls levelTouchControls;
    private Camera camera;
    private Player player;
    private FinishLine finishLine;

//objects will be placeholders for now
    public Level2() {
        //essentials
        camera = new Camera();
        levelTouchControls = new LevelTouchControls();
        player = new Player();
        finishLine = new FinishLine(0);
        InternalClock.setMaxTime(300);
        InternalClock.start();

        //beginning
        //-platforms
        new PlatformManager(0, 0, Constants.SCREEN_WIDTH, 12,0, 1, 0, 0);
        new PlatformManager(3, 3, Scale.UNIT*8, 3);
        new PlatformManager(0, 7, Scale.UNIT, 7);
        new PlatformManager(4, 12, Constants.SCREEN_WIDTH, 6);
        new PlatformManager(10, 18, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(4, 22, Scale.UNIT*8, 6);
        new PlatformManager(2, 28, Scale.UNIT*3, 1);
        new PlatformManager(5, 32, Constants.SCREEN_WIDTH,2);
        new PlatformManager(7, 37, Scale.UNIT*12, 3);
        new PlatformManager(7, 42, Scale.UNIT*8, 1);
        new PlatformManager(10, 46, Constants.SCREEN_WIDTH, 7);

        //switch
        new CoinBrickBlockManager(0, 8, 0);

        //coins
        new CoinBrickBlockManager("brick", 5,4, 13);
        new CoinBrickBlockManager("brick", 5,4, 14);
        new CoinBrickBlockManager("brick", 5,4, 15);
        new CoinBrickBlockManager("brick", 5,4, 16 );

        //doorWay
        new DoorWayManager(13, 48, 0, 0, 57, 0);

        //damageBlock
        new DamageBlockManager("poison", 1, 1, Scale.UNIT*3, 1);

        //middle
        new PlatformManager(0, 55, Scale.UNIT*3, 2);
        new PlatformManager(6, 57, Scale.UNIT*9, 4);
        new PlatformManager(11, 60, Scale.UNIT*12, 1);
        new PlatformManager(9, 65, Scale.UNIT*10, 2);
        new PlatformManager(13, 68, Constants.SCREEN_WIDTH, 5);
        new PlatformManager(0, 70, Scale.UNIT*9, 7);

        //end
        new PlatformManager(4, 76, Scale.UNIT*9, 4);
        new PlatformManager(7, 78, Scale.UNIT*9, 2);
        new PlatformManager(11, 78, Scale.UNIT*12, 1);
        new PlatformManager(0, 84, Scale.UNIT*9, 2);
    }

    public void receivedTouch(MotionEvent event) {
        levelTouchControls.onTouchEvent(player, event);
    }

    public Camera getCamera() {
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
            PlayerProgression.setLevel2Complete();
            PlayerProgression.setLevel2CompleteState(1);
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