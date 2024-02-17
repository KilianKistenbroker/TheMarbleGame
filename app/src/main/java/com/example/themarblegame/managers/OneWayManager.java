//package com.example.themarblegame.managers;
//
//import com.example.themarblegame.obstacles.OneWay;
//
//import java.util.ArrayList;
//
//public class OneWayManager {
//    private static ArrayList<OneWay> oneWays = new ArrayList<>();
//    private OneWay oneWay;
//
//
//    public OneWayManager(String direction, int startX, int startY, int endX, int height){
//        oneWay = new OneWay(direction, startX, startY, endX, height);
//        populateOneWayArray(oneWay);
//    }
//
//    public static ArrayList<OneWay> getOneWays() {
//        return oneWays;
//    }
//
//    public static void populateOneWayArray(OneWay oneWay) {
//        oneWays.add(oneWay);
//    }
//
//    public static void reset() {
//        oneWays.clear();
//    }
//}
