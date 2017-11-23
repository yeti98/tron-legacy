package com.linhnt.speedshoot.bases.action;

import com.linhnt.speedshoot.bases.GameObject;

public interface Action {
    boolean run(long milisecDelay, GameObject owner, GameObject parent);
    void reset();
}
