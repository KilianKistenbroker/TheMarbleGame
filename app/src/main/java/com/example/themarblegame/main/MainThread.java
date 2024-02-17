package com.example.themarblegame.main;

import android.graphics.Canvas;
import android.os.Build;
import android.view.SurfaceHolder;

import androidx.annotation.RequiresApi;

public class MainThread extends Thread {
    private final SurfaceHolder surfaceHolder;
    private final GamePanel gamePanel;
    public static Canvas canvas;

//    public static final double MAX_UPS = 60.0;
//    private static final double UPS_PERIOD = (1E+3/MAX_UPS);

    private boolean isRunning = false;

    public void setRunning(boolean running) {
        this.isRunning = running;
    }


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

//    public double getAverageUPS() {
//        return averageUPS;
//    }
//
//    public double getAverageFPS() {
//        return averageFPS;
//    }

//    public void startLoop() {
//        Log.d("GameLoop.java", "startLoop()");
//        isRunning = true;
//        start();
//    }

//    @Override
//    public void run() {
////        Log.d("GameLoop.java", "run()");
//        super.run();
//
//        // Declare time and cycle count variables
//        int updateCount = 0;
////        int frameCount = 0;
//
//        long startTime;
//        long elapsedTime;
//        long sleepTime;
//
//        // Game loop
//        Canvas canvas = null;
//        startTime = System.currentTimeMillis();
//        while(isRunning) {
//
//            // Try to update and render game
//            try {
//                canvas = surfaceHolder.lockCanvas();
//                synchronized (surfaceHolder) {
//                    gamePanel.update();
//                    updateCount++;
//
//                    gamePanel.draw(canvas);
//                }
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } finally {
//                if(canvas != null) {
//                    try {
//                        surfaceHolder.unlockCanvasAndPost(canvas);
////                        frameCount++;
//                    } catch(Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            // Pause game loop to not exceed target UPS
//            elapsedTime = System.currentTimeMillis() - startTime;
//            sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
//            if(sleepTime > 0) {
//                try {
//                    sleep(sleepTime);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            // Skip frames to keep up with target UPS
//            while(sleepTime < 0 && updateCount < MAX_UPS-1) {
//                gamePanel.update();
//                updateCount++;
//                elapsedTime = System.currentTimeMillis() - startTime;
//                sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
//            }
//
//            // Calculate average UPS and FPS
//            elapsedTime = System.currentTimeMillis() - startTime;
//            if(elapsedTime >= 1000) {
////                averageUPS = updateCount / (1E-3 * elapsedTime);
////                averageFPS = frameCount / (1E-3 * elapsedTime);
//                updateCount = 0;
////                frameCount = 0;
//                startTime = System.currentTimeMillis();
//            }
//        }
//    }

//    public void stopLoop() {
////        Log.d("GameLoop.java", "stopLoop()");
//        isRunning = false;
//        try {
//            join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        double next_game_tick = System.currentTimeMillis();
        int loops;

        while (isRunning) {
            loops = 0;
            canvas = null;

            int MAX_FRAME_SKIP = 5;
            int TICKS_PER_SECOND = 60;
            int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAME_SKIP) {
                try {
                    canvas = this.surfaceHolder.lockHardwareCanvas();
                    synchronized (surfaceHolder) {
                        this.gamePanel.update();
                        this.gamePanel.draw(canvas);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        try {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                next_game_tick += SKIP_TICKS;
                loops++;
            }
        }
    }
}