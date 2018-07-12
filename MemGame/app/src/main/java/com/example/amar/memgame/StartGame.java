package com.example.amar.memgame;

import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;

public class StartGame implements Runnable {

    private Stage stage;
    private MainActivity mainActivity;
    private ArrayList<Integer> orderList;
    private boolean isRunning = false;
    private int index = 0;
    private Thread thread;


    public StartGame(Stage stage, MainActivity mainActivity) {
        this.stage = stage;
        this.mainActivity = mainActivity;
        createList();
        replayOrder();

    }

    public void createList() {
        orderList = new ArrayList<Integer>();
        for (int i = 0; i < stage.getList().size(); i++) {
            orderList.add(stage.getList().get(i));
        }
    }

    private void stageBeaten() {
        mainActivity.winAlert();
    }

    private void replayCurrentStage() {
        mainActivity.loseAlert();
    }

    public boolean isCorrectStar(int clickedStar) {
        if (orderList.size() > 0 && clickedStar == orderList.get(0)) {
            if (orderList.size() == 1) {
                orderList.remove(0);
                stageBeaten();
            } else {
                orderList.remove(0);
            }
            return true;
        } else {
            replayCurrentStage();
            return false;
        }
    }

    public void run() {
        try {
            mainActivity.isButtonsClickable(false);
            if (index < stage.getNbrOfChanges()) {
                for (int i = 0; i < orderList.size(); i++) {
                    Thread.sleep(stage.getChangeTime());
                    mainActivity.stageAnimation(orderList.get(i));
                }
            }
            mainActivity.isButtonsClickable(true);
        } catch (InterruptedException v) {
            System.out.println(v);
        }
    }

    public void replayOrder() {
        //TODO: Check so not multiple threads are running
        index = 0;
        isRunning = true;

        thread = new Thread(this);
        if (!thread.isInterrupted()) {
            thread.interrupt();
        }
        thread.start();
    }

}
