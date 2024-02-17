package com.example.themarblegame.main;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

public class MainActivity extends Activity {

    public static final String TAG = "msg";

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        WordBank.setUsersLanguage();
        WordBank.loadAllText();
        Log.e(TAG, "onCreate: " + WordBank.getUsersLanguage());

        //width = 720 px height =
        if (getResources().getDisplayMetrics().widthPixels >= 1440) {
            Constants.DIVISOR = 2;
            Constants.SCREEN_WIDTH = (int) (getResources().getDisplayMetrics().widthPixels/2);
            Constants.SCREEN_HEIGHT = (int) (getResources().getDisplayMetrics().heightPixels/2);
        } else if (getResources().getDisplayMetrics().widthPixels >= 1080) {
            Constants.DIVISOR = 1.5;
            Constants.SCREEN_WIDTH = (int) (getResources().getDisplayMetrics().widthPixels/1.5);
            Constants.SCREEN_HEIGHT = (int) (getResources().getDisplayMetrics().heightPixels/1.5);
        } else {
            Constants.DIVISOR = 1;
            Constants.SCREEN_WIDTH = getResources().getDisplayMetrics().widthPixels;
            Constants.SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Log.e(TAG, Constants.SCREEN_WIDTH + " " + Constants.SCREEN_HEIGHT + " " );
        Log.e(TAG, getResources().getDisplayMetrics().widthPixels + " " + getResources().getDisplayMetrics().heightPixels);
        setContentView(new GamePanel(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (SceneManager.gameView) {
            if (LevelInterface.powerMenuSelected) {
                LevelInterface.powerMenuSelected = false;
            }
            LevelInterface.pauseMenuSelected = true;
            LevelInterface.pauseMenuState = 1;
        }
    }
}