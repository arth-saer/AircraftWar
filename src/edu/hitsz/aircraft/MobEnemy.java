package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.trajectory.NoShoot;

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
        this.shootNum = 0;
        this.power = 0;
        this.trajectory = new NoShoot();
    }

    @Override
    public int getSCORE(){
        return 10;
    }
    @Override
    public List<BaseProp> dropProp(){
        return new LinkedList<>();
    }
    @Override
    public List<BaseBullet> shoot() {
        return this.getBullets(this, EnemyBullet.class);
    }

}
