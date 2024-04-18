package edu.hitsz.propfactory;

import edu.hitsz.prop.AddHpProp;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.propfactory.PropFactory;

public class AddHpPropFactory implements PropFactory {
    @Override
    public BaseProp createProp(int locationX, int locationY, int speedX, int speedY){
        return new AddHpProp(locationX,locationY,speedX,speedY);
    }
}
