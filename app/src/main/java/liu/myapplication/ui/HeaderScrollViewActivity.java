package liu.myapplication.ui;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import liu.myapplication.R;
import liu.myapplication.utils.ColorUtil;
import liu.myapplication.utils.DensityUtil;
import liu.myapplication.view.MyScrollView;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: 仿美团首页
 * @author: MIFM
 * @date: 2016/7/28 16:23
 */
public class HeaderScrollViewActivity extends AppCompatActivity {
    @BindView(R.id.view_title_bg)
    View viewTitleBg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.view_action_more_bg)
    View viewActionMoreBg;
    @BindView(R.id.fl_action_more)
    FrameLayout flActionMore;
    @BindView(R.id.rl_bar)
    RelativeLayout rlBar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.image4)
    ImageView image4;
    @BindView(R.id.image5)
    ImageView image5;
    @BindView(R.id.scrollView)
    MyScrollView scrollView;

    private int titleHeight;
    private Window window;
    private boolean isFirst = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        ButterKnife.bind(this);
        initView();
        initScrollListener();
        window = getWindow();
    }

    /**
     * activity完成渲染后调用给预留状态栏
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (isFirst){
            titleHeight = rlBar.getHeight();
            Logger.e(titleHeight + "");
            Rect outRect = new Rect();
            this.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
            Logger.e(outRect.top + "outRect.top");
            ViewGroup.LayoutParams layoutParams = rlBar.getLayoutParams();
            layoutParams.height += outRect.top;
            Logger.d(DensityUtil.px2dip(this,outRect.top)+"dp");
            rlBar.setLayoutParams(layoutParams);
            isFirst = !isFirst;
        }
        super.onWindowFocusChanged(hasFocus);
    }

    private void initView() {
        Glide.with(this)
                .load("http://img0.imgtn.bdimg.com/it/u=1296117362,655885600&fm=21&gp=0.jpg")
                .into(image);
        Glide.with(this)
                .load("http://img0.imgtn.bdimg.com/it/u=1296117362,655885600&fm=21&gp=0.jpg")
                .into(image1);
        Glide.with(this)
                .load("http://img0.imgtn.bdimg.com/it/u=1296117362,655885600&fm=21&gp=0.jpg")
                .into(image2);
        Glide.with(this)
                .load("http://img0.imgtn.bdimg.com/it/u=1296117362,655885600&fm=21&gp=0.jpg")
                .into(image3);
        Glide.with(this)
                .load("http://img0.imgtn.bdimg.com/it/u=1296117362,655885600&fm=21&gp=0.jpg")
                .into(image4);
        Glide.with(this)
                .load("http://img0.imgtn.bdimg.com/it/u=1296117362,655885600&fm=21&gp=0.jpg")
                .into(image5);
    }

    private void initScrollListener() {
//        rlBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onGlobalLayout() {
//                titleHeight = rlBar.getHeight();
//                rlBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });

        scrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                handleTitleBarColorEvaluate(scrollY);
            }
        });
    }

    /**
     * 标题栏背景渐变
     * @param scrollY 滑动的距离
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void handleTitleBarColorEvaluate(int scrollY) {
        float fraction;
        fraction = (float) scrollY / (titleHeight*2);
        if (fraction < 0f) fraction = 0f;
        if (fraction > 1f) fraction = 1f;
        rlBar.setAlpha(1f);
        Logger.e(fraction+"");
        if (fraction >= 1f){
            viewTitleBg.setAlpha(0f);
            viewActionMoreBg.setAlpha(0f);
            rlBar.setBackgroundColor(getResources().getColor(R.color.orange));
        }else {
            viewTitleBg.setAlpha(1f - fraction);
            viewActionMoreBg.setAlpha(1f - fraction);
            rlBar.setBackgroundColor(ColorUtil.getNewColorByStartEndColor(this, fraction, R.color.transparent, R.color.orange));
        }
    }
}
