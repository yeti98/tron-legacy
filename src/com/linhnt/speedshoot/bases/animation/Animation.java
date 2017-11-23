package com.linhnt.speedshoot.bases.animation;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.counter.Counter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Arrays;
import java.util.List;

public class Animation extends ImageRenderer {
    protected List<BufferedImage> images;
    protected int current;

    public Animation(BufferedImage... images) {
        this(Arrays.asList(images));
    }

    public Animation(List<BufferedImage> images) {
        super(images.get(0));
        this.images = images;
        current = 0;
    }

    @Override
    public void render(Graphics2D g2d, GameObject gameObject, ImageObserver imageObserver) {
        this.image = this.getNext();
        super.render(g2d, gameObject, imageObserver);
    }

    private BufferedImage getNext() {
        return images.get(current++ % images.size());
    }

    @Override
    public Counter getFrameCounter() {
        return null;
    }

    @Override
    public void reset() {
        current = 0;
    }
}
