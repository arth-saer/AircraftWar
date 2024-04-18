package edu.hitsz.bullet;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.Main;

public class BossBullet extends EnemyBullet{
    private int speed;
    private int order;
    //向外扩散的速度
    public BossBullet(int locationX, int locationY, int speed, int order, int power) {
        super(locationX, locationY, 0 ,0, power);
        this.speed = speed;
        this.order = order;
    }
    @Override
    public void forward() {

        locationX += (int)(speed * Math.cos(order*Math.PI/10));
        locationY += (int)(speed * Math.sin(order*Math.PI/10));


        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }
}
