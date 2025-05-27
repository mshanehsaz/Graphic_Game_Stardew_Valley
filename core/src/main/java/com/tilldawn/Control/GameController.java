package com.tilldawn.Control;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tilldawn.Model.Enemy;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.Weapon;
import com.tilldawn.Model.enums.Enemies;
import com.tilldawn.View.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private EnemyController enemyController;
    private Stage stage;

    public void setView(GameView view) {
        this.view = view;
        this.stage = new Stage();
        playerController = new PlayerController(GameApp.getInstance().getMainPlayer());
        worldController = new WorldController(playerController);
        weaponController = new WeaponController(new Weapon());
        enemyController = new EnemyController(new Enemy(Enemies.Tree, 60, 60));
    }

    public void updateGame(float delta) {
        if (view != null) {
            worldController.update();
            playerController.update();
            weaponController.update(delta);
            enemyController.update(delta);
            enemyController.checkBulletCollision(weaponController.getBullets(), weaponController.getWeapon());
            if (enemyController.checkPlayerCollision()) view.showResultLoseGame();
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }


}

