package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class FireProp extends BaseProp{
    public FireProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void Effect(HeroAircraft heroAircraft){

        System.out.println("FireSupply active!");
        //heroAircraft.setShootNum(heroAircraft.getShootNum()+2);
    }
}
