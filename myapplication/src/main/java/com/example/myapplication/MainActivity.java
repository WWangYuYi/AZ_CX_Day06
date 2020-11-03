package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_popup = findViewById(R.id.btn_popup);
        btn_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1: 创建并加载popupWindow的View
                View mPopup = LayoutInflater.from(MainActivity.this).inflate(R.layout.popu_item, null);
                final TextView mFriends = mPopup.findViewById(R.id.tv_friends);
                //2: 创建PopupWindow对象 选择3个或者4个参数的
                //三个参数：1：加载的popup视图 2：设置popup的宽的 3：设置popup的高度
                //4个参数多了一个popupWindow是否获得焦点
                final PopupWindow popupWindow = new PopupWindow(mPopup, ViewGroup.LayoutParams.MATCH_PARENT, 200);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                //3: 展示PopupWindow的位置 四个参数的
                popupWindow.showAsDropDown(btn_popup, Gravity.TOP,0,0);
                //4：设置点击Popup的点击阴影
                popup_alpha(0.3f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        popup_alpha(1);
                    }
                });
                //5: 设置进出场动画
                popupWindow.setAnimationStyle(R.style.PopAnim);
                mFriends.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Toast.makeText(MainActivity.this, mFriends.getText(), Toast.LENGTH_SHORT).show();
                        NotificationManager systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        String id ="11";
                        String name = "李四";
                        if (Build.VERSION.SDK_INT >= 26){
                            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
                            systemService.createNotificationChannel(channel);
                        }
                        Notification builder = new Notification.Builder(MainActivity.this)
                                .setAutoCancel(true)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("通知")
                                .setContentText("这是一个通知")
                                .build();
                        systemService.notify(22,builder);
                    }
                });
            }

            private void popup_alpha(float f) {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.alpha = f;
                window.setAttributes(attributes);
            }
        });
    }
}
