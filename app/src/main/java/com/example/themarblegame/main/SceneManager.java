package com.example.themarblegame.main;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.levels.Level1;
import com.example.themarblegame.levels.Level10;
import com.example.themarblegame.levels.Level2;
import com.example.themarblegame.levels.Level3;
import com.example.themarblegame.levels.Level4;
import com.example.themarblegame.levels.Level5;
import com.example.themarblegame.levels.Level6;
import com.example.themarblegame.levels.Level7;
import com.example.themarblegame.levels.Level8;
import com.example.themarblegame.levels.Level9;

public class SceneManager implements GameDesigner {
    private static final String TAG = "";
    //    public static boolean paused = true;
//    public static LevelManager levelManager = new LevelManager();
    HomeTouchControls homeTouchControls = new HomeTouchControls();
    HomeInterface homeInterface = new HomeInterface();
    LevelInterface levelInterface = new LevelInterface();
    Background background = new Background();
    public static boolean gameView = false; // might have to be static
    public static int currentLevel = 0; // will be used for switch statement

    public static void resetCamera() {
        switch (currentLevel) {
            case 1:
                HomeTouchControls.level1.getCamera().SET_CAMERA = true;
                break;
            case 2:
                HomeTouchControls.level2.getCamera().SET_CAMERA = true;
                break;
            case 3:
                HomeTouchControls.level3.getCamera().SET_CAMERA = true;
                break;
            case 4:
                HomeTouchControls.level4.getCamera().SET_CAMERA = true;
                break;
            case 5:
                HomeTouchControls.level5.getCamera().SET_CAMERA = true;
                break;
            case 6:
                HomeTouchControls.level6.getCamera().SET_CAMERA = true;
                break;
            case 7:
                HomeTouchControls.level7.getCamera().SET_CAMERA = true;
                break;
            case 8:
                HomeTouchControls.level8.getCamera().SET_CAMERA = true;
                break;
            case 9:
                HomeTouchControls.level9.getCamera().SET_CAMERA = true;
                break;
            case 10:
                HomeTouchControls.level10.getCamera().SET_CAMERA = true;
                break;
        }
    }

    public void receivedTouch(MotionEvent event) {
        if (!gameView)
            homeTouchControls.onTouchEvent(event);
        else {
            //run switch statement here for level selection
            switch (currentLevel) {
                case 1:
                    HomeTouchControls.level1.receivedTouch(event);
                    break;
                case 2:
                    HomeTouchControls.level2.receivedTouch(event);
                    break;
                case 3:
                    HomeTouchControls.level3.receivedTouch(event);
                    break;
                case 4:
                    HomeTouchControls.level4.receivedTouch(event);
                    break;
                case 5:
                    HomeTouchControls.level5.receivedTouch(event);
                    break;
                case 6:
                    HomeTouchControls.level6.receivedTouch(event);
                    break;
                case 7:
                    HomeTouchControls.level7.receivedTouch(event);
                    break;
                case 8:
                    HomeTouchControls.level8.receivedTouch(event);
                    break;
                case 9:
                    HomeTouchControls.level9.receivedTouch(event);
                    break;
                case 10:
                    HomeTouchControls.level10.receivedTouch(event);
                    break;
            }
        }
    }

    public static void reset1(int savedLevel) {
        switch (savedLevel) {
            case 1:
                HomeTouchControls.level1 = null;
                break;
            case 2:
                HomeTouchControls.level2 = null;
                break;
            case 3:
                HomeTouchControls.level3 = null;
                break;
            case 4:
                HomeTouchControls.level4 = null;
                break;
            case 5:
                HomeTouchControls.level5 = null;
                break;
            case 6:
                HomeTouchControls.level6 = null;
                break;
            case 7:
                HomeTouchControls.level7 = null;
                break;
            case 8:
                HomeTouchControls.level8 = null;
                break;
            case 9:
                HomeTouchControls.level9 = null;
                break;
            case 10:
                HomeTouchControls.level10 = null;
                break;
        }
    }

    public static void reset2(int savedLevel) {
        switch (savedLevel) {
            case 1:
                HomeTouchControls.level1 = new Level1();
                break;
            case 2:
                HomeTouchControls.level2 = new Level2();
                break;
            case 3:
                HomeTouchControls.level3 = new Level3();
                break;
            case 4:
                HomeTouchControls.level4 = new Level4();
                break;
            case 5:
                HomeTouchControls.level5 = new Level5();
                break;
            case 6:
                HomeTouchControls.level6 = new Level6();
                break;
            case 7:
                HomeTouchControls.level7 = new Level7();
                break;
            case 8:
                HomeTouchControls.level8 = new Level8();
                break;
            case 9:
                HomeTouchControls.level9 = new Level9();
                break;
            case 10:
                HomeTouchControls.level10 = new Level10();
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (!gameView) {
            background.drawBackground(canvas, "clear day", "green mountains", "green mountains", HomeInterface.SCROLL);
            homeInterface.drawLevels(canvas);
            homeInterface.draw(canvas); //draw all the stuff here
//            homeInterface.drawMenus(canvas);

        } else {
            switch (currentLevel) {
                case 1:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level1.draw(canvas);
                    break;
                case 2:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level2.draw(canvas);
                    break;
                case 3:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level3.draw(canvas);
                    break;
                case 4:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level4.draw(canvas);
                    break;
                case 5:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level5.draw(canvas);
                    break;
                case 6:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level6.draw(canvas);
                    break;
                case 7:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level7.draw(canvas);
                    break;
                case 8:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level8.draw(canvas);
                    break;
                case 9:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level9.draw(canvas);
                    break;
                case 10:
                    background.drawBackground(canvas, "clear day", "green mountains", "green mountains", Camera.getTranslateY());
                    HomeTouchControls.level10.draw(canvas);
                    break;
            }
            levelInterface.draw(canvas);
        }
    }

    @Override
    public void update() {
        if (!gameView) {
            homeInterface.update();
//            background.updateBackground();
        } else {
            if (!LevelInterface.pauseMenuSelected){
//                background.updateBackground();
                switch (currentLevel) {
                    case 1:
                        HomeTouchControls.level1.update();
                        break;
                    case 2:
                        HomeTouchControls.level2.update();
                        break;
                    case 3:
                        HomeTouchControls.level3.update();
                        break;
                    case 4:
                        HomeTouchControls.level4.update();
                        break;
                    case 5:
                        HomeTouchControls.level5.update();
                        break;
                    case 6:
                        HomeTouchControls.level6.update();
                        break;
                    case 7:
                        HomeTouchControls.level7.update();
                        break;
                    case 8:
                        HomeTouchControls.level8.update();
                        break;
                    case 9:
                        HomeTouchControls.level9.update();
                        break;
                    case 10:
                        HomeTouchControls.level10.update();
                        break;
                }
            }
            levelInterface.update();
        }
    }
}
