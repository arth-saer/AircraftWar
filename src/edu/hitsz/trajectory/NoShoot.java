package edu.hitsz.trajectory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public class NoShoot implements Trajectory{
    @Override
    public List<BaseBullet> createBullets(AbstractAircraft abstractAircraft, Class<? extends BaseBullet> bulletClass){
        return new LinkedList<>();
    }
}
