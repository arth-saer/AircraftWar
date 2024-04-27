package edu.hitsz.application;


import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.*;
import edu.hitsz.prop.AddHpProp;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.FirePlusProp;
import edu.hitsz.prop.FireProp;

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
    public static BufferedImage BOSS_ENEMY_IMAGE;
    public static BufferedImage HERO_BULLET_1_IMAGE;
    public static BufferedImage HERO_BULLET_2_IMAGE;
    public static BufferedImage HERO_BULLET_3_IMAGE;
    public static BufferedImage ELITE_BULLET_IMAGE;
    public static BufferedImage ELITE_PLUS_BULLET_IMAGE;
    public static BufferedImage BOSS_BULLET_IMAGE;
    public static BufferedImage ADD_HP_PROP;
    public static BufferedImage BOMB_PROP;
    public static BufferedImage FIRE_PROP;
    public static BufferedImage FIRE_PLUS_PROP;

    static {
        try {

            BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));

            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            ELITE_PLUS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elitePlus.png"));
            BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/boss.png"));


            HERO_BULLET_1_IMAGE = ImageIO.read(new FileInputStream("src/images/hero_bullet_1.png"));
            HERO_BULLET_2_IMAGE = ImageIO.read(new FileInputStream("src/images/hero_bullet_2.png"));
            HERO_BULLET_3_IMAGE = ImageIO.read(new FileInputStream("src/images/hero_bullet_3.png"));

            ELITE_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/elite_enemy_bullet.png"));
            ELITE_PLUS_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/elite_plus_enemy_bullet.png"));
            BOSS_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/boss_bullet.png"));

            ADD_HP_PROP = ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            BOMB_PROP = ImageIO.read(new FileInputStream("src/images/prop_bomb.png"));
            FIRE_PROP = ImageIO.read(new FileInputStream("src/images/prop_bullet.png"));
            FIRE_PLUS_PROP = ImageIO.read(new FileInputStream("src/images/prop_bulletPlus.png"));


            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(ElitePlusEnemy.class.getName(), ELITE_PLUS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), BOSS_ENEMY_IMAGE);


            CLASSNAME_IMAGE_MAP.put(HeroBullet1.class.getName(), HERO_BULLET_1_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet2.class.getName(), HERO_BULLET_2_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet3.class.getName(), HERO_BULLET_3_IMAGE);

            CLASSNAME_IMAGE_MAP.put(EliteBullet.class.getName(), ELITE_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(ElitePlusBullet.class.getName(), ELITE_PLUS_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossBullet.class.getName(), BOSS_BULLET_IMAGE);

            CLASSNAME_IMAGE_MAP.put(AddHpProp.class.getName(), ADD_HP_PROP);
            CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), BOMB_PROP);
            CLASSNAME_IMAGE_MAP.put(FireProp.class.getName(), FIRE_PROP);
            CLASSNAME_IMAGE_MAP.put(FirePlusProp.class.getName(), FIRE_PLUS_PROP);


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
