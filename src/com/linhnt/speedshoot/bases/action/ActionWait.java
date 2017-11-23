package com.linhnt.speedshoot.bases.action;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.counter.Counter;

public class ActionWait implements Action{
    Counter counter;
    public ActionWait(Counter counter) {
        this.counter = counter;
    }

    @Override
    public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
        if(this.counter != null) {
            return counter.run(milisecDelay);
        }
        return true;
    }

    @Override
    public void reset() {
        this.counter.reset();
    }
}
