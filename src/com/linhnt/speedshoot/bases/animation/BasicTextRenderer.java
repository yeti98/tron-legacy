package com.linhnt.speedshoot.bases.animation;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.counter.Counter;

import java.awt.*;
import java.awt.image.ImageObserver;

public class BasicTextRenderer extends Renderer {
    private String text;
    private Font font;

    public BasicTextRenderer(String text, Font font) {
        this.text = text;
        this.font = font;
    }

    @Override
    protected void fitGameObjectToTransform(GameObject gameObject) {}

    @Override
    public boolean contains(Vector2D point) {
        return false;
    }

    @Override
    public void render(Graphics2D g2d, GameObject gameObject, ImageObserver imageObserver) {
        g2d.drawString(text, gameObject.getPosition().x, gameObject.getPosition().y);
    }

    @Override
    public Counter getFrameCounter() {
        return null;
    }

    @Override
    public void reset() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
