package com.example.homework2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.homework2.R;
import com.example.homework2.data.sp.UserInfoSP;

public class ProfileActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        UserInfoSP userInfoSP = UserInfoSP.getInstance(this);

        View user_info_header = findViewById(R.id.user_info_header);
        CardView cvAvatar = user_info_header.findViewById(R.id.cvAvatar);
        ImageView IvAvatar = (ImageView) cvAvatar.getChildAt(0);
        TextView tvUsername = user_info_header.findViewById(R.id.tvUsername);
        TextView tvSignature = user_info_header.findViewById(R.id.tvSignature);

        TextView user_info = findViewById(R.id.user_info);
        TextView user_favorite = findViewById(R.id.user_favorite);
        TextView user_history = findViewById(R.id.user_history);
        TextView user_setting = findViewById(R.id.user_setting);
        TextView user_about = findViewById(R.id.user_about);
        TextView user_feedback = findViewById(R.id.user_feedback);

        String username = userInfoSP.getUsername();
        String signature = userInfoSP.getSignature();
        String avatarPath = userInfoSP.getAvatarPath();
        Toast.makeText(this, avatarPath, Toast.LENGTH_SHORT).show();

        tvUsername.setText(username);
        tvSignature.setText(signature);
        String resourceName = avatarPath.substring("@drawable/".length());
        int resourceId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
        IvAvatar.setImageResource(resourceId);

        user_info.setOnClickListener(v ->
                Toast.makeText(this, "已点击个人信息", Toast.LENGTH_SHORT).show());
        user_favorite.setOnClickListener(v ->
                Toast.makeText(this, "已点击我的收藏", Toast.LENGTH_SHORT).show());
        user_history.setOnClickListener(v ->
                Toast.makeText(this, "已点击浏览历史", Toast.LENGTH_SHORT).show());
        user_setting.setOnClickListener(v ->
                Toast.makeText(this, "已点击设置", Toast.LENGTH_SHORT).show());
        user_about.setOnClickListener(v ->
                Toast.makeText(this, "已点击关于我们", Toast.LENGTH_SHORT).show());
        user_feedback.setOnClickListener(v ->
                Toast.makeText(this, "已点击意见反馈", Toast.LENGTH_SHORT).show());

    }
}
