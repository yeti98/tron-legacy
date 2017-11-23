package com.linhnt.speedshoot.bases.animation;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.counter.Counter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

/**
 * Created by LT on 14/07/2017.
 */
public abstract class Renderer {
    protected AffineTransform transform;
    protected double rotation;
    protected Vector2D position;
    protected Vector2D scale; // (1, 1) at begin

    protected Renderer() {
        transform = new AffineTransform();
        rotation = 0;
        position = new Vector2D(0, 0);
        scale = new Vector2D(1 , 1);
    }

    protected abstract void fitGameObjectToTransform(GameObject gameObject);

    public abstract boolean contains(Vector2D point);

    public abstract void render(Graphics2D g2d, GameObject gameObject, ImageObserver imageObserver);
    public abstract Counter getFrameCounter();
    public abstract void reset();
}
