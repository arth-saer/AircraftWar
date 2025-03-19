package edu.hitsz.application;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.enemyfactory.DefendBossEnemyFactory;
import edu.hitsz.enemyfactory.ShootBossEnemyFactory;
import edu.hitsz.specialeffect.SpecialEffect;
import edu.hitsz.specialeffect.WarningSpecialEffect;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class HardGame extends Game{

    private int changeImage;
    public HardGame(){
        super();
        this.difficulty = 3;
        this.changeImage = 1;
        setImage();

        this.heroCycleDuration = 600;
        this.enemyCycleDuration = 520;
        this.enemyMaxNumber = 7;
        this.changeCycleDuration1 = 16000;
        this.changeCycleDuration2 = 18000;

        this.p1 = 0.3;
        this.p2 = 0.65;
        this.mobEnemySpeedX = 0;
        this.mobEnemySpeedY = 5;
        this.mobEnemyHp = 150;
        this.eliteEnemySpeedX = 1;
        this.eliteEnemySpeedY = 5;
        this.eliteEnemyHp = 200;
        this.elitePlusEnemySpeedX = 2;
        this.elitePlusEnemySpeedY = 5;
        this.elitePlusEnemyHp = 250;
        this.shootBossEnemySpeedX = 3;
        this.shootBossEnemySpeedY = 0;
        this.shootBossEnemyHp = 1500;
        this.defendBossEnemySpeedX = 3;
        this.defendBossEnemySpeedY = 0;
        this.defendBossEnemyHp = 1000;
    }
    private void setImage(){
        if(changeImage == 1){
            String bgPath = "src/images/bg4.jpg";
            try {
                ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(bgPath));
            }catch (IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        else{
            String bgPath = "src/images/bg2.jpg";
            try {
                ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(bgPath));
            }catch (IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
    }
    @Override
    public void changeDifficultyByCycleAndMaxNum(){
        if(timeCountAndNewCycleJudgeForChange1()){
            if(enemyCycleDuration > 400){
                enemyCycleDuration -= 60;
                float f = (float) 60 /(enemyCycleDuration - 60);
                System.out.println("敌机产生速度、敌机发射子弹速度加快" + f + "倍");
            }
            if(enemyMaxNumber < 15){
                enemyMaxNumber += 1;
                System.out.println("敌机数量增加！增加到" + enemyMaxNumber + "架");
            }
            if(enemyCycleDuration <= 400 && enemyMaxNumber >= 15){
                System.out.println("敌机产生速度、敌机发射子弹速度、敌机数量已经达到最大值！！！！加油");
            }
        }

    }
    public void changeDifficultyByEnemyField(){
        if(timeCountAndNewCycleJudgeForChange2()){
            if(p1 > 0.15) {
                this.p1 -= 0.05;
                this.p2 -= 0.05;
                System.out.println("生成的敌机为精英敌机概率增加5%");
            }
            if(elitePlusEnemyHp < 350){
                this.mobEnemyHp += 20;
                this.eliteEnemySpeedX += 1;
                this.eliteEnemySpeedY += 1;
                this.eliteEnemyHp += 30;
                this.elitePlusEnemySpeedX += 1;
                this.elitePlusEnemySpeedY += 1;
                this.elitePlusEnemyHp += 40;
                System.out.println("注意：所有敌机血量增加");
                System.out.println("精英战机移动速度加快");
            }
            if(p1 <= 0.15 && elitePlusEnemyHp >= 350){
                System.out.println("敌方飞机的血量和速度已达最强状态，打败他们！");
            }
        }

    }
    @Override
    public void generateBoss(){
        int nextBoss = 1000*(bossCounter + 1);
        if(score < nextBoss && (nextBoss - score) < 100){
            boolean generateWarning = true;
            for(SpecialEffect specialEffect:specialEffects){
                if(specialEffect instanceof WarningSpecialEffect && !specialEffect.notValid()){
                    generateWarning = false;
                    break;
                }
            }
            if(generateWarning){
                specialEffects.add(new WarningSpecialEffect(300, 50, 0, 0));
            }
        }
        if(score >= nextBoss){
            bossCounter++;
            this.shootBossEnemyHp += 300;
            this.defendBossEnemyHp += 200;
            for(EnemyAircraft enemyAircraft:this.enemyAircrafts){
                if(enemyAircraft instanceof BossEnemy && !enemyAircraft.notValid()){
                    return;
                }
            }
            EnemyAircraft boss;
            if(Math.random() < 0.45){
                enemyFactory = new ShootBossEnemyFactory();
                 boss = enemyFactory.createEnemy(shootBossEnemySpeedX, shootBossEnemySpeedY, shootBossEnemyHp);
            }
            else{
                enemyFactory = new DefendBossEnemyFactory();
                boss = enemyFactory.createEnemy(defendBossEnemySpeedX, defendBossEnemySpeedY, defendBossEnemyHp);
            }

            enemyAircrafts.add(boss);
            this.changeImage = (-1)*this.changeImage;
            setImage();
            ((BossEnemy)boss).setVoice(this.voiceSwitch);

        }
    }

}
