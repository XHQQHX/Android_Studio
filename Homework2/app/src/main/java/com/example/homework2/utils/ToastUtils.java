package com.example.homework2.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homework2.R;

public class ToastUtils {
    public enum Type { NORMAL, SUCCESS, WARNING }

    public static void show(Context context, String message, Type type, int duration) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_custom, null);
        ImageView ivIcon = view.findViewById(R.id.iv_toast_icon);
        TextView tvText = view.findViewById(R.id.tv_toast_text);

        tvText.setText(message);

        switch (type) {
            case SUCCESS:
                ivIcon.setImageResource(R.drawable.ic_success);
                ivIcon.setVisibility(View.VISIBLE);
                break;
            case WARNING:
                ivIcon.setImageResource(R.drawable.ic_warning);
                ivIcon.setVisibility(View.VISIBLE);
                break;
            case NORMAL:
                ivIcon.setVisibility(View.GONE);
                break;
        }

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(duration);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 200);
        toast.show();
    }

    // 简化调用（默认普通样式 + 短时长）
    public static void show(Context context, String message) {
        show(context, message, Type.NORMAL, Toast.LENGTH_SHORT);
    }
}
