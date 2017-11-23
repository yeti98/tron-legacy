package com.linhnt.speedshoot.bases.action;

import com.linhnt.speedshoot.bases.GameObject;

import java.util.Arrays;
import java.util.List;

public class ActionParallel implements Action {
    private List<Action> actions;

    public ActionParallel(Action... actions) {
        this(Arrays.asList(actions));
    }

    public ActionParallel(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
        if(this.actions != null) {
            boolean isDone = true;
            for(Action action: actions) {
                if(action != null) {
                    boolean actionDone = action.run(milisecDelay, owner, parent);
                    isDone = (isDone && actionDone);
                }
            }
            return isDone;
        }
        return true;
    }

    @Override
    public void reset() {
        if(this.actions != null) {
            for(Action action : actions) {
                if(action != null){
                    action.reset();
                }
            }
        }
    }
}
