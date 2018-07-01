package com.example.amar.memgame;

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
        orderList = stage.getList();

        replayOrder();

    }

    public boolean isCorrectStar(int clickedStar) {
        if (orderList.size() > 0 && clickedStar == orderList.get(0)) {
            if (orderList.size() == 1) {
                Toast.makeText(mainActivity, "You won!", Toast.LENGTH_LONG).show();
                orderList.remove(0);
            } else {
                orderList.remove(0);
            }
            return true;
        } else {
            return false;
        }
    }

    public void run() {
        try {
            if (index < stage.getNbrOfChanges()) {
                for (int i = 0; i < orderList.size(); i++) {
                    Thread.sleep(stage.getChangeTime());
                    System.out.println(orderList.get(i));
                    mainActivity.stageAnimation(orderList.get(i));
                }
            }
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
