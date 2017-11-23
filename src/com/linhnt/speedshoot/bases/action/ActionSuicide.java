package com.linhnt.speedshoot.bases.action;

import com.linhnt.speedshoot.bases.GameObject;

public class ActionSuicide implements Action {
    @Override
    public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
        if(!owner.isActive()) {
            if(parent != null) {
                parent.killChild(owner);
            } else {
                owner.setActive(false);
            }
        }
        return true;
    }

    @Override
    public void reset() {
    }
}
