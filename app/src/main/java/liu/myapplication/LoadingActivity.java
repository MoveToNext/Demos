package liu.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.prolificinteractive.parallaxpager.ParallaxContainer;
import com.prolificinteractive.parallaxpager.ParallaxContextWrapper;
import com.utils.library.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @PackageName: liu.myapplication
 * @Description: 欢迎页面
 * @author: mifm
 * @date: 2016/8/23 14:40
 */
public class LoadingActivity extends AppCompatActivity {
    @BindView(R.id.parallax_container)
    ParallaxContainer parallaxContainer;
    @BindView(R.id.iv_man)
    ImageView ivMan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (parallaxContainer != null){
            parallaxContainer.setLooping(false);
            parallaxContainer.setupChildren(getLayoutInflater(),R.layout.view_intro_1,R.layout.view_intro_2,
            R.layout.view_intro_3,R.layout.view_intro_4,R.layout.view_intro_5,R.layout.view_login);
        }

        Button view = (Button) findViewById(R.id.rl_weibo);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoadingActivity.this,MainActivity.class));
                SPUtil.setSP(LoadingActivity.this,"isFirst",false);
                finish();
            }
        });

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ParallaxContextWrapper(newBase));
    }
}
