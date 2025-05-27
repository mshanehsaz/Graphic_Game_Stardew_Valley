package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Control.SettingMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.MusicManager;
import com.tilldawn.Model.SoundManager;
import com.tilldawn.Model.enums.KeyboardController;
import com.tilldawn.Model.enums.Musics;

public class SettingMenuView implements Screen {
    private Stage stage;
    private final Table table;
    private final SettingMenuController controller;

    // labels
    private final Label changeVolume;
    private final Label changeMusic;
    private final Label upChange;
    private final Label downChange;
    private final Label rightChange;
    private final Label leftChange;
    private final Label shotChange;
    private final Label reloadChange;

    // inputs for key bindings
    private final TextField upField;
    private final TextField downField;
    private final TextField rightField;
    private final TextField leftField;
    private final TextField shotField;
    private final TextField reloadField;

    // slider
    private final Slider musicSlider;

    // select box
    private final SelectBox<Musics> musics;

    // check boxes
    private final CheckBox sfxOnOff;
    private final CheckBox autoLoadOnOff;
    private final CheckBox darkModeOnOff;

    // buttons
    private final TextButton save;
    private final TextButton reset;

    public SettingMenuView(SettingMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        table = new Table(skin);

        // initialize labels
        changeVolume = new Label("Change Music Volume", skin);
        changeMusic = new Label("Change Music", skin);
        upChange = new Label("UP Button:", skin);
        downChange = new Label("Down Button:", skin);
        rightChange = new Label("Right Button:", skin);
        leftChange = new Label("Left Button:", skin);
        shotChange = new Label("Shot Button:", skin);
        reloadChange = new Label("Reload Button:", skin);

        // key binding fields
        upField = new TextField("W", skin);
        downField = new TextField("S", skin);
        rightField = new TextField("D", skin);
        leftField = new TextField("A", skin);
        shotField = new TextField("Q", skin);
        reloadField = new TextField("R", skin);


        // slider
        musicSlider = new Slider(0, 1, 0.1f, false, skin);

        // select box
        musics = new SelectBox<>(skin);

        // check boxes
        sfxOnOff = new CheckBox("Sound Effects ON/OFF", skin);
        autoLoadOnOff = new CheckBox("Auto-Reload ON/OFF", skin);
        darkModeOnOff = new CheckBox("Dark-Mode ON/OFF", skin);

        // buttons
        save = new TextButton("Save Changes", skin);
        reset = new TextButton("Reset Changes", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.pad(10);

        // music settings
        musics.setItems(Musics.values());
        musics.setSelected(MusicManager.getInstance().getMusic());
        musicSlider.setValue(MusicManager.getInstance().getVolume());
        table.row().pad(10);
        table.add(changeVolume).left().colspan(2);
        table.add(musicSlider).height(70).width(400).colspan(2);

        table.row().pad(10);
        table.add(changeMusic).left().colspan(2);
        table.add(musics).width(500).colspan(2);

        // fields
        table.row().pad(15);
        Table fields = new Table();
        fields.add(upChange).padRight(10);
        upField.setText(KeyboardController.Up.getaChar());
        fields.add(upField).width(300).height(80).padRight(10);
        fields.add(downChange).padRight(10);
        downField.setText(KeyboardController.Down.getaChar());
        fields.add(downField).width(300).height(80);
        fields.row().pad(10);
        fields.add(rightChange).padRight(10);
        rightField.setText(KeyboardController.Right.getaChar());
        fields.add(rightField).width(300).height(80).padRight(10);
        leftField.setText(KeyboardController.Left.getaChar());
        fields.add(leftChange).padRight(10);
        fields.add(leftField).width(300).height(80);
        fields.row().pad(10);
        fields.add(shotChange).padRight(10);
        shotField.setText(KeyboardController.Shot.getaChar());
        fields.add(shotField).width(300).height(80).padRight(10);
        fields.add(reloadChange).padRight(10);
        reloadField.setText(KeyboardController.Reload.getaChar());
        fields.add(reloadField).width(300).height(80);
        table.add(fields).colspan(4);


        // check boxes
        table.row().pad(15);
        sfxOnOff.setChecked(SoundManager.getInstance().isActive());
        table.add(sfxOnOff).colspan(4).center();
        table.row().pad(10);
        autoLoadOnOff.setChecked(GameApp.getInstance().getMainPlayer().isAutoReload());
        table.add(autoLoadOnOff).colspan(4).center();
        table.row().pad(10);
        table.add(darkModeOnOff).colspan(4).center();

        // save & reset
        table.row().padTop(15);
        table.add(save).width(400).height(100).padRight(20).colspan(2);
        table.add(reset).width(400).height(100).colspan(2);

        stage.addActor(table);


        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = musicSlider.getValue();
                controller.onVolumeChanged(value);
            }
        });

        musics.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Musics selected = musics.getSelected();
                controller.setMusic(selected);
            }
        });

        sfxOnOff.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.sfxChange(sfxOnOff.isChecked());
            }
        });

        autoLoadOnOff.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.autoReload(autoLoadOnOff.isChecked());
            }
        });

        // others... for score

        // Button listeners
        reset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                upField.setText("W");
                downField.setText("S");
                rightField.setText("D");
                leftField.setText("A");
                shotField.setText("Q");
                reloadField.setText("R");
                musics.setSelected(Musics.Waste_Land_Combat);
                musicSlider.setValue(0.5f);
                sfxOnOff.setChecked(false);
                autoLoadOnOff.setChecked(false);
                darkModeOnOff.setChecked(false);
            }
        });

        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                KeyboardController.Up.setAChar(upField.getText());
                KeyboardController.Down.setAChar(downField.getText());
                KeyboardController.Right.setAChar(rightField.getText());
                KeyboardController.Left.setAChar(leftField.getText());
                KeyboardController.Shot.setAChar(shotField.getText());
                KeyboardController.Reload.setAChar(reloadField.getText());
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });



    }

//        field.addListener(new ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                field.setText("Press a key...");
//                Gdx.input.setInputProcessor(new InputAdapter() {
//                    @Override
//                    public boolean keyDown(int keycode) {
//                        String keyName = Input.Keys.toString(keycode);
//                        field.setText(keyName);
//                        controller.setKeyBinding(action, keyName);
//                        // restore stage as input processor
//                        Gdx.input.setInputProcessor(stage);
//                        return true;
//                    }
//                });
//            }
//        });
    @Override
    public void render(float delta) {
        ScreenUtils.clear(.2f, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() { stage.dispose(); }
}
