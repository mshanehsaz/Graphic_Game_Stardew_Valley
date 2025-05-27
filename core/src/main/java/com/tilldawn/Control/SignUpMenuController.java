package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.enums.Avatar;
import com.tilldawn.Model.enums.SecurityQuestion;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.SignUpMenuView;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpMenuController {
    private SignUpMenuView view;
    private final Random random = new Random();


    public void setView(SignUpMenuView view) {
        this.view = view;
    }

    public void handleSignUp(String username, String password, SecurityQuestion securityQuestion, String age, String securityAnswer) {
        if (!ageIsValid(age)) {
            view.showErrorDialog("Please Enter Valid Age.");
            return;
        }

        if (!usernameIsValid(username)) {
            view.showErrorDialog("This Username is Already Taken");
            return;
        }

        if (!passwordIsValid(password)) {
            view.showErrorDialog("Please Enter Valid Password.\n" +
                "- 8 Characters\n" +
                "- An uppercase letter\n" +
                "- A lowercase letter\n" +
                "- A Special Characters (@#$%&*()_)");
            return;
        }
        int ageInt = Integer.parseInt(age);
        int randInt = random.nextInt(5);
        Avatar avatar = Avatar.values()[randInt];
        UserInfo userInfo = new UserInfo(username, password, securityQuestion, securityAnswer, ageInt, 0, 0, 0, avatar);
        GameApp gameApp = GameApp.getInstance();
        gameApp.getUsersInfo().add(userInfo);
        gameApp.setMainPlayer(new Player(userInfo));
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    private boolean passwordIsValid(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&*()_]).{8,}$";
        Matcher matcher = Pattern.compile(regex).matcher(password);
        return matcher.matches();
    }

    private boolean usernameIsValid(String username) {
        GameApp gameApp = GameApp.getInstance();
        return gameApp.getUsersInfo().stream().noneMatch(p -> p.getName().equals(username));
    }

    private boolean ageIsValid(String age) {
        try {
            Integer.parseInt(age);
        } catch (Exception e){
            return false;
        }
        return true;
    }


    public void handleLoginMenu() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    public void handleAsGuest() {
        GameApp gameApp = GameApp.getInstance();
        int randInt = random.nextInt(5);
        Avatar avatar = Avatar.values()[randInt];
        gameApp.setMainPlayer(new Player(new UserInfo("Guest", null, null, null, 0, 0,0, 0, avatar)));
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }


}
