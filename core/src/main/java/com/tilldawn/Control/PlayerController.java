package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.enums.KeyboardController;

public class PlayerController {
    private Player player;

    public PlayerController(Player player){
        this.player = player;
    }

    public void update(){
        player.getPlayerSprite().draw(Main.getBatch());

        if(player.isPlayerIdle()){
            idleAnimation();
        }

        handlePlayerInput();
    }


    public void handlePlayerInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(KeyboardController.Up.getaChar()))){
            player.getPlayerSprite().setPosition(player.getPlayerSprite().getX(), player.getPlayerSprite().getY() + player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(KeyboardController.Down.getaChar()))){
            player.getPlayerSprite().setPosition(player.getPlayerSprite().getX(), player.getPlayerSprite().getY() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(KeyboardController.Right.getaChar()))){
            player.getPlayerSprite().setPosition(player.getPlayerSprite().getX() + player.getSpeed(), player.getPlayerSprite().getY());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(KeyboardController.Left.getaChar()))){
            player.getPlayerSprite().setPosition(player.getPlayerSprite().getX() - player.getSpeed(), player.getPlayerSprite().getY());
            player.getPlayerSprite().flip(true, false);
        }
    }


    public void idleAnimation(){
        Animation<Texture> animation = player.getHero().getAnimation();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTimeAnim()));

        if (!animation.isAnimationFinished(player.getTimeAnim())) {
            player.setTimeAnim(player.getTimeAnim() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTimeAnim(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
