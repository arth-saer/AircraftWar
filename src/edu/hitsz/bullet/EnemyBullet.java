package edu.hitsz.bullet;

import edu.hitsz.prop.BombObserver;

/**
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet implements BombObserver {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int bulletPower) {
        super(locationX, locationY, speedX, speedY, bulletPower);
    }
    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int bulletPower, int speed, double angle) {
        super(locationX, locationY, speedX, speedY, bulletPower, speed, angle);
    }

    @Override
    public int update() {
        vanish();
        return 0;
    }
}
