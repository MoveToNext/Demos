package liu.myapplication;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @PackageName: liu.myapplication
 * @Description: 自定义application
 * @author: liu
 * @date: 2016/9/22 15:09
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "38334716f2", false);
        LeakCanary.install(this);
    }

}
