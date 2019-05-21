package com.games.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//setting up keylistener which takes values from keyboard
public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[65536];//65536 is the max value of the char array, so its safe to use
    public boolean up,down,left,right;
    public void update(){
        up =keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down =keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left =keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right =keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        System.out.println(up);
    }
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
