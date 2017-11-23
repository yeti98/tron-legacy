package com.linhnt.speedshoot.bases.action;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.counter.FrameCounter;

public class ActionMove implements Action {
    protected Vector2D from;
    protected Vector2D to;
    protected FrameCounter frameCounter;
    protected Vector2D velocity;

    public ActionMove(Vector2D from, Vector2D to, int frameCount) {
        this.from = from;
        this.to = to;
        frameCounter = new FrameCounter(frameCount);
        velocity = new Vector2D();
        this.reset();
    }

    protected boolean runLastStep = false;

    @Override
    public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
        if(!frameCounter.run(milisecDelay)) {
            owner.getVelocity().set(this.calculVelocity(milisecDelay, owner));
            return false;
        } else if(!runLastStep) {
            owner.getPosition().set(to);
            owner.getVelocity().set(Vector2D.zeroVector);
            runLastStep = true;
        }
        return true;
    }

    protected Vector2D calculVelocity(long milisecDelay, GameObject owner) {
        velocity.set(to.minus(from));
        return velocity.setLength(velocity.getLength() / frameCounter.getMaxFrame());
    }

    @Override
    public void reset() {
        this.frameCounter.reset();
        runLastStep = false;
    }

    public Vector2D getFrom() {
        return from;
    }

    public void setFrom(Vector2D from) {
        this.from = from;
        this.reset();
    }

    public Vector2D getTo() {
        return to;
    }

    public void setTo(Vector2D to) {
        this.to = to;
        this.reset();
    }

    public FrameCounter getFrameCounter() {
        return frameCounter;
    }

    public void setFrameCounter(FrameCounter frameCounter) {
        this.frameCounter = frameCounter;
    }
}
