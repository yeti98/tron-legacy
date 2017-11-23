package com.linhnt.speedshoot.background;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;

public class Background extends GameObject {
    public Background() {
        try {
            this.setRenderer(
                    new SingleImageRenderer(
                            ImageUtils.read("assets/images/map.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getAnchor().set(0, 0);
    }
}
