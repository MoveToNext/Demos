package liu.myapplication.recycle;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utils.library.ToastUtil;

import liu.myapplication.R;

/**
 * @PackageName: liu.myapplication.recycle
 * @Description:
 * @author:
 * @date: 2016/8/15 11:20
 */
public class RecycleAdapter extends RecyclerView.Adapter {
    private Context context;
    public RecycleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 16) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_list_category_second,parent,false));
        }else {
            return new MyHolderBott(LayoutInflater.from(context).inflate(R.layout.item_list_category_second,parent,false));

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            MyHolder holder1 = (MyHolder) holder;
            holder1.view.setText("text");
        }else {
            ((MyHolderBott) holder).bindViewHolder(position);
        }
    }

    @Override
    public int getItemCount() {
        return 150;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView view;
        public MyHolder(View itemView) {
            super(itemView);
            view = (TextView) itemView.findViewById(R.id.text22);
        }
    }


    public class MyHolderBott extends RecyclerView.ViewHolder {
        TextView view;

        public MyHolderBott(View itemView) {
            super(itemView);
            view = (TextView) itemView.findViewById(R.id.text22);
        }

        public void bindViewHolder(final int position) {
            view.setBackgroundColor(Color.RED);
            view.setText(position+"");
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.show(context,position+"");
                    notifyItemMoved(position,position+5);
                }
            });
        }

    }
}
