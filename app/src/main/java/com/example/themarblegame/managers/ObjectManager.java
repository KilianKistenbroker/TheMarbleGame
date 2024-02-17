package com.example.themarblegame.managers;

import com.example.themarblegame.main.Camera;
import com.example.themarblegame.main.HomeInterface;
import com.example.themarblegame.main.InternalClock;
import com.example.themarblegame.main.LevelInterface;
import com.example.themarblegame.main.PlayerBank;
import com.example.themarblegame.main.SceneManager;

//this will manage data load using reset() and quit()
public class ObjectManager extends PlayerBank {
//    private Animations animations = new Animations();
    public int savedLevel;

    public void reset() {
        savedLevel = SceneManager.currentLevel;
        SceneManager.currentLevel = 0;
        SceneManager.reset1(savedLevel);
        LevelInterface.resetMenus();
        Camera.reset();
        LevelInterface.levelPhysicsTimer.cancel();
        HomeInterface.homePhysicsTimer.cancel();
        resetAll();
        EnemyManager.reset();
        DamageBlockManager.reset();
        DoorWayManager.reset();
        ItemManager.reset();
        InternalClock.cancel();
        LaunchPadManager.reset();
        LevelInterface.resetDrawables();
        PlatformManager.reset();
        PortraitManager.reset();
        RedBlueBlockManager.reset();
        CoinBrickBlockManager.reset();
        setRainFalse();
        setArmsAttached(false);
        SceneManager.reset2(savedLevel);
        SceneManager.currentLevel = savedLevel;
    }

    public void quit() {
        savedLevel = SceneManager.currentLevel;
        SceneManager.currentLevel = 0;
        SceneManager.reset1(savedLevel);
        LevelInterface.resetMenus();
        Camera.reset();
        LevelInterface.levelPhysicsTimer.cancel();
        HomeInterface.homePhysicsTimer.cancel();
        HomeInterface.isReCentered = true;
        SceneManager.gameView = false;
        resetAll();
        EnemyManager.reset();
        DamageBlockManager.reset();
        DoorWayManager.reset();
        ItemManager.reset();
        InternalClock.cancel();
        LaunchPadManager.reset();
        LevelInterface.resetDrawables();
        PlatformManager.reset();
        PortraitManager.reset();
        RedBlueBlockManager.reset();
        CoinBrickBlockManager.reset();
        setRainFalse();
        setArmsAttached(false);
    }
}
