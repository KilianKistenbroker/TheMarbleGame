package com.example.themarblegame.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.themarblegame.R;

import java.util.ArrayList;

public class SkinBank {
    private final ArrayList<Bitmap> skin_a = new ArrayList<>();

    private final ArrayList<Bitmap> skin_b = new ArrayList<>();
    private final ArrayList<Bitmap> empty_b = new ArrayList<>();

    //save this in persistence class
    private static boolean SkinBOwned = false;


    //BOOLEAN SETTERS
    public static void setSkinBOwned(boolean skinBOwned) {SkinBank.SkinBOwned = skinBOwned;}

    //BOOLEAN GETTERS
    public static boolean isSkinBOwned() {return SkinBOwned;}

    public SkinBank(){
        skin_a.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_a1), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        skin_a.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_a2), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        skin_a.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_a3), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        skin_a.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_a4), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        skin_a.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_a1), Constants.SCREEN_WIDTH / 7, Constants.SCREEN_WIDTH / 7, true)));

        skin_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_b1), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        skin_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_b2), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        skin_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_b3), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        skin_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_b4), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        skin_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skin_b1), Constants.SCREEN_WIDTH / 7, Constants.SCREEN_WIDTH / 7, true)));

        empty_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.marble1), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        empty_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.marble1), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        empty_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.marble1), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        empty_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.marble1), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
        empty_b.add((Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.marble1), Constants.SCREEN_WIDTH / 14, Constants.SCREEN_WIDTH / 14, true)));
    }

    public ArrayList<Bitmap> getSkin_a() {return skin_a;}
    public ArrayList<Bitmap> getSkin_b() {return skin_b;}
    public ArrayList<Bitmap> getEmpty_b() {return empty_b;}
}
