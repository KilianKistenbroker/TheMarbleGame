package com.example.themarblegame.managers;

import com.example.themarblegame.obstacles.CoinBrickBlock;

import java.util.ArrayList;

public class CoinBrickBlockManager {
    private static ArrayList<CoinBrickBlock> switchBlocks = new ArrayList<>();
    private static ArrayList<CoinBrickBlock> coinBrickBlocks = new ArrayList<>();
    private CoinBrickBlock switchBlock;
    private CoinBrickBlock coinBrickBlock;

    public CoinBrickBlockManager(int startX, int startY, int ceiling) {
        //do stuff
        this.switchBlock = new CoinBrickBlock(startX, startY, ceiling);
        populateSwitchBlock(switchBlock);
    }

    public CoinBrickBlockManager(String type, int value, int startX, int startY) {
        this.coinBrickBlock = new CoinBrickBlock(type, value, startX, startY);
        populateCoinBrickBlock(coinBrickBlock);
    }

    public CoinBrickBlockManager(String type, int value, int startX, int startY, int l, int t, int r, int b) {
        this.coinBrickBlock = new CoinBrickBlock(type, value, startX, startY, l, t, r, b);
        populateCoinBrickBlock(coinBrickBlock);
    }

    public void populateSwitchBlock(CoinBrickBlock switchBlock) {
        switchBlocks.add(switchBlock);
    }

    public void populateCoinBrickBlock(CoinBrickBlock coinBrickBlock) {
        coinBrickBlocks.add(coinBrickBlock);
    }

    public static ArrayList<CoinBrickBlock> getSwitchBlocks() {
        return switchBlocks;
    }

    public static ArrayList<CoinBrickBlock> getCoinBrickBlocks() {
        return coinBrickBlocks;
    }

    public static void reset() {
        switchBlocks.clear();
        coinBrickBlocks.clear();
        CoinBrickBlock.isCoin = true;
    }
}
