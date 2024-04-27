package edu.hitsz.trajectory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import java.util.List;

public interface Trajectory {
    List<BaseBullet> createBullets(AbstractAircraft abstractAircraft, Class<? extends BaseBullet> bulletClass);
}
