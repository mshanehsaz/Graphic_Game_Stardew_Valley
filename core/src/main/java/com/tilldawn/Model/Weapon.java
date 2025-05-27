package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.enums.Weapons;

public class Weapon {
    private final Weapons weaponType;
    private int ammo;
    private final Texture smgTexture;
    private final Sprite smgSprite;

    public Weapon(){
        weaponType = GameApp.getInstance().getMainPlayer().getWeaponType();
        ammo = weaponType.getAmmoMax();
        smgTexture = new Texture(weaponType.getSource());
        smgSprite = new Sprite(smgTexture);
        smgSprite.setX(GameApp.getInstance().getMainPlayer().getPlayerSprite().getX());
        smgSprite.setY(GameApp.getInstance().getMainPlayer().getPlayerSprite().getY());
        smgSprite.setSize(50,50);
    }

    public Sprite getSmgSprite() {
        return smgSprite;
    }

    public Weapons getWeaponType() {
        return weaponType;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAmmo() {
        return ammo;
    }
}
