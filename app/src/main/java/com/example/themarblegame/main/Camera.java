package com.example.themarblegame.main;

public class Camera {
    private static float translateY = 0;
    private static float translateX = 0;
    private boolean topped = false;
    private boolean bottomed = false;
    private float topLimit = (Constants.SCREEN_HEIGHT/3f);
    private float bottomLimit =  Constants.SCREEN_HEIGHT - (Constants.SCREEN_HEIGHT/3f);
    private float y = topLimit;
    private boolean counter = true;
    public boolean SET_CAMERA = false;
    public int SAVED_SETTINGS = 0;
    public static int CURRENT_AREA = 0;

    //this is for doorway, player update, and overall optimization
    public static void changeAreaTo(int area) {
        switch (area) {
            case 0:
                translateX = 0;
                CURRENT_AREA = 0;
                break;
            case 1:
                translateX = Constants.SCREEN_WIDTH;
                CURRENT_AREA = 1;
                break;
            case 2:
                translateX = Constants.SCREEN_WIDTH*area;
                CURRENT_AREA = 2;
                break;
        }
    }

    public void camera(Player player) {
        if (LevelInterface.pauseMenuSelected) {
            if (counter) {
                LevelTouchControls.SCROLL = (int) translateY;
                LevelTouchControls.POSITION = (int) -translateY;
                SAVED_SETTINGS = (int) translateY;
                counter = false;
            }
            translateY = LevelTouchControls.SCROLL;

        } else {
            counter = true;
            if (SET_CAMERA) {
                translateY = SAVED_SETTINGS;
                SET_CAMERA = false;
            }

            if (player.getBounds().top < topLimit) {
                y = player.getBounds().top;
                translateY = (Constants.SCREEN_HEIGHT/3f) - y;
                topped = true;
            }

            if (player.playerPhysics.getYVelocity() > 0 && topped){
                bottomLimit -= (topLimit - y);
                topLimit = y;
                topped = false;
            }

            if (player.playerPhysics.getYVelocity() < 0 && bottomed){
                topLimit -= (bottomLimit - y);
                bottomLimit = y;
                bottomed = false;
            }

            if (player.getBounds().bottom > bottomLimit) {
                y = player.getBounds().bottom;
                translateY =  (Constants.SCREEN_HEIGHT - (Constants.SCREEN_HEIGHT/3f)) - y;
                bottomed = true;
            }
        }
    }

    public static void reset() {
        LevelTouchControls.SCROLL = 0;
        LevelTouchControls.POSITION = 0;
        CURRENT_AREA = 0;
        translateY = 0;
        translateX = 0;
    }

    public static float getTranslateX() {
        return translateX;
    }

    public static float getTranslateY() {
        return translateY;
    }
}