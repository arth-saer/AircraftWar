package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.enemyfactory.EnemyFactory;

public class EliteEnemyFactory implements EnemyFactory {
    @Override
        public EnemyAircraft createEnemy(){
            return new EliteEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                    (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                    1,
                    4,
                    100);
    }
}
