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

public class Level10 implements GameDesigner {
    private LevelTouchControls levelTouchControls;
    private Camera camera;
    private Player player;
    private FinishLine finishLine;

    public Level10() {
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
        new PlatformManager(9, 6, Scale.UNIT*10, 1);
        new PlatformManager(11, 13, Scale.UNIT*12, 1);
        new PlatformManager(0, 20, Scale.UNIT*9, 20);
        new PlatformManager(13, 17, Constants.SCREEN_WIDTH, 1);
        new PlatformManager(13, 24, Constants.SCREEN_WIDTH, 1);
        new PlatformManager(0, 26, Scale.UNIT, 6);
        new PlatformManager(4, 29, Scale.UNIT*11, 6);
        new PlatformManager(8, 32, Scale.UNIT*10, 1);
        new PlatformManager(4, 38, Scale.UNIT*8, 7);
        new PlatformManager(10, 38, Scale.UNIT*12, 2);
        new PlatformManager(0, 38, Scale.UNIT*2, 2);


        //--redBlueBlocks
        new RedBlueBlockManager("red", 9, 13, Scale.UNIT*11, 1);
        new RedBlueBlockManager("red", 12, 13, Constants.SCREEN_WIDTH, 1);
        new RedBlueBlockManager("blue", 11, 29, Constants.SCREEN_WIDTH, 1, 0, 1, 0, 1);


        //--switches
        new RedBlueBlockManager(9, 7, 0);
        new CoinBrickBlockManager(13, 25, 0);

        //--coinBrickBlocks
        new CoinBrickBlockManager("brick", 5, 8, 21);
        new CoinBrickBlockManager("brick", 5, 8, 22);
        new CoinBrickBlockManager("brick", 5, 8, 23);

        new CoinBrickBlockManager("coin", 20, 12, 38);
        new CoinBrickBlockManager("coin", 20, 13, 38);
        new CoinBrickBlockManager("coin", 20, 12, 37);
        new CoinBrickBlockManager("coin", 20, 13, 37);

        //-middle
        //--platforms
        new PlatformManager(6, 44, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(12, 46, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(9, 52, Scale.UNIT*10, 1);
        new PlatformManager(0, 55, Scale.UNIT*4, 13);
        new PlatformManager(2, 60, Scale.UNIT*7, 2);
        new PlatformManager(4, 64, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(13, 69, Constants.SCREEN_WIDTH, 5);
        new PlatformManager(10, 73, Scale.UNIT*11, 6);
        new PlatformManager(0, 75, Scale.UNIT*8, 6);

        //redBlueBlocks
        new RedBlueBlockManager("red",0, 64, Scale.UNIT*4, 2,0, 1, 0, 1);
        new RedBlueBlockManager("blue", 2, 42, Scale.UNIT*4, 6);
        new RedBlueBlockManager("blue", 10, 42, Scale.UNIT*12, 4);
        new RedBlueBlockManager("blue", 8, 71, Scale.UNIT*10, 2);
        //--switches
        new CoinBrickBlockManager(9, 53, 0);
        new RedBlueBlockManager(5, 69, 1);
        new CoinBrickBlockManager(1, 69, 1);

        //--coinBrickBlocks
        new CoinBrickBlockManager("brick", 5, 6, 55);
        new CoinBrickBlockManager("brick", 5, 12, 69);
        new CoinBrickBlockManager("brick", 5, 12, 68);
        new CoinBrickBlockManager("brick", 5, 11, 69);
        new CoinBrickBlockManager("brick", 5, 11, 68);


        //-end
        //--platforms
        new PlatformManager(0, 84, Constants.SCREEN_WIDTH, 4);
        new PlatformManager(12, 92, Constants.SCREEN_WIDTH, 8);

        //coinBrickBlocks
        new CoinBrickBlockManager("coin", 10, 9, 90);
        new CoinBrickBlockManager("coin", 10, 9, 89);
        new CoinBrickBlockManager("coin", 10, 5, 88);
        new CoinBrickBlockManager("coin", 10, 5, 87);

        //damageBlocks
        new DamageBlockManager("prickle", 4, 85, Scale.UNIT*12, 1);

        //-----------doorWays------------//
        new DoorWayManager(0, 77, 0, 13, 40, 0);
        new DoorWayManager(0, 40, 0, 13, 94, 0);
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
            PlayerProgression.setLevel10Complete();
            PlayerProgression.setLevel10CompleteState(1);
        }
        EnemyManager.enemyCollision(player);
        EnemyManager.mediumLaserCollision(player);
        EnemyManager.laserCollision(player);
        EnemyManager.selfCollision();
        DoorWay.doorWayCollision(player);
        LaunchPad.launchPadCollision(player);
        DamageBlock.prickleCollision(player);
        Platform.platformCollision(player);
        RedBlueBlock.switchBlocksCollision(player);
        CoinBrickBlock.coinBrickBlockCollision(player);
        Item.itemCollision(player);
    }
}
