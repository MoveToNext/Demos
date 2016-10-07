package liu.myapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.orhanobut.logger.Logger;
import com.utils.library.BitmapUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.evnentMessage.TestMessage;
import liu.myapplication.ui.AboutMeActivity;
import liu.myapplication.ui.AnimationActivity;
import liu.myapplication.ui.ClipImageActivity;
import liu.myapplication.ui.CustomViewActivity;
import liu.myapplication.ui.CustomViewGroupActivity;
import liu.myapplication.ui.ExpandableListViewActivity;
import liu.myapplication.ui.GenericActivity;
import liu.myapplication.ui.GuaGuaActivity;
import liu.myapplication.ui.HeaderScrollViewActivity;
import liu.myapplication.ui.LifeToolsActivity;
import liu.myapplication.ui.ObserverTestActivity;
import liu.myapplication.ui.OkhttpActivity;
import liu.myapplication.ui.PullToRefreshListActivity;
import liu.myapplication.ui.RecyclerDefaultActivity;
import liu.myapplication.ui.RecyclerViewActivity;
import liu.myapplication.ui.RemoteViewActivity;
import liu.myapplication.view.BaseActivity;

public class MainActivity extends BaseActivity {

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
    @BindView(R.id.RecyclerDefault)
    Button RecyclerDefault;
    @BindView(R.id.fanxing)
    Button fanxing;
    @BindView(R.id.ExpandableListView)
    Button ExpandableListView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.id_nv_menu)
    NavigationView MnavigationView;
    @BindView(R.id.id_drawer_layout)
    DrawerLayout MdrawerLayout;
    @BindView(R.id.EventBusActivity)
    Button EventBusActivity;
    @BindView(R.id.life_time)
    Button lifeTime;
    @BindView(R.id.custom_viewGroup)
    Button customViewGroup;
    @BindView(R.id.guagua_view)
    Button guaguaView;

    private PopupWindow popupWindow;
    private Context context = this;
    private final static int REQUEST_CAPTURE = 100;
    private final static int REQUEST_PICK = 101;
    private final static int REQUEST_CROP_PHOTO = 102;
    private File tempFile;
    private ImageView id_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /** 添加toolbar */
        initToolBar();
        setSupportActionBar(toolbar);
        initDrawLayout();
        initNavigationViewListener();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        Snackbar.make(toolbar, "action_edit", Snackbar.LENGTH_SHORT).setAction("关闭", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
                        break;
                    case R.id.action_settings:
                        openActivity(AboutMeActivity.class);
                        break;
                }
                return true;
            }
        });

        /** 注册eventbus */
        EventBus.getDefault().register(this);
        createCameraTempFile(savedInstanceState);
    }

    private void createCameraTempFile(Bundle savedInstanceState) {

        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        }
    }

    private String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    private void initNavigationViewListener() {
        View inflateHeaderView = MnavigationView.inflateHeaderView(R.layout.header_just_username);
        id_username = (ImageView) inflateHeaderView.findViewById(R.id.id_userpic);

        id_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWin();
                MdrawerLayout.closeDrawers();
            }
        });
        MnavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Snackbar.make(toolbar, "nav_home", Snackbar.LENGTH_SHORT).setAction("关闭", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
                        break;
                }
                MdrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initDrawLayout() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                MdrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.syncState();
        MdrawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void initToolBar() {

        toolbar.setNavigationIcon(R.mipmap.ic_action_more);
//        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("next");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
//        toolbar.setSubtitle("test");
        toolbar.setSubtitleTextColor(Color.parseColor("#ffffff"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
                .setCancelable(true)
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
    @OnClick({R.id.guagua_view,R.id.custom_viewGroup, R.id.life_time, R.id.EventBusActivity,
            R.id.ExpandableListView, R.id.fanxing, R.id.RecyclerDefault, R.id.RecyclerView,
            R.id.PullToRefreshListView, R.id.popupwindow, R.id.animation_property, R.id.HeaderListView,
            R.id.custom_view, R.id.ok_http, R.id.Observer, R.id.getChannel, R.id.RemoteView})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.animation_property://属性动画
                openActivity(AnimationActivity.class);
                break;
            case R.id.custom_view://自定义栏目
                openActivity(CustomViewActivity.class);
                break;
            case R.id.guagua_view://刮刮卡
                openActivity(GuaGuaActivity.class);
                break;
            case R.id.custom_viewGroup://自定义viewgroup
                openActivity(CustomViewGroupActivity.class);
                break;
            case R.id.ok_http:
                openActivity(OkhttpActivity.class);
                break;
            case R.id.Observer://观察者模式
                openActivity(ObserverTestActivity.class);
                break;
            case R.id.getChannel://获取渠道号
                getMeta_data();
                return;
            case R.id.HeaderListView:
                openActivity(HeaderScrollViewActivity.class);
                break;
            case R.id.RemoteView:
                openActivity(RemoteViewActivity.class);
                break;
            case R.id.popupwindow://弹出popupwindow
                showPopupWin();
                return;
            case R.id.PullToRefreshListView://PullToRefreshListView
                openActivity(PullToRefreshListActivity.class);
                break;
            case R.id.RecyclerView:
                openActivity(RecyclerViewActivity.class);
                break;
            case R.id.RecyclerDefault:
                openActivity(RecyclerDefaultActivity.class);
                break;
            case R.id.fanxing:
                openActivity(GenericActivity.class);
                break;
            case R.id.ExpandableListView:
                openActivity(ExpandableListViewActivity.class);
                break;
            case R.id.EventBusActivity:
                openActivity(liu.myapplication.ui.EventBusActivity.class);
                break;
            case R.id.life_time:
                openActivity(LifeToolsActivity.class);
                break;
        }
    }

    private void showPopupWin() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
//        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.anim_share_popup);
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.showAtLocation(this.popupwindow, Gravity.BOTTOM, 0, 0);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                    != PackageManager.PERMISSION_GRANTED ){
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//            }
//        }

        /** 拍照 */
        inflate.findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(intent, REQUEST_CAPTURE);
                popupWindow.dismiss();
            }
        });
        /** 从相册选择 */
        inflate.findViewById(R.id.btn_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到调用系统图库
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
                popupWindow.dismiss();
            }
        });
        /**  取消 */
        inflate.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
//                // 如果请求取消,这里返回的数组是空的.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // 权限已经被允许
//                    Logger.d("// 权限已经被允许");
//                } else {
//                    // 权限被拒绝
//                    Logger.d("//  // 权限被拒绝");
//                }
//                return;
//            }
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CAPTURE:
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (data.getData() != null) {
                        Uri pic = data.getData();
                        String cropImagePath = getRealFilePathFromUri(getApplicationContext(), pic);
                        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                        int bitmapSize = BitmapUtil.getBitmapSize(bitMap);
                        Logger.d("bitmapSize-" + bitmapSize);
                        id_username.setImageBitmap(bitMap);
                    }
                }
                break;

        }
    }

    private String getRealFilePathFromUri(Context applicationContext, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent(this, ClipImageActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("友情提示");
            builder.setMessage("确认退出应用吗");
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            });
            builder.setPositiveButton("取消", null);
            builder.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBus(TestMessage message) {
        String msg = message.msg;
        toolbar.setTitle(msg);
    }


    /***
     * 取消eventBus
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
