package com.lzy.testproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
		List<String> list=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			list.add("i" + i);
		}

		String s = "3";


		if (list != null) {
			for (String ignored : list) {
				s = ignored;
			}
		}

		s = "10";

	}

	private void test(){

	}
}
