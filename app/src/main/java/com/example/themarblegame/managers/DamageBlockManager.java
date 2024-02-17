package com.example.themarblegame.managers;

import com.example.themarblegame.obstacles.DamageBlock;

import java.util.ArrayList;

public class DamageBlockManager {
    private static ArrayList<DamageBlock> damageBlocks = new ArrayList<>();
    private DamageBlock damageBlock;

    public DamageBlockManager (String name, int startX, int startY, int endX, int height) {
        this.damageBlock = new DamageBlock(name, startX, startY, endX, height);
        populateDamageBlocks(damageBlock);
    }

    public static ArrayList<DamageBlock> getDamageBlocks() {
        return damageBlocks;
    }

    public void populateDamageBlocks(DamageBlock damageBlock) {
        damageBlocks.add(damageBlock);
    }

    public static void reset() {
        damageBlocks.clear();
    }
}