package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.SignUpMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.SoundManager;
import com.tilldawn.Model.enums.SecurityQuestion;

public class SignUpMenuView implements Screen {
    private Stage stage;
    private final Table table;
    private final SignUpMenuController controller;

    // image
    private final Texture texture;
    private final TextureRegionDrawable drawable;
    private final Image image;

    // labels
    private final Label username;
    private final Label password;
    private final Label age;
    private final Label security1;
    private final Label security2;

    // fields
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField ageField;
    private final TextField securityAnswerField;

    // buttons
    private final TextButton signUp;
    private final TextButton asGuest;
    private final TextButton loginMenu;

    // select bow
    private final SelectBox<SecurityQuestion> securityQuestion;


    public SignUpMenuView(SignUpMenuController controller, Skin skin) {
        table = new Table(skin);
        this.controller = controller;
        controller.setView(this);

        //image
        texture = new Texture(Gdx.files.internal("Images_grouped_1/Sprite/T/T_20Logo.png"));
        drawable = new TextureRegionDrawable(new TextureRegion(texture));
        image = new Image(drawable);

        // labels
        username = new Label("Username:", skin);
        password = new Label("Password:", skin);
        age = new Label("Age:", skin);
        security1 = new Label("Answer:", skin);
        security2 = new Label("Recovery Question:", skin);

        // fields
        usernameField = new TextField("Username", skin);
        passwordField = new TextField("Password", skin);
        ageField = new TextField("Age", skin);
        securityAnswerField = new TextField("Answer", skin);

        // buttons
        signUp = new TextButton("Sign Up", skin);
        asGuest = new TextButton("Play As Guest", skin);
        loginMenu = new TextButton("Login Menu", skin);

        // select box
        securityQuestion = new SelectBox<>(skin);


    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        // Add logo
        table.add(image).colspan(2).center().width(450).height(300);
        table.row();

        // Username and Password in the same row
        username.setFontScale(1.2f);
        password.setFontScale(1.2f);
        Table row1 = new Table();
        row1.add(username);
        row1.add(usernameField).width(400).height(80).padRight(50);
        row1.add(password);
        row1.add(passwordField).width(400).height(80);
        table.add(row1).colspan(2);
        table.row().pad(15, 0, 10, 0);

        // Age row
        age.setFontScale(1.2f);
        Table ageRow = new Table();
        ageRow.add(age).right().padRight(10);
        ageRow.add(ageField).width(300).height(80);
        table.add(ageRow).colspan(2);
        table.row().pad(10, 0, 10, 0);

        // Security Question label and select box in one row
        security2.setFontScale(1.2f);
        Table securityQuestionRow = new Table();
        securityQuestionRow.add(security2).right().padRight(10);
        securityQuestionRow.add(securityQuestion).width(450).height(80);
        securityQuestion.setItems(SecurityQuestion.FatherName, SecurityQuestion.FavoriteColor, SecurityQuestion.Birthplace);
        table.add(securityQuestionRow).colspan(2);
        table.row().pad(10, 0, 10, 0);

        // Security Answer row
        security1.setFontScale(1.2f);
        Table securityRow = new Table();
        securityRow.add(security1).right().padRight(10);
        securityRow.add(securityAnswerField).width(400).height(80);
        table.add(securityRow).colspan(2);
        table.row().pad(15, 0, 20, 0);

        // Buttons row (Sign up and login)
        Table buttonsRow = new Table();
        signUp.getLabel().setFontScale(1f);
        loginMenu.getLabel().setFontScale(1f);
        buttonsRow.add(signUp).width(300).height(90).padRight(40);
        buttonsRow.add(loginMenu).width(400).height(90);
        table.add(buttonsRow).colspan(2).center();
        table.row().pad(10, 0, 0, 0);

        // As Guest button
        asGuest.getLabel().setColor(0.1f, 0.7f, 0.3f, 1);
        table.add(asGuest).colspan(2).center().height(90).width(500);

        stage.addActor(table);

        // Logo animation
        image.addAction(Actions.forever
            (Actions.sequence
                (Actions.moveBy(0, 30f, 1.5f),
                    Actions.moveBy(0, -30f, 1.5f))));

        // Button listeners
        signUp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleSignUp(usernameField.getText(),
                    passwordField.getText(),
                    securityQuestion.getSelected(),
                    ageField.getText(),
                    securityAnswerField.getText());
            }
        });

        loginMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleLoginMenu();
            }
        });

        asGuest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.getInstance().playClick();
                controller.handleAsGuest();
            }
        });
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
