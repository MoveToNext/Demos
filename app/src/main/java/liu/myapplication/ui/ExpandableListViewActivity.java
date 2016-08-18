package liu.myapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liu.myapplication.R;
import liu.myapplication.utils.DataUtil;

/**
 * @PackageName: liu.myapplication.ui
 * @Description:
 * @author:
 * @date: 2016/8/18 13:42
 */
public class ExpandableListViewActivity extends AppCompatActivity {
    @BindView(R.id.ExpandableListView)
    android.widget.ExpandableListView ExpandableListView;

    private Context context = this;
    private String[][] data;
    private List<String> strings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandlistview);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        ExpandableListView.setAdapter(new MyexpandAdapter());
        ExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Logger.d("被点击");
                return false;
            }
        });
    }

    private void initData() {

        data = DataUtil.getMutuData();
        strings = DataUtil.getChildData();
    }

    class MyexpandAdapter extends BaseExpandableListAdapter{

        /** 获取组视图标签总数 */
        @Override
        public int getGroupCount() {
            return data.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return data[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return data[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return data[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            TextView view = new TextView(context);
            view.setText(strings.get(groupPosition));
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            TextView textView = new TextView(context);
            textView.setText(data[groupPosition][childPosition]);
            return textView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
