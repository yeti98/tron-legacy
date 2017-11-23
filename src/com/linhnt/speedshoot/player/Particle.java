package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;

public class Particle extends GameObject {
    public Particle(){
        try {
            this.setRenderer(new SingleImageRenderer(ImageUtils.read("assets/images/tail1- v1.png")));
            this.getScale().set(0.5f, 0.5f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
