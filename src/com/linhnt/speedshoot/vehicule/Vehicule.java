package com.linhnt.speedshoot.vehicule;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.physic.PhysicBody;

public class Vehicule extends GameObject implements PhysicBody {
    public final int MAX_SPEED = 40; //(km/h)
    public final float RATE_CONVERT = 10;

    public float speed;

    @Override
    public boolean contains(Vector2D point) {
        return this.renderer.contains(point.minus(this.position));
    }

    public float getSpeedInKmH() {
        velocity.getLength();
        return 0;
    }

    public void matchSpeedToVelocity() {
        float length = speed / RATE_CONVERT;
        System.out.println(length);
        velocity.setLengthThis(length);
    }

    public boolean isValidSpeed() {
        return speed <= MAX_SPEED;
    }
}
