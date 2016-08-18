package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.R;
import liu.myapplication.generic.IFanxi;
import liu.myapplication.generic.TestClassOne;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_generic)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_generic:
                DoTest();
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
