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
    public static final String TAB_ALL = "      全部      ";
    public static final String TAB_ALLFLLOW = "  全部跟进  ";
    public static final String TAB_WAIT_FOLLOW = "全部待跟进";
    public static final String TAB_OVER_FOLLOW = "超期未跟进";
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

        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabNameList = new ArrayList<>();
        tabNameList.add(TAB_ALL);
        tabNameList.add(TAB_ALLFLLOW);
        tabNameList.add(TAB_WAIT_FOLLOW);
        tabNameList.add(TAB_OVER_FOLLOW);
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
            return TestFragment.newInstance("111" + position);
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