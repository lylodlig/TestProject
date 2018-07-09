## 简介
DiffUtil是recyclerview support library v7 24.2.0版本中新增的类，DiffUtil是一个工具类，当你的RecyclerView需要更新数据时，将新旧数据集传给它，它就能快速告知adapter有哪些数据需要更新。就相当于如果改变了就对某个item刷新，没改变就没刷新，可以简称为局部刷新。

以前我们调用的mAdapter.notifyDataSetChanged()相当于无脑刷新，现在使用这个工具类可以实现局部刷新，
它会自动计算新老数据集的差异，并根据差异情况，自动调用以下四个方法：

```
adapter.notifyItemRangeInserted(position, count);
adapter.notifyItemRangeRemoved(position, count);
adapter.notifyItemMoved(fromPosition, toPosition);
adapter.notifyItemRangeChanged(position, count, payload);
```

## Diffutils.Callback
主要就是需要实现这个接口，包含以下四个方法：

- `public abstract int getOldListSize();`获得老的数据源大小

- `public abstract int getNewListSize();`获得新的数据源大小

- `public abstract boolean areItemsTheSame(int oldItemPosition, int newItemPosition);`用于判断是否是相同的Item

- `public abstract boolean areContentsTheSame(int oldItemPosition, int newItemPosition);`用于判断Item的内容是否发生变化