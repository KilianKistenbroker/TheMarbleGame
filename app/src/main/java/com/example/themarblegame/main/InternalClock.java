package com.example.themarblegame.main;

import android.util.Log;

import java.util.TimerTask;

public class InternalClock {

    //in-game timer
    private static final String TAG = "msg";
    static boolean timerRunning = false;
    static int maxTime;
    public static int secondsPassed = 0;

    public static int LHS = 59;
    public static int RHS = 59;
    public static String isZero = "00";

    static java.util.Timer timer;
    static TimerTask timerTask;

    private static boolean livesRunning = false;
    static java.util.Timer livesTimer;
    static TimerTask livesTimerTask;

    public static void setLivesRunning(boolean livesRunning) {
        InternalClock.livesRunning = livesRunning;
    }

    public static String getLivesTime() {
        if (LHS < 10) {
            if (RHS < 10)
                return "0"+LHS+":0"+RHS;
            else
                return "0"+LHS+":"+RHS;
        } else if (RHS < 10)
            return LHS+":0"+RHS;
        else
            return LHS+":"+RHS;
    }

    public static void resetLivesTimer() {
        livesRunning = false;
        LHS = 59;
        RHS = 59;
    }

    public static void setMaxTime(int maxTime) {
        InternalClock.maxTime = maxTime;
    }

    public static int getSecondsRemaining() {
        return (maxTime - secondsPassed);
    }

    public static void pause() {
        timerRunning = false;
    }

    public static void resume() {
        timerRunning = true;
    }

    public static void restart() {
        cancel();
        start();
    }

    public static void start() {
        timer = new java.util.Timer();
        timerTask  = new TimerTask() {
            @Override
            public void run() {
                if (timerRunning) {
                    secondsPassed++;
                }
                Log.e(TAG, "run: " + secondsPassed + " seconds" );
//                Log.e(TAG, "timeStep: " + TIME_STEP + " seconds" );
                if (secondsPassed >=maxTime) {
                    timerRunning = false;
                    secondsPassed = maxTime;
                    LevelInterface.gameOver1 = true;
                    LevelInterface.gameOverState = 1;
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }



    public static void livesLeftTimerTask() {
        livesTimer = new java.util.Timer();
        livesTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (livesRunning) {
                    if (LHS == 0 && RHS == 0) {
                        PlayerBank.setLives(1);
                        LHS = 60;
                        livesRunning = false;
                    } else if (RHS == 0) {
                        LHS--;
                        RHS = 59;
                    } else
                        RHS--;
                }
            }
        };
        livesTimer.scheduleAtFixedRate(livesTimerTask,0,1000);
    }

//    public static void resumeLives() {
//        livesRunning = true;
//    }

    public static void pauseLives() {
        livesRunning = false;
    }

    public static void cancel() {
        timer.cancel();
        secondsPassed = 0;
        timerRunning = false;
//        timer = null;
//        timerTask = null;
    }
}
