package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.*;


public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handlePreGame() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PreGameMenuView(GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    public void handleSetting() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingMenuView(new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }

    }

    public void handleProfile() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    public void handleScoreBoard() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
//            Main.getMain().setScreen(new ScoreBoardView(new ScoreBoardController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    public void handleHintMenu() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
//            Main.getMain().setScreen(new HintMenuView(new HintMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    public void handleLogOut() {
        GameApp.getInstance().setMainPlayer(null);
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SignUpMenuView(new SignUpMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }


}
