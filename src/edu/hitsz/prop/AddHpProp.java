package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;

public class AddHpProp extends BaseProp{
    public AddHpProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public int Effect(){
        if(voiceSwitch){
            new MusicThread("src/videos/get_supply.wav").start();
        }
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        int increase = 1000;
        heroAircraft.increaseHp(increase);
        return 0;
    }
}
