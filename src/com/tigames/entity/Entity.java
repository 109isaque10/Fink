package com.tigames.entity;

import com.tigames.graphics.Animation;
import com.tigames.graphics.Sprite;
import com.tigames.util.AABB;
import com.tigames.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private final int STILL = 0;
    private final int RIGHT = 2;
    private final int LEFT = 1;
    protected Animation ani;
    protected Sprite sprite;
    protected  Vector2f pos;
    protected int size, currentAnimation, atkSpeed, atkDuration;
    protected boolean right, left, atk, still;
    protected float dx, dy, maxSpeed = 3f, acc = 2.5f, dec = 0.2f;
    protected AABB hitBounds, bounds;
    public Entity(Sprite sprite, Vector2f orgin, int size){
        this.sprite = sprite;
        pos = orgin;
        this.size = size;
        bounds = new AABB(orgin, size, size);
        hitBounds = new AABB(new Vector2f(orgin.x + (size / 2), orgin.y), size, size);
        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }
    public void setSprite(Sprite sprite){this.sprite = sprite;}
    public void setSize(int i){size = i;}
    public void setMaxSpeed(float f){maxSpeed = f;}
    public void setAcc(float f){acc = f;}
    public void setDec(float f){dec = f;}
    public AABB getBounds(){return bounds;}
    public int getSize(){return size;}
    public Animation getAnimation(){return ani;}
    public void setAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }
    public void animate(){
        if(still){
            if(currentAnimation != STILL || ani.getDelay() == -1){
                setAnimation(STILL, sprite.getSpriteArray(STILL), 5);
            }
        }
        else if(left){
            if(currentAnimation != LEFT || ani.getDelay() == -1){
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }
        else if(right){
            if(currentAnimation != RIGHT || ani.getDelay() == -1){
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }
        else{
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }
    private void setHitBoxDirection(){
        if(left){
            hitBounds.setyOffSet(-size);
            hitBounds.setxOffSet(0);
        }
        else if(right){
            hitBounds.setyOffSet(0);
            hitBounds.setxOffSet(0);
        }
    }
    public void update(){
        animate();
        setHitBoxDirection();
        ani.update();
    }
    public abstract void render(Graphics2D g);
}
