package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.BossBullet;
import edu.hitsz.prop.*;
import edu.hitsz.propfactory.AddHpPropFactory;
import edu.hitsz.propfactory.BombPropFactory;
import edu.hitsz.propfactory.FirePropFactory;
import edu.hitsz.propfactory.PropFactory;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends EnemyAircraft{

    private int shootNum = 20;

    private int power = 75;


    private int direction = 1;



    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public int getSCORE() {
        return 200;
    }
    @Override
    public List<BaseProp> dropProp(){
        int i, j;
        List<BaseProp> res = new LinkedList<>();

        PropFactory propFactory;
        for(j = -1; j < 2; j++)
        {
            i = (int)(Math.random()*100);
            if(i%5==0){
                propFactory = new AddHpPropFactory();
                res.add(propFactory.createProp(this.getLocationX() + 40 * j,this.getLocationY(),0,3));
            }
            else if(i%5==1){
                propFactory = new BombPropFactory();
                res.add(propFactory.createProp(this.getLocationX() + 40 * j,this.getLocationY(),0,3));
            }
            else if(i%5==2) {
                propFactory = new FirePropFactory();
                res.add(propFactory.createProp(this.getLocationX() + 40 * j,this.getLocationY(),0,3));
            }
        }

        return res;
    }
    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY();
        BaseBullet bullet;
        int i;
        
        //实现方式一,forward函数不变
        for(i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new BossBullet(
                    (int)(x + 50 * Math.cos(i * 2 * Math.PI / shootNum)),
                    (int)(y + 50 * Math.sin(i * 2 * Math.PI / shootNum)),
                    (int)(5 * Math.cos(i * 2 * Math.PI / shootNum)),
                    (int)(5 * Math.sin(i * 2 * Math.PI / shootNum)),
                    power);
            res.add(bullet);
        }
        /*
        for(i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new BossBullet(
                    (int)(x + 50 * Math.cos(i * 2 * Math.PI / shootNum)),
                    (int)(y + 50 * Math.sin(i * 2 * Math.PI / shootNum)),
                    6,
                    i,
                    power,
                    2,
                    shootNum);
            res.add(bullet);
        }
        */
        return res;
    }

}
