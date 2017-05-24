package com.example.copengxiaolue.personaldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.copengxiaolue.personaldemo.category.CategoryFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentPagerAdapter mFragmentAdapter;

    private static final String CATEGORY_APP = "App";
    private static final String CATEGORY_Android = "Android";
    private static final String CATEGORY_IOS = "iOS";
    private static final String CATEGORY_WEB = "前端";

    private static final String[] CATEGORY_TITLES = new String[]{
            CATEGORY_APP,
            CATEGORY_Android,
            CATEGORY_IOS,
            CATEGORY_WEB
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        mFragmentAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return CategoryFragment.newInstance(CATEGORY_TITLES[position]);
            }

            @Override
            public int getCount() {
                return CATEGORY_TITLES.length;
            }
        };
        viewPager.setAdapter(mFragmentAdapter);
    }
}
