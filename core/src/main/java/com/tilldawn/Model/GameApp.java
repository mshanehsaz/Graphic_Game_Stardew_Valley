package com.tilldawn.Model;

import java.util.ArrayList;

public class GameApp {
    private static GameApp game;

    private GameApp() {
    }

    public static GameApp getInstance() {
        if (game == null) game = new GameApp();
        return game;
    }


    private boolean isGamePause = false;

    private ArrayList<UserInfo> usersInfo;

    private Player mainPlayer;

    public ArrayList<UserInfo> getUsersInfo() {
        return usersInfo;
    }

    public void setUsersInfo(ArrayList<UserInfo> usersInfo) {
        this.usersInfo = usersInfo;
    }

    public Player getMainPlayer() {
        return mainPlayer;
    }

    public void setMainPlayer(Player mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    public static GameApp getGame() {
        return game;
    }

    public static void setGame(GameApp game) {
        GameApp.game = game;
    }

    public boolean isGamePause() {
        return isGamePause;
    }

    public void setGamePause(boolean gamePause) {
        isGamePause = gamePause;
    }
}

