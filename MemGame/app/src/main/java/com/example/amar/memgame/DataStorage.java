package com.example.amar.memgame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class DataStorage {

    private SharedPreferences sp;
    private Context context;
    private int currentStage;

    public DataStorage (Context context){
        this.context = context;
    }

    public int getCurrentStage() {
        sp = context.getSharedPreferences("currentStage_pref", Activity.MODE_PRIVATE);
        currentStage = sp.getInt("currentStage_pref", -1);
        return currentStage;
    }

    public void putSharedPreference(int currentStage) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("currentStage_pref", currentStage);
        editor.commit();
    }
}
