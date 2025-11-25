package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework2.activity.LoginActivity;
import com.example.homework2.data.repository.UserRepository;
import com.example.homework2.data.sp.SharePreference;
import com.example.homework2.data.sp.UserInfoSP;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private UserRepository userRepository;
    private SharePreference sharePreference;
    private UserInfoSP userInfoSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDependencies();
        initDatabaseAndInsertDefaultUser();

        new Thread(() -> {
            // 获取数据库文件的绝对路径
            String dbPath = getDatabasePath("nodes.db").getAbsolutePath();
            File dbFile = new File(dbPath);
            boolean isExists = dbFile.exists();

            // 主线程弹 Toast 提示
            runOnUiThread(() -> {
                if (isExists) {
                    Toast.makeText(MainActivity.this, "数据库文件已创建：" + dbPath, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "数据库文件不存在", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();

    }
    private void initDependencies() {
        userRepository = new UserRepository(this);
        sharePreference = SharePreference.getInstance(this);
        userInfoSP = UserInfoSP.getInstance(this);
    }
    private void initDatabaseAndInsertDefaultUser() {
        new Thread(() -> {
            try {
                boolean hasInserted = sharePreference.getHasInsertDefault();
                if (!hasInserted) {
                    boolean isInserted = userRepository.insertDefaultUser();
                    if (isInserted) {
                        sharePreference.setHasInsertDefault(true);
                    } else {
                        runOnUiThread(() -> Toast.makeText(
                                MainActivity.this,
                                "数据库初始化失败，请重启App",
                                Toast.LENGTH_SHORT
                        ).show());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(()-> Toast.makeText(
                        MainActivity.this,
                        "启动失败：" + e.getMessage(),
                        Toast.LENGTH_SHORT
                ).show());
            } finally {
                jumpToNextPage();
            }
        }).start();
    }
    private void jumpToNextPage() {
        Intent targetIntent;
        if (userInfoSP.isLogin()) {
            targetIntent = new Intent(MainActivity.this, LoginActivity.class);
        } else {
            targetIntent = new Intent(MainActivity.this, LoginActivity.class);
        }
        startActivity(targetIntent);
        finish();
    }
}