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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.R;

/**
 * Created by liutongyang on 2016/7/30.
 */
public class RemoteViewActivity extends AppCompatActivity {

    @BindView(R.id.btn_send_notification)
    Button send_notifi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remoteview);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_send_notification})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.btn_send_notification:
                send_notifi();
            break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void send_notifi() {
        Notification.Builder builder = new Notification.Builder(this).setTicker("显示于屏幕顶端状态栏的文本");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Intent intent = new Intent(this, RemoteViewActivity.class);
        PendingIntent p = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification build = builder.setContentIntent(p).setContentTitle("title").setContentText("text").build();
        NotificationManager manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manger.notify(1,build);
    }
}
