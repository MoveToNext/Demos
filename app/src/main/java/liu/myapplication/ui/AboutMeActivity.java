package liu.myapplication.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.R;
import liu.myapplication.bean.Cities;
import liu.myapplication.bean.MyAnimation;
import liu.myapplication.bean.Person;
import liu.myapplication.image.DoubleCache;
import liu.myapplication.image.ImageLoader;
import liu.myapplication.image.ImageLoaderConfig;
import liu.myapplication.BaseActivity;

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
    private Cities cities;

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
//                getclass();
                showAnimation();
                break;
        }
    }

    private void showAnimation() {
        MyAnimation animation = new MyAnimation();
        ivHead.startAnimation(animation);
    }

    private void getclass() {
        try {
            Class<?> aClass = Class.forName(Person.class.getName());
            Constructor<?>[] constructors = aClass.getConstructors();
            Logger.d(constructors.length);
            Field[] fields = aClass.getDeclaredFields();
            Method count = aClass.getDeclaredMethod("speak", String.class);
            count.invoke(constructors[0].newInstance(), "Hello World！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
