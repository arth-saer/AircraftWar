package edu.hitsz.dao;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.io.*;


public class ScoreDaoImpByFile implements ScoreDao{

    private String fileName;
    private List<ScoreData> allScoreDatas;

    public ScoreDaoImpByFile(String fileName) {
        this.fileName = fileName;
        this.getAllScores();

    }

    public List<ScoreData> getScores(int difficulty) {

        List<ScoreData> scoreDatas = new LinkedList<>();

        try {

            FileInputStream fileIn = new FileInputStream(this.fileName);
            ObjectInputStream ois = new ObjectInputStream(fileIn);

            while(true){
                try{
                    ScoreData scoreData = (ScoreData) ois.readObject();
                    if(scoreData.getDifficult() == difficulty){
                        scoreDatas.add(scoreData);
                    }
                }catch (EOFException eof)
                {
                    break;
                }
            }

            ois.close();
            fileIn.close();

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return scoreDatas;
    }
    public void getAllScores() {

        this.allScoreDatas = new LinkedList<>();

        try {

            FileInputStream fileIn = new FileInputStream(this.fileName);
            ObjectInputStream ois = new ObjectInputStream(fileIn);

            while(true){
                try{
                    allScoreDatas.add((ScoreData) ois.readObject());
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
        for (ScoreData scoreData : allScoreDatas) {
            System.out.println(rank + "  " + scoreData.getName() + "  " + scoreData.getScore() + "  " + scoreData.getDate());
            rank++;
        }
    }

    @Override
    public void sortByScore(){

        allScoreDatas.sort(Comparator.comparingInt(ScoreData::getScore).reversed());
    }

    @Override
    public void doAdd(ScoreData scoreData){

        allScoreDatas.add(scoreData);

        try{

            FileOutputStream fileOut = new FileOutputStream(this.fileName, false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            for (ScoreData scoreData1 : allScoreDatas) {
                out.writeObject(scoreData1);
            }

            out.flush();
            out.close();
            fileOut.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void doDelete(ScoreData scoreData){

        for(ScoreData scoreData1 : allScoreDatas){
            if (scoreData1.getDifficult() == scoreData.getDifficult()
                    && scoreData1.getScore() == scoreData.getScore()
                    && scoreData1.getName().equals(scoreData.getName())
                    && scoreData1.getDate().equals(scoreData.getDate())) {
                allScoreDatas.remove(scoreData1);
                break;
            }
        }

        try{
            FileOutputStream fileOut = new FileOutputStream(this.fileName, false);
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);

            for (ScoreData scoreData1 : allScoreDatas) {
                oos.writeObject(scoreData1);
            }

            oos.flush();
            oos.close();
            fileOut.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
