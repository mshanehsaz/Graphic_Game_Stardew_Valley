package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.tilldawn.Model.enums.Musics;

public class MusicManager {
    private static MusicManager instance;
    private Music currentMusic;
    private Musics music = Musics.Waste_Land_Combat;

    private MusicManager() {}

    public static MusicManager getInstance() {
        if (instance == null) instance = new MusicManager();
        return instance;
    }

    public void playMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(music.getSource()));
        currentMusic.setLooping(true);
        currentMusic.setVolume(.5f);
        currentMusic.play();
    }

    public void pause() {
        if (currentMusic != null && currentMusic.isPlaying()) currentMusic.pause();
    }

    public void resume() {
        if (currentMusic != null && !currentMusic.isPlaying()) currentMusic.play();
    }

    public void stop() {
        if (currentMusic != null) currentMusic.stop();
    }

    public void setVolume(float volume) {
        if (currentMusic != null) currentMusic.setVolume(volume);
    }

    public float getVolume() {
        return currentMusic.getVolume();
    }

    public void setMusic(Musics music) {
        this.music = music;
    }

    public Musics getMusic() {
        return music;
    }

    public void dispose() {
        if (currentMusic != null) {
            currentMusic.dispose();
            currentMusic = null;
        }
    }
}
