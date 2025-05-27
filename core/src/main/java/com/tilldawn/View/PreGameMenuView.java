package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.SoundManager;
import com.tilldawn.Model.enums.Hero;
import com.tilldawn.Model.enums.Weapons;

public class PreGameMenuView implements Screen {

    private final Stage stage;
    private final Table table;

    private final SelectBox<Hero> heroSelectBox;
    private final SelectBox<Weapons> weaponSelectBox;
    private final SelectBox<Integer> durationSelectBox;
    private final TextButton startGameBtn;
    private final TextButton backBtn;
    private final Image heroPreview;

    private Texture currentHeroTexture;

    public PreGameMenuView(Skin skin) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        table.center().pad(20);

        // Hero selection
        table.add(new Label("Select Hero:", skin)).left();
        heroSelectBox = new SelectBox<>(skin);
        heroSelectBox.setItems(Hero.values());
        heroSelectBox.setSelected(Hero.Dasher); // default
        table.add(heroSelectBox).width(300).pad(10);
        table.row();

        // Weapon selection
        table.add(new Label("Select Weapon:", skin)).left();
        weaponSelectBox = new SelectBox<>(skin);
        weaponSelectBox.setItems(Weapons.values());
        weaponSelectBox.setSelected(Weapons.values()[0]);
        table.add(weaponSelectBox).width(300).pad(10);
        table.row();

        // Duration selection
        table.add(new Label("Game Duration:", skin)).left();
        durationSelectBox = new SelectBox<>(skin);
        durationSelectBox.setItems(2, 5, 10, 20);
        durationSelectBox.setSelected(2);
        table.add(durationSelectBox).width(300).pad(10);
        table.row();

        // Hero preview
        table.add(new Label("Hero Preview:", skin)).colspan(2).center().padTop(20);
        table.row();
        currentHeroTexture = new Texture(heroSelectBox.getSelected().getSource());
        heroPreview = new Image(new TextureRegionDrawable(currentHeroTexture));
        heroPreview.setSize(200, 200);
        table.add(heroPreview).colspan(2).center().width(200).height(200);
        table.row();

        // Buttons
        startGameBtn = new TextButton("Start Game", skin);
        backBtn = new TextButton("Back", skin);

        Table buttonTable = new Table(skin);
        buttonTable.add(startGameBtn).width(400).height(120).padRight(40);
        buttonTable.add(backBtn).width(400).height(120);
        table.add(buttonTable).colspan(2).padTop(30);

        stage.addActor(table);

        setupListeners();
    }

    private void setupListeners() {
        heroSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Hero selected = heroSelectBox.getSelected();
                updateHeroPreview(new Texture(selected.getSource()));
                GameApp.getInstance().getMainPlayer().setHero(selected);
            }
        });

        durationSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float selected = durationSelectBox.getSelected();
                GameApp.getInstance().getMainPlayer().setTime(selected);
            }
        });

        weaponSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Weapons selected = weaponSelectBox.getSelected();
                GameApp.getInstance().getMainPlayer().setWeaponType(selected);
            }
        });

        startGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Player player = GameApp.getGame().getMainPlayer();
                player.setHero(heroSelectBox.getSelected());
                player.setWeaponType(weaponSelectBox.getSelected());
                player.setPlayerHealth(heroSelectBox.getSelected().getHP());

                Main.getMain().setScreen(GameView.getInstance());
            }
        });

        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
    }

    public void updateHeroPreview(Texture newTexture) {
        if (currentHeroTexture != null) {
            currentHeroTexture.dispose();
        }
        currentHeroTexture = newTexture;
        heroPreview.setDrawable(new TextureRegionDrawable(currentHeroTexture));
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0f, 0f, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        if (currentHeroTexture != null) {
            currentHeroTexture.dispose();
        }
        stage.dispose();
    }
}
