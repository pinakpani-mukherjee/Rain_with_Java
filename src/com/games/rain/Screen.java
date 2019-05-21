package com.games.rain;

import java.util.Random;

public class Screen {
    private int width;
    private int height;
    public int[] pixels;
    public int MAP_SIZE = 16;
    public int MAP_SIZE_MASK = MAP_SIZE -1;
    public int[] tiles = new int[MAP_SIZE*MAP_SIZE];//64*64 tiles, total of 4096 tiles

    private Random random = new Random();
    public Screen(int width,int height){
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        for (int i = 0; i <MAP_SIZE*MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }
    //creating the clear method
    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    //creating render function for screen
    public void render(int xOffset, int yOffset){    //rendering pixel by pixel,left to right, from top to bottom
        for (int y = 0;y<height;y++){
            int yy = y+yOffset;
            if (yy<0||yy>=height) break;
            for (int x = 0;x<width;x++){
                int xx = x+xOffset;
                if(xx<0||xx>=width) break;
                int tileIndex = ((xx>>4)&MAP_SIZE_MASK)+((yy>>4)&MAP_SIZE_MASK)*MAP_SIZE;//setting tile at 16*16 size, use bitwise operators

                pixels[x+y*width] = tiles[tileIndex];
            }
        }
    }
}
