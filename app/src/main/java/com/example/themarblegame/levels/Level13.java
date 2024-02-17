//package com.example.themarblegame.levels;
//
//import android.graphics.Canvas;
//import android.view.MotionEvent;
//
//import com.example.themarblegame.interfaces.GameDesigner;
//import com.example.themarblegame.main.Camera;
//import com.example.themarblegame.main.InternalClock;
//import com.example.themarblegame.main.LevelTouchControls;
//import com.example.themarblegame.main.Player;
//import com.example.themarblegame.main.Scale;
//import com.example.themarblegame.managers.CoinBrickBlockManager;
//import com.example.themarblegame.managers.DamageBlockManager;
//import com.example.themarblegame.managers.DoorWayManager;
//import com.example.themarblegame.managers.EnemyManager;
//import com.example.themarblegame.managers.ItemManager;
//import com.example.themarblegame.managers.LaunchPadManager;
//import com.example.themarblegame.managers.PlatformManager;
//import com.example.themarblegame.managers.PortraitManager;
//import com.example.themarblegame.managers.RedBlueBlockManager;
//import com.example.themarblegame.obstacles.CoinBrickBlock;
//import com.example.themarblegame.obstacles.DamageBlock;
//import com.example.themarblegame.obstacles.DoorWay;
//import com.example.themarblegame.obstacles.FinishLine;
//import com.example.themarblegame.obstacles.Item;
//import com.example.themarblegame.obstacles.LaunchPad;
//import com.example.themarblegame.obstacles.Platform;
//import com.example.themarblegame.obstacles.RedBlueBlock;
//
//public class Level13 implements GameDesigner {
//    private LevelTouchControls levelTouchControls;
//    private Camera camera;
//    private Player player;
//    private FinishLine finishLine;
//
//    public Level13() {
//        //essentials
//        camera = new Camera();
//        levelTouchControls = new LevelTouchControls();
//        player = new Player();
//        finishLine = new FinishLine(0);
//        InternalClock.setMaxTime(300);
//        InternalClock.start();
//
//    }
//
//    public void receivedTouch(MotionEvent event) {
//        levelTouchControls.onTouchEvent(player, event);
//    }
//
//    public Camera getCamera(){
//        return camera;
//    }
//
//    @Override
//    public void draw(Canvas canvas) { //order matters here
//
//        //camera
//        camera.camera(player);
//        canvas.translate(0, camera.getTranslateY());
//
//        //background stuff
//        player.rainPressed(canvas);
//        finishLine.draw(canvas);
//
//        for (int i = 0; i < PortraitManager.getPortraits().size(); i++) {
//            PortraitManager.getPortraits().get(i).draw(canvas);
//        }
//        for (int i = 0; i < DoorWayManager.getDoorWays().size(); i++) {
//            DoorWayManager.getDoorWays().get(i).draw(canvas);
//        }
//        for (int i = 0; i < ItemManager.getItems().size(); i++) {
//            ItemManager.getItems().get(i).draw(canvas);
//        }
//        for (int i = 0; i < LaunchPadManager.getLaunchPads().size(); i++) {
//            LaunchPadManager.getLaunchPads().get(i).draw(canvas);
//        }
//        for (int i = 0; i < DamageBlockManager.getDamageBlocks().size(); i++) {
//            DamageBlockManager.getDamageBlocks().get(i).draw(canvas);
//        }
////        for (int i = 0; i < OneWayManager.getOneWays().size(); i++) {
////            OneWayManager.getOneWays().get(i).draw(canvas);
////        }
//        for (int i = 0; i < PlatformManager.getPlatforms().size(); i++) {
//            PlatformManager.getPlatforms().get(i).draw(canvas);
//        }
//        for (int i = 0; i < RedBlueBlockManager.getSwitchBlocks().size(); i++) {
//            RedBlueBlockManager.getSwitchBlocks().get(i).drawSwitch(canvas);
//        }
//        for (int i = 0; i < RedBlueBlockManager.getRedBlocks().size(); i++) {
//            RedBlueBlockManager.getRedBlocks().get(i).drawRed(canvas);
//        }
//        for (int i = 0; i < RedBlueBlockManager.getBlueBlocks().size(); i++) {
//            RedBlueBlockManager.getBlueBlocks().get(i).drawBlue(canvas);
//        }
//        for (int i = 0; i < CoinBrickBlockManager.getSwitchBlocks().size(); i++) {
//            CoinBrickBlockManager.getSwitchBlocks().get(i).drawSwitch(canvas);
//        }
//        for (int i = 0; i < CoinBrickBlockManager.getCoinBrickBlocks().size(); i++) {
//            CoinBrickBlockManager.getCoinBrickBlocks().get(i).drawBlock(canvas);
//        }
//
//        EnemyManager.drawRect(canvas, player);
//        player.draw(canvas);
//
//        //this keeps UI display constant
//        canvas.translate(0, -camera.getTranslateY());
//
//        player.drawHealth(canvas);
//
//    }
//
//    @Override
//    public void update() {
//        player.update();
//        finishLine.finishLineCollision(player);
//        EnemyManager.enemyCollision(player);
//        EnemyManager.mediumLaserCollision(player);
//        EnemyManager.laserCollision(player);
//        EnemyManager.selfCollision();
//
////        OneWay.oneWayCollision(playerPhysics);
//        DoorWay.doorWayCollision(player);
//        LaunchPad.launchPadCollision(player);
//        DamageBlock.prickleCollision(player);
//        Platform.platformCollision(player);
//        RedBlueBlock.switchBlocksCollision(player);
//        CoinBrickBlock.coinBrickBlockCollision(player);
//        Item.itemCollision(player);
//    }
//}
