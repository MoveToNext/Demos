package com.example.robust;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.meituan.robust.Patch;
import com.meituan.robust.PatchExecutor;
import com.meituan.robust.RobustCallBack;
import com.meituan.robust.patch.annotaion.Modify;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text3);
        showText();
        getPatchServer();

    }

    @Modify
    private void showText() {
        text.setText("我是修复后的");
    }

    /**
     * 从服务器下拉补丁文件
     */
    private void getPatchServer() {
        OkHttpUtils.get().url("http://oo0zaukkd.bkt.clouddn.com/patch.jar")
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getPath()+ File.separator+"robust","patch.jar") {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Log.d("MainActivity", "response.getAbsoluteFile():" + response.getAbsoluteFile());
                        initPatch();
                    }
                });
    }

    private void initPatch() {
        new PatchExecutor(getApplicationContext(), new PatchManipulateImp(), new RobustCallBack() {
            @Override
            public void onPatchListFetched(boolean result, boolean isNet) {

            }

            @Override
            public void onPatchFetched(boolean result, boolean isNet, Patch patch) {

            }

            @Override
            public void onPatchApplied(boolean result, Patch patch) {

            }

            @Override
            public void logNotify(String log, String where) {

            }

            @Override
            public void exceptionNotify(Throwable throwable, String where) {

            }
        }).start();
    }
}
