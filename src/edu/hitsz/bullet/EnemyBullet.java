package edu.hitsz.bullet;

/**
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }
    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power, int speed, double angle) {
        super(locationX, locationY, speedX, speedY, power, speed, angle);
    }

}
