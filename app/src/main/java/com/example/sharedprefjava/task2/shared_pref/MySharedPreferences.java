package com.example.sharedprefjava.task2.shared_pref;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static MySharedPreferences mySharedPreferences = new MySharedPreferences();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static MySharedPreferences getInstance(Context context){
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        }
        return mySharedPreferences;
    }

    public String getUser(){
        return sharedPreferences.getString("users","");
    }

    public boolean setUser(String str) {
        editor = sharedPreferences.edit();
        editor.putString("users", str);
        return editor.commit();
    }
}
