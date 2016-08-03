package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Button;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.R;
import liu.myapplication.view.MyView;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: 自定义view
 * @author: MIFM
 * @date: 2016/7/26 11:44
 */
public class CustomViewActivity extends AppCompatActivity {

    @BindView(R.id.my_view)
    MyView myView;
    @BindView(R.id.ListView)
    android.widget.ListView ListView;
    @BindView(R.id.btn_showHeight)
    Button btnShowHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick(R.id.btn_showHeight)
    public void onClick() {
        int measuredHeight = myView.getMeasuredHeight();
        int height = myView.getHeight();
        myView.layout(myView.getLeft()+2,myView.getTop()+1,myView.getRight()+2,myView.getBottom());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.d("接受到touch事件了");
        return super.onTouchEvent(event);
    }
}
