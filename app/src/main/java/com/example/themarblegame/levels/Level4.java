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

public class Level4 implements GameDesigner {
    private LevelTouchControls levelTouchControls;
    private Camera camera;
    private Player player;
    private FinishLine finishLine;

    public Level4() {
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
        new PlatformManager(0, 7, Scale.UNIT*5, 7);
        new PlatformManager(8, 12, Scale.UNIT*9, 1);
        new PlatformManager(12, 18, Constants.SCREEN_WIDTH, 7);
        new PlatformManager(8, 24, Scale.UNIT*9, 1);
        new PlatformManager(5, 23, Scale.UNIT*6, 1);
        new PlatformManager(0, 22, Scale.UNIT*2, 3);
        new PlatformManager(0, 34, Scale.UNIT*3, 4);
        new PlatformManager(5, 37, Constants.SCREEN_WIDTH, 7);
        new PlatformManager(5, 42, Scale.UNIT*10, 2);
        new PlatformManager(5, 44, Scale.UNIT*6, 2, 1, 1, 1, 0);
        new PlatformManager(9, 44, Scale.UNIT*10, 2, 1, 1, 1, 0);
        new PlatformManager(7, 50, Scale.UNIT*10, 2);

        //-middle
        //--platforms
        new PlatformManager(0, 49, Scale.UNIT*4, 2);
        new PlatformManager(0, 51, Scale.UNIT, 2, 1, 1, 1,0);
        new PlatformManager(3, 51, Scale.UNIT*4, 2, 1, 1, 1, 0);
        new PlatformManager(2, 60, Scale.UNIT*7, 4);
        new PlatformManager(2, 62, Scale.UNIT*3, 2, 1, 1, 1, 0);
        new PlatformManager(6, 62, Scale.UNIT*7, 2, 1, 1, 1, 0);
        new PlatformManager(9, 61, Scale.UNIT*10, 1);
        new PlatformManager(12, 61, Constants.SCREEN_WIDTH, 4);

        //launchpads
        new LaunchPadManager(0, 52);

        //damageBlocks
        new DamageBlockManager("poison ivy", 1, 50, Scale.UNIT*3, 1);
        new DamageBlockManager("poison ivy", 6, 43, Scale.UNIT*9, 1);
        new DamageBlockManager("poison ivy", 3, 61, Scale.UNIT*6, 1);

        //end
        new PlatformManager(0, 74, Constants.SCREEN_WIDTH, 4);
        new PlatformManager(10, 76, Scale.UNIT*11, 2);
        new PlatformManager(7, 76, Scale.UNIT*8, 2);
        new PlatformManager(4, 76, Scale.UNIT*5, 2);
        new PlatformManager(0, 76, Scale.UNIT*2, 2);
        new PlatformManager(4, 86, Scale.UNIT*5, 4);
        new PlatformManager(7, 88, Scale.UNIT*8, 4);


        //redBlueBlocks
        new RedBlueBlockManager("blue", 4, 78, Scale.UNIT*5, 2);
        new RedBlueBlockManager("blue", 7, 80, Scale.UNIT*8, 4);
        new RedBlueBlockManager("blue", 10, 82, Scale.UNIT*11, 6);

        //switches
        new RedBlueBlockManager(0, 77, 0);

        //damageBlocks
        new DamageBlockManager("poison ivy", 2, 75, Scale.UNIT*4, 1);
        new DamageBlockManager("poison ivy", 5, 75, Scale.UNIT*7, 1);
        new DamageBlockManager("poison ivy", 8, 75, Scale.UNIT*10, 1);

        //------------doorWays----------//
        new DoorWayManager(0, 24, 0, 0, 36, 0);
        new DoorWayManager(13, 63, 0, 13, 76, 0);
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
            PlayerProgression.setLevel4Complete();
            PlayerProgression.setLevel4CompleteState(1);
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
