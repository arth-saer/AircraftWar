package edu.hitsz.bullet;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 子弹类。
 * 也可以考虑不同类型的子弹
 *
 * @author hitsz
 */
public class BaseBullet extends AbstractFlyingObject {

    protected int bulletPower;
    protected int speed;
    protected double angle;

    public BaseBullet(int locationX, int locationY, int speedX, int speedY, int bulletPower) {
        super(locationX, locationY, speedX, speedY);
        this.bulletPower = bulletPower;
    }

    public BaseBullet(int locationX, int locationY, int speedX, int speedY, int bulletPower, int speed, double angle) {
        super(locationX, locationY, speedX, speedY);
        this.bulletPower = bulletPower;
        this.speed = speed;
        this.angle = angle;
    }

    @Override
    public void forward() {
        if((speedX != 0) || (speedY != 0))
        {
            locationX += speedX;
            locationY += speedY;
        }
        else{
            locationX += (int)(speed * Math.cos(angle));
            locationY += (int)(speed * Math.sin(angle));
        }

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

    public int getBulletPower() {
        return bulletPower;
    }
}
