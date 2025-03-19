package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.enemyfactory.EnemyFactory;

public class MobEnemyFactory implements EnemyFactory {
    @Override
    public EnemyAircraft createEnemy(int speedX, int speedY, int hp){
        return new MobEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                speedX,
                speedY,
                hp);
    }

}
