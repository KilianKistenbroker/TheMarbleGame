package com.example.themarblegame.managers;

import com.example.themarblegame.obstacles.Platform;

import java.util.ArrayList;

public class PlatformManager {
    private static ArrayList<Platform> platforms = new ArrayList<>();
    private Platform platform;

    public PlatformManager(int startX, int startY, int endX, int height, int bitmap) {
        this.platform = new Platform(startX, startY, endX, height, bitmap);
        populatePlatforms(platform);
    }

    public PlatformManager(int startX, int startY, int endX, int height) {
        this.platform = new Platform(startX, startY, endX, height);
        populatePlatforms(platform);
    }

    public PlatformManager(int startX, int startY, int endX, int height, int l, int t, int r, int b) {
        this.platform = new Platform(startX, startY, endX, height, l, t, r, b);
        populatePlatforms(platform);
    }

    public void populatePlatforms(Platform platform) {
        platforms.add(platform);
    }

    public static ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public static void reset() {
        platforms.clear();
    }
}
