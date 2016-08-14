package liu.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.ui.AnimationActivity;
import liu.myapplication.ui.CustomViewActivity;
import liu.myapplication.ui.HeaderScrollViewActivity;
import liu.myapplication.ui.ObserverTestActivity;
import liu.myapplication.ui.OkhttpActivity;
import liu.myapplication.ui.PullToRefreshListActivity;
import liu.myapplication.ui.RecyclerViewActivity;
import liu.myapplication.ui.RemoteViewActivity;
import liu.myapplication.view.MyView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.custom_view)
    Button customView;
    @BindView(R.id.ok_http)
    Button okHttp;
    @BindView(R.id.Observer)
    Button Observer;
    @BindView(R.id.getChannel)
    Button getChannel;
    @BindView(R.id.animation_property)
    Button animationProperty;
    @BindView(R.id.HeaderListView)
    Button HeaderListView;
    @BindView(R.id.RemoteView)
    Button RemoteView;
    @BindView(R.id.popupwindow)
    Button popupwindow;
    @BindView(R.id.PullToRefreshListView)
    Button PullToRefreshListView;
    @BindView(R.id.RecyclerView)
    Button RecyclerView;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        Log.e("height", height + "");
        Log.e("width", width + "");
    }

    /**
     * 获得清单文件中的数值
     *
     * @return meta_data值
     */
    public String getMeta_data() {
        ApplicationInfo appInfo = null;
        try {
            appInfo = this.getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String msg = appInfo.metaData.getString("UMENG_CHANNEL");
        Logger.d(msg);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("渠道号")
                .setMessage(msg)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
//        getChannel.setText(msg);
        return msg;
    }

    /**
     * 响应点击事件
     *
     * @param view
     */
    @OnClick({R.id.RecyclerView,R.id.PullToRefreshListView, R.id.popupwindow, R.id.animation_property, R.id.HeaderListView, R.id.custom_view, R.id.ok_http, R.id.Observer, R.id.getChannel, R.id.RemoteView})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.animation_property://属性动画
                intent.setClass(this, AnimationActivity.class);
                break;
            case R.id.custom_view://自定义栏目
                intent.setClass(this, CustomViewActivity.class);
                break;
            case R.id.ok_http:
                intent.setClass(this, OkhttpActivity.class);
                break;
            case R.id.Observer://观察者模式
                intent.setClass(this, ObserverTestActivity.class);
                break;
            case R.id.getChannel://获取渠道号
                getMeta_data();
                return;
            case R.id.HeaderListView:
                intent.setClass(this, HeaderScrollViewActivity.class);
                break;
            case R.id.RemoteView:
                intent.setClass(this, RemoteViewActivity.class);
                break;
            case R.id.popupwindow://弹出popupwindow
                showPopupWin();
                return;
            case R.id.PullToRefreshListView://PullToRefreshListView
                intent.setClass(this, PullToRefreshListActivity.class);
                break;
            case R.id.RecyclerView:
                intent.setClass(this, RecyclerViewActivity.class);
                break;
        }
        startActivity(intent);
    }

    private void showPopupWin() {
        PopupWindow p = new PopupWindow(this);
        TextView t = new TextView(this);
        t.setText("i am a pop");
        t.setTextColor(Color.BLACK);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        p.setBackgroundDrawable(dw);
        p.setContentView(new MyView(this));
        p.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        p.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        Logger.d("chidfdsfsdfds");
        p.showAtLocation(this.popupwindow, Gravity.BOTTOM, 0, 0);
    }
}
