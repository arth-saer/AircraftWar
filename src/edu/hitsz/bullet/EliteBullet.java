package edu.hitsz.bullet;

public class EliteBullet extends EnemyBullet{
    public EliteBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);

    }
    public EliteBullet(int locationX, int locationY, int speedX, int speedY, int power, int speed, double angle) {
        super(locationX, locationY, speedX, speedY, power, speed, angle);
    }
}
