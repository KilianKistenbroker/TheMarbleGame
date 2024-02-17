package com.example.themarblegame.managers;

import com.example.themarblegame.obstacles.Portrait;

import java.util.ArrayList;

public class PortraitManager {
    private static final ArrayList<Portrait> portraits = new ArrayList<>();

    public PortraitManager (int startX, int startY, int width, int height, int bitmap, boolean isPhysics) {
        Portrait portrait = new Portrait(startX, startY, width, height, bitmap, isPhysics);
        populatePortraits(portrait);
    }
    public PortraitManager (int startX, int startY, int width, int height, boolean isPhysics) {
        Portrait portrait = new Portrait(startX, startY, width, height, isPhysics);
        populatePortraits(portrait);
    }

    public static ArrayList<Portrait> getPortraits() {
        return portraits;
    }

    public void populatePortraits(Portrait portrait){
        portraits.add(portrait);
    }

    public static void reset() {
        portraits.clear();
    }
}
