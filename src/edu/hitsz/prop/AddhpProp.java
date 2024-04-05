package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class AddhpProp extends BaseProp{
    private int increase = 300;
    public AddhpProp (int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void Effect(HeroAircraft heroAircraft){
        heroAircraft.increaseHp(increase);
    }
}
