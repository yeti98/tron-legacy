package com.linhnt.speedshoot.bases.action;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;

public class ActionMoveFaster extends ActionMove {
    //x0 + x1 + x2 + .. xMaxFrameCount = distance
    //x1 = rate * x0;
    //x0 + x0 * rate^1 + x0 * rate^2 +... + x0 * rate^maxFrameCount = distance
    //x0 = distance / (1 + rate^1 + rate^2 +... + rate^maxFrameCount)
    //x0 = distance / (rate^(maxFrameCount + 1) - 1) * (rate - 1)
    private static float rate = 1.1f;
    private double x0;

    public ActionMoveFaster(Vector2D from, Vector2D to, int frameCount) {
        super(from, to, frameCount);
        x0 = to.minus(from).getLength() / (Math.pow(rate, frameCount + 1) - 1) * (rate  - 1);
        System.out.println(x0);
    }

    @Override
    protected Vector2D calculVelocity(long milisecDelay, GameObject owner) {
        int current = frameCounter.getCurrent() - 1;
        velocity.set(to.minus(from));
        double length = x0 * Math.pow(rate, current);
        return velocity.setLength((float)length);
    }
}
