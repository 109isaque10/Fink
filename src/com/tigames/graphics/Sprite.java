package com.tigames.graphics;

import com.tigames.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {
    private BufferedImage SPRITE_SHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 16;
    public int w, h;
    private int wSprite, hSprite;
    public Sprite(String file){
        w = TILE_SIZE;
        h = TILE_SIZE;
        System.out.println("Loading: " + file + "...");
        SPRITE_SHEET = loadSprite(file);
        wSprite = SPRITE_SHEET.getWidth() / w;
        hSprite = SPRITE_SHEET.getHeight() / h;
        loadSpriteArray();
    }
    public Sprite(String file, int w, int h){
        this.w = w;
        this.h = h;
        System.out.println("Loading: " + file + "...");
        SPRITE_SHEET = loadSprite(file);
        wSprite = SPRITE_SHEET.getWidth() / w;
        hSprite = SPRITE_SHEET.getHeight() / h;
        loadSpriteArray();
    }
    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }
    public void setWidth(int i){
        w = i;
        wSprite = SPRITE_SHEET.getWidth() / w;
    }
    public void setHeight(int i){
        h = i;
        hSprite = SPRITE_SHEET.getHeight() / h;
    }
    public int getWidth() {return w;}
    public int getHeight() {return h;}
    private BufferedImage loadSprite(String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        }catch (Exception e){
            System.out.println("Error loading sprite: " + file);
        }
        return sprite;
    }
    public void loadSpriteArray(){
        spriteArray = new BufferedImage[hSprite][wSprite];
        for(int y = 0; y < hSprite; y++){
            for(int x = 0; x < wSprite; x++){
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }
    public BufferedImage getSpriteSheet() {return SPRITE_SHEET;}
    public BufferedImage getSprite(int x, int y) { return SPRITE_SHEET.getSubimage(x * w, y * h, w, h);}
    public BufferedImage[] getSpriteArray(int i){return spriteArray[i];}
    public BufferedImage[][] getSpriteArray2(int i){return spriteArray;}
    public void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffSet, int yOffSet){
        float x = pos.x;
        float y = pos.y;
        for(int i = 0; i < img.size(); i++){
            if(img.get(i) != null){
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
            }
            x += xOffSet;
            y += yOffSet;
        }
    }
    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffSet, int yOffSet){
        float x = pos.x;
        float y = pos.y;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) != 32){
                g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            }
            x += xOffSet;
            y += yOffSet;
        }
    }
}
