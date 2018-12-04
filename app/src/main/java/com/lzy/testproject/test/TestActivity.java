package com.lzy.testproject.test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lzy.testproject.R;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "lzy";
    private int count = 1;
    private ArrayList<String> tabNameList;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        ViewPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabNameList = new ArrayList<>();
        tabNameList.add("1");
        tabNameList.add("2");
        tabNameList.add("3");
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            TestFragment fragment = new TestFragment();
            return fragment;
        }

        @Override
        public int getCount() {
            return tabNameList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNameList.get(position);
        }
    }
}