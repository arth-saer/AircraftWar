package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.*;

import java.util.LinkedList;
import java.util.List;


public class EliteEnemy extends EnemyAircraft {

    private int shootNum = 1;

    private int power = 30;


    private int direction = 1;
    private PropFactory propFactory;

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

    public List<BaseProp> dropProp(){
        int i = (int)(Math.random()*100);
        List<BaseProp> res = new LinkedList<>();
        if(i%5==0){
            propFactory = new AddHpPropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,4));
        }
        else if(i%5==1){
            propFactory = new BombPropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,4));
        }
        else if(i%5==2) {
            propFactory = new FirePropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,4));
        }
        return res;
    }
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*4;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }

}
