package edu.hitsz.propfactory;

import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.ShieldProp;

public class ShieldPropFactory implements PropFactory{
    public BaseProp createProp(int locationX, int locationY, int speedX, int speedY){
        return new ShieldProp(locationX,locationY,speedX,speedY);
    }
}
