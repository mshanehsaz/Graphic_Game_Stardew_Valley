package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Control.SignUpMenuController;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.MusicManager;
import com.tilldawn.Model.SaveLoadFile;
import com.tilldawn.View.ProfileMenuView;
import com.tilldawn.View.SettingMenuView;
import com.tilldawn.View.SignUpMenuView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        GameApp gameApp = GameApp.getInstance();
        gameApp.setUsersInfo(SaveLoadFile.loadFile());
        MusicManager.getInstance().playMusic();
        getMain().setScreen(new SignUpMenuView(new SignUpMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        GameApp gameApp = GameApp.getInstance();
        SaveLoadFile.saveFile(gameApp.getUsersInfo());
        MusicManager.getInstance().dispose();
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }
}
