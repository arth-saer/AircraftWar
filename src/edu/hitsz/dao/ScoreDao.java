package edu.hitsz.dao;

import java.util.List;

public interface ScoreDao {
    void getScoresFromFile();


    void  printScoreDatas();

    void sortByScore();

    void doAdd(ScoreData scoreData);

    void doDelete(ScoreData scoreData);

}
