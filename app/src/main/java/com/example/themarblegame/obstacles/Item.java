package com.example.themarblegame.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.PlayerBank;
import com.example.themarblegame.main.Player;
import com.example.themarblegame.main.Scale;
import com.example.themarblegame.managers.ItemManager;

public class Item implements GameDesigner {
    private Rect item;
    private String name;
    private int amount;
    private boolean counter = true;

    public Item(String name, int amount, int x, int y) {
        this.name = name;
        this.amount = amount;
        this.item = new Rect(Scale.UNIT*x, Scale.GROUND-Scale.UNIT*y, Scale.UNIT*x + Scale.UNIT, Scale.GROUND-Scale.UNIT*y + Scale.UNIT);
    }

    public Rect getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public boolean isCounter() {
        return counter;
    }

    public void setCounter(boolean counter) {
        this.counter = counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    //run itemCollision(PlayerPhysics playerPhysics, Item item)
    public static void itemCollision(Player player) {

        for (int i = 0; i < ItemManager.getItems().size(); i++) {
            switch (ItemManager.getItems().get(i).name) {

                case "parachute":
                    if (Rect.intersects(player.getBounds(), ItemManager.getItems().get(i).item)){
                        PlayerBank.addBalloon(ItemManager.getItems().get(i).amount);
                        ItemManager.getItems().get(i).item.setEmpty();
                        player.setStatus("Parachute " + " +" + ItemManager.getItems().get(i).amount);
                    } break;

                case "static parachute":
                    if (Rect.intersects(player.getBounds(), ItemManager.getItems().get(i).item) && ItemManager.getItems().get(i).counter){
                        ItemManager.getItems().get(i).counter = false;
                        PlayerBank.addBalloon(ItemManager.getItems().get(i).amount);
                        player.setStatus("Parachute " + " +" + ItemManager.getItems().get(i).amount);
                    }
                    if (player.isBalloonInUse()){
                        ItemManager.getItems().get(i).counter = true;
                    } break;

                case "health up":
                    if (Rect.intersects(player.getBounds(), ItemManager.getItems().get(i).item)){
                        PlayerBank.setHealth(player.getHealth());
                        ItemManager.getItems().get(i).item.setEmpty();
                        player.setStatus("Health Up");
                    } break;

                case "rain":
                    if (Rect.intersects(player.getBounds(), ItemManager.getItems().get(i).item)){
                        PlayerBank.addRainPower(ItemManager.getItems().get(i).amount);
                        ItemManager.getItems().get(i).item.setEmpty();
                        player.setStatus("Rain " + " +" + ItemManager.getItems().get(i).amount);

                    } break;
                case "shell shock":
                    if (Rect.intersects(player.getBounds(), ItemManager.getItems().get(i).item)) {
                        PlayerBank.addArms(ItemManager.getItems().get(i).amount);
                        ItemManager.getItems().get(i).item.setEmpty();
                        player.setStatus("Shell Shock " + " +" + ItemManager.getItems().get(i).amount);
                    } break;
                case "random":
                    if (Rect.intersects(player.getBounds(), ItemManager.getItems().get(i).item)){
                        int x = (int) (Math.random() * 101);
                        if (x > 60){ //coin up
                            ItemManager.getItems().get(i).amount = 20;
                            PlayerBank.setScore(ItemManager.getItems().get(i).amount);
                            ItemManager.getItems().get(i).item.setEmpty();
                            player.setStatus("Coin " + " +" + ItemManager.getItems().get(i).amount);
                        } else if (x > 50) { //health up
                            PlayerBank.setHealth(player.getHealth());
                            ItemManager.getItems().get(i).item.setEmpty();
                            player.setStatus("Health Up");
                        } else if (x > 30){ //parachute
                            ItemManager.getItems().get(i).amount = 1;
                            PlayerBank.addBalloon(ItemManager.getItems().get(i).amount);
                            ItemManager.getItems().get(i).item.setEmpty();
                            player.setStatus("Parachute " + " +" + ItemManager.getItems().get(i).amount);
                        } else if (x > 25) { //rain
                            ItemManager.getItems().get(i).amount = 1;
                            PlayerBank.addRainPower(ItemManager.getItems().get(i).amount);
                            ItemManager.getItems().get(i).item.setEmpty();
                            player.setStatus("Rain " + " +" + ItemManager.getItems().get(i).amount);
                        } else if (x > 20) { //shell shock
                            ItemManager.getItems().get(i).amount = 1;
                            PlayerBank.addArms(ItemManager.getItems().get(i).amount);
                            ItemManager.getItems().get(i).item.setEmpty();
                            player.setStatus("Shell Shock " + " +" + ItemManager.getItems().get(i).amount);
                        } else{ //poison
                            player.setPoisoned(true);
                            ItemManager.getItems().get(i).item.setEmpty();
                            player.setStatus("Poisoned");
                        }
                    }
                    break;
            }
        }
    }

    Paint paint = new Paint();

    @Override
    public void draw(Canvas canvas) {

        switch (name) {
                case "parachute":
                case "static parachute":
                    paint.setColor(Color.RED);
                    break;
                case "random":
                    paint.setColor(Color.DKGRAY);
                    break;
            }
            canvas.drawRect(item, paint);
    }

    @Override
    public void update() {

    }
}
