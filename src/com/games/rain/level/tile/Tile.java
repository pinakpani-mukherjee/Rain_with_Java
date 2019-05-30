package com.games.rain.level.tile;

import com.games.rain.graphics.Screen;
import com.games.rain.graphics.Sprite;

public class Tile {
    public int x,y;
    public Sprite sprite;
    public static Tile grass = new GrassTile(Sprite.grass);
    public Tile(Sprite sprite){
        this.sprite = sprite;
    }
    public void render(int x, int y, Screen screen){
    }
    public Boolean solid(){
        return false;
    }
}
