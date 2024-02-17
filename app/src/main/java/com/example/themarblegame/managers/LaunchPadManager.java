package com.example.themarblegame.managers;

import com.example.themarblegame.obstacles.LaunchPad;

import java.util.ArrayList;

public class LaunchPadManager {
    private static ArrayList<LaunchPad> launchPads = new ArrayList<>();
    private LaunchPad launchPad;

    public LaunchPadManager (int x, int y) {
        this.launchPad = new LaunchPad(x, y);
        populateLaunchPads(launchPad);
    }

    public static ArrayList<LaunchPad> getLaunchPads() {
        return launchPads;
    }

    public void populateLaunchPads(LaunchPad launchPad) {
        launchPads.add(launchPad);
    }

    public static void reset() {
        launchPads.clear();
    }
}
