package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;
import edu.hitsz.trajectory.FanShape;
import edu.hitsz.trajectory.Linear;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FireProp extends BaseProp{
    //public static boolean isRunning = false;
    public FireProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public int Effect(){
        if(voiceSwitch){
            new MusicThread("src/videos/get_supply.wav").start();
        }
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        System.out.println("FireSupply active!");
        //int shootNum = heroAircraft.getShootNum();
        heroAircraft.setShootNum(3);
        heroAircraft.setPower(75);
        heroAircraft.setTrajectory(new FanShape());

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.schedule(() -> {
            heroAircraft.setShootNum(2);
            heroAircraft.setPower(50);
            heroAircraft.setTrajectory(new Linear());
        }, 5, TimeUnit.SECONDS);
        executor.shutdown();
        return 0;

    }
}
