package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.tilldawn.Control.WeaponController;
import com.tilldawn.Model.enums.Hero;
import com.tilldawn.Model.enums.KeyboardController;
import com.tilldawn.Model.enums.Weapons;

import java.util.ArrayList;

public class Player {
    private final UserInfo userInfo;
    private Hero hero = Hero.Dasher;
    private Weapons weaponType = Weapons.Revolver;
    private Texture playerTexture = hero.getInitialTexture();
    private Sprite playerSprite = new Sprite(playerTexture);
    private final float speed = hero.getSpeed();
    private float playerHealth = hero.getHP();
    private float timeAnim;
    private float time = 2;
    private boolean autoReload = false;
    private int timeToGo = 0;


    public float getSpeed() {
        return speed;
    }
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public Player(UserInfo userInfo){
        this.userInfo = userInfo;
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
    }

    public Rectangle getHitBox() {
        return new Rectangle(
            playerSprite.getX(), playerSprite.getY(),
            playerSprite.getWidth(), playerSprite.getHeight()
        );
    }

    public void getDamage(){
        playerHealth -= 1;
    }

    public boolean isDied(){
        return playerHealth <= 0;
    }


    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public float getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(float playerHealth) {
        this.playerHealth = playerHealth;
    }

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Weapons getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapons weaponType) {
        this.weaponType = weaponType;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public int getTimeToGo() {
        return timeToGo;
    }

    public void setTimeToGo(int timeToGo) {
        this.timeToGo = timeToGo;
    }

    public float getTimeAnim() {
        return timeAnim;
    }

    public void setTimeAnim(float timeAnim) {
        this.timeAnim = timeAnim;
    }
}
