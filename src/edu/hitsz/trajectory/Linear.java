package edu.hitsz.trajectory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class Linear implements Trajectory{
    @Override
    public List<BaseBullet> createBullets(AbstractAircraft abstractAircraft, Class<? extends BaseBullet> bulletClass){
        List<BaseBullet> res = new LinkedList<>();
        int x = abstractAircraft.getLocationX();
        int y = abstractAircraft.getLocationY() + abstractAircraft.getDirection() * 2;
        int speedX = abstractAircraft.getSpeedX();
        int speedY = abstractAircraft instanceof EnemyAircraft ? abstractAircraft.getSpeedY() + abstractAircraft.getDirection() * 6 : -10;
        int shootNum = abstractAircraft.getShootNum();

        try {
            BaseBullet bullet;
            Constructor<? extends BaseBullet>[] constructors = (Constructor<? extends BaseBullet>[]) bulletClass.getConstructors();
            Constructor<? extends BaseBullet> constructor = constructors[0];
            for(int i=0; i<shootNum; i++){
                Object[] parameters = {x + (i*2 - shootNum + 1)*10, y, speedX, speedY, abstractAircraft.getPower()};
                bullet =  constructor.newInstance(parameters);
                res.add(bullet);
            }
        } catch (ReflectiveOperationException e) {
            System.err.println("无法实例化类");
            e.printStackTrace();
        }
        return res;
    }
}
