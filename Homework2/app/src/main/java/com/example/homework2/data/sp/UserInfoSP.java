package com.example.homework2.data.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoSP {
    private static final String SP_NAME = "user_info";
    private static final String KEY_IS_LOGIN = "is_login";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_AVATAR_PATH = "avatar_path";
    private static final String KEY_SIGNATURE = "signature";

    private static volatile UserInfoSP instance;
    private final SharedPreferences sp;
    private UserInfoSP(Context context) {
        sp = context.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
    public static UserInfoSP getInstance(Context context) {
        if (instance == null) {
            synchronized (UserInfoSP.class) {
                if (instance == null)
                    instance = new UserInfoSP(context);
            }
        }
        return instance;
    }
    public void saveUserInfo(String username, String avatarPath, String signature) {
        sp.edit()
                .putBoolean(KEY_IS_LOGIN, true)
                .putString(KEY_USER_NAME, username)
                .putString(KEY_AVATAR_PATH, avatarPath)
                .putString(KEY_SIGNATURE, signature)
                .apply();
    }
    public boolean isLogin() {
        return sp.getBoolean(KEY_IS_LOGIN, false);
    }
    public String getUsername() {
        return sp.getString(KEY_USER_NAME, "默认用户");
    }
    public String getAvatarPath() {
        return sp.getString(KEY_AVATAR_PATH, "@drawable/ic_default_avatar");
    }
    public String getSignature() {
        return sp.getString(KEY_SIGNATURE, "暂无签名");
    }
}
