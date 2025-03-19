package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.trajectory.Trajectory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {

    protected int maxHp;
    protected int hp;
    protected int direction;
    protected Trajectory trajectory;
    protected int shootNum;
    protected int power;


    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }




    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public List<BaseBullet> getBullets(AbstractAircraft abstractAircraft, Class<? extends BaseBullet> bulletClass){
        return trajectory.createBullets(abstractAircraft, bulletClass);
    }

    public void setTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
    }

    public int getHp() {
        return hp;
    }
    public int getDirection(){
        return direction;
    }

    public int getShootNum(){
        return shootNum;
    }
    public void setShootNum(int shootNum){
        this.shootNum = shootNum;
    }
    public int getPower(){
        return power;
    }
    public void setPower(int power){
        this.power = power;
    }
    public abstract List<BaseBullet> shoot();

}


