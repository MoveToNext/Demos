package liu.myapplication.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.R;
import liu.myapplication.image.DoubleCache;
import liu.myapplication.image.ImageLoader;
import liu.myapplication.image.ImageLoaderConfig;
import liu.myapplication.view.BaseActivity;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: 关于我
 * @author:
 * @date: 2016/9/29 17:47
 */
public class AboutMeActivity extends BaseActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.appCompatButton)
    AppCompatButton appCompatButton;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.dd)
    ConstraintLayout dd;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_constraintlayout);
        ButterKnife.bind(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageLoaderConfig config = new ImageLoaderConfig.Builder(getApplicationContext())
                        .setCache(new DoubleCache(getApplicationContext()))
                        .setthreadCount(3)
                        .create();
                imageLoader = ImageLoader.getInstance();
                imageLoader.init(config);
            }
        },1000);
    }

    @OnClick({R.id.iv_head,R.id.appCompatButton})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.appCompatButton:
                imageLoader.displayImage("http://img.woyaogexing.com/2016/09/29/38c98cc0cb034253!200x200.jpg",
                        ivHead);
                break;

            case R.id.iv_head:
                break;
        }
    }
}
