package com.tilldawn.Control;

import com.tilldawn.Model.GameApp;
import com.tilldawn.Model.MusicManager;
import com.tilldawn.Model.SoundManager;
import com.tilldawn.Model.enums.Musics;
import com.tilldawn.View.SettingMenuView;

public class SettingMenuController {
    private SettingMenuView view;

    public void setView(SettingMenuView view) {
        this.view = view;
    }

    public void onVolumeChanged(float value) {
        MusicManager.getInstance().setVolume(value);
    }

    public void setMusic(Musics selected) {
        MusicManager.getInstance().setMusic(selected);
        MusicManager.getInstance().dispose();
        MusicManager.getInstance().playMusic();
    }

    public void sfxChange(boolean active) {
        SoundManager.getInstance().setActive(active);
    }

    public void autoReload(boolean active) {
        GameApp.getInstance().getMainPlayer().setAutoReload(active);
    }


}
