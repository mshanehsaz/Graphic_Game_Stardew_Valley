package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.SoundManager;

public class MainMenuView implements Screen {
    private Stage stage;
    private final Table table;
    private final MainMenuController controller;

    // buttons
    private final TextButton preGame;
    private final TextButton setting;
    private final TextButton profile;
    private final TextButton scoreBoard;
    private final TextButton hintMenu;
    private final TextButton logout;

    public MainMenuView(MainMenuController controller, Skin skin) {
        table = new Table(skin);
        this.controller = controller;
        controller.setView(this);

        // buttons
        preGame = new TextButton("Pre Game Menu", skin);
        setting = new TextButton("Setting", skin);
        profile = new TextButton("Profile", skin);
        scoreBoard = new TextButton("Score Board", skin);
        hintMenu = new TextButton("Hint Menu", skin);
        logout = new TextButton("Log Out", skin);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(preGame).width(450).height(120);
        table.row().pad(15);
        table.add(setting).width(450).height(120);
        table.row().pad(15);
        table.add(profile).width(450).height(120);
        table.row().pad(15);
        table.add(scoreBoard).width(450).height(120);
        table.row().pad(15);
        table.add(hintMenu).width(450).height(120);
        table.row().pad(15);
        table.add(logout).width(450).height(120);

        stage.addActor(table);


        preGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handlePreGame();
            }
        });

        setting.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleSetting();
            }
        });

        profile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleProfile();
            }
        });

        scoreBoard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleScoreBoard();
            }
        });

        hintMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleHintMenu();
            }
        });

        logout.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleLogOut();
            }
        });
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(.2f, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    }

}


//    private Stage stage;
//    private final TextButton playButton;
//    private final Label gameTitle;
//    private final TextField field;
//    public Table table;
//    private final MainMenuController controller;
//
//    public SignUpMenuView(MainMenuController controller, Skin skin) {
//        this.controller = controller;
//        this.gameTitle = new Label("This is a title", skin);
//        this.field = new TextField("this is a field", skin);
//        this.playButton = new TextButton("play", skin);
//        this.table = new Table();
//
//        controller.setView(this);
//    }
//
//    @Override
//    public void show() {
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//
//        table.setFillParent(true);
//        table.center();
//        table.add(gameTitle);
//        table.row().pad(10, 0 , 10 , 0);
//        table.add(field).width(300).height(200);
//        table.row().pad(10, 0 , 10 , 0);
//        table.add(playButton);
//
//        stage.addActor(table);
//    }
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0, 1);
//        Main.getBatch().begin();
//        Main.getBatch().end();
//        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        stage.draw();
//        controller.handleMainMenuButtons();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//
//    public TextButton getPlayButton() {
//        return playButton;
//    }
//
//    public TextField getField() {
//        return field;
//    }
