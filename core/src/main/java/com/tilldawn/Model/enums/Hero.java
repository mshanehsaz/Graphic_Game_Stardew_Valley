package com.tilldawn.Model.enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldawn.Model.GameAssetManager;

public enum Hero {
    Dasher("Images_grouped_1/Sprite/T/T_Dasher_Portrait.png", 3, 10,
        GameAssetManager.getGameAssetManager().getDasher(), GameAssetManager.getGameAssetManager().getDasherTex0()),
    Diamond("Images_grouped_1/Sprite/T/T_Diamond_Portrait.png", 7, 1,
        GameAssetManager.getGameAssetManager().getDiamond(), GameAssetManager.getGameAssetManager().getDiamondTex0()),
    Lilith("Images_grouped_1/Sprite/T/T_Lilith_Portrait.png", 5, 3,
        GameAssetManager.getGameAssetManager().getLilith(), GameAssetManager.getGameAssetManager().getLilithTex0()),
    Scarlett("Images_grouped_1/Sprite/T/T_Scarlett_Portrait.png", 3, 5,
        GameAssetManager.getGameAssetManager().getScarlet(), GameAssetManager.getGameAssetManager().getScarletTex0()),
    Shana("Images_grouped_1/Sprite/T/T_Shana_Portrait.png", 4, 4,
        GameAssetManager.getGameAssetManager().getShana(), GameAssetManager.getGameAssetManager().getScarletTex0());

    private final Texture initialTexture;
    private final Animation<Texture> animation;
    private final String source;
    private final int HP;
    private final int speed;

    Hero(String source, int HP, int speed, Animation<Texture> animation, Texture initialTexture) {
        this.source = source;
        this.HP = HP;
        this.speed = speed;
        this.animation = animation;
        this.initialTexture = initialTexture;
    }

    public String getSource() {
        return source;
    }

    public int getHP() {
        return HP;
    }

    public int getSpeed() {
        return speed;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public Texture getInitialTexture() {
        return initialTexture;
    }
}
