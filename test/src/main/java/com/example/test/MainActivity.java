package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.jump2recycle)
    TextView jump2recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Observable.just("1", "2", "3").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("MainActivity", "onSubscribe");
                d.dispose();
            }

            @Override
            public void onNext(String value) {
                Log.d("MainActivity", "onNext");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "onComplete");
            }
        });
        Observable.error(new DeadObjectException()).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.dispose();
            }

            @Override
            public void onNext(Object value) {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("MainActivity", "e:" + e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @OnClick(R.id.jump2recycle)
    public void onViewClicked() {
        startActivity(new Intent(this,RecycleActivity.class));
    }
}
