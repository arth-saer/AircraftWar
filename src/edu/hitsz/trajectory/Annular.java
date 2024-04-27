package edu.hitsz.trajectory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.BossBullet;
import edu.hitsz.bullet.HeroBullet3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class Annular implements Trajectory{
    @Override
    public List<BaseBullet> createBullets(AbstractAircraft abstractAircraft,Class<? extends BaseBullet> bulletClass){

        List<BaseBullet> res = new LinkedList<>();
        int x = abstractAircraft.getLocationX();
        int y = abstractAircraft.getLocationY();
        int shootNum = abstractAircraft.getShootNum();

        int iniRadius = abstractAircraft instanceof EnemyAircraft? 40 : 8;
        int speed = abstractAircraft instanceof EnemyAircraft? 11 : 10;


        try{
            int i;
            BaseBullet bullet;
            Constructor<? extends BaseBullet>[] constructors = (Constructor<? extends BaseBullet>[]) bulletClass.getConstructors();
            Constructor<? extends BaseBullet> constructor = constructors[1];

            for(i=0; i<shootNum; i++){
                Object[] parameters = {(int)(x + iniRadius * Math.cos(i * 2 * Math.PI / shootNum)),
                        (int)(y + iniRadius * Math.sin(i * 2 * Math.PI / shootNum)),
                        0,
                        0,
                        abstractAircraft.getPower(),
                        speed,
                        i * 2 * Math.PI / shootNum};
                bullet = constructor.newInstance(parameters);
                res.add(bullet);
            }
        } catch (ReflectiveOperationException e) {
            System.err.println("无法实例化类");
            e.printStackTrace();
        }

        return res;
    }
}
