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

public class Level6 implements GameDesigner {
    private LevelTouchControls levelTouchControls;
    private Camera camera;
    private Player player;
    private FinishLine finishLine;

    public Level6() {
        //essentials
        camera = new Camera();
        levelTouchControls = new LevelTouchControls();
        player = new Player();
        finishLine = new FinishLine(0);
        InternalClock.setMaxTime(300);
        InternalClock.start();

        //platforms intro
        new PlatformManager(0, 0, Constants.SCREEN_WIDTH, 12, 0, 0, 0, 0); //ground 14x8
        new PlatformManager(9, 2, Scale.UNIT*11, 2, 1, 1, 1, 0);//2x2
        new PlatformManager(5, 4, Scale.UNIT*7, 4, 1, 1, 1, 0);//2x4
        new PlatformManager(2, 6, Scale.UNIT*7, 2);//5x2
        new PlatformManager(4, 9, Scale.UNIT*7, 1,1,0,1,1);//3x1
        new PlatformManager(4, 12, Constants.SCREEN_WIDTH, 3, 1, 1, 0, 1);
        new PlatformManager(4, 14, Scale.UNIT*6, 2);
        new PlatformManager(13, 26, Constants.SCREEN_WIDTH, 1, 1, 1, 0, 1);
        new PlatformManager(1, 12, Scale.UNIT*2, 1);

        //platforms middle
        new PlatformManager(0, 46, Scale.UNIT*3, 6);
        new PlatformManager(5, 51, Constants.SCREEN_WIDTH, 11);
        new PlatformManager(10, 58, Scale.UNIT*11, 4);
        new PlatformManager(0, 56, Scale.UNIT*10, 2, 1, 1, 0, 1);
        new PlatformManager(5, 54, Scale.UNIT*7, 1);
        new PlatformManager(0, 60, Scale.UNIT*11, 2, 0, 1, 1, 1);
        new PlatformManager(13, 53, Constants.SCREEN_WIDTH, 2, 1, 1, 0, 1);
        new PlatformManager(0, 49, Scale.UNIT, 1, 0, 1, 1, 1);
        new PlatformManager(0, 66, Scale.UNIT*2, 6, 0,1,1,0);
        new PlatformManager(11, 68, Constants.SCREEN_WIDTH, 2, 0,1,0,1);
        new PlatformManager(10, 71, Scale.UNIT*11, 5,1,1,1,1);
        new PlatformManager(11, 76, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(13, 71, Constants.SCREEN_WIDTH, 1,1,1,0,0);

        //platforms end
        new PlatformManager(0, 84, Scale.UNIT*3, 9, 0, 0, 1, 1);
        new PlatformManager(4, 80, Scale.UNIT*5, 1);
        new PlatformManager(5, 76, Scale.UNIT*6, 1);

        //switches
        new RedBlueBlockManager(13, 13, 0);
        new RedBlueBlockManager(13, 27, 0);
        new RedBlueBlockManager(2, 34, 0);
        new RedBlueBlockManager(9, 57, 0);
        new CoinBrickBlockManager(1, 29, 0);
        new CoinBrickBlockManager(12, 74, 1);

        //redBlueBlocks
        new RedBlueBlockManager("blue", 10, 30, Scale.UNIT*11, 18);
        new RedBlueBlockManager("blue", 13, 18, Constants.SCREEN_WIDTH, 1);
        new RedBlueBlockManager("red", 10, 31, Scale.UNIT*11, 1);
        new RedBlueBlockManager("red", 6, 30, Scale.UNIT*7, 1);
        new RedBlueBlockManager("red", 2, 33, Scale.UNIT*3, 1);
//        new RedBlueBlockManager("red", 2, 4, Scale.UNIT*3, 4, 1, 0, 1, 0);
        new RedBlueBlockManager("red", 5, 53, Scale.UNIT*6, 2);
        new RedBlueBlockManager("red", 3, 41, Scale.UNIT*5, 1, 0, 1, 0, 1);
        new RedBlueBlockManager("blue", 3, 58, Scale.UNIT*4 , 2, 1,0,1,0);
        new RedBlueBlockManager("blue", 1, 28, Scale.UNIT*2, 6);
        new RedBlueBlockManager("blue", 4, 24, Scale.UNIT*6, 6);
        new RedBlueBlockManager("blue", 8, 18, Scale.UNIT*10, 6, 1, 1, 0, 1);
        new RedBlueBlockManager("blue", 3, 76, Scale.UNIT*5, 1, 0, 1, 0, 1);
        new RedBlueBlockManager("blue", 6, 76, Scale.UNIT*11, 1, 0, 1, 0, 1);


        //coinBricks
        new CoinBrickBlockManager("brick", 1, 0, 6, 0, 1, 0, 0);
        new CoinBrickBlockManager("brick", 1, 1, 6, 0, 1, 0, 0);
        new CoinBrickBlockManager("brick", 1, 0, 5, 0, 0, 0, 0);
        new CoinBrickBlockManager("brick", 1, 1, 5, 0, 0, 0, 0);
        new CoinBrickBlockManager("brick", 1, 0, 4, 0, 0, 0, 0);
        new CoinBrickBlockManager("brick", 1, 1, 4, 0, 0, 0, 0);
        new CoinBrickBlockManager("brick", 1, 0, 3, 0, 0, 0, 0);
        new CoinBrickBlockManager("brick", 1, 1, 3, 0, 0, 0, 0);
        new CoinBrickBlockManager("brick", 1, 0, 2, 0, 0, 0, 0);
        new CoinBrickBlockManager("brick", 1, 1, 2, 0, 0, 0, 0);
        new CoinBrickBlockManager("brick", 1, 0, 1, 0, 0, 0, 0);
        new CoinBrickBlockManager("brick", 1, 1, 1, 0, 0, 0, 0);
        new CoinBrickBlockManager("coin", 5, 12, 69,0,0,0,0);
        new CoinBrickBlockManager("coin", 5, 12, 70,0,0,0,0);
        new CoinBrickBlockManager("coin", 5, 12, 71,0,1,0,0);
        new CoinBrickBlockManager("coin", 5, 11, 71,0,1,0,0);
        new CoinBrickBlockManager("coin", 5, 11, 70,0,0,0,0);
        new CoinBrickBlockManager("coin", 5, 11, 69,0,0,0,0);


        //doors
        new DoorWayManager(4, 2, 0, 0, 48, 0);
        new DoorWayManager(13, 70, 0, 9, 58, 0);
        new DoorWayManager(0, 58, 0, 13, 78, 0);

        //items
        new ItemManager("static parachute", 1, 1, 67);
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
            PlayerProgression.setLevel6Complete();
            PlayerProgression.setLevel6CompleteState(1);
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
