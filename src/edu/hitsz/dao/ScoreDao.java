package edu.hitsz.dao;

import java.util.List;

public interface ScoreDao {
    List<ScoreData> getScores(int difficulty);
    void getAllScores();


    void  printScoreDatas();

    void sortByScore();

    void doAdd(ScoreData scoreData);

    void doDelete(ScoreData scoreData);

}
