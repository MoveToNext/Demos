package liu.myapplication.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import liu.myapplication.R;

/**
 * @PackageName: liu.myapplication.recyclerView
 * @Description:
 * @author:
 * @date: 2016/8/12 14:35
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Category> lists;
    private Context context;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, List<Category> lists) {
        this.context = context;
        this.lists = lists;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {

        return lists.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Category.SECOND_TYPE) {
            return new SecondViewHolder(layoutInflater.inflate(R.layout.item_list_category_second, null, false));
        }
        return new ThirdViewHolder(layoutInflater.inflate(R.layout.item_list_category_third, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case Category.SECOND_TYPE:
                SecondViewHolder secondViewHolder = (SecondViewHolder) holder;
                secondViewHolder.secondCategory.setText(lists.get(position).getCategoryName());
                break;
            case Category.THIRD_TYPE:
                ThirdViewHolder thirdViewHolder = (ThirdViewHolder) holder;
                thirdViewHolder.thirdCategory.setText(lists.get(position).getCategoryName());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
