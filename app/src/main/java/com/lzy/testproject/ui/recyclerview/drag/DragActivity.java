package com.lzy.testproject.ui.recyclerview.drag;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lzy.testproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Route(path = "/lzy/constraint/TestActivity")
public class DragActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        for (int i = 0; i < 50; i++) {
            list.add("sfjlkskfj是德国进口了：" + i);
        }

        RecyclerView mRecyclerView=findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
         adapter = new MyAdapter();
        mRecyclerView.setAdapter(adapter);

        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                //四个方向可拖动
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
//                final int swipeFlags = 0;
                final int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                //上下可拖动
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //得到当拖拽的viewHolder的Position
            int fromPosition = viewHolder.getAdapterPosition();
            //拿到当前拖拽到的item的viewHolder
            int toPosition = target.getAdapterPosition();
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(list, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(list, i, i - 1);
                }
            }
            adapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        //滑动删除
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int adapterPosition = viewHolder.getAdapterPosition();
            list.remove(adapterPosition);
            adapter.notifyDataSetChanged();
        }
        /**
         * 长按选中Item的时候开始调用
         *
         * @param viewHolder
         * @param actionState
         */
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setBackgroundColor(Color.RED);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }


        /**
         * 手指松开的时候还原
         * @param recyclerView
         * @param viewHolder
         */
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setBackgroundColor(0);
        }

        //设置不可拖拽
        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }
    });

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(DragActivity.this).inflate(R.layout.item_text,parent,false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            TextView textView=holder.itemView.findViewById(R.id.tv);
            textView.setText(list.get(position));
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //第一个和第二个item不可拖动
                    if(position!=0&&position!=1){
                        itemTouchHelper.startDrag(holder);
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
