package com.example.themarblegame.levels;

import android.graphics.Canvas;
import android.view.MotionEvent;
import com.example.themarblegame.R;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.Camera;
import com.example.themarblegame.main.Constants;
import com.example.themarblegame.main.InternalClock;
import com.example.themarblegame.main.LevelInterface;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.PlayerBank;
import com.example.themarblegame.main.PlayerProgression;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.main.LevelTouchControls;
import com.example.themarblegame.managers.CoinBrickBlockManager;
import com.example.themarblegame.managers.DamageBlockManager;
import com.example.themarblegame.managers.DoorWayManager;
import com.example.themarblegame.managers.ItemManager;
import com.example.themarblegame.managers.LaunchPadManager;
import com.example.themarblegame.managers.PlatformManager;
import com.example.themarblegame.managers.PortraitManager;
import com.example.themarblegame.obstacles.CoinBrickBlock;
import com.example.themarblegame.obstacles.DoorWay;
import com.example.themarblegame.managers.EnemyManager;
import com.example.themarblegame.obstacles.FinishLine;
import com.example.themarblegame.obstacles.Item;
import com.example.themarblegame.obstacles.LaunchPad;
import com.example.themarblegame.obstacles.Platform;
import com.example.themarblegame.obstacles.DamageBlock;
import com.example.themarblegame.obstacles.Portrait;

public class Level1 implements GameDesigner {
    private final LevelTouchControls levelTouchControls;
    private final Camera camera;
    private final Player player;
    private final FinishLine finishLine1;
//    private FinishLine finishLine2;


