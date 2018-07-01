package com.example.amar.memgame;

import java.util.ArrayList;
import java.util.Collections;

public class Stage {

    private int currentStage;
    private int nbrStars;
    private int nbrOfChanges;
    private int changeTime;

    private ArrayList<Integer> changeOrder;


    public Stage(int currentStage) {
        this.currentStage = currentStage;
        setStars();
        numberOfChanges();
        changeTime();
    }

    private void setStars(){
        changeOrder = new ArrayList<Integer>();
        if(currentStage<5){
            nbrStars = 2;
        }else if(currentStage>=5){
            nbrStars = 3;
        }else if(currentStage>=15){
            nbrStars = 4;
        }else if(currentStage>=20){
            nbrStars = 5;
        }else if(currentStage>=25){
            nbrStars = 6;
        }

        if(changeOrder.isEmpty()) {
            int index = nbrStars;
            while (index >= 0) {
                changeOrder.add(index);
                index--;
            }
            shuffleOrder();
        }

    }

    private void shuffleOrder(){
        Collections.shuffle(changeOrder);
        System.out.println(changeOrder.toString());
    }
    private void numberOfChanges(){
        //TODO: Algorithm for nbr of switches
        nbrOfChanges = 4;
    }
    private void changeTime(){
        //TODO: Algorithm for switch time
        changeTime = 1000;
    }


    public int getCurrentStage(){
        return currentStage;
    }
    public int getNbrOfStars(){
        return nbrStars;
    }

    public int getChangeTime(){
        return changeTime;
    }
    public int getNbrOfChanges(){
        return nbrOfChanges;
    }
    public ArrayList<Integer> getList(){
        return changeOrder;
    }

}
