package edu.hitsz.dao;

import java.io.Serializable;

public class ScoreData implements Serializable {

    private int difficulty;

    private int score;
    private String name;
    private String date;

    public ScoreData(int difficult, int score, String name, String date){
        this.difficulty = difficult;
        this.score = score;
        this.name = name;
        this.date = date;
    }

    public void setDifficult(int difficult) {
        this.difficulty = difficult;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDifficult() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
