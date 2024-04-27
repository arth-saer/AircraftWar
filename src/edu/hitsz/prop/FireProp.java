package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.trajectory.FanShape;

public class FireProp extends BaseProp{
    public FireProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void Effect(HeroAircraft heroAircraft){

        heroAircraft.setShootNum(3);
        heroAircraft.setPower(75);

        heroAircraft.setTrajectory(new FanShape());
        System.out.println("FireSupply active!");

    }
}
