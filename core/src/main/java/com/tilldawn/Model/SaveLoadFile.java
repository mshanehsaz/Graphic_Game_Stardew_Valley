package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveLoadFile {

    private static final String FILE_PATH = "usersInfo/allUsers.json";

    public static void saveFile(ArrayList<UserInfo> userInfos) {
        Gson gson = new Gson();
        String json = gson.toJson(userInfos);
        FileHandle file = Gdx.files.local(FILE_PATH);

        if (!file.file().getParentFile().exists()) {
            file.file().getParentFile().mkdirs();
        }

        file.writeString(json, false);
    }

    public static ArrayList<UserInfo> loadFile() {
        FileHandle file = Gdx.files.local(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        String json = file.readString();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<UserInfo>>() {}.getType();
        return gson.fromJson(json, listType);
    }
}
