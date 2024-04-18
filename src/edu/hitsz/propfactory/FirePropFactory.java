package edu.hitsz.propfactory;

import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.FireProp;
import edu.hitsz.propfactory.PropFactory;

public class FirePropFactory implements PropFactory {
    @Override
    public BaseProp createProp(int locationX, int locationY, int speedX, int speedY){
        return new FireProp(locationX,locationY,speedX,speedY);
    }
}
