package edu.hitsz.prop;

import edu.hitsz.application.MusicThread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BombProp extends BaseProp{
    private List<BombObserver> observers;
    public BombProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
        observers = new ArrayList<>();
    }
    public void addObserver(BombObserver observer){
        observers.add(observer);
    }
    public int notifyObservers(){
        int score = 0;
        for(BombObserver observer:observers){
            score += observer.update();
        }
        return score;
    }
    public int Effect(){
        if(voiceSwitch){
            new MusicThread("src/videos/get_supply.wav").start();
        }
        if(voiceSwitch){
            new MusicThread("src/videos/bomb_explosion.wav").start();
        }
        System.out.println("BombSupply active!");

        return notifyObservers();

    }
}
