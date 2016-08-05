package liu.myapplication.pulltorefreshlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * 封装了ScrollView的下拉刷新
 * 
 * @author Li Hong
 * @since 2013-8-22
 */
public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView> {

	private boolean isPullDownEnable = true;

	public boolean isPullDownEnable() {
		return isPullDownEnable;
	}

	public void setPullDownEnable(boolean isPullDownEnable) {
		this.isPullDownEnable = isPullDownEnable;
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            context
	 */
	public PullToRefreshScrollView(Context context) {
		this(context, null);
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            context
	 * @param attrs
	 *            attrs
	 */
	public PullToRefreshScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            context
	 * @param attrs
	 *            attrs
	 * @param defStyle
	 *            defStyle
	 */
	public PullToRefreshScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * @see com.nj1s.lib.pullrefresh.PullToRefreshBase#createRefreshableView(android.content.Context,
	 *      android.util.AttributeSet)
	 */
	@Override
	protected ScrollView createRefreshableView(Context context, AttributeSet attrs) {
		ScrollView scrollView = new ScrollView(context);
		return scrollView;
	}

	/**
	 * @see com.nj1s.lib.pullrefresh.PullToRefreshBase#isReadyForPullDown()
	 */
	@Override
	protected boolean isReadyForPullDown() {
		return isPullDownEnable;
	}

	/**
	 * @see com.nj1s.lib.pullrefresh.PullToRefreshBase#isReadyForPullUp()
	 */
	@Override
	protected boolean isReadyForPullUp() {
		View scrollViewChild = mRefreshableView.getChildAt(0);
		if (null != scrollViewChild) {
			return mRefreshableView.getScrollY() >= (scrollViewChild.getHeight() - getHeight());
		}

		return false;
	}

	@Override
	protected void startLoading() {
		super.startLoading();
	}

	@Override
	public void setScrollLoadEnabled(boolean scrollLoadEnabled) {
		if (isScrollLoadEnabled() == scrollLoadEnabled) {
			return;
		}

		super.setScrollLoadEnabled(scrollLoadEnabled);
	}

	@Override
	protected LoadingLayout createHeaderLoadingLayout(Context context, AttributeSet attrs) {
		return new RotateLoadingLayout(context);
	}
}
