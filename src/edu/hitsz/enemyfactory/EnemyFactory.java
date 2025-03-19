package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.EnemyAircraft;

public interface EnemyFactory {
    EnemyAircraft createEnemy(int speedX, int speedY, int hp);
}
