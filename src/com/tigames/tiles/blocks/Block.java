package com.tigames.tiles.blocks;

import com.tigames.util.AABB;
import com.tigames.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    protected int w, h;
    protected BufferedImage img;
    protected Vector2f pos;
    public Block(BufferedImage img, Vector2f pos, int w, int h){
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }
    public abstract boolean update(AABB p);
    public void render(Graphics2D g){
        g.drawImage(img, (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h, null);
    }
}
