package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ProfileMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.UserInfo;

public class InfoMenuView implements Screen {
    private final Stage stage;
    private final Table table;
    private final Skin skin;

    public InfoMenuView(Skin skin) {
        this.skin = skin;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        table.center().pad(30);

        buildUI();
        stage.addActor(table);
    }

    private void buildUI() {
        UserInfo u = GameApp.getInstance().getMainPlayer().getUserInfo();

        Image avatar = new Image(new Texture(u.getAvatar().getSource()));
        avatar.setSize(150, 150);
        table.add(avatar).colspan(2).center().padBottom(30);
        table.row();

        table.add(new Label("Username:", skin)).left().pad(5);
        table.add(new Label(u.getName(), skin)).left().pad(5);
        table.row().pad(15);

        table.add(new Label("Score:", skin)).left().pad(5);
        table.add(new Label(String.valueOf(u.getScore()), skin)).left().pad(5);
        table.row().pad(15);

        table.add(new Label("Kills:", skin)).left().pad(5);
        table.add(new Label(String.valueOf(u.getKill()), skin)).left().pad(5);
        table.row().pad(15);

        table.add(new Label("Time Alive:", skin)).left().pad(5);
        table.add(new Label(String.valueOf(u.getTimeAlive()), skin)).left().pad(5);
        table.row().pad(15);

        table.add(new Label("Age:", skin)).left().pad(5);
        table.add(new Label(String.valueOf(u.getAge()), skin)).left().pad(5);
        table.row().padTop(30);

        TextButton backBtn = new TextButton("Back", skin);
        table.add(backBtn).colspan(2).width(300).height(100);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
}
