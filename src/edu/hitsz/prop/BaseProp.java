package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.AbstractFlyingObject;
public abstract class BaseProp extends AbstractFlyingObject{

    public BaseProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    public abstract void Effect(HeroAircraft heroAircraft);

}