    public Level1() {
        //essentials
        camera = new Camera();
        levelTouchControls = new LevelTouchControls();
        player = new Player();
        finishLine1 = new FinishLine(0);
//        finishLine2 = new FinishLine(0, Scale.GROUND-Scale.UNIT*12, Scale.UNIT*5, Scale.GROUND-Scale.UNIT*11); // for testing
        PlayerBank.setAllCollectables(27);
        InternalClock.setMaxTime(200);
        InternalClock.start();

//        new PortraitManager(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, R.drawable.ic_launcher_background);
//        new PortraitManager(10*Scale.UNIT, Scale.GROUND - (25 * Scale.UNIT), Scale.UNIT, Scale.UNIT, R.drawable.the_guide); //the guide
 
        new PlatformManager(0, 0, Constants.SCREEN_WIDTH, 12, R.drawable.ground); //ground
        new PlatformManager(0,  4, 9 * Scale.UNIT, 4, R.drawable.platform1); //platform1
        new PlatformManager(7, 12, Constants.SCREEN_WIDTH, 4, R.drawable.platform2); //platform2
        new PlatformManager(2, 16, 3 * Scale.UNIT, 1,R.drawable.platform3); //platform3
        new PlatformManager(10, 24, Constants.SCREEN_WIDTH, 9, R.drawable.platform4); //platform4
        new PlatformManager(12, 32, Constants.SCREEN_WIDTH, 2, R.drawable.platform5); //platform5
        new PlatformManager(0, 41, 6 * Scale.UNIT, 4); //platform6
        new PlatformManager(9, 41, 11 * Scale.UNIT, 4); //platform6.5
        new PlatformManager(0, 45, 4 * Scale.UNIT, 4); //platform7
        new PlatformManager(5, 54, 6 * Scale.UNIT, 1); //platform8
        new PlatformManager(11, 52, 12 * Scale.UNIT, 1); //platform11
        new PlatformManager(13, 57, Constants.SCREEN_WIDTH, 1); //platform12
        new PlatformManager(0, 26, 5 * Scale.UNIT, 4); //platform13
        new PlatformManager(11, 33, Constants.SCREEN_WIDTH, 1); //platform14
        new PlatformManager(0, 70, 3 * Scale.UNIT, 2); //platform15
        new PlatformManager(5, 74, Constants.SCREEN_WIDTH, 5); //platform16
        new PlatformManager(5, 80, 11 * Scale.UNIT, 3); //platform17
        new PlatformManager(0, 81, 3 * Scale.UNIT, 3); //platform18
        new PlatformManager(8, 38, 9 * Scale.UNIT, 1); //platform19
        new PlatformManager(7,  8, Scale.UNIT*9, 4,1,0,0,0);

        //other objects
        new DoorWayManager(0, 6, 0, 11, 32, 0); //doorWay1
        new LaunchPadManager(0, 46); //launchpad1


        //damageBlock
        new DamageBlockManager("poison", 0, 37, Scale.UNIT*6, 1); //damageBlock1
        new DamageBlockManager("poison", 8, 37, Scale.UNIT*11, 1); //damageBlock2
        new DamageBlockManager("poison", 0, 22, Scale.UNIT*5, 1); //damageBlock3
        new DamageBlockManager("prickle", 5, 69, Constants.SCREEN_WIDTH, 1); //damageBlock4

//        //for testing
//        new DamageBlockManager("poison ivy", 0, 6, Scale.UNIT*8, 1);


        //coins
        new CoinBrickBlockManager("coin", 1, 11, 13); //coin1
        new CoinBrickBlockManager("coin", 1, 9, 13); //coin2
        new CoinBrickBlockManager("coin", 1, 1, 27); //coin 3
        new CoinBrickBlockManager("coin", 1, 3, 27); //coin 4
        new CoinBrickBlockManager("coin", 1, 5, 55); //coin 5
        new CoinBrickBlockManager("coin", 1, 11, 53); //coin 6
        new CoinBrickBlockManager("coin", 5, 8, 39); //coin 7
        new CoinBrickBlockManager("coin", 5, 8, 40); //coin 8
        new CoinBrickBlockManager("coin", 5, 8, 41); //coin 9
        new CoinBrickBlockManager("coin", 1, 5, 81); //coin 10
        new CoinBrickBlockManager("coin", 1, 6, 81); //coin 11
        new CoinBrickBlockManager("coin", 1, 7, 81); //coin 12
        new CoinBrickBlockManager("coin", 1, 8, 81); //coin 13
        new CoinBrickBlockManager("coin", 1, 9, 81); //coin 14
        new CoinBrickBlockManager("coin", 1, 10,81); //coin 15
        new CoinBrickBlockManager("coin", 5, 0, 82); //coin 16
        new CoinBrickBlockManager("coin", 5, 0, 83); //coin 17
        new CoinBrickBlockManager("coin", 5, 2, 82); //coin 18
        new CoinBrickBlockManager("coin", 5, 2, 83); //coin 19
        new CoinBrickBlockManager("coin", 10, 1, 82); //coin 20
        new CoinBrickBlockManager("coin", 5, 1, 83); //coin 21

//        //for testing
//        new CoinBrickBlockManager("coin", 20, 1, 5); //coin 21
//        new CoinBrickBlockManager("coin", 20, 2, 5); //coin 21
//        new CoinBrickBlockManager("coin", 20, 3, 5); //coin 21
//        new CoinBrickBlockManager("coin", 20, 4, 5); //coin 21
//        new CoinBrickBlockManager("coin", 20, 5, 5); //coin 21
//        new CoinBrickBlockManager("coin", 20, 6, 5); //coin 21



        new ItemManager("random", 0, 10, 13); //random1
        new ItemManager("static parachute", 1, 2, 17); //static parachute 1
        new ItemManager("static parachute", 1, 13, 58); //static parachute 2
        new ItemManager("health up", 0, 2,27); //health up 1


        //enemies
//        new EnemyManager("fast", 7*Scale.UNIT, Scale.GROUND - (13*Scale.UNIT)); //enemy1
//        new EnemyManager("slow", 5*Scale.UNIT, Scale.GROUND - (13*Scale.UNIT)); //enemy2
//        new EnemyManager("medium", 3*Scale.UNIT, Scale.GROUND - (13*Scale.UNIT)); //enemy3
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
        canvas.translate(0, Camera.getTranslateY());

        //background stuff
        player.rainPressed(canvas);
        finishLine1.draw(canvas);
//        finishLine2.draw(canvas);


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
        for (int i = 0; i < CoinBrickBlockManager.getSwitchBlocks().size(); i++) {
            CoinBrickBlockManager.getSwitchBlocks().get(i).drawSwitch(canvas);
        }
        for (int i = 0; i < CoinBrickBlockManager.getCoinBrickBlocks().size(); i++) {
            CoinBrickBlockManager.getCoinBrickBlocks().get(i).drawBlock(canvas);
        }

        Platform.drawGuts(canvas);
        EnemyManager.drawRect(canvas, player);
        player.draw(canvas);

        //this keeps UI display constant
        canvas.translate(0, -Camera.getTranslateY());

        player.drawHealth(canvas);
    }

    @Override
    public void update() {
        player.update();
        finishLine1.finishLineCollision(player);
//        finishLine2.finishLineCollision(player);
        if (LevelInterface.levelComplete) {
            PlayerProgression.setLevel1Complete();
            PlayerProgression.setLevel1CompleteState(1);
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
        CoinBrickBlock.coinBrickBlockCollision(player);
        Item.itemCollision(player);
    }
}