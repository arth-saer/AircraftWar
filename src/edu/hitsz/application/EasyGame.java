package edu.hitsz.application;


import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;


public class EasyGame extends Game{

    public EasyGame(){
        super();
        this.difficulty = 1;
        String bgPath = "src/images/bg.jpg";
        try {
            ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(bgPath));
        }catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        this.heroCycleDuration = 520;
        this.enemyCycleDuration = 600;
        this.enemyMaxNumber = 4;

        this.p1 = 0.45;
        this.p2 = 0.75;
        this.mobEnemySpeedX = 0;
        this.mobEnemySpeedY = 3;
        this.mobEnemyHp = 50;
        this.eliteEnemySpeedX = 0;
        this.eliteEnemySpeedY = 4;
        this.eliteEnemyHp = 100;
        this.elitePlusEnemySpeedX = 1;
        this.elitePlusEnemySpeedY = 4;
        this.elitePlusEnemyHp = 150;
    }
    @Override
    public void changeDifficultyByCycleAndMaxNum(){}
    @Override
    public void changeDifficultyByEnemyField(){}
    @Override
    public void generateBoss(){
    }
}
