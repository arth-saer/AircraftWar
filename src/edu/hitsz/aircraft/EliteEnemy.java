package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EliteBullet;
import edu.hitsz.prop.*;
import edu.hitsz.propfactory.*;
import edu.hitsz.trajectory.Linear;

import java.util.LinkedList;
import java.util.List;


public class EliteEnemy extends EnemyAircraft {






    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 1;
        this.power = 30;
        this.trajectory = new Linear();
    }
    @Override
    public int getSCORE() {
        return 20;
    }
    @Override
    public List<BaseProp> dropProp(){
        int i = (int)(Math.random()*100);
        List<BaseProp> res = new LinkedList<>();

        PropFactory propFactory;

        if(i%9==0){
            propFactory = new AddHpPropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,3));
        }
        else if(i%9==1){
            propFactory = new BombPropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,3));
        }
        else if(i%9==2) {
            propFactory = new FirePropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,3));
        }
        else if(i%9==3) {
            propFactory = new FirePlusPropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,3));
        }
        return res;
    }
    @Override
    public List<BaseBullet> shoot() {
        return this.getBullets(this, EliteBullet.class);
    }

}
