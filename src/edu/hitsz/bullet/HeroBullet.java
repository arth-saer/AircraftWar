package edu.hitsz.bullet;

/**
 * @Author hitsz
 */
public class HeroBullet extends BaseBullet {

    public HeroBullet(int locationX, int locationY, int speedX, int speedY, int bulletPower) {
        super(locationX, locationY, speedX, speedY, bulletPower);
    }
    public HeroBullet(int locationX, int locationY, int speedX, int speedY, int bulletPower, int speed, double angle) {
        super(locationX, locationY, speedX, speedY, bulletPower, speed, angle);
    }

}
