package com.games.rain.level;

import com.games.rain.graphics.Screen;
import com.games.rain.level.tile.Tile;

public class Level {
    protected int width,height;
    protected int[] tiles;

    //constructer

    public Level(int width,int height){
        this.width = width;
        this.height = height;
        tiles = new int[width*height];
        generateLevel();
    }
    public Level(String path){
        loadLevel(path);
    }

    private void loadLevel(String path) {
    }

    protected void generateLevel() {
    }
    public void update(){
    }
    private void time(){
    }
    public void render(int xScroll, int yScroll, Screen screen){
        screen.setOffset(xScroll,yScroll);
        int x0 = xScroll>>4;
        int x1 = (xScroll + screen.width+16) >> 4;
        int y0 = yScroll>>4;
        int y1 = (yScroll+screen.height+16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x,y).render(x,y,screen);
            }
        }
    }
    public Tile getTile(int x, int y){
        if(x<0||y<0||x>=width||y>=height){
            return Tile.voidTile;
        }
        if (tiles[x+y*width]== 0)return Tile.grass;
        return Tile.voidTile;
    }
}
// this is the god damn superclass for the level