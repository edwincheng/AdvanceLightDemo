package com.github.edwincheng.advancelightdemo.chap_one;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.github.edwincheng.advancelightdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @ file name:    :
 * @ author        : edwincheng
 * @ e-mail        : zwp_edwincheng@163.com
 * @ date          : 20-2-27 17:01
 * @ description   :
 * @ modify author :
 * @ modify date   :
 */
public class NotificationActivity extends AppCompatActivity {
    @BindView(R.id.btn_normal)
    TextView btnNormal;
    @BindView(R.id.btn_fold)
    TextView btnFold;
    @BindView(R.id.btn_suspension)
    TextView btnSuspension;

    private Unbinder bind;
    private Notification.Builder builder;
    private Intent intent;
    private PendingIntent pendingIntent;

    private NotificationManager notificationManager;

    @OnClick({R.id.btn_normal, R.id.btn_fold, R.id.btn_suspension})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                // 获取系统 通知管理 服务
                builder = new Notification.Builder(NotificationActivity.this);

                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
                pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

                builder.setAutoCancel(true);
                builder.setContentTitle("普通通知");
                builder.setContentText("这是一条普通通知");

                // 兼容  API 26，Android 8.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
                    NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
                    // 注册通道，注册后除非卸载再安装否则不改变
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId("AppTestNotificationId");
                }

                notificationManager.notify(1, builder.build());
                break;
            case R.id.btn_fold:
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_hold);

                builder = new Notification.Builder(NotificationActivity.this);

                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.qq.com"));
                pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

                builder.setAutoCancel(true);
                builder.setContentTitle("折叠通知");
                builder.setContentText("这是一条折叠通知");

                // 兼容  API 26，Android 8.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
                    NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
                    // 注册通道，注册后除非卸载再安装否则不改变
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId("AppTestNotificationId");
                }

                Notification notification = builder.build();
                notification.bigContentView = remoteViews;

                notificationManager.notify(1, builder.build());

                break;
            case R.id.btn_suspension:
                /** 悬挂通知 */
                notification();
                break;
        }
    }

    private void notification() {
        Intent intent = new Intent(this, NotificationActivity.class);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //8.0 以后需要加上channelId 才能正常显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "default";
            String channelName = "默认通知";
            manager.createNotificationChannel(new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH));
        }

        //设置TaskStackBuilder
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("标题")
                .setContentText("这是内容，点击我可以跳转")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .build();

        manager.notify(1, notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        bind = ButterKnife.bind(this);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
