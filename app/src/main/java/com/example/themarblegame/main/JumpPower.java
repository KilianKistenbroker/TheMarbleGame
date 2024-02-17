package com.example.themarblegame.main;

import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class JumpPower{

    //this is for power jumping
    private static final String TAG = "msg";
    boolean timerRunning = false;
    int secondsPassed = 0;

    Timer timer = new Timer();

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            secondsPassed++;
            Log.e(TAG, "run: " + secondsPassed + "seconds" );
            if (secondsPassed == 100) {
                Player.setJumpPower(100);
                timer.cancel();
                timerRunning = false;
            }
        }
    };

    public void start() {
        if (!timerRunning){
            timer.scheduleAtFixedRate(timerTask, 0, 10);
            timerRunning = true;
        }
    }

    public void cancel() {
        Player.setJumpPower(secondsPassed);
        timer.cancel();
        secondsPassed = 0;
        timerRunning = false;
    }
}