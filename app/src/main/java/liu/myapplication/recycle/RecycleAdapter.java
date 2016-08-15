package liu.myapplication.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_list_category_second,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        holder1.view.setText("text");
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView view;
        public MyHolder(View itemView) {
            super(itemView);
            view = (TextView) itemView.findViewById(R.id.text22);
        }
    }
}
