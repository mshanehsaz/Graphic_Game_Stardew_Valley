package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.enums.KeyboardController;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    private final Weapon weapon;
    private final ArrayList<Bullet> bullets = new ArrayList<>();

    private boolean isReloading = false;
    private float reloadTimer = 0;

    public WeaponController(Weapon weapon) {
        this.weapon = weapon;
    }

    public void update(float delta) {
        updateWeaponPosition();
        updateReload(delta);
        updateBullets();
    }

    private void updateWeaponPosition() {
        Sprite weaponSprite = weapon.getSmgSprite();
        Sprite playerSprite = GameApp.getInstance().getMainPlayer().getPlayerSprite();

        weaponSprite.setPosition(playerSprite.getX(), playerSprite.getY());
        weaponSprite.draw(Main.getBatch());
    }

    private void updateReload(float delta) {
        if (isReloading) {
            reloadTimer += delta;
            if (reloadTimer >= weapon.getWeaponType().getTimeReload()) {
                isReloading = false;
                weapon.setAmmo(weapon.getWeaponType().getAmmoMax());
                SoundManager.getInstance().playReload();
            }
        }
    }

    public void handleWeaponShoot(int x, int y) {
        if (isReloading) return;
        int projectileCount = weapon.getWeaponType().getProjectile();
        int availableAmmo = weapon.getAmmo();
        int bulletsToShoot = Math.min(projectileCount, availableAmmo);

        int spacing = 2;

        float startX = weapon.getSmgSprite().getX() + weapon.getSmgSprite().getWidth() / 2;
        float startY = weapon.getSmgSprite().getY() + weapon.getSmgSprite().getHeight() / 2;

        for (int i = 0; i < bulletsToShoot; i++) {
            int offset = (i - projectileCount / 2) * spacing;

            if (projectileCount % 2 == 0 && i >= projectileCount / 2) {
                offset += spacing;
            }
            if (weapon.getAmmo() > 0) {
                SoundManager.getInstance().playShot();
                bullets.add(new Bullet(startX, startY + offset, x, y));
                weapon.setAmmo(weapon.getAmmo() - 1);

                if (weapon.getAmmo() == 0 && GameApp.getInstance().getMainPlayer().isAutoReload()) {
                    startReloading();
                }
            } else {
                if (GameApp.getInstance().getMainPlayer().isAutoReload()) {
                    startReloading();
                }
                SoundManager.getInstance().playNonShot();
            }
        }

    }

    public void handleWeaponRotation(float x, float y) {
        Sprite weaponSprite = weapon.getSmgSprite();
        Player player = GameApp.getGame().getMainPlayer();
        float weaponCenterX = player.getPlayerSprite().getX() + weaponSprite.getWidth() / 2;
        float weaponCenterY = player.getPlayerSprite().getY() + weaponSprite.getHeight() / 2;

        float angleRad = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);
        float angleDeg = angleRad * MathUtils.radiansToDegrees;

        weaponSprite.setRotation(angleDeg);
    }


    public void updateBullets() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(KeyboardController.Reload.getaChar()))) {
            if (!isReloading && weapon.getAmmo() < weapon.getWeaponType().getAmmoMax()) {
                startReloading();
            }
        }

        for (Bullet b : bullets) {
            b.getPosition().add(b.getVelocity());
            b.getSprite().setPosition(b.getPosition().x, b.getPosition().y);
            b.getSprite().draw(Main.getBatch());


        }
    }

    private void startReloading() {
        isReloading = true;
        reloadTimer = 0;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
