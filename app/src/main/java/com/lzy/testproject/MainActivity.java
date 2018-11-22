package com.lzy.testproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.annotation.BindView;
import com.lzy.annotation.Losd;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	@BindView(R.id.tv)
	TextView textView;

	@BindView(R.id.tv1)
	TextView textView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


	}

	private void test(){

	}
}
