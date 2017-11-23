package com.linhnt.speedshoot.bases.pool;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.physic.Physic;
import com.linhnt.speedshoot.bases.physic.PhysicBody;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class GameObjectPool {
    private static List<GameObject> gameObjects = new ArrayList<>();
    private static List<GameObject> newGameObjects = new ArrayList<>();
    private static List<GameObject> pool = new ArrayList<>();

    public static GameObject add(GameObject go) {
        newGameObjects.add(go);
        return go;
    }

    public static GameObject addPhysics(GameObject go) {
        if(PhysicBody.class.isAssignableFrom(go.getClass())) {
            Physic.addBody((PhysicBody) go);
        }
        return go;
    }

    public static <E extends GameObject> E create(Class<E> cls) throws Exception{
        E newInstance = cls.newInstance();
        newInstance.setActive(true);
        return newInstance;
    }

    public static <E extends GameObject> E createAndAdd(Class<E> cls) throws Exception{
        E newInstance = create(cls);
        add(newInstance);
        return newInstance;
    }

    public static <E extends GameObject> E createAndAddAnddAddToPhysics(Class<E> cls) throws Exception{
        E newInstance = createAndAdd(cls);
        addPhysics(newInstance);
        return newInstance;
    }

    public static <E extends GameObject> E recycle(Class<E> cls) throws Exception{
        for(GameObject go : pool) {
            if(!go.isActive() && cls.isAssignableFrom(go.getClass())) {
                go.reset();
                return (E)go;
            }
        }
        E newInstance = create(cls);
        pool.add(newInstance);
        return newInstance;
    }

    public static <E extends GameObject> E recycleAndAdd(Class<E> cls) throws Exception {
        E newInstance = recycle(cls);
        add(newInstance);
        return newInstance;
    }

    public static <E extends GameObject> E recycleAndAddAndAddToPhysic(Class<E> cls) throws Exception {
        E newInstance = recycleAndAdd(cls);
        addPhysics(newInstance);
        return newInstance;
    }

    public static void runAll(long milisecDelay, GameObject parent) {
        for(GameObject go : gameObjects) {
            if(go.isActive()) {
                go.run(milisecDelay, parent);
            }
        }

        if(!newGameObjects.isEmpty()) {
            gameObjects.addAll(newGameObjects);
            newGameObjects.clear();
        }
    }

    public static void renderAll(Graphics2D graphics2D, ImageObserver imageObserver) {
        for(GameObject go : gameObjects) {
            if(go.isActive()) {
                go.render(graphics2D, null);
            }
        }
    }

    public static void runActionAll(long milisecDelay) {
        for(GameObject go : gameObjects) {
            if(go.isActive()) {
                go.runAction(milisecDelay, null);
            }
        }
    }
}
