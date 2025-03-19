package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.DefendBossEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class DefendBossEnemyFactory implements EnemyFactory{
    public EnemyAircraft createEnemy(int speedX, int speedY, int hp){
        return new DefendBossEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.DEFEND_BOSS_ENEMY_IMAGE.getWidth())),
                (int) (Main.WINDOW_HEIGHT * 0.11),
                speedX,
                speedY,
                hp);
    }
}
