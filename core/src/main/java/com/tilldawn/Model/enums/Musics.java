package com.tilldawn.Model.enums;

public enum Musics {
    Pretty_Dungeon("SFX/AudioClip/Songs/Pretty Dungeon LOOP.wav"),
    Waste_Land_Combat("SFX/AudioClip/Songs/Wasteland Combat Loop.wav");

    private final String source;

    Musics(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
