package com.utils.library.adapter.recyclerview;

public interface MultiItemTypeSupport<T>
{
	int getLayoutId(int itemType);

	int getItemViewType(int position, T t);
}