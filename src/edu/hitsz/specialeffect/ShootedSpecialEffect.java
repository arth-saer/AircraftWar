package edu.hitsz.specialeffect;

import java.util.concurrent.TimeUnit;

public class ShootedSpecialEffect extends SpecialEffect{
    public ShootedSpecialEffect(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                vanish();
            }
        }, 120, TimeUnit.MILLISECONDS);
    }
}
