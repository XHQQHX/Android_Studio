package com.example.homework2.data.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.homework2.data.db.MyDBHelper;
import com.example.homework2.data.model.User;

public class UserRepository {
    private final MyDBHelper dbHelper;
    public UserRepository(Context context) {
        this.dbHelper = new MyDBHelper(context);
    }
    public boolean insertDefaultUser() {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            // 1. 先查询默认账号是否已存在（避免重复插入）
            User defaultUser = queryUserByEmail("root@example.com");
            if (defaultUser != null) {
                Log.d(TAG, "默认账号已存在");
                return true;
            } else {
                Log.d(TAG, "默认账号不存在，开始插入");
            }

            db.execSQL(
                    "INSERT INTO " + MyDBHelper.TABLE_USER + "(" +
                            MyDBHelper.COLUMN_USERNAME + "," +
                            MyDBHelper.COLUMN_EMAIL + "," +
                            MyDBHelper.COLUMN_PASSWORD + "," +
                            MyDBHelper.COLUMN_AVATAR_PATH + "," +
                            MyDBHelper.COLUMN_SIGNATURE + ")" +
                            "VALUES (" +
                            "'root'," +
                            "'root@example.com'," +
                            "'123456'," +
                            "'@drawable/default_avatar'," +
                            "'我是管理员'" +
                            ")"
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User queryUserByEmail(String email) {
        User user = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                MyDBHelper.COLUMN_ID,
                MyDBHelper.COLUMN_USERNAME,
                MyDBHelper.COLUMN_EMAIL,
                MyDBHelper.COLUMN_PASSWORD,
                MyDBHelper.COLUMN_AVATAR_PATH,
                MyDBHelper.COLUMN_SIGNATURE
        };
        String selection = MyDBHelper.COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                MyDBHelper.TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null, null, null
        );
        if (cursor.moveToNext()) {
            // 用全参构造方法快速封装数据
            user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(MyDBHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.COLUMN_USERNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.COLUMN_PASSWORD)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.COLUMN_AVATAR_PATH)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.COLUMN_SIGNATURE))
            );
        }
        cursor.close();
        return user;
    }
}
