package com.games.centurion;

import java.awt.*;

public class Screen {
    private int width;
    private int height;
    public int[] pixels;

    public Screen(int width,int height){
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
    }
    //creating the clear method
    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    //creating render function for screen
    public void render(){    //rendering pixel by pixel,left to right, from top to bottom
        for (int y = 0;y<height;y++){
            for (int x = 0;x<width;x++){
                pixels[x+y*width] = 0xff00ff;
            }
        }
    }
}
