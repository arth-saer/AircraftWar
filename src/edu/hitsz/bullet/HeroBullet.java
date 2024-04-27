package edu.hitsz.bullet;

/**
 * @Author hitsz
 */
public class HeroBullet extends BaseBullet {

    public HeroBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }
    public HeroBullet(int locationX, int locationY, int speedX, int speedY, int power, int speed, double angle) {
        super(locationX, locationY, speedX, speedY, power, speed, angle);
    }

}
