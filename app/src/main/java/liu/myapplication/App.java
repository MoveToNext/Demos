package liu.myapplication;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.taobao.hotfix.HotFixManager;
import com.taobao.hotfix.PatchLoadStatusListener;
import com.taobao.hotfix.util.PatchStatusCode;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @PackageName: liu.myapplication
 * @Description: 自定义application
 * @author: liu
 * @date: 2016/9/22 15:09
 */
public class App extends Application {
    public static String appVersion;
    public static String appId;
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "38334716f2", false);
        LeakCanary.install(this);

        initApp();
        initHotfix();
    }

    private void initApp() {
        this.appId = "78826-1"; //替换掉自己应用的appId
        try {
            this.appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            this.appVersion = "1.0.0";
        }
    }

    private void initHotfix() {
        HotFixManager.getInstance().initialize(this, appVersion, appId, true, new PatchLoadStatusListener() {
            @Override
            public void onload(int mode, int code, String info, int handlePatchVersion) {
                // 补丁加载回调通知
                if (code == PatchStatusCode.CODE_SUCCESS_LOAD) {
                    // TODO: 10/24/16 表明补丁加载成功
                    Log.d("App", "补丁加载成功");
                } else if (code == PatchStatusCode.CODE_ERROR_NEEDRESTART) {
                    // TODO: 10/24/16 表明新补丁生效需要重启. 业务方可自行实现逻辑, 提示用户或者强制重启, 建议: 用户可以监听进入后台事件, 然后应用自杀
                    Log.d("App", "新补丁生效需要重启");
                } else {
                    // TODO: 10/25/16 其它错误信息, 查看PatchStatusCode类说明
                    Log.d("App", "错误信息, 查看PatchStatusCode类说明");
                }
            }
        });
        HotFixManager.getInstance().queryNewHotPatch();
    }


}
