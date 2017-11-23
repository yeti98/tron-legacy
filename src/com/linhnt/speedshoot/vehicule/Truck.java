package com.linhnt.speedshoot.vehicule;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.animation.Animation;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;

public class Truck extends Vehicule {
    public Truck() {
        try {
//            this.setRenderer(new SingleImageRenderer(ImageUtils.read("assets/images/bus.png")));
            this.setRenderer(new Animation(
                    ImageUtils.readAllInForder(
                            "assets/images/truck_animation")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getPosition().set(0, 400);
        this.getVelocity().set(2, 0);
        this.getScale().set(0.05f, 0.05f);
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent); //
        if(this.getPosition().x > 800) {
            this.getVelocity().set(0, 0);
            //scale
        } else {
            this.getScale().addThis(0.001f, 0.001f);
        }
    }
}
