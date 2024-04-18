package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EliteBullet;
import edu.hitsz.prop.*;
import edu.hitsz.propfactory.AddHpPropFactory;
import edu.hitsz.propfactory.BombPropFactory;
import edu.hitsz.propfactory.FirePropFactory;
import edu.hitsz.propfactory.PropFactory;

import java.util.LinkedList;
import java.util.List;


public class EliteEnemy extends EnemyAircraft {

    private int shootNum = 1;

    private int power = 30;


    private int direction = 1;



    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
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

        if(i%7==0){
            propFactory = new AddHpPropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,3));
        }
        else if(i%7==1){
            propFactory = new BombPropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,3));
        }
        else if(i%7==2) {
            propFactory = new FirePropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,3));
        }
        return res;
    }
    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = this.getSpeedX();
        int speedY = this.getSpeedY() + direction*4;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EliteBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }

}
