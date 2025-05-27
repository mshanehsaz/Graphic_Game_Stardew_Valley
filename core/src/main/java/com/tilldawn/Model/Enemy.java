package com.tilldawn.Model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.tilldawn.Model.enums.Enemies;

public class Enemy {
    private final Enemies enemyType;
    private final Sprite sprite;
    private float stateTime = 0f;
    private int currentHP;

    public Enemy(Enemies enemyType, float x, float y) {
        this.enemyType = enemyType;
        currentHP = enemyType.getHP();
        this.sprite = new Sprite(enemyType.getInitialTexture());
        this.sprite.setPosition(x, y);
        this.sprite.setSize(100, 120);
    }

    public void takeDamage(int amount) {

        currentHP -= amount;
    }

    public boolean isDead() {
        return currentHP <= 0;
    }

    public void update(float delta, int speed) {
        float px = GameApp.getInstance().getMainPlayer().getPlayerSprite().getX();
        float py = GameApp.getInstance().getMainPlayer().getPlayerSprite().getY();

        float dx = px - sprite.getX();
        float dy = py - sprite.getY();
        float dist = (float) Math.sqrt(dx * dx + dy * dy);

        if (dist > 5f) {
            float vx = (dx / dist) * speed * delta;
            float vy = (dy / dist) * speed * delta;
            sprite.translate(vx, vy);
        }

        stateTime += delta;
    }

    public Rectangle getHitBox() {
        return new Rectangle(
            sprite.getX(), sprite.getY(),
            sprite.getWidth(), sprite.getHeight()
        );
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    public Enemies getEnemyType() {
        return enemyType;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }
}
