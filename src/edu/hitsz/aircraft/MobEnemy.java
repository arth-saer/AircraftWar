package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.BaseProp;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends EnemyAircraft {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    @Override
    public int getSCORE(){
        return 10;
    }
    @Override
    public List<BaseProp> dropProp(){
        List<BaseProp> res = new LinkedList<>();
        return res;
    }
    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }

}
