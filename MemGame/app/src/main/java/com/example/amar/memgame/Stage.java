package com.example.amar.memgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Stage {

    private int currentStage;
    private int nbrStars;
    private int nbrOfChanges;
    private int changeTime;
    private String currentLevel;

    private ArrayList<Integer> changeOrder;


    public Stage(String currentLevel, int currentStage) {
        this.currentLevel = currentLevel;
        this.currentStage = currentStage;
        numberOfChanges();
        changeTime();
        setStars();
    }

    private void setStars() {
        //Fibonacci?
        changeOrder = new ArrayList<Integer>();
        //nbrStars = 4;
        if (currentStage < 5) {
            nbrStars = 3;
        } else if (currentStage >= 5) {
            nbrStars = 3;
        } else if (currentStage >= 15) {
            nbrStars = 3;
        } else if (currentStage >= 20) {
            nbrStars = 3;
        } else if (currentStage >= 25) {
            nbrStars = 3;
        }


        if (changeOrder.isEmpty()) {
            Random ran = new Random();
            int changes = nbrOfChanges;
            int stars = nbrStars;

            while (changes >= 0) {
                changeOrder.add(ran.nextInt(stars - 0 + 1));
                changes--;
            }
            shuffleOrder();
        }
    }

    private void shuffleOrder() {
        Collections.shuffle(changeOrder);
        System.out.println(changeOrder.toString());
    }

    private void numberOfChanges() {
        //TODO: Algorithm for nbr of switches
        //nbrOfChanges = 2;

        if(currentStage<5){
            nbrOfChanges = 2;
        }else if(currentStage >= 5 && currentStage < 10){
            nbrOfChanges = 3;
        }else if(currentStage >=10 && currentStage < 15){
            nbrOfChanges = 4;
        }else if(currentStage >=15 && currentStage < 20){
            nbrOfChanges = 5;
        }else if(currentStage >=20){
            nbrOfChanges = 6;
        }
    }

    private void changeTime() {
        //TODO: Algorithm for switch timee
        if(currentStage<5){
            changeTime = 1200;
        }else if(currentStage >= 5 && currentStage < 10){
            changeTime = 1000;
        }else if(currentStage >=10 && currentStage < 15){
            changeTime = 1000;
        }else if(currentStage >=15 && currentStage < 20){
            changeTime = 750;
        }else if(currentStage >=20){
            changeTime = 500;
        }
    }


    public int getCurrentStage() {
        return currentStage;
    }

    public int getNbrOfStars() {
        return nbrStars;
    }

    public int getChangeTime() {
        return changeTime;
    }

    public int getNbrOfChanges() {
        return nbrOfChanges;
    }

    public ArrayList<Integer> getList() {
        return changeOrder;
    }

}
