package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.enemyfactory.EnemyFactory;

public class BossEnemyFactory implements EnemyFactory {
    @Override
    public EnemyAircraft createEnemy(){
        return new BossEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())),
                (int) (Main.WINDOW_HEIGHT * 0.15),
                2,
                0,
                6000);
    }
}
