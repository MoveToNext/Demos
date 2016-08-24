package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.R;
import liu.myapplication.generic.IFanxi;
import liu.myapplication.generic.TestClassOne;
import liu.myapplication.utils.FileUpload;
import liu.myapplication.view.AutoScrollTextView;

/**
 * @PackageName: liu.myapplication.ui
 * @Description:
 * @author:
 * @date: 2016/8/17 10:23
 */
public class GenericActivity extends AppCompatActivity {
    @BindView(R.id.btn_generic)
    Button btnGeneric;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.text_switcher)
    AutoScrollTextView textSwitcher;
    @BindView(R.id.btn_upload)
    Button btnUpload;

    private ArrayList<String> titleList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        // 制造测试数据
        for (int i = 0; i < 3; i++) {
            titleList.add("ABC position : " + i);
        }
        textSwitcher.setTextList(titleList);
        textSwitcher.setOnItemClickListener(new AutoScrollTextView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("", "position : " + position);
                Toast.makeText(GenericActivity.this, "position : " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        textSwitcher.startAutoScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        textSwitcher.stopAutoScroll();
    }

    @OnClick({R.id.btn_upload,R.id.btn_generic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_generic:
                DoTest();
                break;
            case R.id.btn_upload:
                new Thread(){
                    @Override
                    public void run() {
                        FileUpload.toUploadFile("hah", new FileUpload.MyListener() {
                            @Override
                            public void to(String s) {
//                                Toast.makeText(GenericActivity.this,s,Toast.LENGTH_SHORT).show();
                                Logger.d("ddddd" + s);
                                Snackbar.make(textSwitcher,s,Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                }.start();


                break;
        }
    }

    private void DoTest() {
//        FanXing fanXing = new FanXing(new Button(this));
//        TextView print = fanXing.print();
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        print.setLayoutParams(params);
//        llContent.addView(print);
        IFanxi<Button> fanxi = new TestClassOne();
        fanxi.add(new Button(this));
        TextView textView = fanxi.get();
        textView.setText("go go go");
        llContent.addView(textView);
    }
}
