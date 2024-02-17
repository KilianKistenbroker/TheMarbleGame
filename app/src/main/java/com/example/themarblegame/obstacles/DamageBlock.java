package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.PlayerBank;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.DamageBlockManager;

public class DamageBlock implements GameDesigner {

    private Rect damageBlock;
    private boolean counter;
    private String name;

    public DamageBlock(String name, int startX, int startY, int endX, int height) {
        this.name = name;
        this.damageBlock = new Rect(Scale.UNIT*startX, Scale.GROUND-Scale.UNIT*startY, endX, Scale.GROUND-Scale.UNIT*startY + Scale.UNIT*height);
    }

    public Rect getDamageBlock() {
        return damageBlock;
    }

    public boolean isCounter() {
        return counter;
    }

    public String getName() {
        return name;
    }

    public static void prickleCollision(Player player) {
        for (int i = 0; i < DamageBlockManager.getDamageBlocks().size(); i++) {
            switch (DamageBlockManager.getDamageBlocks().get(i).name){
                case "prickle":
                    if (Rect.intersects(player.getBounds(), DamageBlockManager.getDamageBlocks().get(i).damageBlock)&& DamageBlockManager.getDamageBlocks().get(i).counter) {
                        PlayerBank.setHealth(-20);
                        player.setStatus("Pricked" + " -20");
                        DamageBlockManager.getDamageBlocks().get(i).counter = false;
                    }
                    if (!Rect.intersects(player.getBounds(), DamageBlockManager.getDamageBlocks().get(i).damageBlock))
                        DamageBlockManager.getDamageBlocks().get(i).counter = true;
                    break;
                case "poison":
                    if (Rect.intersects(player.getBounds(), DamageBlockManager.getDamageBlocks().get(i).damageBlock)&& !player.isPoisoned()) {
                        player.setPoisoned(true);
                        player.setStatus("Poisoned");
                    }
                    break;
                case "poison ivy":
                    if (Rect.intersects(player.getBounds(), DamageBlockManager.getDamageBlocks().get(i).damageBlock)&& DamageBlockManager.getDamageBlocks().get(i).counter) {
                        PlayerBank.setHealth(-10 );
                        player.setPoisoned(true);
                        player.setStatus("Poison Ivy" + " -10");
                        DamageBlockManager.getDamageBlocks().get(i).counter = false;
                    }
                    if (!Rect.intersects(player.getBounds(), DamageBlockManager.getDamageBlocks().get(i).damageBlock))
                        DamageBlockManager.getDamageBlocks().get(i).counter = true;
                    break;
            }
        }
    }

    Paint paint = new Paint();

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(damageBlock, paint);
    }

    @Override
    public void update() {

    }
}