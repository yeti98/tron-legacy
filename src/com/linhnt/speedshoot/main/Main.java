package com.linhnt.speedshoot.main;

import com.linhnt.speedshoot.player.Player1;
import com.linhnt.speedshoot.player.Player2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by minhdq99hp on 11/11/2017.
 */
public class Main {
    private static void setupKeyboardListener(JFrame frame, Player1 player1, Player2 player2) throws Exception {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case Settings.KEY_SPEED_UP: {
                        if(player1.getTail().length>5) {
                            player1.setSpeed(Settings.TURBO_SPEED);
                            player1.getTail().length-=0.05;
                        }
                        break;
                    }
                    case Settings.KEY_LEFT: {
                        player1.getVelocity().rotateThis(-Settings.DELTA_ANGLE);
                        break;
                    }
                    case Settings.KEY_RIGHT: {
                        player1.getVelocity().rotateThis(Settings.DELTA_ANGLE);
                        break;
                    }
                    case Settings.KEY_SPEED_UP_2: {
                        if(player2.getTail().length>5) {
                            player2.setSpeed(Settings.TURBO_SPEED);
                            player2.getTail().length-=0.05;
                        }
                        break;
                    }
                    case Settings.KEY_LEFT_2: {
                        player2.getVelocity().rotateThis(Settings.DELTA_ANGLE);
                        break;
                    }
                    case Settings.KEY_RIGHT_2: {
                        player2.getVelocity().rotateThis(-Settings.DELTA_ANGLE);
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case Settings.KEY_SPEED_UP: {
                        player1.setSpeed(Settings.NORMAL_SPEED);
                        break;
                    }
                    case Settings.KEY_SPEED_UP_2: {
                        player2.setSpeed(Settings.NORMAL_SPEED);
                        break;
                    }
                }
            }
        });
    }
    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setTitle(Settings.GAME_TITLE);
        window.setSize(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);

        try {

            GameCanvas canvas = new GameCanvas();

            window.add(canvas);
            window.setVisible(true);

            setupKeyboardListener(window, canvas.getPlayer1(), canvas.getPlayer2());

            canvas.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
