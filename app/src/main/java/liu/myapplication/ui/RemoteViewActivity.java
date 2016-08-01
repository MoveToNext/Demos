package liu.myapplication.ui;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.MainActivity;
import liu.myapplication.R;

/**
 * Created by liutongyang on 2016/7/30.
 */
public class RemoteViewActivity extends AppCompatActivity {

    @BindView(R.id.send_custom_Notification)//发送自定义
    Button btn_custom;
    @BindView(R.id.send_default_Notification)//发送系统默认的
    Button btn_default;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remoteview);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.send_custom_Notification,R.id.send_default_Notification})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.send_custom_Notification:
                send_custom_notifi();
            break;
            case R.id.send_default_Notification:
                send_notifi();
                break;
        }
    }

    /**
     * 发送自定义的通知栏
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void send_custom_notifi() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        RemoteViews remoteView = new RemoteViews(getPackageName(),R.layout.notifi_remoteview);
        remoteView.setImageViewResource(R.id.notifi_image,R.mipmap.ic_launcher);
        remoteView.setTextViewText(R.id.notifi_text,"我是测试文本");
        PendingIntent p1 = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent p2 = PendingIntent.getActivity(this, 0, new Intent(this, RemoteViewActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.id.notifi_image,p1);
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("title")
                .setContentInfo("info")
                .setContent(remoteView)
                .setContentIntent(p2);
        Notification build = builder.build();
        build.contentView = remoteView;
        build.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,build);
    }

    /**
     * 发送系统默认的通知栏
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void send_notifi() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(Notification.PRIORITY_HIGH)
                .setTicker("显示于屏幕顶端状态栏的文本")
                .setSubText("fdf");
        Intent intent = new Intent(this, RemoteViewActivity.class);
        PendingIntent p = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification build = builder.setContentIntent(p).setContentTitle("title").setContentText("text").build();
        build.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manger.notify(1,build);
    }
}
