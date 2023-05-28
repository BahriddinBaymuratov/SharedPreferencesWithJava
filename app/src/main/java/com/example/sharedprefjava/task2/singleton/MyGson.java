package com.example.sharedprefjava.task2.singleton;

import com.google.gson.Gson;

public class MyGson {

    private static MyGson myGson = new MyGson();
    private static Gson gson;

    public MyGson() {
    }

    public static MyGson getInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return myGson;
    }

    public Gson getGson() {
        return gson;
    }
}
