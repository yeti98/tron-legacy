package com.linhnt.speedshoot.vehicule;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.action.*;
import com.linhnt.speedshoot.bases.counter.MilisecCounter;
import com.linhnt.speedshoot.bases.group.Group;
import com.linhnt.speedshoot.bases.pool.GameObjectPool;
import com.linhnt.speedshoot.main.Settings;

import java.util.Random;

public class VehiculeManager extends GameObject {
    Group vehiculeGroupLan1;
    Group vehiculeGroupLan2;
    Random random = new Random();

    public VehiculeManager() throws Exception {
        setupAction();
        vehiculeGroupLan1 = GameObjectPool.createAndAdd(Group.class);
        vehiculeGroupLan2 = GameObjectPool.createAndAdd(Group.class);
    }

    private void setupAction() {
        Action actionSumoonOne = new Action() {
            @Override
            public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
                summonVehicule();
                return true;
            }

            @Override
            public void reset() {

            }
        };
        Action actionWait = new ActionWait(new MilisecCounter(2000));
        Action actionSequence = new ActionSequence(actionSumoonOne, actionWait);
        Action actionRepeat = new ActionRepeat(actionSequence, true);
        this.setAction(actionRepeat);
    }

    public void summonVehicule() {
        try {
            int vehiculeType = random.nextInt(2);
            int lan = random.nextInt(2);
            Vehicule vehicule;
            switch (vehiculeType) {
                case 1:
                    vehicule = GameObjectPool.create(Bus.class);
                    break;
                case 0:
                default:
                    vehicule = GameObjectPool.create(Truck.class);
            }
            setupVehicule(vehicule, lan);
            if(lan == 0) {
                vehiculeGroupLan1.addChild(vehicule, 0);
            } else {
                vehiculeGroupLan2.addChild(vehicule, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupVehicule(Vehicule vehicule, int lan) {
        vehicule.speed = 30 + random.nextInt(30);
        GameObjectPool.addPhysics(vehicule);
        vehicule.setAction(vehiculeAction());
        vehicule.getScale().multiplyThis(0.5f);
        if(lan == 0) {
            vehicule.getVelocity().set(1, 0);
            vehicule.getPosition().set(-30, 300);
        } else {
            vehicule.getVelocity().set(1, 0);
            vehicule.getPosition().set(-30, 320);
        }
    }

    private Action vehiculeAction() {
        Action actionSetVelocity = new Action() {
            @Override
            public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
                //TODO: test vehicule da ra khoi man hinh chua
                if(owner.getPosition().x < (Settings.GAME_WIDTH + 100)) {
                    ((Vehicule)owner).matchSpeedToVelocity();
                    return false;
                } else {
                    owner.getVelocity().set(0, 0);
                }
                return true;
            }

            @Override
            public void reset() {

            }
        };
        Action actionSetScale = new Action() {
            @Override
            public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
                //TODO: test vehicule da ra khoi man hinh chua
                if(owner.getPosition().x < Settings.GAME_WIDTH + 100) {
                    //TODO: ((Vehicule)owner).matchPositionToScale();
                    owner.getScale().addThis(0.001f, 0.001f);
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public void reset() {

            }
        };

        Action killVehicule = new Action() {
            @Override
            public boolean run(long milisecDelay, GameObject owner, GameObject parent) {
                owner.setActive(false);
                return true;
            }

            @Override
            public void reset() {

            }
        };

        Action actionParallel = new ActionParallel(actionSetVelocity, actionSetScale);
        Action actionSequence = new ActionSequence(actionParallel, killVehicule);
        return actionSequence;
    }
}
