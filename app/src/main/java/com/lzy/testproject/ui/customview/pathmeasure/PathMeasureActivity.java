package com.lzy.testproject.ui.customview.pathmeasure;

import android.content.Context;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.lzy.testproject.R;

public class PathMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_measure);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Path path = new Path();
        path.addCircle(500, 800, 300, Path.Direction.CW);
        PathLayoutManager mPathLayoutManager = new PathLayoutManager(path, 40);
        recyclerView.setLayoutManager(mPathLayoutManager);
        recyclerView.setAdapter(new MyAdapter(this));

        ProgressBar mLoadingProgressBar = new ProgressBar(this);
        mLoadingProgressBar.setVisibility(View.VISIBLE);
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 100;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

    }
}
