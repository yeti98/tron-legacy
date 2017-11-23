package com.linhnt.speedshoot.bases.action;

import com.linhnt.speedshoot.bases.GameObject;

import java.util.Arrays;
import java.util.List;

public class ActionSequence implements Action {
    private List<Action> actions;
    private int currentActionIndex = 0;

    public ActionSequence(Action... actions) {
        this(Arrays.asList(actions));
    }

    public ActionSequence(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
        if(this.actions != null && currentActionIndex < actions.size()) {
            Action currentAction = actions.get(currentActionIndex);
            if(currentAction == null || currentAction.run(milisecDelay, owner, parent)) {
                currentActionIndex++;
            }
            return false;
        }
        return true;
    }

    @Override
    public void reset() {
        for(Action action : actions) {
            if(action != null) {
                action.reset();
            }
        }
        this.currentActionIndex = 0;
    }
}
