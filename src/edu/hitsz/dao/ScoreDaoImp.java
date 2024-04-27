package edu.hitsz.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

import static java.lang.System.in;

public class ScoreDaoImp implements ScoreDao{
    private String fileName;
    private List<ScoreData> scoreDatas;

    public ScoreDaoImp(String fileName) {
        this.fileName = fileName;
        this.getScoresFromFile();

    }

    @Override
    public void getScoresFromFile() {

        this.scoreDatas = new LinkedList<>();

        try {

            FileInputStream fileIn = new FileInputStream(this.fileName);
            ObjectInputStream ois = new ObjectInputStream(fileIn);

            while(true){
                try{
                    scoreDatas.add((ScoreData) ois.readObject());
                }catch (EOFException eof)
                {
                    break;
                }
            }

            ois.close();
            fileIn.close();

        }catch (IOException | ClassNotFoundException e ) {
            System.out.println("");
        }
    }


    @Override
    public void printScoreDatas(){


        System.out.println("----------------得分排行榜----------------");
        int rank = 1;
        for (ScoreData scoreData : scoreDatas) {
            System.out.println(rank + "  " + scoreData.getName() + "  " + scoreData.getScore() + "  " + scoreData.getDate());
            rank++;
        }
    }

    @Override
    public void sortByScore(){

        scoreDatas.sort(Comparator.comparingInt(ScoreData::getScore).reversed());
    }

    @Override
    public void doAdd(ScoreData scoreData){

        scoreDatas.add(scoreData);

        try{

            FileOutputStream fileOut = new FileOutputStream(this.fileName, false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            for (ScoreData scoreData1 : scoreDatas) {
                out.writeObject(scoreData1);
            }

            out.flush();
            out.close();
            fileOut.close();

        }catch (IOException e) {
            System.out.println("");
        }
    }



    @Override
    public void doDelete(ScoreData scoreData){

        for(ScoreData scoreData1 : scoreDatas){
            if (scoreData1.getScore() == scoreData.getScore()
                    && scoreData1.getName().equals(scoreData.getName())
                    && scoreData1.getDate().equals(scoreData.getDate())) {
                scoreDatas.remove(scoreData1);
                break;
            }
        }

        try{
            FileOutputStream fileOut = new FileOutputStream(this.fileName, false);
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);

            for (ScoreData scoreData1 : scoreDatas) {
                oos.writeObject(scoreData1);
            }

            oos.flush();
            oos.close();
            fileOut.close();

        }catch (IOException e) {
            System.out.println("");
        }
    }

}
