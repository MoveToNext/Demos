package liu.myapplication.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import liu.myapplication.R;

/**
 * @PackageName: liu.myapplication.recyclerView
 * @Description:
 * @author:
 * @date: 2016/8/12 14:36
 */
public class SecondViewHolder extends RecyclerView.ViewHolder {
    public TextView secondCategory;
    public SecondViewHolder(View itemView) {
        super(itemView);
        secondCategory = (TextView) itemView.findViewById(R.id.text22);
    }
}
