package com.example.homework2.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework2.R;
import com.example.homework2.activity.ProfileActivity;
import com.example.homework2.data.model.User;
import com.example.homework2.data.repository.UserRepository;
import com.example.homework2.data.sp.UserInfoSP;

public class LoginActivity extends AppCompatActivity {

    private EditText login_email,login_password;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        TextView login_forget_password = findViewById(R.id.login_forget_password);
        Button login_btn = findViewById(R.id.login_btn);
        Button login_wechat_btn = findViewById(R.id.login_wechat_btn);
        Button login_apple_btn = findViewById(R.id.login_apple_btn);
        TextView login_signup = findViewById(R.id.login_signup);

        setupPasswordToggle();

        login_btn.setOnClickListener(v -> handleLogin());
        login_forget_password.setOnClickListener(v ->
                Toast.makeText(LoginActivity.this, "已点击忘记密码", Toast.LENGTH_SHORT).show());
        login_wechat_btn.setOnClickListener(v ->
                Toast.makeText(LoginActivity.this, "已点击微信登录", Toast.LENGTH_SHORT).show());
        login_apple_btn.setOnClickListener(v ->
                Toast.makeText(LoginActivity.this, "已点击苹果登录", Toast.LENGTH_SHORT).show());
        login_signup.setOnClickListener(v ->
                Toast.makeText(LoginActivity.this, "已点击注册", Toast.LENGTH_SHORT).show());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupPasswordToggle() {
        login_password.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (login_password.getRight() -
                        login_password.getCompoundDrawables()[2].getBounds().width() - 30)) {
                    togglePasswordVisibility();
                    return true;
                }
            }
            return false;
        });
    }
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            login_password.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_password, 0, R.drawable.ic_eye_close, 0);
            isPasswordVisible = false;
        } else {
            login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            login_password.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_password, 0, R.drawable.ic_eye_open, 0);
            isPasswordVisible = true;
        }
        login_password.setSelection(login_password.getText().length());
    }
    private void handleLogin(){
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        UserRepository userRepository = new UserRepository(this);
        User user = userRepository.queryUserByEmail(email);
        if(user != null && user.getPassword().equals(password)) {
            UserInfoSP userInfoSP = UserInfoSP.getInstance(this);
            userInfoSP.saveUserInfo(user.getUsername(), user.getAvatar_path(), user.getSignature());

            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            finish();
        } else {
            Toast.makeText(this, "邮箱或密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
