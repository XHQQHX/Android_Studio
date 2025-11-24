package com.example.homework2.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.homework2.data.db.MyDBHelper;

import java.io.ByteArrayOutputStream;

public class UserRepository {
    private final MyDBHelper dbHelper;
    public UserRepository(Context context) {
        dbHelper = new MyDBHelper(context);
    }
    public void insertUser(String username, String email, String password, Bitmap avatar, String signature) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("password", password);
        values.put("signature", signature);

        if (avatar != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            avatar.compress(Bitmap.CompressFormat.PNG, 100, stream);
            values.put("avatar", stream.toByteArray());
        }

        db.insert("user", null, values);
        db.close();
    }
    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=? AND password=?",
                new String[]{username, password});

        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }
    public Bitmap getUserAvatar(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT avatar FROM user WHERE username=?", new String[]{username});
        Bitmap avatar = null;
        if (cursor.moveToFirst()) {
            byte[] data = cursor.getBlob(cursor.getColumnIndex("avatar"));
            if (data != null) avatar = BitmapFactory.decodeByteArray(data, 0, data.length);
        }
        cursor.close();
        db.close();
        return avatar;
    }
}
