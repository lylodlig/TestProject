package com.lzy.testproject.ui.recyclerview.drag;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.lzy.testproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwipActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private MyAdapter adapter;
    private String TAG="lzy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        for (int i = 0; i < 50; i++) {
            list.add("sfjlkskfj是德国进口了：" + i);
        }

        final RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        mRecyclerView.setAdapter(adapter);

        //判断滑动到顶部，底部
        //方法1
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i(TAG, "--------------------------------------");
                if(mRecyclerView.canScrollVertically(1)){
                    Log.i(TAG, "direction 1: true");
                }else {
                    Log.i(TAG, "direction 1: false");//滑动到底部
                }
                if(mRecyclerView.canScrollVertically(-1)){
                    Log.i(TAG, "direction -1: true");
                }else {
                    Log.i(TAG, "direction -1: false");//滑动到顶部
                }
            }
        });

        //方法2
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i(TAG, "--------------------------------------");
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.i(TAG, "firstCompletelyVisibleItemPosition: "+firstCompletelyVisibleItemPosition);
                if(firstCompletelyVisibleItemPosition==0)
                    Log.i(TAG, "滑动到顶部");

                int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                Log.i(TAG, "lastCompletelyVisibleItemPosition: "+lastCompletelyVisibleItemPosition);
                if(lastCompletelyVisibleItemPosition==layoutManager.getItemCount()-1)
                    Log.i(TAG, "滑动到底部");
            }
        });
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyAdapter.ViewHolder(LayoutInflater.from(SwipActivity.this).inflate(R.layout.item_text2, parent, false));
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
            View contentView = holder.itemView.findViewById(R.id.swipe_content);
            TextView title = holder.itemView.findViewById(R.id.title);
            Button btnDelete = holder.itemView.findViewById(R.id.btnDelete);
            Button btnUnRead = holder.itemView.findViewById(R.id.btnUnRead);
            Button btnTop = holder.itemView.findViewById(R.id.btnTop);

            //这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑
            ((SwipeMenuView)holder.itemView).setIos(true).setLeftSwipe(position % 2 == 0 ? true : false);

            title.setText(list.get(position) + (position % 2 == 0 ? "我只能右滑动" : "我只能左滑动"));

            //隐藏控件
            btnUnRead.setVisibility(position % 3 == 0 ? View.GONE : View.VISIBLE);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    adapter.notifyItemRemoved(position);
                        //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                        //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                        //((CstSwipeDelMenu) holder.itemView).quickClose();
                }
            });
            //注意事项，设置item点击，不能对整个holder.itemView设置咯，只能对第一个子View，即原来的content设置，这算是局限性吧。
            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SwipActivity.this,"店家"+position,Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onClick() called with: v = [" + v + "]");
                }
            });
            //置顶：
            btnTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SwipActivity.this,"置顶"+position,Toast.LENGTH_SHORT).show();

                }
            });
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
