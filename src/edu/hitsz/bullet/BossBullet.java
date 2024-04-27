package edu.hitsz.bullet;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.Main;

public class BossBullet extends EnemyBullet{
    public BossBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);

    }
    public BossBullet(int locationX, int locationY, int speedX, int speedY, int power, int speed, double angle) {
        super(locationX, locationY, speedX, speedY, power, speed, angle);
    }
}
