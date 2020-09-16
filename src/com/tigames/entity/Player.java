package com.tigames.entity;

import com.tigames.graphics.Sprite;
import com.tigames.util.KeyHandler;
import com.tigames.util.MouseHandler;
import com.tigames.util.Vector2f;

import java.awt.*;

public class Player extends Entity {
    public Player(Sprite sprite, Vector2f   orgin, int size) { super(sprite, orgin, size); }
    public void move() {
        if(left){
            dx -= acc;
            if(dx < -maxSpeed)
                dx = -maxSpeed;
        }else{
            if(dx < 0) {
                dx += dec;
                if(dx > 0)
                    dx = 0;
            }
        }
        if(right){
            dx += acc;
            if(dx > maxSpeed)
                dx = maxSpeed;
        }else{
            if(dx > 0) {
                dx -= dec;
                if(dx < 0)
                    dx = 0;
            }
        }
    }
    public void update(){
        super.update();
        move();
        pos.x += dx;
    }
    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImg(),(int) (pos.x), (int) (pos.y), size, size, null);
    }
    public void input(MouseHandler mouse, KeyHandler key) {
        if (mouse.getButton() == 1) {
            System.out.println("Player: " + pos.x + ", " + pos.y);
        }
        if (key.left.down) {
            left = true;
        } else {
            left = false;
        }
        if (key.right.down) {
            right = true;
        } else {
            right = false;
        }
        if (key.atk.down) {
            atk = true;
        } else {
            atk = false;
        }
        if(!key.left.down && !key.right.down && !key.atk.down){
            still = true;
        }else{
            still = false;
        }
    }
}
