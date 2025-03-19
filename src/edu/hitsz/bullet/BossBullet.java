package edu.hitsz.bullet;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.prop.BombObserver;

public class BossBullet extends BaseBullet implements BombObserver {
    public BossBullet(int locationX, int locationY, int speedX, int speedY, int bulletPower) {
        super(locationX, locationY, speedX, speedY, bulletPower);

    }
    public BossBullet(int locationX, int locationY, int speedX, int speedY, int bulletPower, int speed, double angle) {
        super(locationX, locationY, speedX, speedY, bulletPower, speed, angle);
    }
    @Override
    public int update() {
        vanish();
        return 0;
    }
}
