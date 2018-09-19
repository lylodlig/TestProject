package com.lzy.testproject.ui.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lzy.testproject.R;

public class TestActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		SearchView searchView = findViewById(R.id.searchView);
		TextView textView = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
		textView.setHint("东方航空是");
		textView.setTextSize(16);
//		searchView.setIconified(false);
		searchView.setIconifiedByDefault(false);

//		searchView.onActionViewExpanded();
		searchView.setQueryHint("sdfsfs");
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				Log.i("lzy", "onQueryTextChange: ---------");
				return false;
			}
		});
		searchView.setOnCloseListener(new SearchView.OnCloseListener() {
			@Override
			public boolean onClose() {
				Log.i("lzy", "onClose: ---------");
				return false;
			}
		});

//		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

		final View view = findViewById(R.id.view);

		view.post(new Runnable() {
			@Override
			public void run() {
				view.requestFocus();

			}
		});

	}
}
