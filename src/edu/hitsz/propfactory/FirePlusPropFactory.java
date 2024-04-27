package edu.hitsz.propfactory;

import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.FirePlusProp;


public class FirePlusPropFactory implements PropFactory{
    public BaseProp createProp(int locationX, int locationY, int speedX, int speedY){
        return new FirePlusProp(locationX,locationY,speedX,speedY);
    }
}
