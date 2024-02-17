package com.example.themarblegame.managers;

import com.example.themarblegame.obstacles.RedBlueBlock;

import java.util.ArrayList;

public class RedBlueBlockManager {
    private static ArrayList<RedBlueBlock> switchBlocks = new ArrayList<>();
    private static ArrayList<RedBlueBlock> redBlocks = new ArrayList<>();
    private static ArrayList<RedBlueBlock> blueBlocks = new ArrayList<>();
    private RedBlueBlock switchBlock;
    private RedBlueBlock redBlock;
    private RedBlueBlock blueBlock;

    public RedBlueBlockManager(int startX, int startY, int ceiling) {
        this.switchBlock = new RedBlueBlock(startX, startY, ceiling);
        populateSwitchBlock(switchBlock);
    }

    public RedBlueBlockManager(String color, int startX, int startY, int endX, int height) {
        if (color.equalsIgnoreCase("red")) {
            this.redBlock = new RedBlueBlock(color, startX, startY, endX, height);
            populateRedBlock(redBlock);
        } else {
            this.blueBlock = new RedBlueBlock(color, startX, startY, endX, height);
            populateBlueBlock(blueBlock);
        }
    }

    public RedBlueBlockManager(String color, int startX, int startY, int endX, int height, int l, int t, int r, int b) {
        if (color.equalsIgnoreCase("red")) {
            this.redBlock = new RedBlueBlock(color, startX, startY, endX, height, l, t, r, b);
            populateRedBlock(redBlock);
        } else {
            this.blueBlock = new RedBlueBlock(color, startX, startY, endX, height, l, t, r, b);
            populateBlueBlock(blueBlock);
        }
    }

    public void populateSwitchBlock(RedBlueBlock redBlueBlock) {
        switchBlocks.add(redBlueBlock);
    }

    public void populateRedBlock(RedBlueBlock redBlueBlock) {
        redBlocks.add(redBlueBlock);
    }

    public void populateBlueBlock(RedBlueBlock redBlueBlock) {
        blueBlocks.add(redBlueBlock);
    }

    public static ArrayList<RedBlueBlock> getSwitchBlocks() {
        return switchBlocks;
    }

    public static ArrayList<RedBlueBlock> getRedBlocks() {
        return redBlocks;
    }

    public static ArrayList<RedBlueBlock> getBlueBlocks() {
        return blueBlocks;
    }

    public static void reset() {
        switchBlocks.clear();
        redBlocks.clear();
        blueBlocks.clear();
        RedBlueBlock.isRed = true;
    }
}
