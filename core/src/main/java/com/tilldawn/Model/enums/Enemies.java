package com.tilldawn.Model.enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.GameAssetManager;

public enum Enemies {
    Tree(GameAssetManager.getGameAssetManager().getTreeTex0(),
        GameAssetManager.getGameAssetManager().getTree(), 15, 0, 0),
    EyeBat(GameAssetManager.getGameAssetManager().getEyeBatTex0(),
        GameAssetManager.getGameAssetManager().getEyeBat(), 25, 3, 100),
    Tentacle(GameAssetManager.getGameAssetManager().getTentacleTex0(),
        GameAssetManager.getGameAssetManager().getTentacle(), 50, 10, 80),
    Elder(GameAssetManager.getGameAssetManager().getElderTex(),
        GameAssetManager.getGameAssetManager().getElder(), 400, 0, 70);

    private final Texture initialTexture;
    private final Animation<TextureRegion> animation;
    private final int HP;
    private final int baseRespawnTime;
    private final int speed;

    Enemies(Texture tex, Animation<TextureRegion> anim, int hp, int respawn, int speed) {
        this.initialTexture = tex;
        this.animation = anim;
        this.HP = hp;
        this.baseRespawnTime = respawn;
        this.speed = speed;
    }

    public Texture getInitialTexture() {
        return initialTexture;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public int getHP() {
        return HP;
    }

    public int getBaseRespawnTime() {
        return baseRespawnTime;
    }

    public int getSpeed() {
        return speed;
    }
}

