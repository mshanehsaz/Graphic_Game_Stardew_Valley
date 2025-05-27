package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Control.ProfileMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.SoundManager;
import com.tilldawn.Model.enums.Avatar;
import com.tilldawn.Model.enums.Musics;

public class ProfileMenuView implements Screen {
    private final Stage stage;
    private final Table table;
    private ProfileMenuController controller;

    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField confirmPasswordField;
    private final TextButton changeUsernameBtn;
    private final TextButton changePasswordBtn;
    private final TextButton deleteAccountBtn;
    private final TextButton showInfo;
    private final SelectBox<Avatar> avatarSelectBox;
    private final TextButton customAvatarBtn;
    private final TextButton back;
    private final Image avatarPreview;

    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        table.center().pad(20);

        // Username
        table.add(new Label("Username:", skin));
        usernameField = new TextField("", skin);
        table.add(usernameField).width(300);
        changeUsernameBtn = new TextButton("Change Username", skin);
        table.add(changeUsernameBtn);
        table.row().pad(10);

        // Password
        table.add(new Label("New Password:", skin));
        passwordField = new TextField("", skin);
        table.add(passwordField).width(300);
        table.row().pad(5);
        table.add(new Label("Confirm Password:", skin));
        confirmPasswordField = new TextField("", skin);
        table.add(confirmPasswordField).width(300);
        changePasswordBtn = new TextButton("Change Password", skin);
        table.add(changePasswordBtn);
        table.row().pad(10);

        // Delete
        Table button = new Table();
        deleteAccountBtn = new TextButton("Delete Account", skin);
        button.add(deleteAccountBtn).padRight(20).width(500).height(120);
        button.add().colspan(1);
        showInfo = new TextButton("Show My Account", skin);
        button.add(showInfo).width(500).height(120);
        table.add(button).colspan(3);
        table.row().pad(20);

        // Avatar Selection
        table.add(new Label("Select Avatar:", skin));
        avatarSelectBox = new SelectBox<>(skin);
        avatarSelectBox.setItems(Avatar.values());
        avatarSelectBox.setSelected(GameApp.getInstance().getMainPlayer().getUserInfo().getAvatar());
        table.add(avatarSelectBox).width(400).center().colspan(2);
        table.row().pad(10);

        // Custom Avatar
        Table button1 = new Table();
        customAvatarBtn = new TextButton("Upload Custom Avatar", skin);
        button1.add(customAvatarBtn).colspan(3).padRight(20).width(500).height(120);
        button1.add().colspan(1);
        back = new TextButton("Back", skin);
        button1.add(back).colspan(3).width(500).height(120);
        table.add(button1).colspan(3);
        table.row().pad(20);

        // Avatar Preview
        avatarPreview = new Image(new Texture(GameApp.getInstance().getMainPlayer().getUserInfo().getAvatar().getSource()));
        avatarPreview.setSize(100, 100);
        table.add().colspan(1);
        table.add(new Label("Preview:", skin));
        table.add(avatarPreview).width(150).height(150).colspan(2);

        stage.addActor(table);
    }

    public void updateAvatarPreview(Texture texture) {
        avatarPreview.setDrawable(new TextureRegionDrawable(texture));
    }

    public void showErrorDialog(String message) {
        Dialog dialog = new Dialog("Error", table.getSkin()) {
            protected void result(Object object) {
                this.hide();
            }
        };
        dialog.getTitleLabel().setFontScale(1f);
        dialog.getTitleLabel().setColor(Color.RED);
        dialog.getTitleLabel().setAlignment(Align.center);
        dialog.pad(50);
        dialog.setWidth(1000);
        dialog.setHeight(1000);
        dialog.text(message);
        dialog.button("OK");
        dialog.getContentTable().pad(20);
        dialog.show(stage);
    }

    @Override
    public void show() {
        changeUsernameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleChangeUsername(usernameField.getText());
            }
        });

        changePasswordBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleChangePassword(passwordField.getText(), confirmPasswordField.getText());
            }
        });

        deleteAccountBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleDeleteAccount();
            }
        });

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

        showInfo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new InfoMenuView(GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

        avatarSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Avatar selected = avatarSelectBox.getSelected();
                controller.handleSelectAvatar(selected);
            }
        });

//        customAvatarBtn.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                SoundManager.getInstance().playClick();
//                FileHandle file = Gdx.files.local("custom_avatar.png");
//                if (file.exists()) {
//                    controller.handleCustomAvatar(file);
//                } else {
//                    showErrorDialog("custom_avatar.png not found in local storage path.");
//                }
//            }
//        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        stage.act(delta);
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
        stage.dispose();
    }
}
