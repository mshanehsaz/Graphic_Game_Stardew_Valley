package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.enums.Enemies;
import com.tilldawn.View.GameView;

import java.util.ArrayList;
import java.util.Random;

public class EnemyController {

    private final ArrayList<Enemy> enemies = new ArrayList<>();
    boolean treeOn = false;


    public EnemyController(Enemy enemy) {
        enemies.add(enemy);
    }

    public void spawnEnemy(Enemies type, float x, float y, float sizeX, float sizeY) {
        Enemy enemy = new Enemy(type, x, y);
        enemy.getSprite().setSize(sizeX, sizeY);
        enemies.add(enemy);
    }

    private int lastSpawnTimeTree = -1;
    private int lastSpawnTimeTentacle = -1;
    private int lastSpawnTimeEyeBat = -1;
    private boolean lastSpawnTimeElder = false;

    private float speedTimer = 0f;
    private boolean isBoosted = false;

    public void update(Float delta) {
        Random random = new Random();
        Player player = GameApp.getInstance().getMainPlayer();
        int currentTime = player.getTimeToGo();

        for (Enemy enemy : enemies) {
            if (enemy.getEnemyType() == Enemies.Elder) {
                int baseSpeed = enemy.getEnemyType().getSpeed();
                int speed = baseSpeed;

                speedTimer += delta;

                if (!isBoosted && speedTimer >= 4f) {
                    isBoosted = true;
                    speedTimer = 0f;
                } else if (isBoosted && speedTimer >= 2f) {
                    isBoosted = false;
                    speedTimer = 0f;
                }

                if (isBoosted) {
                    speed = baseSpeed * 5;
                }

                enemy.update(delta, speed);
                enemy.getSprite().draw(Main.getBatch());
                idleAnimation(enemy);

                continue;
            }

            enemy.update(delta, enemy.getEnemyType().getSpeed());
            enemy.getSprite().draw(Main.getBatch());
            idleAnimation(enemy);
        }

        for (Enemies enemy : Enemies.values()) {
            switch (enemy) {

                case Tree: {
                    if (treeOn) break;
                    if (currentTime == 2 && lastSpawnTimeTree != currentTime) {
                        for (int i = 0; i < 8; i++) {
                            int randX = random.nextInt(Gdx.graphics.getWidth());
                            int randY = random.nextInt(Gdx.graphics.getHeight());
                            spawnEnemy(enemy, randX, randY, 80, 110);
                        }
                        treeOn = true;
                        lastSpawnTimeTree = currentTime;
                    }
                    break;
                }

                case Tentacle: {
                    if (currentTime % 3 == 0 && lastSpawnTimeTentacle != currentTime) {
                        for (int i = 0; i < currentTime / 30; i++) {
                            int randX = random.nextInt(Gdx.graphics.getWidth());
                            int randY = random.nextInt(Gdx.graphics.getHeight());
                            spawnEnemy(enemy, randX, randY, 60, 100);
                        }
                        lastSpawnTimeTentacle = currentTime;
                    }
                    break;
                }

                case EyeBat: {
                    int spawnStart = (int) (player.getTime() * 60 / 4);
                    if (currentTime >= spawnStart &&
                        currentTime % 10 == 0 &&
                        lastSpawnTimeEyeBat != currentTime) {

                        int spawnCount = (int) (((4 * currentTime) - player.getTime() + 30) / 30);
                        for (int i = 0; i < spawnCount; i++) {
                            int randX = random.nextInt(Gdx.graphics.getWidth());
                            int randY = random.nextInt(Gdx.graphics.getHeight());
                            spawnEnemy(enemy, randX, randY, 80, 70);
                        }
                        lastSpawnTimeEyeBat = currentTime;
                    }
                    break;
                }

                case Elder:
                    if (currentTime >= (player.getTime() * 60 / 2) && !lastSpawnTimeElder) {
                        int randX = random.nextInt(Gdx.graphics.getWidth());
                        int randY = random.nextInt(Gdx.graphics.getHeight());
                        spawnEnemy(enemy, randX, randY, 150, 130);
                        lastSpawnTimeElder = true;
                    }
                    break;
            }
        }
    }

    public void idleAnimation(Enemy enemy) {
        Animation<TextureRegion> animation = enemy.getEnemyType().getAnimation();

        enemy.getSprite().setRegion(animation.getKeyFrame(enemy.getStateTime()));

        if (!animation.isAnimationFinished(enemy.getStateTime())) {
            enemy.setStateTime(enemy.getStateTime() + Gdx.graphics.getDeltaTime());
        } else {
            enemy.setStateTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void checkBulletCollision(ArrayList<Bullet> bullets, Weapon weapon) {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();

        for (Bullet bullet : bullets) {
            for (Enemy enemy : enemies) {
                if (bullet.getHitBox().overlaps(enemy.getHitBox())) {
                    enemy.takeDamage(weapon.getWeaponType().getDamage());
                    bulletsToRemove.add(bullet);

                    if (enemy.isDead()) {
                        GameView.getInstance().addKill();
                        onEnemyKilled(enemy);
                    }
                    break;
                }
            }
        }

        bullets.removeAll(bulletsToRemove);
    }

    public void draw(Batch batch) {
        for (Enemy enemy : enemies) {
            enemy.draw(batch);
        }
    }

    public void onEnemyKilled(Enemy enemy) {

        enemies.remove(enemy);

    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }


    float lastPlayerCollisionTime = -2f;

    public boolean checkPlayerCollision() {
        Player player = GameApp.getGame().getMainPlayer();
        float currentTime = player.getTimeToGo();
        if (currentTime - lastPlayerCollisionTime < 2f) {
            return false;
        }

        for (Enemy enemy : enemies) {
            if (player.getHitBox().overlaps(enemy.getHitBox())) {
                SoundManager.getInstance().playDamage();
                player.getDamage();

                lastPlayerCollisionTime = currentTime;

                if (player.isDied()) {
                    SoundManager.getInstance().playLose();
                    GameView.getInstance().setGameEnded(true);
                    return true;
                }
                break;
            }
        }
        return false;
    }
}


