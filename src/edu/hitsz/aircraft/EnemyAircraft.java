package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.prop.BaseProp;

import java.util.List;

public abstract class EnemyAircraft extends AbstractAircraft{



    public EnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);

    }
    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }
    public abstract int getSCORE();
    public abstract List<BaseProp> dropProp();
}
