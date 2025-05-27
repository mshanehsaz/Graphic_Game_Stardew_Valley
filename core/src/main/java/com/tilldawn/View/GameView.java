package com.tilldawn.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.SoundManager;
import com.tilldawn.Model.enums.KeyboardController;

public class GameView implements Screen, InputProcessor {
    private static GameView gameView;

    public static GameView getInstance(){
        if (gameView == null) gameView = new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin());
        return gameView;
    }

    private final OrthographicCamera camera;
    private final GameController controller;
    private final Table topBar = new Table();

    private int kills = 0;
    private int timeToGo = 0;

    public void addKill(){
        kills ++;
    }

    private final Stage stage = new Stage(new ScreenViewport());
    private final Label timeLabel;
    private final Label ammo;
    private final ProgressBar healthBar;
    private final TextButton pause;

    private float remainingTimeInSeconds;
    private boolean gameEnded = false;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(this);

        float minutesFromUser = GameApp.getInstance().getMainPlayer().getTime();
        remainingTimeInSeconds = minutesFromUser * 60f;

        // Time Label
        timeLabel = new Label("", skin);
        timeLabel.setFontScale(2f);

        // Health bar
        float maxHealth = GameApp.getInstance().getMainPlayer().getHero().getHP();
        healthBar = new ProgressBar(0, 20, 1, false, skin, "health");
        healthBar.setValue(maxHealth);
        healthBar.setAnimateDuration(0.2f);
        healthBar.setWidth(400);
        healthBar.setHeight(25);

        // Layout with Table
        topBar.top().left();
        topBar.setFillParent(true);
        topBar.padTop(10).padLeft(20);

        topBar.add(timeLabel).left().pad(30);
        topBar.add(healthBar).width(400).height(25).left().pad(30);

        ammo = new Label("Ammo:", skin);
        ammo.setColor(Color.GOLD);
        ammo.setFontScale(1.5f);
        topBar.add(ammo);

        pause = new TextButton("||", skin);
        pause.getLabel().setFontScale(1.5f);
        topBar.add(pause);
        topBar.row().pad(5);



        stage.addActor(topBar);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        Main.getBatch().begin();

        Player player = GameApp.getInstance().getMainPlayer();
        if (!GameApp.getInstance().isGamePause() && !gameEnded) {
            remainingTimeInSeconds -= delta;
            if (remainingTimeInSeconds <= 0) {
                remainingTimeInSeconds = 0;
                gameEnded = true;
                showResultWinGame();
            }
            updateHealthBar();
            controller.updateGame(delta);
            updateTimeLabel();
            updateAmmo();
            timeToGo = Math.round(player.getTime() * 60 - remainingTimeInSeconds);
            player.setTimeToGo(Math.round(player.getTime() * 60 - remainingTimeInSeconds));
            camera.position.set(
                player.getPlayerSprite().getX(),
                player.getPlayerSprite().getY(),
                0);
            camera.update();
            Main.getBatch().setProjectionMatrix(camera.combined);
        }

        Main.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

    private void updateTimeLabel() {
        int totalSeconds = (int) remainingTimeInSeconds;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String timeStr = String.format("Time %02d:%02d", minutes, seconds);
        timeLabel.setText(timeStr);
        timeLabel.setColor(Color.CYAN);
    }

    private void updateHealthBar() {
        float currentHealth = GameApp.getInstance().getMainPlayer().getPlayerHealth();
        healthBar.setValue(currentHealth);
    }


    private void updateAmmo() {
        int ammo1 = controller.getWeaponController().getWeapon().getAmmo();
        String ammoString = String.format("Ammo: %d", ammo1);
        if (ammo1 <= 0) ammo.setColor(Color.RED);
        else ammo.setColor(Color.GOLD);
        ammo.setText(ammoString);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.valueOf(KeyboardController.Shot.getaChar())) {
            controller.getWeaponController().handleWeaponShoot(
                (int) GameApp.getInstance().getMainPlayer().getPlayerSprite().getX(),
                (int) GameApp.getInstance().getMainPlayer().getPlayerSprite().getY()
            );
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!GameApp.getGame().isGamePause() && !gameEnded) {
            Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
            controller.getWeaponController().handleWeaponShoot((int) worldCoordinates.x, (int) worldCoordinates.y);
//            controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (!GameApp.getGame().isGamePause() && !gameEnded) {
            Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
            controller.getWeaponController().handleWeaponRotation(worldCoordinates.x, worldCoordinates.y);
            return true;
        }
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public void showResultLoseGame() {
        Dialog dialog = new Dialog("You Lose!", GameAssetManager.getGameAssetManager().getSkin()) {
            @Override
            protected void result(Object object) {
                this.hide();
                if (Boolean.TRUE.equals(object)) {
                    Gdx.app.exit();
                }
            }
        };
        Player player = GameApp.getInstance().getMainPlayer();
        player.getUserInfo().setTimeAlive(player.getUserInfo().getTimeAlive() + timeToGo);
        player.getUserInfo().setKill(player.getUserInfo().getKill() + kills);

        dialog.getTitleLabel().setFontScale(1f);
        dialog.getTitleLabel().setColor(Color.RED);
        dialog.getTitleLabel().setAlignment(Align.center);
        dialog.pad(50);
        dialog.setWidth(1500);
        dialog.setHeight(1500);
        dialog.text("The Monsters Killed You\n\n" +
            "Kills: " + kills + "\n" +
            "Time Alive: " + timeToGo).center();
        dialog.button("Exit Game", true);
        dialog.getContentTable().pad(20);

        dialog.show(stage);

        Gdx.input.setInputProcessor(stage);
    }


    public void showResultWinGame() {
        SoundManager.getInstance().playWin();
        Dialog dialog = new Dialog("You Win!", GameAssetManager.getGameAssetManager().getSkin()) {
            @Override
            protected void result(Object object) {
                this.hide();
                if (Boolean.TRUE.equals(object)) {
                    Gdx.app.exit();
                }
            }
        };
        Player player = GameApp.getInstance().getMainPlayer();
        player.getUserInfo().setTimeAlive(player.getUserInfo().getTimeAlive() + timeToGo);
        player.getUserInfo().setKill(player.getUserInfo().getKill() + kills);

        dialog.getTitleLabel().setFontScale(1f);
        dialog.getTitleLabel().setColor(Color.GREEN);
        dialog.getTitleLabel().setAlignment(Align.center);
        dialog.pad(50);
        dialog.setWidth(1500);
        dialog.setHeight(1500);
        dialog.text("You Could Survive!\n\n" +
            "Kills: " + kills + "\n" +
            "Time Alive: " + timeToGo).center();
        dialog.button("Exit Game", true);
        dialog.getContentTable().pad(20);

        dialog.show(stage);

        Gdx.input.setInputProcessor(stage);
    }


}
