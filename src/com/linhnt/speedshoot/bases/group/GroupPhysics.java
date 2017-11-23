package com.linhnt.speedshoot.bases.group;

import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.physic.PhysicBody;

public class GroupPhysics extends Group implements PhysicBody {
    public GroupPhysics() {
        super();
    }

    @Override
    public boolean contains(Vector2D point) {
        return false;
    }
}
