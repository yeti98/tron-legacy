package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.main.Settings;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;

public class Player2 extends Player {

    public Player2(){
        this.getVelocity().set(0, 1);
        this.matchSpeedToVelocity();
        this.getPosition().set(Settings.GAME_WIDTH / 2, 100);
        try {
            this.setRenderer(new SingleImageRenderer(ImageUtils.read("assets/images/player2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSpeed(float speed) {
        super.setSpeed(speed);
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);
    }
}
