package com.games.rain.graphics;

import com.games.rain.level.tile.Tile;

import java.util.Random;

public class Screen {
    public int width;
    public int height;
    public int[] pixels;
    public int MAP_SIZE = 64;
    public int MAP_SIZE_MASK = MAP_SIZE -1;

    public int xOffset,yOffset;

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

    public  void  renderTile(int xp,int yp, Tile tile){
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y <tile.sprite.SIZE ; y++) {
            int ya = y+yp;
            for (int x = 0; x <tile.sprite.SIZE ; x++) {
                int xa = x+xp;
                if(xa<-tile.sprite.SIZE||xa>=width||ya<0||ya>= height) break;
                if(xa<0) xa = 0;
                pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
            }

        }

    }
    public void setOffset(int xOffset,int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}

