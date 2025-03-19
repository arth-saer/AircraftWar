package edu.hitsz.application;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.enemyfactory.ShootBossEnemyFactory;
import edu.hitsz.specialeffect.SpecialEffect;
import edu.hitsz.specialeffect.WarningSpecialEffect;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class RegularGame extends Game{
    private int changeImage;
    private boolean change;
    public RegularGame(){
        super();
        this.difficulty = 2;
        this.changeImage = 1;
        setImage();
        this.change = true;

        this.heroCycleDuration = 560;
        this.enemyCycleDuration = 600;
        this.enemyMaxNumber = 5;
        this.changeCycleDuration1 = 18000;
        this.changeCycleDuration2 = 20000;

        this.p1 = 0.4;
        this.p2 = 0.7;
        this.mobEnemySpeedX = 0;
        this.mobEnemySpeedY = 4;
        this.mobEnemyHp = 100;
        this.eliteEnemySpeedX = 1;
        this.eliteEnemySpeedY = 4;
        this.eliteEnemyHp = 150;
        this.elitePlusEnemySpeedX = 1;
        this.elitePlusEnemySpeedY = 5;
        this.elitePlusEnemyHp = 200;
        this.shootBossEnemySpeedX = 2;
        this.shootBossEnemySpeedY = 0;
        this.shootBossEnemyHp = 1000;
    }

    private void setImage(){
        if(changeImage == 1){
            String bgPath = "src/images/bg3.jpg";
            try {
                ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(bgPath));
            }catch (IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        else{
            String bgPath = "src/images/bg5.jpg";
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
            if(enemyCycleDuration > 480){
                enemyCycleDuration -= 20;
                float f = (float) 20 /(enemyCycleDuration - 20);
                System.out.println("敌机产生速度、敌机发射子弹速度加快" + f + "倍");
            }
            if(enemyMaxNumber < 12){
                enemyMaxNumber += 1;
                System.out.println("敌机数量增加！增加到" + enemyMaxNumber + "架");
            }
            if(enemyCycleDuration <= 480 && enemyMaxNumber >= 12){
                System.out.println("敌机产生速度、敌机发射子弹速度、敌机数量已经达到最大值！！！！加油");
            }
        }

    }
    @Override
    public void changeDifficultyByEnemyField(){
        if(timeCountAndNewCycleJudgeForChange2()){
            change = !change;
            if(p1 > 0.2) {
                this.p1 -= 0.05;
                this.p2 -= 0.05;
                System.out.println("注意：生成的敌机为超级精英敌机概率增加5%");
            }
            if(elitePlusEnemyHp < 300){
                this.mobEnemyHp += 10;
                this.eliteEnemyHp += 30;
                this.elitePlusEnemyHp += 30;
                System.out.println("注意：所有敌机血量增加");
                if(change){
                    this.eliteEnemySpeedX += 1;
                    this.eliteEnemySpeedY += 1;
                    this.elitePlusEnemySpeedX += 1;
                    this.elitePlusEnemySpeedY += 1;
                    System.out.println("精英战机移动速度加快");
                }
            }
            if(p1 <= 0.2 && elitePlusEnemyHp >= 300){
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
            for(EnemyAircraft enemyAircraft:this.enemyAircrafts){
                if(enemyAircraft instanceof BossEnemy && !enemyAircraft.notValid()){
                    return;
                }
            }
            enemyFactory = new ShootBossEnemyFactory();
            EnemyAircraft boss = enemyFactory.createEnemy(shootBossEnemySpeedX, shootBossEnemySpeedY, shootBossEnemyHp);
            enemyAircrafts.add(boss);
            this.changeImage = (-1)*this.changeImage;
            setImage();
            ((BossEnemy)boss).setVoice(voiceSwitch);

        }

    }

}
