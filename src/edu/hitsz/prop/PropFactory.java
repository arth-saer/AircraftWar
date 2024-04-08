package edu.hitsz.prop;

public interface PropFactory{
    public abstract BaseProp createProp(int locationX, int locationY, int speedX, int speedY);
}
