package com.games.rain;

import com.games.rain.input.Keyboard;
import com.games.rain.graphics.Screen;
import com.games.rain.level.Level;
import com.games.rain.level.RandomLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    //setting the basic dimensions of the game. Very important to set this in the beginning to avoid graphical errors
    public static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }

    public static int width = 300;
    public static int height = width/16*9;
    public static int scale = 3;
    public static String title = "Rain";
    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Boolean running = false;
    //creating a screen oblject from our screen class

    private Screen screen;
    //most important shit
    private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    //getting the raster, by typecasting the raster of the image into a DataBufferInt
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    //creating a constructor for Game class
    public Game(){
        //creating dimension from superclass Canvas
        Dimension size = new Dimension(width*scale,height*scale);
        setPreferredSize(size);
        screen = new Screen(width,height);//sets the height in the screen class
        frame = new JFrame();
        key = new Keyboard();
        level = new RandomLevel(64,64);

        addKeyListener(key);//add keyListner after you have set key, or else you get a null ptr execption
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
        //setting up last time for timer
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0/60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;//setting up timer so delta is only >=1 60 times a second, which is our update cap
            lastTime = now;
            while(delta>=1){
                update();// updates game logic to keep consistant pace
                updates++;
                delta--;
            }
            render();// renders the game on the screen
            frames++;
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frame.setTitle(title+"  |  "+frames);
                updates = 0;
                frames = 0;
            }
        }
    }
    int x = 0; int y= 0 ;
    public void update(){

        key.update();
        if (key.up) y--;
        if (key.down) y++;
        if (key.left) x--;
        if (key.right) x++;

    }
    public void render(){
        // creating a buffer strategy, buffer of 2/3 frames???
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){// incase the bs doesnt exist
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        level.render(x,y,screen);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        //drawing graphics on the screen
        Graphics g = bs.getDrawGraphics();
        //this is where we write all of our graphics
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
        // end of graphics display
        //need to dispose of your graphics to release system resources, really important to run this at the end
        g.dispose();
        bs.show();
    }

    //setting our main method
    public static void main(String[] args ){
        Game game = new Game();
        //really important to set frame.setResizable to false, as it causes a lot of graphical errors
        //always set this to false
        game.frame.setResizable(false);
        //sets title
        game.frame.setTitle(Game.title);
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
