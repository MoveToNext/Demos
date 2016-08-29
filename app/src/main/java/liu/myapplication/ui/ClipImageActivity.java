package liu.myapplication.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.utils.library.BitmapUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.R;
import liu.myapplication.view.ClipViewLayout;

/**
 * @PackageName: liu.myapplication.ui
 * @Description:
 * @author:
 * @date: 2016/8/22 14:32
 */
public class ClipImageActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.stock_name)
    TextView stockName;
    @BindView(R.id.clipViewLayout1)
    ClipViewLayout clipViewLayout1;
    @BindView(R.id.clipViewLayout2)
    ClipViewLayout clipViewLayout2;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;
    @BindView(R.id.bt_ok)
    TextView btOk;
    @BindView(R.id.bottom)
    RelativeLayout bottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_image);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
            //设置图片资源
        clipViewLayout1.setImageSrc(getIntent().getData());
    }

    @OnClick({R.id.iv_back, R.id.stock_name, R.id.clipViewLayout1, R.id.clipViewLayout2, R.id.btn_cancel, R.id.bt_ok, R.id.bottom})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.bt_ok:
                generateUriAndReturn();
                break;
        }
    }

    private void generateUriAndReturn() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap = clipViewLayout1.clip();
        if (zoomedCropBitmap == null){
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null){
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null){
                    Bitmap bitmap = BitmapUtil.getRoundedCornerBitmap(zoomedCropBitmap, 500);
                    boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
                    Logger.e("compress"+compress);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                if (outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Intent intent = new Intent();
        intent.setData(mSaveUri);
        setResult(RESULT_OK,intent);
        finish();
    }
}
