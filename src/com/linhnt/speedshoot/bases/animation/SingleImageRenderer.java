package com.linhnt.speedshoot.bases.animation;

import java.awt.image.BufferedImage;

public class SingleImageRenderer extends ImageRenderer {

    public SingleImageRenderer(BufferedImage image) {
        super(image);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
