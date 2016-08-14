package liu.myapplication.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import liu.myapplication.R;

/**
 * @PackageName: liu.myapplication.recyclerView
 * @Description:
 * @author:
 * @date: 2016/8/12 14:41
 */
public class ThirdViewHolder extends RecyclerView.ViewHolder {
    public TextView thirdCategory;

    public ThirdViewHolder(View itemView) {
        super(itemView);
        thirdCategory = (TextView) itemView.findViewById(R.id.text33);
    }
}
