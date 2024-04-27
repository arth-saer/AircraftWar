package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.trajectory.Annular;

public class FirePlusProp extends BaseProp{
    public FirePlusProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void Effect(HeroAircraft heroAircraft){

        heroAircraft.setShootNum(20);
        heroAircraft.setPower(100);

        heroAircraft.setTrajectory(new Annular());
        System.out.println("FirePlusSupply active!");

    }
}
