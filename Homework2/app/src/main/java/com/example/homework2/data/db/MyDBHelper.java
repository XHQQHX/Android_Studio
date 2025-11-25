package com.example.homework2.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "nodes.db";  // 数据库名
    private static final int DB_VERSION = 1;           // 数据库版本号
    public static final String TABLE_USER = "user";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_AVATAR_PATH = "avatar_path";
    public static final String COLUMN_SIGNATURE = "signature";
    public MyDBHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_USERNAME + " TEXT NOT NULL," +
                        COLUMN_EMAIL + " TEXT UNIQUE NOT NULL," +
                        COLUMN_PASSWORD + " TEXT NOT NULL," +
                        COLUMN_AVATAR_PATH + " TEXT DEFAULT ''," +
                        COLUMN_SIGNATURE + " TEXT DEFAULT '暂无签名')"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 目前不需要升级逻辑
    }
}
