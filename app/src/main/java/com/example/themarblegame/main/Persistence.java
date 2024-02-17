package com.example.themarblegame.main;

import android.content.Context;
import android.content.SharedPreferences;

public final class Persistence{
    private final SharedPreferences sharedPreferences;

    public Persistence(Context context) {
        this.sharedPreferences = context.getSharedPreferences("houseTapper_dataSaveFile", Context.MODE_PRIVATE);
    }

    public void saveData() {
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putInt("coins", PlayerBank.getCoin());
        sharedPreferencesEditor.apply();
    }

    public void getData() {
//        PlayerAssets.setSavedCoins(sharedPreferences.getInt("coins", 0));
    }
}