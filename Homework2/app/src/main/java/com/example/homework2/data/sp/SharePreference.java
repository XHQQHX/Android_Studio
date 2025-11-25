package com.example.homework2.data.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {
    public static final String SP_NAME = "sp_name";
    public static final String SP_KEY = "sp_key";

    private static volatile SharePreference instance;
    private final SharedPreferences sp;
    private SharePreference(Context context) {
        sp = context.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
    public static SharePreference getInstance(Context context) {
        if (instance == null) {
            synchronized (SharePreference.class) {
                if (instance == null) {
                    instance = new SharePreference(context);
                }
            }
        }
        return instance;
    }
    public void setHasInsertDefault(boolean hasInsertDefault) {
        sp.edit().putBoolean(SP_KEY, hasInsertDefault).apply();
    }
    public boolean getHasInsertDefault() {
        return sp.getBoolean(SP_KEY, false);
    }
}
