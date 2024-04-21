package edu.hitsz.bullet;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.Main;

public class BossBullet extends EnemyBullet{
    public BossBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);

    }
    /*
    private int speed;
    private int order;
    private int bossSpeedX;

    private int shootNum;

    public BossBullet(int locationX, int locationY, int speed, int order, int power, int bossSpeedX, int shootNum) {
        super(locationX, locationY, 0, 0, power);
        this.speed = speed;
        this.order = order;
        this.bossSpeedX = bossSpeedX;
        this.shootNum = shootNum;
    }
    @Override
    public void forward() {

        locationX = locationX + bossSpeedX + (int)(speed * Math.cos(order * 2 * Math.PI / shootNum));
        locationY = locationY  + (int)(speed * Math.sin(order * 2 * Math.PI / shootNum));

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
    */
}
