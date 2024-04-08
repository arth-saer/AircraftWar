package edu.hitsz.prop;

public class AddHpPropFactory implements PropFactory{
    @Override
    public BaseProp createProp(int locationX, int locationY, int speedX, int speedY){
        return new AddHpProp(locationX,locationY,speedX,speedY);
    }
}
