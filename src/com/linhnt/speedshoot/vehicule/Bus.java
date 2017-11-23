package com.linhnt.speedshoot.vehicule;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.action.Action;
import com.linhnt.speedshoot.bases.action.ActionSequence;
import com.linhnt.speedshoot.bases.action.ActionSuicide;
import com.linhnt.speedshoot.bases.animation.Animation;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.bases.physic.PhysicBody;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;

public class Bus extends Vehicule {
    public Bus() {
        try {
//            this.setRenderer(new SingleImageRenderer(ImageUtils.read("assets/images/bus.png")));
            this.setRenderer(new Animation(ImageUtils.readAllInForder("assets/images/bus_animation")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getPosition().set(0, 400);
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);
    }
}
