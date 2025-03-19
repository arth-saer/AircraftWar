package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.enemyfactory.EnemyFactory;

public class ElitePlusEnemyFactory implements EnemyFactory {
    @Override
    public EnemyAircraft createEnemy(int speedX, int speedY, int hp){
        return new ElitePlusEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_PLUS_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                speedX,
                speedY,
                hp);
    }
}
