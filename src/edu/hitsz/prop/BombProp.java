package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.trajectory.Linear;

public class BombProp extends BaseProp{
    public BombProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void Effect(HeroAircraft heroAircraft){

        heroAircraft.setShootNum(3);
        heroAircraft.setPower(50);

        heroAircraft.setTrajectory(new Linear());
        System.out.println("BombSupply active!");
    }
}
