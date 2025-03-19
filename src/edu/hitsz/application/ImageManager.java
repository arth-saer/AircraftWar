package edu.hitsz.application;


import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.*;
import edu.hitsz.obstacle.Obstacle;
import edu.hitsz.prop.*;
import edu.hitsz.specialeffect.BombSpecialEffect;
import edu.hitsz.specialeffect.ShieldSpecialEffect;
import edu.hitsz.specialeffect.ShootedSpecialEffect;
import edu.hitsz.specialeffect.WarningSpecialEffect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage BACKGROUND_IMAGE;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage ELITE_ENEMY_IMAGE;
    public static BufferedImage ELITE_PLUS_ENEMY_IMAGE;
    public static BufferedImage SHOOT_BOSS_ENEMY_IMAGE;
    public static BufferedImage DEFEND_BOSS_ENEMY_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage BOSS_BULLET_IMAGE;
    public static BufferedImage ADD_HP_PROP;
    public static BufferedImage BOMB_PROP;
    public static BufferedImage FIRE_PROP;
    public static BufferedImage FIRE_PLUS_PROP;
    public static BufferedImage SHIELD_PROP;
    public static BufferedImage OBSTACLE;
    public static BufferedImage OBSTACLE_SPECIAL_EFFECT;
    public static BufferedImage SHOOTED_SPECIAL_EFFECT;
    public static BufferedImage WARNING_SPECIAL_EFFECT;
    public static BufferedImage BOMB_SPECIAL_EFFECT;


    static {
        try {

            BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));

            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            ELITE_PLUS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elitePlus.png"));
            SHOOT_BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/shoot_boss.png"));
            DEFEND_BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/defend_boss.png"));


            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/hero_bullet.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/enemy_bullet.png"));
            BOSS_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/boss_bullet.png"));

            ADD_HP_PROP = ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            BOMB_PROP = ImageIO.read(new FileInputStream("src/images/prop_bomb.png"));
            FIRE_PROP = ImageIO.read(new FileInputStream("src/images/prop_bullet.png"));
            FIRE_PLUS_PROP = ImageIO.read(new FileInputStream("src/images/prop_bulletPlus.png"));
            SHIELD_PROP = ImageIO.read(new FileInputStream("src/images/prop_shield.png"));

            OBSTACLE = ImageIO.read(new FileInputStream("src/images/obstacle.png"));
            OBSTACLE_SPECIAL_EFFECT = ImageIO.read(new FileInputStream("src/images/shield_special_effect.png"));
            SHOOTED_SPECIAL_EFFECT = ImageIO.read(new FileInputStream("src/images/shooted_special_effect.png"));
            WARNING_SPECIAL_EFFECT = ImageIO.read(new FileInputStream("src/images/warning_special_effect.png"));
            BOMB_SPECIAL_EFFECT = ImageIO.read(new FileInputStream("src/images/bomb_special_effect.png"));


            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(ElitePlusEnemy.class.getName(), ELITE_PLUS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(ShootBossEnemy.class.getName(), SHOOT_BOSS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(DefendBossEnemy.class.getName(), DEFEND_BOSS_ENEMY_IMAGE);


            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossBullet.class.getName(), BOSS_BULLET_IMAGE);

            CLASSNAME_IMAGE_MAP.put(AddHpProp.class.getName(), ADD_HP_PROP);
            CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), BOMB_PROP);
            CLASSNAME_IMAGE_MAP.put(FireProp.class.getName(), FIRE_PROP);
            CLASSNAME_IMAGE_MAP.put(FirePlusProp.class.getName(), FIRE_PLUS_PROP);
            CLASSNAME_IMAGE_MAP.put(ShieldProp.class.getName(), SHIELD_PROP);

            CLASSNAME_IMAGE_MAP.put(Obstacle.class.getName(), OBSTACLE);
            CLASSNAME_IMAGE_MAP.put(ShieldSpecialEffect.class.getName(), OBSTACLE_SPECIAL_EFFECT);
            CLASSNAME_IMAGE_MAP.put(ShootedSpecialEffect.class.getName(), SHOOTED_SPECIAL_EFFECT);
            CLASSNAME_IMAGE_MAP.put(WarningSpecialEffect.class.getName(), WARNING_SPECIAL_EFFECT);
            CLASSNAME_IMAGE_MAP.put(BombSpecialEffect.class.getName(), BOMB_SPECIAL_EFFECT);


        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
