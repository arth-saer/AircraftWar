package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BossBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.enemyfactory.*;
import edu.hitsz.obstacle.Obstacle;
import edu.hitsz.prop.*;
import edu.hitsz.specialeffect.BombSpecialEffect;
import edu.hitsz.specialeffect.ShieldSpecialEffect;
import edu.hitsz.specialeffect.ShootedSpecialEffect;
import edu.hitsz.specialeffect.SpecialEffect;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {

    protected int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    protected ScheduledExecutorService executorService;
    protected ScheduledExecutorService bgmExecutorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    protected final int timeInterval = 40;

    protected HeroAircraft heroAircraft;
    protected List<EnemyAircraft> enemyAircrafts;
    protected List<BaseBullet> heroBullets;
    protected List<BaseBullet> enemyBullets;
    protected List<BaseProp> props;
    protected List<Obstacle> obstacles;
    protected List<SpecialEffect> specialEffects;



    /**
     * 当前得分
     */
    protected int score = 0;
    /**
     * 当前时刻
     */
    protected int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    protected int enemyCycleDuration = 600;
    protected int enemyCycleTime = 0;
    protected int heroCycleDuration = 520;
    protected int heroCycleTime = 0;
    protected int enemyObstacleCycleDuration = 2400;
    protected int enemyObstacleCycleTime = 0;

    protected int changeCycleTime1 = 0;
    protected int changeCycleDuration1;
    protected int changeCycleTime2 = 0;
    protected int changeCycleDuration2;

    /**
     * 屏幕中出现的敌机最大数量
     */
    protected int enemyMaxNumber = 6;

    protected double p1;
    protected double p2;

    protected int mobEnemySpeedX;
    protected int mobEnemySpeedY;
    protected int mobEnemyHp;
    protected int eliteEnemySpeedX;
    protected int eliteEnemySpeedY;
    protected int eliteEnemyHp;
    protected int elitePlusEnemySpeedX;
    protected int elitePlusEnemySpeedY;
    protected int elitePlusEnemyHp;
    protected int bossCounter = 0;
    protected int shootBossEnemySpeedX;
    protected int shootBossEnemySpeedY;
    protected int shootBossEnemyHp;
    protected int defendBossEnemySpeedX;
    protected int defendBossEnemySpeedY;
    protected int defendBossEnemyHp;

    /**
     * 游戏结束标志
     */
    protected boolean gameOverFlag = false;

    protected EnemyFactory enemyFactory;

    protected int difficulty;
    public static boolean voiceSwitch = false;
    protected MusicThread bgm = new MusicThread("src/videos/bgm.wav");




    public Game() {

        heroAircraft = HeroAircraft.getHeroAircraft();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();
        obstacles = new LinkedList<>();
        specialEffects = new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());
        this.bgmExecutorService = new ScheduledThreadPoolExecutor(1);
        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {
        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;
           //System.out.println(time);


            // 敌机的产生和射击
            if (timeCountAndNewCycleJudgeForEnemy()) {
                //System.out.println(time);
                // 产生普通敌机
                generateEnemy();
                // 产生boss机
                generateBoss();
                // 飞机射出子弹
                enemyShootAction();
            }

            // 英雄机的射击
            if(timeCountAndNewCycleJudgeForHero())
            {
                //英雄射出子弹
                heroShootAction();
            }
            if(timeCountAndNewCycleJudgeForEnemyObstacle())
            {
                // 第二种boss机产生障碍物
                enemyObstacleAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 道具移动
            propMoveAction();

            // 障碍物移动
            obstacleMoveAction();

            // 特效移动
            specialEffectMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            // 每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            checkGameOver();

            // 通过改变英雄设计周期、敌机的产生和射击周期来改变游戏难度
            changeDifficultyByCycleAndMaxNum();

            // 通过改变敌机的属性字段、（超级）精英战机和普通战机的生成概率来改变游戏难度
            changeDifficultyByEnemyField();
        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

        if(voiceSwitch) {
            bgmExecutorService.scheduleWithFixedDelay(bgm, 1, 1, TimeUnit.MILLISECONDS);
        }

    }

    //***********************
    //      Action 各部分
    //***********************

    protected abstract void changeDifficultyByCycleAndMaxNum();
    protected abstract void changeDifficultyByEnemyField();


    protected boolean timeCountAndNewCycleJudgeForEnemy() {
        enemyCycleTime += timeInterval;
        if (enemyCycleTime >= enemyCycleDuration && enemyCycleTime - timeInterval < enemyCycleTime) {
            // 跨越到新的周期
            enemyCycleTime %= enemyCycleDuration;
            return true;
        } else {
            return false;
        }
    }
    protected boolean timeCountAndNewCycleJudgeForHero() {
        heroCycleTime += timeInterval;
        if (heroCycleTime >= heroCycleDuration && heroCycleTime - timeInterval < heroCycleTime) {
            // 跨越到新的周期
            heroCycleTime %= heroCycleDuration;
            return true;
        } else {
            return false;
        }
    }
    protected boolean timeCountAndNewCycleJudgeForEnemyObstacle() {
        enemyObstacleCycleTime += timeInterval;
        if (enemyObstacleCycleTime >= enemyObstacleCycleDuration && enemyObstacleCycleTime - timeInterval < enemyObstacleCycleTime) {
            // 跨越到新的周期
            enemyObstacleCycleTime %= enemyObstacleCycleDuration;
            return true;
        } else {
            return false;
        }
    }
    protected boolean timeCountAndNewCycleJudgeForChange1() {
        changeCycleTime1 += timeInterval;
        if (changeCycleTime1 >= changeCycleDuration1 && changeCycleTime1 - timeInterval < changeCycleTime1) {
            // 跨越到新的周期
            changeCycleTime1 %= changeCycleDuration1;
            return true;
        } else {
            return false;
        }
    }
    protected boolean timeCountAndNewCycleJudgeForChange2() {
        changeCycleTime2 += timeInterval;
        if (changeCycleTime2 >= changeCycleDuration2 && changeCycleTime2 - timeInterval < changeCycleTime2) {
            // 跨越到新的周期
            changeCycleTime2 %= changeCycleDuration2;
            return true;
        } else {
            return false;
        }
    }

    protected void generateEnemy(){
        double judge = Math.random();
        if(judge < p1){
            if (enemyAircrafts.size() < enemyMaxNumber) {
                enemyFactory = new MobEnemyFactory();
                enemyAircrafts.add(enemyFactory.createEnemy(mobEnemySpeedX, mobEnemySpeedY, mobEnemyHp));
            }
        }
        else if(judge < p2) {
            if (enemyAircrafts.size() < enemyMaxNumber) {
                enemyFactory = new EliteEnemyFactory();
                enemyAircrafts.add(enemyFactory.createEnemy(eliteEnemySpeedX, eliteEnemySpeedY, eliteEnemyHp));
            }
        }
        else{
            if (enemyAircrafts.size() < enemyMaxNumber) {
                enemyFactory = new ElitePlusEnemyFactory();
                enemyAircrafts.add(enemyFactory.createEnemy(elitePlusEnemySpeedX, elitePlusEnemySpeedY, elitePlusEnemyHp));
            }
        }
    }
    protected abstract void generateBoss();
    protected void enemyShootAction(){
        // TODO 敌机射击
        for(EnemyAircraft enemyAircraft : enemyAircrafts) {
            List<BaseBullet> shooted = enemyAircraft.shoot();
            enemyBullets.addAll(shooted);
        }

    }
    protected void heroShootAction(){
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
//        if(this.voiceSwitch){
//            voiceExecutorService.submit(new MusicThread("src/videos/bullet.wav"));
//        }
    }
    protected void enemyObstacleAction(){
        //  防御boss产生障碍物
        for(EnemyAircraft enemyAircraft : enemyAircrafts) {
            if(enemyAircraft instanceof DefendBossEnemy){
                if(obstacles.size() < 6){
                    obstacles.add(((DefendBossEnemy) enemyAircraft).generateObstacle());
                }
            }
        }

    }

    protected void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    protected void aircraftsMoveAction() {
        for (EnemyAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }
    protected void propMoveAction() {
        for (BaseProp prop:props) {
            prop.forward();
        }
    }
    protected void obstacleMoveAction() {
        for (Obstacle obstacle:obstacles) {
            obstacle.forward();
        }
    }
    protected void specialEffectMoveAction() {
        for (SpecialEffect specialEffect:specialEffects) {
            specialEffect.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    protected void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                heroAircraft.decreaseHp(bullet.getBulletPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (EnemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getBulletPower());
                    bullet.vanish();
                    if(voiceSwitch){
                        new MusicThread("src/videos/bullet_hit.wav").start();                    }
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
//                            if(enemyAircraft instanceof BossEnemy){
//                                bgm.tempSleep(false);
//                                bgm.interrupt();
//                            }
                            List<BaseProp> dropped = enemyAircraft.dropProp();
                            if(!dropped.isEmpty()){
                                props.addAll(dropped);
                            }
                            score += enemyAircraft.getSCORE();
                    }
                    else {
                        specialEffects.add(new ShootedSpecialEffect(enemyAircraft.getLocationX(),
                                enemyAircraft.getLocationY(),
                                enemyAircraft.getSpeedX(),
                                enemyAircraft.getSpeedY()));
                    }

                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    if(!(enemyAircraft instanceof BossEnemy)){
                        enemyAircraft.vanish();
                    }
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
            for(Obstacle obstacle:obstacles){
                if (obstacle.notValid()) {
                    continue;
                }
                if(obstacle.crash(bullet)){
                    bullet.vanish();
                }
                if (obstacle.crash(heroAircraft) || heroAircraft.crash(obstacle)) {
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }
        // Todo: 我方获得道具，道具生效
        for(BaseProp prop:props){
            if (prop.crash(heroAircraft)) {
                prop.vanish();
                if(prop instanceof BombProp){
                    //specialEffects.add(new BombSpecialEffect(prop.getLocationX(), prop.getLocationY(), 0, 0));
                    for(EnemyAircraft enemyAircraft:enemyAircrafts){
                        if(!enemyAircraft.notValid()){
                            ((BombProp) prop).addObserver(enemyAircraft);
                            specialEffects.add(new BombSpecialEffect(enemyAircraft.getLocationX(),
                                    enemyAircraft.getLocationY(),
                                    enemyAircraft.getSpeedX(),
                                    enemyAircraft.getSpeedY()));
                        }
                    }
                    for(BaseBullet baseBullet:enemyBullets){
                        if(!baseBullet.notValid()){
                            if(baseBullet instanceof BossBullet){
                                ((BombProp) prop).addObserver((BossBullet)baseBullet);
                            }
                            else{
                                ((BombProp) prop).addObserver((EnemyBullet)baseBullet);
                            }
                        }
                    }
                }
                else if(prop instanceof ShieldProp){
                    specialEffects.add(new ShieldSpecialEffect(heroAircraft.getLocationX(),heroAircraft.getLocationY(),0,0));
                }
                score += prop.Effect();
            }
        }


    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 删除无效的道具
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    protected void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
        obstacles.removeIf(Obstacle::notValid);
        specialEffects.removeIf(SpecialEffect::notValid);
    }
    protected void checkGameOver(){
        if (heroAircraft.getHp() <= 0) {
            // 游戏结束

            for(EnemyAircraft enemyAircraft : enemyAircrafts) {
                if(enemyAircraft instanceof BossEnemy){
                    enemyAircraft.vanish();
                }
            }
//                bgm.tempSleep(false);
//                bgm.interrupt();
            bgm.stopPlaying();
            bgmExecutorService.shutdown();
            executorService.shutdown();
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                try {
//                    bgm.join();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            gameOverFlag = true;
            System.out.println("Game Over!");
            if(this.voiceSwitch){
                Thread gameOverVoice = new MusicThread("src/videos/game_over.wav");
                gameOverVoice.start();
            }

            JOptionPane.showMessageDialog(this, "游戏结束！");


            ScoreTable scoreTable = new ScoreTable(this.difficulty);
            Main.cardPanel.add(scoreTable.getMainPanel());
            Main.cardLayout.last(Main.cardPanel);
            scoreTable.addScore(score);

        }

    }

    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, obstacles);

        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, props);
        paintImageWithPositionRevised(g, specialEffects);
        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    protected void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    protected void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(0x0022FF));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
