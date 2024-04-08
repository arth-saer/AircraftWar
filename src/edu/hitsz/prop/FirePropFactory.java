package edu.hitsz.prop;

public class FirePropFactory implements PropFactory{
    @Override
    public BaseProp createProp(int locationX, int locationY, int speedX, int speedY){
        return new FireProp(locationX,locationY,speedX,speedY);
    }
}
