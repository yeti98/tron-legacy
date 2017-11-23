package com.linhnt.speedshoot.bases.animation;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.counter.Counter;
import com.linhnt.speedshoot.main.Settings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class ImageRenderer extends Renderer{
    protected BufferedImage image;
    protected BufferedImage cache;
    protected Graphics2D cacheGraphics;

    public ImageRenderer(BufferedImage image) {
        super();
        this.image = image;
        this.cache = new BufferedImage(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.cacheGraphics = cache.createGraphics();
        cacheGraphics.setColor(Settings.FONT_COLOR);
    }

    @Override
    protected void fitGameObjectToTransform(GameObject gameObject) {
        //scale
        Vector2D scaleTo = gameObject.getScale().clone();

        //position
        //convert
        Vector2D translate = gameObject.getPosition().minus(gameObject.getAnchor().multiply(this.image.getWidth(), this.image.getHeight()).multiplyThis(gameObject.getScale()));
        scale.set(gameObject.getScale());
        position.set(gameObject.getPosition());

        transform.setTransform(scaleTo.x, transform.getShearY(), transform.getShearX(), scaleTo.y, translate.x, translate.y);
    }

    protected boolean pointInCache(Vector2D point) {
        return point.x >= 0 && point.x < cache.getWidth()
                && point.y >= 0 && point.y < cache.getHeight();
    }

    @Override
    public boolean contains(Vector2D point) {
        point.addThis(this.position);
        return this.pointInCache(point) && cache.getRGB((int)point.x, (int)point.y) != Settings.FONT_COLOR_RGB;
    }

    @Override
    public void render(Graphics2D g2d, GameObject gameObject, ImageObserver imageObserver) {
        if(image != null) {
            fitGameObjectToTransform(gameObject);
            //save cache
            cacheGraphics.fillRect(0, 0, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
            cacheGraphics.drawRenderedImage(image, transform);
            g2d.drawRenderedImage(image, transform);
        }
    }

    @Override
    public Counter getFrameCounter() {
        return null;
    }

    @Override
    public void reset() { }
}
