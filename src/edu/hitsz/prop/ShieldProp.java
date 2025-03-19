package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ShieldProp extends BaseProp{
    public ShieldProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public int Effect(){
        if(voiceSwitch){
            new MusicThread("src/videos/get_supply.wav").start();
        }
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        heroAircraft.setShield(true);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> heroAircraft.setShield(false), 5, TimeUnit.SECONDS);
        executor.shutdown();
        return 0;
    }
}
