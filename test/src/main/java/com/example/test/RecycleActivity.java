package com.example.test;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 * PackageName:  com.example.test
 * Description:
 * Created by :   Liu
 * date:         2017/10/9 上午11:02
 * </pre>
 */
public class RecycleActivity extends AppCompatActivity {

    @BindView(R.id.recycle)
    RecyclerView mRecycleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy);
        ButterKnife.bind(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        DividerDecoration dividerDecoration = new DividerDecoration();
        RecycleAdapter mAdapter = new RecycleAdapter(this);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        Drawable drawable = RecycleActivity.this.getResources().getDrawable(R.drawable.item);
        dividerItemDecoration.setDrawable(drawable);
        mRecycleView.addItemDecoration(dividerDecoration);
        mRecycleView.setAdapter(mAdapter);
    }

}
