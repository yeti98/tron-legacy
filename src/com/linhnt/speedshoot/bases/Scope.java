package com.linhnt.speedshoot.bases;

/**
 * Created by LT on 14/07/2017.
 */
public class Scope {
    public Float min;
    public Float max;

    public Scope() {
    }

    public Scope(Float min, Float max) {
        this.min = min;
        this.max = max;
    }

    public Scope set(Float min, Float max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public static float scope(float value, Scope scope) {
        if(scope.min != null && value < scope.min) {
            return scope.min;
        }
        if(scope.max != null && value > scope.max) {
            return scope.max;
        }
        return value;
    }
}
