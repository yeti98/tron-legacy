package com.linhnt.speedshoot.bases.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameMouseListener extends MouseAdapter {
    public static GameMouseListener instance = new GameMouseListener();

    private GameMouseListener() {
        mouseListeners = new ArrayList<>();
    };

    private List<MouseAdapter> mouseListeners;

    @Override
    public void mouseClicked(MouseEvent e) {
        for(MouseAdapter mouseListener : mouseListeners) {
            mouseListener.mouseClicked(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MouseAdapter mouseListener : mouseListeners) {
            mouseListener.mouseMoved(e);
        }
    }

    private void register(MouseAdapter mouseKeeper) {
        if(mouseKeeper != null) {
            this.mouseListeners.add(mouseKeeper);
        }
    }

    private void clearRegister() {
        this.mouseListeners.clear();
    }

    public static void registerToInstance(MouseAdapter mouseKeeper) {
        instance.register(mouseKeeper);
    }

    public static void clearRegisterInstance() {
        instance.clearRegister();
    }
}
