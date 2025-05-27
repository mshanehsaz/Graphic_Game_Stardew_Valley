package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Main;
import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.enums.Avatar;
import com.tilldawn.View.ProfileMenuView;
import com.tilldawn.View.SignUpMenuView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController {
    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleChangeUsername(String newName) {
        if (GameApp.getInstance().getUsersInfo().stream().anyMatch(p -> p.getName().equals(newName))) {
            view.showErrorDialog("Username already exists.");
            return;
        }
        GameApp.getInstance().getMainPlayer().getUserInfo().setName(newName);
    }

    public void handleChangePassword(String pass, String confirm) {
        if (!pass.equals(confirm)) {
            view.showErrorDialog("Passwords do not match.");
            return;
        }
        if (!isComplex(pass)) {
            view.showErrorDialog("Please Enter Valid Password.\n" +
                "- 8 Characters\n" +
                "- An uppercase letter\n" +
                "- A lowercase letter\n" +
                "- A Special Characters (@#$%&*()_)");
            return;
        }
        GameApp.getInstance().getMainPlayer().getUserInfo().setPassword(pass);
    }

    private boolean isComplex(String pass) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&*()_]).{8,}$";
        Matcher matcher = Pattern.compile(regex).matcher(pass);
        return matcher.matches();
    }

    public void handleDeleteAccount() {
        GameApp gameApp = GameApp.getInstance();
        gameApp.getUsersInfo().remove(gameApp.getMainPlayer().getUserInfo());
        Main.getMain().setScreen(new SignUpMenuView(new SignUpMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleSelectAvatar(Avatar avatar) {
        Texture tex = new Texture(Gdx.files.internal(avatar.getSource()));
        GameApp.getInstance().getMainPlayer().getUserInfo().setAvatar(avatar);
        view.updateAvatarPreview(tex);
    }

//    public void handleCustomAvatar(FileHandle file) {
//        GameApp.getInstance().getMainPlayer().getUserInfo().setAvatar(null); // نشان‌دهنده تصویر سفارشی
//        view.updateAvatarPreview(new Texture(file));
//    }
}
