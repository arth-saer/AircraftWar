package edu.hitsz.specialeffect;

import edu.hitsz.aircraft.HeroAircraft;

import java.util.concurrent.TimeUnit;

public class ShieldSpecialEffect extends SpecialEffect{
    public ShieldSpecialEffect(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                vanish();
            }
        }, 5000, TimeUnit.MILLISECONDS);
    }
    @Override
    public void forward() {
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        locationX = heroAircraft.getLocationX();
        locationY = heroAircraft.getLocationY();
    }
}
