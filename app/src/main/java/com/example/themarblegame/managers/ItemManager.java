package com.example.themarblegame.managers;

import com.example.themarblegame.obstacles.Item;

import java.util.ArrayList;

public class ItemManager {
    private static ArrayList<Item> items = new ArrayList<>();
    private Item item;

    public ItemManager(String name, int amount, int x, int y){
        this.item = new Item(name, amount, x, y);
        populateItems(item);
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public void populateItems(Item item){
        items.add(item);
    }

    public static void reset() {
        items.clear();
    }
}
