package edu.hitsz.propfactory;

import edu.hitsz.prop.BaseProp;

public interface PropFactory{
    BaseProp createProp(int locationX, int locationY, int speedX, int speedY);
}
