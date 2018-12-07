package com.lzy.testproject.ui.recyclerview.sortedlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.testproject.R;

import java.util.List;

/**
 * Created by LiZhiyu on 2018/12/7.
 */
public class SortedAdapter extends RecyclerView.Adapter<SortedAdapter.ViewHolder> {

    // 数据源使用SortedList
    private SortedList<City> mSortedList;
    private LayoutInflater mInflater;

    public SortedAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }

    public void setSortedList(SortedList<City> mSortedList) {
        this.mSortedList = mSortedList;
    }

    /**
     * 批量更新操作，例如：如果在循环中添加多个项，并且它们被放置到连续的索引中.
     * <pre>
     *     mSortedList.beginBatchedUpdates();
     *     try {
     *         mSortedList.add(item1)
     *         mSortedList.add(item2)
     *         mSortedList.remove(item3)
     *         ...
     *     } finally {
     *         mSortedList.endBatchedUpdates();
     *     }
     * </pre>
     * */
    public void setData(City mData){
        mSortedList.beginBatchedUpdates();
        mSortedList.addAll(mData);
        mSortedList.endBatchedUpdates();
    }

    public void setData(List<City> mData){
        mSortedList.beginBatchedUpdates();
        mSortedList.addAll(mData);
        mSortedList.endBatchedUpdates();
    }

    public void removeData(int index){
        mSortedList.removeItemAt(index);
    }

    public void clear(){
        mSortedList.clear();
    }

    @Override
    @NonNull
    public SortedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SortedAdapter.ViewHolder holder, final int position) {
        City bean = mSortedList.get(position);
        holder.mTvName.setText(bean.getCityName() + "(" + bean.getFirstLetter() + ")");
    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;

        ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }

}