package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class AddHpProp extends BaseProp{
    private int increase = 300;
    public AddHpProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void Effect(HeroAircraft heroAircraft){
        heroAircraft.increaseHp(increase);
    }
}
