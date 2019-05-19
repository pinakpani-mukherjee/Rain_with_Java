package com.games.centurion;

import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.util.TreeMap;

public class Game extends Canvas implements Runnable {
    //setting the basic dimensions of the game. Very important to set this in the beginning to avoid graphical errors
    public static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }

    public static int width = 300;
    public static int height = width/16*9;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private Boolean running = false;
    //creating a constructor for Game class
    public Game(){
        //creating dimension from superclass Canvas
        Dimension size = new Dimension(width*scale,height*scale);
        setPreferredSize(size);

        frame = new JFrame();

    }

    public synchronized void start(){//this is the start method to start the game thread
        running = true;
        thread = new Thread(this,"Display");
        thread.start();
    }
    public synchronized void stop(){// this is our stop method to stop the game thread
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//this is the game loop that keeps our program running
    public void run(){
        while (running){
            System.out.println("Running");
        }
    }

    //setting our main method
    public static void main(String[] args ){
        Game game = new Game();
        //really important to set frame.setResizable to false, as it causes a lot of graphical errors
        //always set this to false
        game.frame.setResizable(false);
        //sets title
        game.frame.setTitle("Rain");
        //adds the game to the canvas
        game.frame.add(game);
        //packs the JFrame
        game.frame.pack();
        //add ability to close with exit click
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //sets the JFrame in the middle of the screen
        game.frame.setLocationRelativeTo(null);
        //shows the frame. very important to set to true, or else you will not see the window
        game.frame.setVisible(true);


        //starting our game
        game.start();
    }
}
