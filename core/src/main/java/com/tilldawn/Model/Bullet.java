package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private final Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private final Sprite sprite = new Sprite(texture);
    private Vector2 position;
    private Vector2 velocity;
    private float speed = 10f;

    private final float x;
    private final float y;

    public Bullet(float x, float y , int targetX , int targetY){
        sprite.setSize(20 , 20);
        Vector2 direction = new Vector2(targetX - x, targetY - y).nor();
        position = new Vector2(x,y);
        velocity = direction.scl(speed);
        this.x = x;
        this.y = y;
        sprite.setPosition(x , y);
    }

    public Rectangle getHitBox() {
        return new Rectangle(
            sprite.getX(), sprite.getY(),
            sprite.getWidth(), sprite.getHeight()
        );
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

}
