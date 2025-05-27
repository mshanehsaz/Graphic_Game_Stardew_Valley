package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    //Dasher
    private final String dasher0 = "Images_grouped_1/Heros/Dasher/idle/Idle_0 #8325.png";
    private final String dasher1 = "Images_grouped_1/Heros/Dasher/idle/Idle_1 #8355.png";
    private final String dasher2 = "Images_grouped_1/Heros/Dasher/idle/Idle_2 #8814.png";
    private final String dasher3 = "Images_grouped_1/Heros/Dasher/idle/Idle_3 #8452.png";
    private final String dasher4 = "Images_grouped_1/Heros/Dasher/idle/Idle_4 #8313.png";
    private final String dasher5 = "Images_grouped_1/Heros/Dasher/idle/Idle_5 #8302.png";
    private final Texture dasherTex0 = new Texture(dasher0);
    private final Texture dasherTex1 = new Texture(dasher1);
    private final Texture dasherTex2 = new Texture(dasher2);
    private final Texture dasherTex3 = new Texture(dasher3);
    private final Texture dasherTex4 = new Texture(dasher4);
    private final Texture dasherTex5 = new Texture(dasher5);
    private final Animation<Texture> dasher = new Animation<>(0.1f, dasherTex0,
        dasherTex1,
        dasherTex2,
        dasherTex3,
        dasherTex4,
        dasherTex5);

    //Diamond
    private final String diamond0 = "Images_grouped_1/Heros/Diamond/idle/Idle_0 #8328.png";
    private final String diamond1 = "Images_grouped_1/Heros/Diamond/idle/Idle_1 #8358.png";
    private final String diamond2 = "Images_grouped_1/Heros/Diamond/idle/Idle_2 #8817.png";
    private final String diamond3 = "Images_grouped_1/Heros/Diamond/idle/Idle_3 #8455.png";
    private final String diamond4 = "Images_grouped_1/Heros/Diamond/idle/Idle_4 #8316.png";
    private final String diamond5 = "Images_grouped_1/Heros/Diamond/idle/Idle_5 #8305.png";
    private final Texture diamondTex0 = new Texture(diamond0);
    private final Texture diamondTex1 = new Texture(diamond1);
    private final Texture diamondTex2 = new Texture(diamond2);
    private final Texture diamondTex3 = new Texture(diamond3);
    private final Texture diamondTex4 = new Texture(diamond4);
    private final Texture diamondTex5 = new Texture(diamond5);
    private final Animation<Texture> diamond = new Animation<>(0.1f, diamondTex0,
        diamondTex1,
        diamondTex2,
        diamondTex3,
        diamondTex4,
        diamondTex5);

    //lilith
    private final String lilith0 = "Images_grouped_1/Heros/Lilith/idle/Idle_0 #8333.png";
    private final String lilith1 = "Images_grouped_1/Heros/Lilith/idle/Idle_1 #8363.png";
    private final String lilith2 = "Images_grouped_1/Heros/Lilith/idle/Idle_2 #8822.png";
    private final String lilith3 = "Images_grouped_1/Heros/Lilith/idle/Idle_3 #8460.png";
    private final String lilith4 = "Images_grouped_1/Heros/Lilith/idle/Idle_4 #8321.png";
    private final String lilith5 = "Images_grouped_1/Heros/Lilith/idle/Idle_5 #8310.png";
    private final Texture lilithTex0 = new Texture(lilith0);
    private final Texture lilithTex1 = new Texture(lilith1);
    private final Texture lilithTex2 = new Texture(lilith2);
    private final Texture lilithTex3 = new Texture(lilith3);
    private final Texture lilithTex4 = new Texture(lilith4);
    private final Texture lilithTex5 = new Texture(lilith5);
    private final Animation<Texture> lilith = new Animation<>(0.1f, lilithTex0,
        lilithTex1,
        lilithTex2,
        lilithTex3,
        lilithTex4,
        lilithTex5);

    //Scarlet
    private final String scarlet0 = "Images_grouped_1/Heros/Scarlet/idle/Idle_0 #8327.png";
    private final String scarlet1 = "Images_grouped_1/Heros/Scarlet/idle/Idle_1 #8357.png";
    private final String scarlet2 = "Images_grouped_1/Heros/Scarlet/idle/Idle_2 #8816.png";
    private final String scarlet3 = "Images_grouped_1/Heros/Scarlet/idle/Idle_3 #8454.png";
    private final String scarlet4 = "Images_grouped_1/Heros/Scarlet/idle/Idle_4 #8315.png";
    private final String scarlet5 = "Images_grouped_1/Heros/Scarlet/idle/Idle_5 #8304.png";
    private final Texture scarletTex0 = new Texture(scarlet0);
    private final Texture scarletTex1 = new Texture(scarlet1);
    private final Texture scarletTex2 = new Texture(scarlet2);
    private final Texture scarletTex3 = new Texture(scarlet3);
    private final Texture scarletTex4 = new Texture(scarlet4);
    private final Texture scarletTex5 = new Texture(scarlet5);
    private final Animation<Texture> scarlet = new Animation<>(0.1f, scarletTex0,
        scarletTex1,
        scarletTex2,
        scarletTex3,
        scarletTex4,
        scarletTex5);

    //shana
    private final String shana0 = "Images_grouped_1/Heros/Shana/idle/Idle_0 #8330.png";
    private final String shana1 = "Images_grouped_1/Heros/Shana/idle/Idle_1 #8360.png";
    private final String shana2 = "Images_grouped_1/Heros/Shana/idle/Idle_2 #8819.png";
    private final String shana3 = "Images_grouped_1/Heros/Shana/idle/Idle_3 #8457.png";
    private final String shana4 = "Images_grouped_1/Heros/Shana/idle/Idle_4 #8318.png";
    private final String shana5 = "Images_grouped_1/Heros/Shana/idle/Idle_5 #8307.png";
    private final Texture shanaTex0 = new Texture(shana0);
    private final Texture shanaTex1 = new Texture(shana1);
    private final Texture shanaTex2 = new Texture(shana2);
    private final Texture shanaTex3 = new Texture(shana3);
    private final Texture shanaTex4 = new Texture(shana4);
    private final Texture shanaTex5 = new Texture(shana5);
    private final Animation<Texture> shana = new Animation<>(0.1f, shanaTex0,
        shanaTex1,
        shanaTex2,
        shanaTex3,
        shanaTex4,
        shanaTex5);

    // Tree
    private final String tree0 = "Images_grouped_1/Enemies/Tree/T_TreeMonster_0.png";
    private final String tree1 = "Images_grouped_1/Enemies/Tree/T_TreeMonster_1.png";
    private final String tree2 = "Images_grouped_1/Enemies/Tree/T_TreeMonster_2.png";
    private final Texture treeTex0 = new Texture(tree0);
    private final Texture treeTex1 = new Texture(tree1);
    private final Texture treeTex2 = new Texture(tree2);
    private final Animation<TextureRegion> tree = new Animation<>(0.4f, new TextureRegion(treeTex0),
        new TextureRegion(treeTex1),
        new TextureRegion(treeTex2));

    // Tentacle
    private final String tentacle0 = "Images_grouped_1/Enemies/TentacleIdle/TentacleIdle0.png";
    private final String tentacle1 = "Images_grouped_1/Enemies/TentacleIdle/TentacleIdle1.png";
    private final String tentacle2 = "Images_grouped_1/Enemies/TentacleIdle/TentacleIdle2.png";
    private final String tentacle3 = "Images_grouped_1/Enemies/TentacleIdle/TentacleIdle3.png";
    private final Texture tentacleTex0 = new Texture(tentacle0);
    private final Texture tentacleTex1 = new Texture(tentacle1);
    private final Texture tentacleTex2 = new Texture(tentacle2);
    private final Texture tentacleTex3 = new Texture(tentacle3);
    private final Animation<TextureRegion> tentacle = new Animation<>(0.4f,
        new TextureRegion(tentacleTex0),
        new TextureRegion(tentacleTex1),
        new TextureRegion(tentacleTex2),
        new TextureRegion(tentacleTex3));

    // EyeBat
    private final String eyeBat0 = "Images_grouped_1/Enemies/Eyebat/T_EyeBat_0.png";
    private final String eyeBat1 = "Images_grouped_1/Enemies/Eyebat/T_EyeBat_1.png";
    private final String eyeBat2 = "Images_grouped_1/Enemies/Eyebat/T_EyeBat_2.png";
    private final String eyeBat3 = "Images_grouped_1/Enemies/Eyebat/T_EyeBat_3.png";
    private final Texture eyeBatTex0 = new Texture(eyeBat0);
    private final Texture eyeBatTex1 = new Texture(eyeBat1);
    private final Texture eyeBatTex2 = new Texture(eyeBat2);
    private final Texture eyeBatTex3 = new Texture(eyeBat3);
    private final Animation<TextureRegion> eyeBat = new Animation<>(0.4f,
        new TextureRegion(eyeBatTex0),
        new TextureRegion(eyeBatTex1),
        new TextureRegion(eyeBatTex2),
        new TextureRegion(eyeBatTex3));

    // Elder
    private final String elder0 = "Images_grouped_1/Enemies/Elder/ElderBrain.png";
    private final Texture elderTex = new Texture(elder0);
    private final Animation<TextureRegion> elder = new Animation<>(0.4f, new TextureRegion(elderTex));


    private GameAssetManager(){

    }

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Texture getDasherTex0() {
        return dasherTex0;
    }

    public Animation<Texture> getDasher() {
        return dasher;
    }

    public Texture getDiamondTex0() {
        return diamondTex0;
    }

    public Animation<Texture> getDiamond() {
        return diamond;
    }

    public Texture getLilithTex0() {
        return lilithTex0;
    }

    public Animation<Texture> getLilith() {
        return lilith;
    }

    public Texture getScarletTex0() {
        return scarletTex0;
    }

    public Animation<Texture> getScarlet() {
        return scarlet;
    }

    public Texture getShanaTex0() {
        return shanaTex0;
    }

    public Animation<Texture> getShana() {
        return shana;
    }

    public String getBullet(){
        return "Images_grouped_1/Sprite/ArmedAndReady/ArmedAndReady.png";
    }

    public Texture getDasherTex1() {
        return dasherTex1;
    }

    public Texture getTreeTex0() {
        return treeTex0;
    }

    public Animation<TextureRegion> getTree() {
        return tree;
    }

    public Texture getTentacleTex0() {
        return tentacleTex0;
    }

    public Animation<TextureRegion> getTentacle() {
        return tentacle;
    }

    public Texture getEyeBatTex0() {
        return eyeBatTex0;
    }

    public Animation<TextureRegion> getEyeBat() {
        return eyeBat;
    }

    public Texture getElderTex() {
        return elderTex;
    }

    public Animation<TextureRegion> getElder() {
        return elder;
    }

    public String getEyeBatBullet() {
        return "Images_grouped_1/Enemies/Eyebat/T_EyeBat_EM.png";
    }
}
