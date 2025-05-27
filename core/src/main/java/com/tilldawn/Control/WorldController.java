package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Main;

public class WorldController {
    private PlayerController playerController;
    private final Texture backgroundTexture;

    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture("background.png");
        this.playerController = playerController;
    }

    public void update() {
        Main.getBatch().draw(backgroundTexture, 0, 0);
    }

}
