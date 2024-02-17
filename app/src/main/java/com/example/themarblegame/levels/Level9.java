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

public class Level9 implements GameDesigner {
    private LevelTouchControls levelTouchControls;
    private Camera camera;
    private Player player;
    private FinishLine finishLine;

    public Level9() {
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
        new PlatformManager(9, 6, Scale.UNIT*11, 6);
        new PlatformManager(0, 18, Scale.UNIT*7, 12);
        new PlatformManager(12, 18, Constants.SCREEN_WIDTH, 1);
        new PlatformManager(3, 24, Scale.UNIT*7, 3);
        new PlatformManager(6, 28, Scale.UNIT*8, 2);
        new PlatformManager(11, 41, Scale.UNIT*12, 1);

        //--redBlueBlocks
        new RedBlueBlockManager("blue", 9, 18, Scale.UNIT*10, 1);
        new RedBlueBlockManager("red", 3, 21, Scale.UNIT*7, 3, 1, 0, 1, 0);
        new RedBlueBlockManager("blue", 0, 24, Scale.UNIT*3, 3, 0, 1, 0, 1);
        new RedBlueBlockManager("blue", 8, 32, Scale.UNIT*11, 6);
        new RedBlueBlockManager("blue", 8, 36, Scale.UNIT*9, 1);
        new RedBlueBlockManager("red", 8, 39, Scale.UNIT*9, 1);
        new RedBlueBlockManager("red", 3, 39, Scale.UNIT*4, 1);
        new RedBlueBlockManager("blue", 11, 40, Constants.SCREEN_WIDTH, 14);

        //damageBlock
        new DamageBlockManager("poison ivy", 1, 1, Scale.UNIT*9, 1);

        //doorWay
        new DoorWayManager(0, 2, 0, 13, 20, 0);

        //items
        new ItemManager("static parachute", 1, 10, 7);

        //switches
        new RedBlueBlockManager(0, 19, 0);
        new RedBlueBlockManager(11, 42, 0);
        new RedBlueBlockManager(13, 51, 0);
        new CoinBrickBlockManager(0, 62, 0);

        //-middle

        //--platforms
        new PlatformManager(3, 47,Scale.UNIT*4, 3);
        new PlatformManager(6, 47,Constants.SCREEN_WIDTH, 3);
        new PlatformManager(0, 42, Scale.UNIT, 1);
        new PlatformManager(9, 50, Scale.UNIT*11, 3);
        new PlatformManager(13, 50, Constants.SCREEN_WIDTH, 3);
        new PlatformManager(2, 56, Scale.UNIT*4, 4);
        new PlatformManager(11, 56, Scale.UNIT*12, 1);
        new PlatformManager(8, 56, Scale.UNIT*9, 3);
        new PlatformManager(0, 61, Scale.UNIT, 2);
        new PlatformManager(3, 66, Scale.UNIT*5, 7);
        new PlatformManager(7, 67, Scale.UNIT*8, 2);
        new PlatformManager(11, 66, Scale.UNIT*12, 1);
        new PlatformManager(12, 71, Constants.SCREEN_WIDTH, 1);
        new PlatformManager(8, 76, Scale.UNIT*10, 6);

        //--doorWay
        new DoorWayManager(13, 73, 0, 13, 87, 0);

        //--redBlueBlocks
        new RedBlueBlockManager("red",4, 49, Scale.UNIT*6, 5);
        new RedBlueBlockManager("red", 4, 56, Scale.UNIT*8, 1, 0, 1, 0, 1);
        new RedBlueBlockManager("red", 9, 56, Scale.UNIT*11, 1, 0, 1, 0, 1);
        new RedBlueBlockManager("red", 12, 56, Constants.SCREEN_WIDTH, 1, 0, 1, 0, 1);

        //damageBlocks
        new DamageBlockManager("prickle", 6, 48, Scale.UNIT*9, 1);
        new DamageBlockManager("prickle", 11, 48, Scale.UNIT*13, 1);

        //coinBrickBlock
        new CoinBrickBlockManager("brick", 5, 0, 66, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 1, 66, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 2, 66, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 5, 66, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 6, 66, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 8, 66, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 9, 66, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 10, 66, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 12, 66, 0, 1, 0, 1);
        new CoinBrickBlockManager("brick", 5, 13, 66, 0, 1, 0, 1);

        //-end

        //--platforms
        new PlatformManager(12, 85, Constants.SCREEN_WIDTH, 1);

        //coinBrickBlock
        new CoinBrickBlockManager("coin",100, 7, 84);
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
            PlayerProgression.setLevel9Complete();
            PlayerProgression.setLevel9CompleteState(1);
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
