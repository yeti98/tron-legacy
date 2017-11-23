package com.linhnt.speedshoot.main;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by minhdq99hp on 11/11/2017.
 */
public class Settings {
    // window
    public static String GAME_TITLE = "Tron Legacy";
    public static int GAME_WIDTH = 1280;
    public static int GAME_HEIGHT = 720;
    public static Color FONT_COLOR = new Color(0x990099);
    public static int FONT_COLOR_RGB = FONT_COLOR.getRGB();

    // player
    public static final int INIT_BLOOD = 100;
    public static final int INIT_POINT = 0;
    public static final float NORMAL_SPEED = 6;
    public static final float TURBO_SPEED = 12;
    public static final float DELTA_ANGLE = (float)Math.PI / 24;

    // player_1
    public static final int KEY_LEFT = KeyEvent.VK_LEFT;
    public static final int KEY_RIGHT = KeyEvent.VK_RIGHT;
    public static final int KEY_SPEED_UP = KeyEvent.VK_UP;

    // player_2
    public static final int KEY_LEFT_2 = KeyEvent.VK_A;
    public static final int KEY_RIGHT_2 = KeyEvent.VK_D;
    public static final int KEY_SPEED_UP_2 = KeyEvent.VK_W;
}
