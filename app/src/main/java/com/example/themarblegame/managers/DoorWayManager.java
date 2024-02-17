package com.example.themarblegame.managers;

import com.example.themarblegame.obstacles.DoorWay;

import java.util.ArrayList;

public class DoorWayManager {
    private static ArrayList<DoorWay> doorWays = new ArrayList<>();
    private DoorWay doorWay;

    public static ArrayList<DoorWay> getDoorWays() {
        return doorWays;
    }

    public DoorWayManager (int x1, int y1, int a1, int x2, int y2, int a2) {
        this.doorWay = new DoorWay(x1, y1, a1, x2, y2, a2);
        populateDoorWays(doorWay);
    }

    public void populateDoorWays(DoorWay doorWay){
        doorWays.add(doorWay);
    }

    public static void reset() {
        doorWays.clear();
    }
}
