package com.tianfei.go_up;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.tianfei.go_up.View.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.user_viewpager) ViewPager viewPager;
    @BindView(R2.id.navigation) BottomNavigationView navigation;

    private ViewPagerAdapter viewPagerAdapter;
    private MenuItem menuItem;

    private FragmentTimer fragmentTimer;
    private FragmentLine fragmentLine;
    private FragmentPie fragmentPie;
    private FragmentPerson fragmentPerson;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            menuItem = item;
            switch (item.getItemId()) {
                case R.id.navigation_timer:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_line:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_pie:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_me:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    private ViewPager.OnPageChangeListener mOnpageChangeListener
            = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            if (menuItem != null) {
                menuItem.setChecked(false);
            } else {
                navigation.getMenu().getItem(0).setChecked(false);
            }
            menuItem = navigation.getMenu().getItem(i);
            menuItem.setChecked(true);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(mOnpageChangeListener);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        List<Fragment> list = new ArrayList<>();
        list.add(FragmentTimer.newInstance());
        list.add(FragmentLine.newInstance());
        list.add(FragmentPie.newInstance());
        list.add(FragmentPerson.newInstance());
        viewPagerAdapter.setList(list);

    }


}
