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

//move this to level10
public class Level3 implements GameDesigner {
    private LevelTouchControls levelTouchControls;
    private Camera camera;
    private Player player;
    private FinishLine finishLine;

    public Level3() {
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
        new PlatformManager(0, 6, Scale.UNIT*4, 6);
        new PlatformManager(0, 10, Scale.UNIT*2, 2);
        new PlatformManager(5, 20, Scale.UNIT*7, 6);
        new PlatformManager(3, 16, Scale.UNIT*5, 4);
        new PlatformManager(2, 21, Scale.UNIT*3, 3);
        new PlatformManager(10, 24, Constants.SCREEN_WIDTH, 6);
        new PlatformManager(0, 35, Scale.UNIT*3, 2);
        new PlatformManager(6, 35, Constants.SCREEN_WIDTH, 2);
        new PlatformManager(7, 38, Scale.UNIT*8, 3);
        new PlatformManager(10, 38, Scale.UNIT*11, 3);
        new PlatformManager(13, 38, Constants.SCREEN_WIDTH, 3);
        new PlatformManager(4, 38, Scale.UNIT*5, 1);
        new PlatformManager(10, 50, Constants.SCREEN_WIDTH, 8);

        //--switches
        new RedBlueBlockManager(13, 39, 0);

        //--redBlueBlocks
        new RedBlueBlockManager("blue", 7, 43, Scale.UNIT*8, 1);

        //damageBlocks
        new DamageBlockManager("poison ivy", 4, 1, Scale.UNIT*9, 1);
        new DamageBlockManager("prickle", 11, 36, Scale.UNIT*13, 1);
        new DamageBlockManager("prickle", 8, 36, Scale.UNIT*10, 1);

        //items
        new ItemManager("static parachute",1, 13,25);

        //-middle
        //--platforms
        new PlatformManager(0, 53, Scale.UNIT*2, 3);
        new PlatformManager(4, 58, Scale.UNIT*5, 1);
        new PlatformManager(10, 66, Scale.UNIT*11, 1);
        new PlatformManager(10, 74, Scale.UNIT*12, 2);
        new PlatformManager(7, 77, Scale.UNIT*9, 5);
        new PlatformManager(4, 82, Scale.UNIT*6, 10);

        //redBlueBlock
        new RedBlueBlockManager("red", 7, 62, Scale.UNIT*8, 1);
        new RedBlueBlockManager("blue", 12, 70, Constants.SCREEN_WIDTH, 2);

        //switches
        new RedBlueBlockManager(4, 59, 0);
        new RedBlueBlockManager(10, 67, 0);

        //-------doorWays-------//
        new DoorWayManager(13, 52, 0, 0, 55, 0);
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
        canvas.translate(-camera.getTranslateX(), camera.getTranslateY());

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
        canvas.translate(camera.getTranslateX(), -camera.getTranslateY());

        player.drawHealth(canvas);

    }

    @Override
    public void update() {
        player.update();
        finishLine.finishLineCollision(player);
        if (LevelInterface.levelComplete) {
            PlayerProgression.setLevel3Complete();
            PlayerProgression.setLevel3CompleteState(1);
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
