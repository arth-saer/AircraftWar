package edu.hitsz.specialeffect;

import java.util.concurrent.TimeUnit;

public class WarningSpecialEffect extends SpecialEffect{
    public WarningSpecialEffect(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                vanish();
            }
        }, 240, TimeUnit.MILLISECONDS);
    }
}
