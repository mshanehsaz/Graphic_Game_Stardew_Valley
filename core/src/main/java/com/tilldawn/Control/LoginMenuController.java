package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.enums.Avatar;
import com.tilldawn.Model.enums.SecurityQuestion;
import com.tilldawn.View.GameView;
import com.tilldawn.View.LoginMenuView;
//import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.SignUpMenuView;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleLogin(String username, String password) {
        UserInfo userInfo = getUserInfo(username);
        if (userInfo == null) {
            view.showErrorDialog("There isn't any User with This Username.");
            return;
        }

        if (!userInfo.getPassword().equals(password)) {
            view.showErrorDialog("The Password is Incorrect");
            return;
        }

        GameApp gameApp = GameApp.getInstance();
        gameApp.setMainPlayer(new Player(userInfo));
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    private UserInfo getUserInfo(String username) {
        GameApp gameApp = GameApp.getInstance();
        for (UserInfo userInfo : gameApp.getUsersInfo()){
            if (userInfo.getName().equals(username)){
                return userInfo;
            }
        }
        return null;
    }

    public void handleBack() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SignUpMenuView(new SignUpMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    public void handleForgotPassword() {
        view.getSecurity1().setVisible(true);
        view.getSecurity2().setVisible(true);
        view.getSecurityAnswerField().setVisible(true);
        view.getSecurityQuestion().setVisible(true);
        view.getGetPass().setVisible(true);
    }

    public void handleGetPass(String username, SecurityQuestion securityQuestion, String answer) {
        UserInfo userInfo = getUserInfo(username);
        if (userInfo == null) {
            view.showErrorDialog("There isn't any User with This Username.");
            return;
        }

        if (!userInfo.getSecurityQuestion().equals(securityQuestion) || !userInfo.getSecurityAnswer().equalsIgnoreCase(answer)){
            view.showErrorDialog("Check Security Question & Answer again");
            return;
        }

        view.getPasswordField().setText(userInfo.getPassword());
    }
}
