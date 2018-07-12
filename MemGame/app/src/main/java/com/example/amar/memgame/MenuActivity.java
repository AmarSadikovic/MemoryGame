package com.example.amar.memgame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private DataStorage dataStorage;
    private Button btnStart, btnMedLevel, btnReset;
    private SharedPreferences sp;
    private TextView tvStage;
    private int currentStage;
    private String currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dataStorage = new DataStorage(this);

        tvStage = findViewById(R.id.tvStage);
        initialize();
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentStage = dataStorage.getCurrentStage();
                currentLevel = "mediumLevel";
                dataStorage.putSharedPreference(currentStage);
                Intent myIntent = new Intent(MenuActivity.this, MainActivity.class);
                myIntent.putExtra("currentLevelKey", currentLevel); //Optional parameters
                MenuActivity.this.startActivity(myIntent);
            }
        });
        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStorage.putSharedPreference(1);
                currentStage = dataStorage.getCurrentStage();
                tvStage.setText("Current Stage: " + currentStage);
            }
        });
        btnMedLevel = findViewById(R.id.btnMedLevel);
        btnMedLevel.setEnabled(false); //Stage not done
        btnMedLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentStage = dataStorage.getCurrentStage();
                dataStorage.putSharedPreference(currentStage);
                Intent myIntent = new Intent(MenuActivity.this, MediumLevel.class);
                myIntent.putExtra("currentStageKey", currentStage); //Optional parameters
                MenuActivity.this.startActivity(myIntent);
            }
        });

    }

    private void initialize() {
        currentStage = dataStorage.getCurrentStage();
        if (currentStage == -1) {
            dataStorage.putSharedPreference(1);
            currentStage = dataStorage.getCurrentStage();
        }
        tvStage.setText("Current Stage: " + currentStage);
    }

}
