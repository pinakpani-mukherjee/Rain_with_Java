package com.games.rain.level;

import com.games.rain.graphics.Screen;

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
    }
}
// this is the god damn superclass for the level