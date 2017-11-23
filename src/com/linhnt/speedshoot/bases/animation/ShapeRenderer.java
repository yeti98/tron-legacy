package com.linhnt.speedshoot.bases.animation;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.counter.Counter;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.ImageObserver;

public class ShapeRenderer extends Renderer {
    protected Color color;
    protected Area area;
    protected Vector2D areaSize;
    protected Vector2D lastUpdateAnchor;

    public ShapeRenderer(Shape shape) {
        this(Color.white, shape);
    }

    public ShapeRenderer(Color color, Shape shape) {
        super();
        this.color = color;
        this.area = new Area(shape);
        areaSize = new Vector2D();
        matchAreaSize();
        lastUpdateAnchor = new Vector2D();
    }

    private void matchAreaSize() {
        areaSize.set((float)area.getBounds().getSize().getWidth(), (float)area.getBounds().getSize().getHeight());
    }

    @Override
    protected void fitGameObjectToTransform(GameObject gameObject)  {
        //scale
//        Vector2D scaleTo = new Vector2D(gameObject.getScale().x / scale.x, gameObject.getScale().y / scale.y);
        Vector2D scaleTo = gameObject.getScale().multiply(scale.reverseMultiply());

        //position
        //convert
        position.multiplyThis(gameObject.getScale().multiply(scale.reverseMultiply()));
        Vector2D translate = gameObject.getPosition().minus(position);

//        updateAnchor(translate, gameObject);
        //TODO: update anchor???

//        translate.minusThis(gameObject.getAnchor().multiply(this.areaSize));
//        //rotation
//        double rotate = gameObject.getRotate() - rotation;
//        rotation = gameObject.getRotate();
//        transform.setToRotation(rotate);
//        this.fitTransformRotate();

        scale.set(gameObject.getScale());
        position.set(gameObject.getPosition());
        //why fit with rotation its not work ????
        transform.setTransform(scaleTo.x, transform.getShearY(), transform.getShearX(), scaleTo.y, translate.x, translate.y);
    }

//    private void updateAnchor(Vector2D translate, GameObject gameObject) {
//        Vector2D needUpdateAnchor = gameObject.getAnchor().multiply(areaSize.multiply(gameObject.getScale().reverseMultiply()));
//        translate.minusThis(needUpdateAnchor.minus(lastUpdateAnchor));
//        lastUpdateAnchor.set(needUpdateAnchor);
//    }

    public void fitTransformToArea() {
        area.transform(transform);
        matchAreaSize();
    }

    @Override
    public boolean contains(Vector2D point) {
        return area.contains(point.x, point.y);
    }

    @Override
    public void render(Graphics2D g2d, GameObject gameObject, ImageObserver imageObserver) {
        if(area != null) {
            if(color != null) {
                g2d.setColor(color);
            }
            fitGameObjectToTransform(gameObject);
            fitTransformToArea();
            g2d.fill(area);
        }
    }

    @Override
    public Counter getFrameCounter() {
        return null;
    }

    @Override
    public void reset() {

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
