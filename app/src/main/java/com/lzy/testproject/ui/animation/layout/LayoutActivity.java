package com.lzy.testproject.ui.animation.layout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.lzy.testproject.R;
import com.lzy.testproject.other.disklrucache.DiskLruCache;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setNumColumns(4);
        gridView.setAdapter(new MAdapter(this));
        //代码中添加
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_home);
//        LayoutAnimationController controller = new LayoutAnimationController(animation, 0.2f);
//        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        gridView.setLayoutAnimation(controller);

    }


    class MAdapter extends BaseAdapter {
        Context context;

        public MAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.img, parent, false);
            ImageView imageView = view.findViewById(R.id.iv);

            return view;
        }
    }
}
