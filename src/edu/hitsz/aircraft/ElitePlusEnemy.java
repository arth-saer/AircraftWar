package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.ElitePlusBullet;
import edu.hitsz.prop.*;
import edu.hitsz.propfactory.AddHpPropFactory;
import edu.hitsz.propfactory.BombPropFactory;
import edu.hitsz.propfactory.FirePropFactory;
import edu.hitsz.propfactory.PropFactory;

import java.util.LinkedList;
import java.util.List;

public class ElitePlusEnemy extends EnemyAircraft{
    private int shootNum = 3;

    private int power = 50;


    private int direction = 1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    public ElitePlusEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }
    @Override
    public int getSCORE(){
        return 30;
    }
    @Override
    public List<BaseProp> dropProp(){
        int i = (int)(Math.random()*100);
        List<BaseProp> res = new LinkedList<>();

        PropFactory propFactory;

        if(i%6==0){
            propFactory = new AddHpPropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,3));
        }
        else if(i%6==1){
            propFactory = new BombPropFactory();
            res.add(propFactory.createProp(this.getLocationX(),this.getLocationY(),0,3));
        }
        else if(i%6==2) {
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
        int speedY = this.getSpeedY() + direction*2;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            bullet = new ElitePlusBullet(x + 10 * (i - 1), y, 2 * i - 1, speedY, power);
            res.add(bullet);
        }
        return res;
    }
}
