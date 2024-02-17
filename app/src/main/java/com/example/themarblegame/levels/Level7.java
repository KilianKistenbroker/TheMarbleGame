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

public class Level7 implements GameDesigner {
    private LevelTouchControls levelTouchControls;
    private Camera camera;
    private Player player;
    private FinishLine finishLine;

    public Level7() {
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
        new PlatformManager(4, 7, Scale.UNIT*6, 3);
        new PlatformManager(8, 13, Scale.UNIT*10, 6);
        new PlatformManager(8, 13, Scale.UNIT*10, 6);
        new PlatformManager(12, 17, Scale.UNIT*13, 1);
        new PlatformManager(9, 23, Scale.UNIT*10, 1);
        new PlatformManager(2, 27, Scale.UNIT*3, 2);
        new PlatformManager(2, 23, Scale.UNIT*6, 2);
        new PlatformManager(6, 27, Scale.UNIT*8, 6);
        new PlatformManager(12, 27, Scale.UNIT*13, 1);
        new PlatformManager(3, 33, Scale.UNIT*12, 4);
        new PlatformManager(12, 33, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(0, 33, Scale.UNIT, 1);

        //--coinBrickBlocks
        new CoinBrickBlockManager("brick", 1, 1, 33);
        new CoinBrickBlockManager("brick", 1, 2, 33);

        //--redBlueBlocks
        new RedBlueBlockManager("red", 10, 23, Constants.SCREEN_WIDTH, 1);
        new RedBlueBlockManager("red", 8, 27, Scale.UNIT*12, 1);
        new RedBlueBlockManager("red", 6, 29, Scale.UNIT*8, 2);
        new RedBlueBlockManager("red", 0, 32, Scale.UNIT*3, 3);
        new RedBlueBlockManager("red", 0, 27, Scale.UNIT*2, 1);
        new RedBlueBlockManager("blue", 0, 23, Scale.UNIT*2, 2);

        //damageBlocks
        new DamageBlockManager("poison", 0, 26, Scale.UNIT*2, 1);
        new DamageBlockManager("prickle", 6, 34, Scale.UNIT*9, 1);
        new DamageBlockManager("prickle", 11, 34, Constants.SCREEN_WIDTH, 1);
        new DamageBlockManager("prickle", 4, 85, Scale.UNIT*12, 1);

        //--items
        new ItemManager("static parachute", 1, 12, 18);

        //--switches
        new RedBlueBlockManager(4, 24, 0);
        new CoinBrickBlockManager(13, 31, 1);

        //-middle
        //--platforms
        new PlatformManager(4, 35, Scale.UNIT*6, 2);
        new PlatformManager(9, 37, Scale.UNIT*11, 4);
        new PlatformManager(8, 46, Scale.UNIT*10, 7);
        new PlatformManager(13, 37, Constants.SCREEN_WIDTH, 1);
        new PlatformManager(4, 46, Scale.UNIT*5, 1);
        new PlatformManager(0, 47, Scale.UNIT*2, 2);
        new PlatformManager(5, 58, Scale.UNIT*7, 8);


        //--launchpads
        new LaunchPadManager(0, 48);
        new LaunchPadManager(6, 59);

        //-end
        new PlatformManager(8, 68, Scale.UNIT*10, 2);
        new PlatformManager(12, 71, Scale.UNIT*13, 1);
        new PlatformManager(0, 76, Scale.UNIT*2, 2);
        new PlatformManager(4, 76, Scale.UNIT*8, 2);
        new PlatformManager(5, 81, Scale.UNIT*8, 3);
        new PlatformManager(10, 76, Scale.UNIT*12, 2);
        new PlatformManager(0, 84, Constants.SCREEN_WIDTH, 3,0,1,0,1);
        new PlatformManager(12, 85, Constants.SCREEN_WIDTH, 1,1,1,0,0);
        new PlatformManager(5, 86, Scale.UNIT*6, 1);
        new PlatformManager(9, 86, Scale.UNIT*10, 1);

        //doorways
        new DoorWayManager(0, 78, 0, 13, 87, 0);


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
            PlayerProgression.setLevel7Complete();
            PlayerProgression.setLevel7CompleteState(1);
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
