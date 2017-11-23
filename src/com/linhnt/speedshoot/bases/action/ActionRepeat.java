package com.linhnt.speedshoot.bases.action;

import com.linhnt.speedshoot.bases.GameObject;

public class ActionRepeat implements Action {
    private Action repeatAction;
    private int maxRepeatTime;
    private int repeatTime;
    private boolean repeatForever;

    public ActionRepeat(Action repeatAction, int maxRepeatTime) {
        this.repeatAction = repeatAction;
        this.maxRepeatTime = maxRepeatTime;
    }

    public ActionRepeat(Action repeatAction, boolean repeatForever) {
        this.repeatAction = repeatAction;
        this.repeatForever = repeatForever;
    }

    @Override
    public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
        if(this.repeatAction != null) {
            if(this.repeatForever) {
                if(this.repeatAction.run(milisecDelay, owner, parent)){
                    this.repeatAction.reset();
                }
                return false;
            }else if(repeatTime < maxRepeatTime) {
                if(this.repeatAction.run(milisecDelay, owner, parent)){
                    this.repeatAction.reset();
                    repeatTime++;
                }
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @Override
    public void reset() {
        this.repeatTime = 0;
        this.repeatAction.reset();
    }
}
