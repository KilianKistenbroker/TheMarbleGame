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

public class Level5 implements GameDesigner {
    private LevelTouchControls levelTouchControls;
    private Camera camera;
    private Player player;
    private FinishLine finishLine;

    public Level5() {
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
        new PlatformManager(0, 0, Constants.SCREEN_WIDTH, 12,0, 1, 0, 0);
        new PlatformManager(12, 7, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(4, 12, Scale.UNIT*9, 12);
        new PlatformManager(0, 9, Scale.UNIT, 1);
        new PlatformManager(3, 16, Scale.UNIT*4, 1);
        new PlatformManager(4, 27, Scale.UNIT*9, 12);
        new PlatformManager(1, 27, Scale.UNIT*2, 1);
        new PlatformManager(4, 29, Scale.UNIT*5, 2, 1, 1, 1, 0);
        new PlatformManager(8, 29, Scale.UNIT*9, 2);
        new PlatformManager(10, 34, Scale.UNIT*11, 2);
        new PlatformManager(12, 36, Scale.UNIT*13, 2);

        //--launchPad
        new LaunchPadManager(3, 17);

        //--damageBlocks
        new DamageBlockManager("prickle",5, 28, Scale.UNIT*8, 1);
        new DamageBlockManager("poison ivy", 0, 1, Scale.UNIT*4, 1);

        //-middle
        //--platform
        new PlatformManager(4, 42, Scale.UNIT*11, 4);
        new PlatformManager(13, 42, Constants.SCREEN_WIDTH, 1);
        new PlatformManager(4, 44, Scale.UNIT*5, 2);
        new PlatformManager(7, 44, Scale.UNIT*8, 2);
        new PlatformManager(10, 44, Scale.UNIT*11, 2, 1, 1, 1, 0);
        new PlatformManager(0, 50, Scale.UNIT*2, 2);
        new PlatformManager(4, 54, Scale.UNIT*5, 4);
        new PlatformManager(7, 56, Scale.UNIT*8, 8);
        new PlatformManager(10, 58, Constants.SCREEN_WIDTH, 8);
        new PlatformManager(10, 64, Scale.UNIT*12, 4);
        new PlatformManager(7, 65, Scale.UNIT*8, 1);
        new PlatformManager(0, 70, Scale.UNIT, 6);
        new PlatformManager(8, 72, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(10, 74, Scale.UNIT*11, 2);
        new PlatformManager(13, 74, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(4, 74, Scale.UNIT*8, 6);

        //--launchpads
        new LaunchPadManager(13, 75 );

        //switches
        new RedBlueBlockManager(7, 66, 0);

        //redBlueBlocks
        new RedBlueBlockManager("red",7, 64, Scale.UNIT*10, 1, 0, 1, 0, 1);
        new RedBlueBlockManager("blue",1, 65, Scale.UNIT*7, 1, 0, 1, 0, 1);

        //damageBlock
        new DamageBlockManager("prickle", 8, 73, Scale.UNIT*10, 1);
        new DamageBlockManager("prickle", 11, 73, Scale.UNIT*13, 1);
        new DamageBlockManager("prickle", 5, 43, Scale.UNIT*7, 1);
        new DamageBlockManager("prickle", 8, 43, Scale.UNIT*10, 1);
        new DamageBlockManager("prickle", 8, 70, Constants.SCREEN_WIDTH, 1);

        //-end
        //--platforms
        new PlatformManager(9, 88, Scale.UNIT*11, 4);
        new PlatformManager(6, 86, Scale.UNIT*7, 2);
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
            PlayerProgression.setLevel5Complete();
            PlayerProgression.setLevel5CompleteState(1);
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
