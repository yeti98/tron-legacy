package com.linhnt.speedshoot.bases;

import com.linhnt.speedshoot.bases.action.Action;
import com.linhnt.speedshoot.bases.animation.Renderer;
import com.linhnt.speedshoot.bases.matchwithparent.MatchWithParent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class GameObject {
    protected Renderer renderer;
    protected Vector2D position;
    protected Vector2D anchor;
    protected Vector2D velocity;
    protected Vector2D scale;
    protected double rotate;
    protected boolean active;
    protected List<GameObject> children;
    protected List<GameObject> newChildren;
    protected List<GameObject> removeChildren;
    protected List<Integer> newChildrenIndex;
    protected Action action;
    protected MatchWithParent matchWithParent;
    protected MouseAdapter mouseAdapter;

    public GameObject() {
        this.position = new Vector2D();
        this.anchor = new Vector2D(0.5f, 0.5f);
        this.velocity = new Vector2D();
        this.scale = new Vector2D(1, 1);
//        this.matchWithParent = new BasicMatchWithParent();
        active = true;
    }

    public void render(Graphics2D graphics2D, ImageObserver imageObserver) {
        if(this.isActive() && this.renderer != null) {
            this.renderer.render(graphics2D, this, imageObserver);
        }

        if(this.children != null) {
            for(GameObject gameObject : children) {
                if(gameObject.isActive())
                    gameObject.render(graphics2D, imageObserver);
            }
        }
    }

    public void run(long milisecDelay, GameObject parent) {
        this.position.addThis(this.velocity);
        if(this.matchWithParent != null)
            this.matchWithParent.match(this, parent);

        if(this.children != null) {
            for(GameObject gameObject : children) {
                if(gameObject.isActive())
                    gameObject.run(milisecDelay, this);
            }
        }

        if(this.newChildren != null) {
            while(newChildren.size() > 0) {
                int index = newChildrenIndex.get(0);
                if(index < 0) {
                    this.children.add(newChildren.get(0));
                } else {
                    this.children.add(newChildrenIndex.get(0), newChildren.get(0));
                }
                newChildrenIndex.remove(0);
                newChildren.remove(0);
            }
        }

        if(this.removeChildren != null) {
            if(this.children != null) {
                while(removeChildren.size() > 0) {
                    this.children.remove(removeChildren.get(0));
                    this.removeChildren.remove(0);
                }
            } else {
                this.removeChildren.clear();
            }
        }
    }

    public void runAction(long milisecDelay, GameObject parent) {
        if(this.action != null) {
            this.action.run(milisecDelay, this, parent);
        }

        if(this.children != null) {
            for(GameObject gameObject : children) {
                if(gameObject.isActive())
                    gameObject.runAction(milisecDelay, this);
            }
        }
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getAnchor() {
        return anchor;
    }

    public void setAnchor(Vector2D anchor) {
        this.anchor = anchor;
    }

    public double getRotate() {
        return rotate;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getScale() {
        return scale;
    }

    public void setScale(Vector2D scale) {
        this.scale = scale;
    }

    public void addChild(GameObject gameObject) {
        if(this.newChildren == null) {
            this.children = new ArrayList<>();
            this.newChildren = new ArrayList<>();
            this.newChildrenIndex = new ArrayList<>();
        }
        this.newChildren.add(gameObject);
        this.newChildrenIndex.add(-1);
    }

    public void addChild(GameObject gameObject, int index) {
        if(this.newChildren == null) {
            this.children = new ArrayList<>();
            this.newChildren = new ArrayList<>();
            this.newChildrenIndex = new ArrayList<>();
        }
        this.newChildren.add(gameObject);
        this.newChildrenIndex.add(index);
    }

    public void removeChild(GameObject gameObject) {
        if(removeChildren == null) {
            this.removeChildren = new ArrayList<>();
        }
        this.removeChildren.add(gameObject);
    }

    public void killChild(GameObject gameObject) {
        gameObject.setActive(false);
        this.removeChild(gameObject);
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<GameObject> getChildren() {
        return children;
    }

    public MatchWithParent getMatchWithParent() {
        return matchWithParent;
    }

    public void setMatchWithParent(MatchWithParent matchWithParent) {
        this.matchWithParent = matchWithParent;
    }

    public MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

    public void setMouseAdapter(MouseAdapter mouseAdapter) {
        this.mouseAdapter = mouseAdapter;
    }

    public void reset() {
        this.position.set(Vector2D.zeroVector);
        this.anchor.set(0.5f, 0.5f);
        this.velocity.set(Vector2D.zeroVector);
        this.scale.set(Vector2D.unitVector);
        active = true;
        if(this.children != null) {
            this.children.clear();
        }
    }
}
