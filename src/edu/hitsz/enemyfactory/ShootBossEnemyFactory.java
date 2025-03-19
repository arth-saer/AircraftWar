package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.ShootBossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class ShootBossEnemyFactory implements EnemyFactory {
    @Override
    public EnemyAircraft createEnemy(int speedX, int speedY, int hp){
        return new ShootBossEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.SHOOT_BOSS_ENEMY_IMAGE.getWidth())),
                (int) (Main.WINDOW_HEIGHT * 0.15),
                speedX,
                speedY,
                hp);
    }

}
