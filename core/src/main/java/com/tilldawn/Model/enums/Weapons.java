package com.tilldawn.Model.enums;

public enum Weapons {
    Revolver("Images_grouped_1/Sprite/Guns/RevolverStill.png", 20, 1, 1, 15),
    Shotgun("Images_grouped_1/Sprite/Guns/T_Shotgun_SS_0.png", 10, 4, 1, 10),
    SMGsDual("Images_grouped_1/Sprite/Guns/SMGStill.png", 8, 1, 2, 36);

    private final String source;
    private final int damage;
    private final int projectile;
    private final int timeReload;
    private final int ammoMax;

    Weapons(String source, int damage, int projectile, int timeReload, int ammoMax) {
        this.source = source;
        this.damage = damage;
        this.projectile = projectile;
        this.timeReload = timeReload;
        this.ammoMax = ammoMax;
    }

    public String getSource() {
        return source;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getTimeReload() {
        return timeReload;
    }

    public int getAmmoMax() {
        return ammoMax;
    }
}
