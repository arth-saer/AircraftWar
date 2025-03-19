package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.obstacle.Obstacle;
import edu.hitsz.trajectory.Annular;
import edu.hitsz.trajectory.NoShoot;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class DefendBossEnemy extends BossEnemy{
    public DefendBossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 0;
        this.power = 200;
        this.trajectory = new NoShoot();
    }
    public Obstacle generateObstacle(){
        return new Obstacle(25 + (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.OBSTACLE.getWidth())),
                    (int) (100 + Math.random() * (Main.WINDOW_WIDTH - ImageManager.OBSTACLE.getHeight()) * 0.75),
                    0,2);
    }
}
