package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    private static SoundManager instance;
    private boolean isActive = true;
    private final Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/UIClick36.wav"));
    private final Sound shot = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/single_shot.wav"));
    private final Sound nonShot = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Switch (8).wav"));
    private final Sound reload = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Weapon_Shotgun_Reload.wav"));
    private final Sound damage = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Monster_2_RecieveAttack_HighIntensity_01.wav"));
    private final Sound lose = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/You Lose (4).wav"));
    private final Sound win = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/You Win (2).wav"));

    public static SoundManager getInstance() {
        if (instance == null) instance = new SoundManager();
        return instance;
    }

    public void playClick() {
        if (isActive) clickSound.play(1f);
    }

    public void playShot() {
        if (isActive) shot.play(1f);
    }

    public void playNonShot() {
        if (isActive) nonShot.play(1f);
    }

    public void playReload() {
        if (isActive) reload.play(1f);
    }

    public void playDamage() {
        if (isActive) damage.play(1f);
    }

    public void playLose() {
        if (isActive) lose.play(2f);
    }

    public void playWin() {
        if (isActive) win.play(1f);
    }


    public void dispose() {
        clickSound.dispose();
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }
}
