package com.example.test;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 * PackageName:  com.example.test
 * Description:
 * Created by :   Liu
 * date:         2017/10/9 上午11:17
 * </pre>
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.NormalTextViewHolder> {

    LayoutInflater mLayoutInflater;
    public RecycleAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.bindHolder(position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }



    public static class NormalTextViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cv_item)
        CardView cardView;
        @BindView(R.id.text_view)
        TextView text;

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindHolder(int position) {
            text.setText("位置：："+position);
        }
    }
}
