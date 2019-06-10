package com.games.rain.graphics;

public class Sprite {
      public final int SIZE;
      private  int x,y;
      public int[] pixels;
      private SpriteSheet sheet;

      public static Sprite grass = new Sprite(16,0,0,SpriteSheet.tiles);//crating the grass sprite
      public static Sprite voidsprite = new Sprite(16,0x339BF5);

      public Sprite(int size,int x, int y, SpriteSheet sheet){
          SIZE = size;
          pixels = new int[SIZE*SIZE];
          this.x = x*size;
          this.y = y*size;
          this.sheet = sheet;
          load();
      }
      public Sprite(int size, int color){
          SIZE = size;
          pixels = new int[SIZE*SIZE];
          setColor(color);
      }

    private void setColor(int color){
        for (int i = 0; i < SIZE*SIZE; i++) {
            pixels[i] = color;
        }
    }

    private void load(){
          for (int y= 0; y <SIZE ; y++) {
              for (int x = 0; x < SIZE; x++) {
                  pixels[x+y*SIZE] = sheet.pixels[(x+this.x) + (y+this.y)*sheet.SIZE];
              }
          }
      }
}
