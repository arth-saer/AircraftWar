package edu.hitsz.aircraft;

import edu.hitsz.trajectory.Annular;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ShootBossEnemy extends BossEnemy{
    public ShootBossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 20;
        this.power = 75;
        this.trajectory = new Annular();
    }
}
