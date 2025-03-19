package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.*;
import edu.hitsz.trajectory.Linear;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {


    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private volatile boolean shield = false;
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.direction = -1;
        this.shootNum = 2;
        this.power = 50;
        this.trajectory = new Linear();

    }
    private static HeroAircraft heroAircraft = new HeroAircraft(
            Main.WINDOW_WIDTH / 2,
            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
            0, 0, 25000
            );
    public static HeroAircraft getHeroAircraft(){
        return heroAircraft;
    }


    public void increaseHp(int increase) {
        hp = Math.min(hp + increase, maxHp);
    }
    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    public boolean getShield() {
        return shield;
    }
    public void setShield(boolean shield) {
        this.shield = shield;
    }

    @Override
    public void decreaseHp(int decrease){
        if(!this.shield){
            hp -= decrease;
            if(hp <= 0){
                hp=0;
                vanish();
            }
        }
    }
    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot(){

        return this.getBullets(HeroAircraft.getHeroAircraft(), HeroBullet.class);

    }

}
