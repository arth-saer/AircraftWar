package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    HeroAircraft heroAircraft;
    @BeforeEach
    void setUp() {
        heroAircraft = HeroAircraft.getHeroAircraft();
    }

    @AfterEach
    void tearDown() {
        heroAircraft.setLocation(Main.WINDOW_WIDTH / 2,
                Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight());
        heroAircraft = null;
    }

    @Test
    void decreaseHp() {
        int decrease = 10000;
        heroAircraft.decreaseHp(decrease);
        assertEquals(49990000, heroAircraft.getHp());
    }

    //英雄战机大小为100 * 83，所处位置为[256 , 685]
    //英雄战机范围为x方向上 [206, 306]; y方向上[665，705]
    //精英战机大小为105 * 68
    //105/2 = 52 x临界点x [155, 357]
    //68/4 = 17 临界点y [649, 721]
    @Test
    void crash() {
        EliteEnemy eliteEnemy = new EliteEnemy(300, 700, 1 ,4 , 150);
        assertTrue(heroAircraft.crash(eliteEnemy));
    }

    @Test
    void setLocation() {
        heroAircraft.setLocation(520.1314, 521);
        assertEquals(520, heroAircraft.getLocationX());
        assertEquals(521, heroAircraft.getLocationY());


    }


}