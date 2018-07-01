package com.example.amar.memgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvStage;
    private ImageView ivStar0, ivStar1, ivStar2, ivStar3;
    private Stage stage;
    private StartGame startGame;
    private int currentStage;
    private Animation animAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentStage = 16;

        tvStage = findViewById(R.id.tvStage);
        tvStage.setText("Stage: "+currentStage);
        ivStar0 = findViewById(R.id.ivStar0);
        ivStar1 = findViewById(R.id.ivStar1);
        ivStar2 = findViewById(R.id.ivStar2);
        ivStar3 = findViewById(R.id.ivStar3);

        ivStar0.setOnClickListener(this);
        ivStar1.setOnClickListener(this);
        ivStar2.setOnClickListener(this);
        ivStar3.setOnClickListener(this);

        animAlpha = new AnimationUtils().loadAnimation(this, R.anim.anim_alpha);
        stage = new Stage(currentStage);
        startGame = new StartGame(stage, this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivStar0:
                view.startAnimation(animAlpha);
                System.out.println(startGame.isCorrectStar(0));
                System.out.println(stage.getList().toString());
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
                //startGame.replayOrder();
                System.out.println(startGame.isCorrectStar(3));
                break;
            default:
                break;
        }
    }

    public void stageAnimation(int objectNbr) {
        String temp = "ivStar" + objectNbr;
        if (objectNbr == 0) {
            ivStar0.startAnimation(animAlpha);
        } else if (objectNbr == 1) {
            ivStar1.startAnimation(animAlpha);
        } else if (objectNbr == 2) {
            ivStar2.startAnimation(animAlpha);
        } else if (objectNbr == 3) {
            ivStar3.startAnimation(animAlpha);
        }

    }
}
