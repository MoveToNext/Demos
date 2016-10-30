package liu.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * @PackageName: liu.myapplication.view
 * @Description:
 * @author:
 * @date: 2016/8/26 11:45
 */
public class BaseActivity extends AppCompatActivity {
    protected int width;
    protected int height;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        
    }

    public void openActivity(Class<?> mclass) {
        openActivity(mclass,null);
    }
    public void openActivity(Class<?> mclass, Bundle bundle) {

        Intent intent = new Intent(this,mclass);
        if (null != bundle){
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }
    public void showSnackbar(String msg){
        View decorView = getWindow().getDecorView();
        showSnackbar(decorView,msg,null);
        Snackbar.make(decorView,msg,Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackbar(View view,String msg,String action){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).setAction(action, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
    }
}
