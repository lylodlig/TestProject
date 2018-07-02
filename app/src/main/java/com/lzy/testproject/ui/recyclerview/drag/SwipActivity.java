package com.lzy.testproject.ui.recyclerview.drag;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lzy.testproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwipActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        for (int i = 0; i < 50; i++) {
            list.add("sfjlkskfj是德国进口了：" + i);
        }

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        mRecyclerView.setAdapter(adapter);

        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                final int swipeFlags = ItemTouchHelper.LEFT;
                return makeMovementFlags(0, swipeFlags);
            } else return makeMovementFlags(0, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        //滑动删除
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//            int adapterPosition = viewHolder.getAdapterPosition();
//            list.remove(adapterPosition);
//            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //如果dX小于等于删除方块的宽度，那么我们把该方块滑出来
                if (Math.abs(dX) <= 200) {
                    viewHolder.itemView.scrollTo(-(int) dX, 0);
                }
                //如果dX还未达到能删除的距离，此时慢慢增加“眼睛”的大小，增加的最大值为ICON_MAX_SIZE
//                else if (Math.abs(dX) <= recyclerView.getWidth() / 2){
//                    double distance = (recyclerView.getWidth() / 2 -getSlideLimitation(viewHolder));
//                    double factor = ICON_MAX_SIZE / distance;
//                    double diff =  (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor;
//                    if (diff >= ICON_MAX_SIZE)
//                        diff = ICON_MAX_SIZE;
//                    ((MyAdapter.NormalItem)viewHolder).tv.setText("");   //把文字去掉
//                    ((MyAdapter.NormalItem) viewHolder).iv.setVisibility(View.VISIBLE);  //显示眼睛
//                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ((MyAdapter.NormalItem) viewHolder).iv.getLayoutParams();
//                    params.width = (int) (fixWidth + diff);
//                    params.height = (int) (fixWidth + diff);
//                    ((MyAdapter.NormalItem) viewHolder).iv.setLayoutParams(params);
//                }
            } else {
                //拖拽状态下不做改变，需要调用父类的方法
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
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

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyAdapter.ViewHolder(LayoutInflater.from(SwipActivity.this).inflate(R.layout.item_text2, parent, false));
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
            TextView textView = holder.itemView.findViewById(R.id.item);
            textView.setText(list.get(position));
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    //第一个和第二个item不可拖动
//                    if(position!=0&&position!=1){
//                        itemTouchHelper.startDrag(holder);
//                    }
//                    return false;
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
