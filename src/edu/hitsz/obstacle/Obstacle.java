package edu.hitsz.obstacle;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.trajectory.Linear;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Obstacle extends AbstractFlyingObject{
    private final ScheduledExecutorService executor;

    public Obstacle(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                vanish();
            }
        }, 12, TimeUnit.SECONDS);
    }
    @Override
    public void forward(){
        locationX += speedX;
        locationY += speedY;
        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }
    @Override
    public void vanish(){
        isValid = false;
        this.executor.shutdown();
    }
}
