package com.linhnt.speedshoot.bases.physic;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Physic {
    private static List<PhysicBody> bodies = new ArrayList<>();

    public static <E extends GameObject> E bodyContains (float x, float y, Class<E> cls) {
        return bodyContains(new Vector2D(x, y), cls);
    }

    public static <E extends GameObject> E bodyContains (Vector2D point, Class<E> cls) {
        int bodiesSize = bodies.size();
        for(int i = bodiesSize - 1; i >= 0; i--) {
            PhysicBody body = bodies.get(i);
            if(GameObject.class.isAssignableFrom(body.getClass())) {
                E foundBodyContains = bodyContains(point, (GameObject)body, cls);
                if(foundBodyContains != null) {
                    return foundBodyContains;
                }
            }
        }
        return null;
    }

    private static <E extends GameObject> E bodyContains(Vector2D point, GameObject gameObject, Class<E> cls) {
        if(gameObject != null) {
            if(((PhysicBody)gameObject).contains(point) && PhysicBody.class.isAssignableFrom(gameObject.getClass())
                    && cls.isAssignableFrom(gameObject.getClass())) {
                return (E)gameObject;
            }
            if(gameObject.getChildren() != null) {
                int childrenSize = gameObject.getChildren().size();
                for(int i = childrenSize - 1; i >= 0; i--) {
                    GameObject child = gameObject.getChildren().get(i);
                    E foundBodyContains = bodyContains(point, child, cls);
                    if(foundBodyContains != null) {
                        return foundBodyContains;
                    }
                }
            }
        }

        return null;
    }

    public static void addBody(PhysicBody body) {
        bodies.add(body);
    }

    public static void removeBody(PhysicBody body) {
        bodies.remove(body);
    }

    public static void clearBodies() {
        bodies.clear();
    }

}
