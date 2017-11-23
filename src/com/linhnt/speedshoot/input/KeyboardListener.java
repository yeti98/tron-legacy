package com.linhnt.speedshoot.input;


import com.linhnt.speedshoot.main.Settings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by minhdq99hp on 12/11/2017.
 */
public class KeyboardListener implements KeyListener {
    private boolean leftPress;
    private boolean rightPress;
    private boolean speedUpPress;
    private boolean leftPress2;
    private boolean rightPress2;
    private boolean speedUpPress2;

    public static final KeyboardListener instance = new KeyboardListener();

    public KeyboardListener() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case Settings.KEY_SPEED_UP: {
                speedUpPress = true;
                break;
            }
            case Settings.KEY_LEFT: {
                leftPress = true;
                break;
            }
            case Settings.KEY_RIGHT: {
                rightPress = true;
                break;
            }
            case Settings.KEY_SPEED_UP_2: {
                speedUpPress2 = true;
                break;
            }
            case Settings.KEY_LEFT_2: {
                leftPress2 = true;
                break;
            }
            case Settings.KEY_RIGHT_2: {
                rightPress2 = true;
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case Settings.KEY_SPEED_UP: {
                speedUpPress = false;
                break;
            }
            case Settings.KEY_LEFT: {
                leftPress = false;
                break;
            }
            case Settings.KEY_RIGHT: {
                rightPress = false;
                break;
            }
            case Settings.KEY_SPEED_UP_2: {
                speedUpPress2 = false;
                break;
            }
            case Settings.KEY_LEFT_2: {
                leftPress2 = false;
                break;
            }
            case Settings.KEY_RIGHT_2: {
                rightPress2 = false;
                break;
            }
        }
    }

    public boolean isSpeedUpPress2(){
        return speedUpPress2;
    }

    public boolean isLeftPress2(){
        return leftPress2;
    }

    public boolean isRightPress2(){
        return rightPress2;
    }

    public boolean isSpeedUpPress(){
        return speedUpPress;
    }

    public boolean isLeftPress() {
        return leftPress;
    }

    public boolean isRightPress() {
        return rightPress;
    }

}
