package com.linhnt.speedshoot.bases.matchwithparent;

import com.linhnt.speedshoot.bases.GameObject;

public class BasicMatchWithParent implements MatchWithParent {
    public BasicMatchWithParent() {}
    @Override
    public void match(GameObject child, GameObject parent) {
        if(parent == null)
            return;
        child.getPosition().set(parent.getPosition());
        child.getScale().set(parent.getScale());
        child.setRotate(parent.getRotate());
    }
}
