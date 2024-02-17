package com.example.themarblegame.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.themarblegame.R;
import com.example.themarblegame.obstacles.Portrait;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private final SceneManager sceneManager;
    private final Persistence persistence = new Persistence(getContext());
//    Portrait background1;
//    Portrait background;
//    Background background;


    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT = context;
        thread = new MainThread(getHolder(), this);
        sceneManager = new SceneManager();
        setFocusable(true);
        getHolder().setFixedSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
//        background1 = new Portrait(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, R.drawable.background1, false);
//        background = new Portrait(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, R.drawable.background, false);
//        background = new Background();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true);
        thread.start();
        persistence.getData();

        if (InternalClock.livesTimer == null)
            InternalClock.livesLeftTimerTask();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (SceneManager.gameView){
            LevelInterface.pauseMenuSelected = true;
            LevelInterface.pauseMenuState = 1;
        }

        persistence.saveData();
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sceneManager.receivedTouch(event);
        return true;
    }

    public void update() {
        sceneManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas!=null){
            super.draw(canvas);
//            canvas.drawBitmap(background1.getScaledBitmap(), null, background1.getRect(), null);
//            canvas.drawBitmap(background.getScaledBitmap(), null, background.getRect(), null);
//            background.drawBackground(canvas);

//            canvas.drawColor( Color.WHITE);
            sceneManager.draw(canvas);
        } else
            thread.setRunning(false);
    }
}
