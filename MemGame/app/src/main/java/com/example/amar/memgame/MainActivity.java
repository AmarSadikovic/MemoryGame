package com.example.amar.memgame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;

    private DataStorage dataStorage;

    private TextView tvStage;
    private ImageView ivStar0, ivStar1, ivStar2, ivStar3;
    private Stage stage;
    private StartGame startGame;
    private int currentStage;
    private String currentLevel;
    private Animation animAlpha;
    private Animation animRotation;

    private Animation animBlink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataStorage = new DataStorage(this);
        intent = getIntent();

        //currentStage =  intent.getIntExtra("currentLevel", 1);
        currentStage = dataStorage.getCurrentStage();

        tvStage = findViewById(R.id.tvStage);
        tvStage.setText("Stage: " + currentStage);
        ivStar0 = findViewById(R.id.ivStar0);
        ivStar1 = findViewById(R.id.ivStar1);
        ivStar2 = findViewById(R.id.ivStar2);
        ivStar3 = findViewById(R.id.ivStar3);

        ivStar0.setOnClickListener(this);
        ivStar1.setOnClickListener(this);
        ivStar2.setOnClickListener(this);
        ivStar3.setOnClickListener(this);

        animAlpha = new AnimationUtils().loadAnimation(this, R.anim.anim_alpha);
        animRotation = new AnimationUtils().loadAnimation(this, R.anim.anim_rotation);

        animBlink = new AnimationUtils().loadAnimation(this, R.anim.anim_blink);

        stage = new Stage(currentLevel, currentStage);
        startGame = new StartGame(stage, this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivStar0:
                view.startAnimation(animAlpha);
                startGame.isCorrectStar(0);
                break;
            case R.id.ivStar1:
                view.startAnimation(animAlpha);
                System.out.println(startGame.isCorrectStar(1));
                break;
            case R.id.ivStar2:
                view.startAnimation(animAlpha);
                System.out.println(startGame.isCorrectStar(2));
                break;
            case R.id.ivStar3:
                view.startAnimation(animAlpha);
                System.out.println(startGame.isCorrectStar(3));
                break;
            default:
                break;
        }
    }

    public void isButtonsClickable(boolean isClickable) {
        if (!isClickable) {
            ivStar0.setEnabled(false);
            ivStar1.setEnabled(false);
            ivStar2.setEnabled(false);
            ivStar3.setEnabled(false);
        } else if (isClickable) {
            ivStar0.setEnabled(true);
            ivStar1.setEnabled(true);
            ivStar2.setEnabled(true);
            ivStar3.setEnabled(true);
        }
    }

    public void winAlert() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View dView = getLayoutInflater().inflate(R.layout.dialog_win, null);
        Button btnNext = dView.findViewById(R.id.btnNextStage);
        mBuilder.setView(dView);
        final AlertDialog winDialog = mBuilder.create();
        winDialog.setCancelable(false);
        winDialog.setCanceledOnTouchOutside(false);
        winDialog.show();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStage();
                winDialog.dismiss();

            }
        });

    }

    public void loseAlert() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View dView = getLayoutInflater().inflate(R.layout.dialog_lose, null);
        Button btnPlayAgain = dView.findViewById(R.id.btnPlayAgain);
        mBuilder.setView(dView);
        final AlertDialog loseDialog = mBuilder.create();
        loseDialog.setCancelable(false);
        loseDialog.setCanceledOnTouchOutside(false);
        loseDialog.show();
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replayStage();
                loseDialog.dismiss();

            }
        });

    }

    public void nextStage() {
        int nextStage = dataStorage.getCurrentStage() + 1;
        dataStorage.putSharedPreference(nextStage);
        tvStage.setText("Stage: " + nextStage);
        stage = new Stage(currentLevel, nextStage);
        startGame = new StartGame(stage, this);
    }


    public void replayStage() {
        startGame = new StartGame(stage, this);
    }

    public void stageAnimation(int objectNbr) {
        if (objectNbr == 0) {
            ivStar0.startAnimation(animBlink);
        } else if (objectNbr == 1) {
            ivStar1.startAnimation(animBlink);
        } else if (objectNbr == 2) {
            ivStar2.startAnimation(animBlink);
        } else if (objectNbr == 3) {
            ivStar3.startAnimation(animBlink);
        }
    }
}
