package com.linhnt.speedshoot.bonus;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.pool.GameObjectPool;
import com.linhnt.speedshoot.main.Settings;

import java.util.ArrayList;
import java.util.Random;

public class BonusManager extends GameObject {
    private ArrayList<Bonus> bonusArrayList = new ArrayList<>();
    private Random r = new Random();

    public BonusManager(){
        try {
            setupBonus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupBonus() throws Exception {
        for(int i=0; i<20; i++){
            Bonus bonus = GameObjectPool.createAndAddAnddAddToPhysics(Bonus.class);
            bonus.getPosition().set(r.nextInt(Settings.GAME_WIDTH), r.nextInt(Settings.GAME_HEIGHT));

            bonusArrayList.add(bonus);
        }
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);

    }
}
