package com.hddata.baseapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ViewPager vpStart;
    private ImageView[] imageViews;

    private ViewGroup vgContent;
    private ViewGroup vgSign;

    private List<View> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initApp();
    }

    private void initApp() {

        LayoutInflater inflater = getLayoutInflater();

        //添加页面数据
        list.add(inflater.from(MainActivity.this).inflate(R.layout.item_p0102_1, null));
        list.add(inflater.from(MainActivity.this).inflate(R.layout.item_p0102_2, null));

        vgContent = (ViewGroup) inflater.inflate(R.layout.activity_main, null);
        vgSign = (ViewGroup) vgContent.findViewById(R.id.p0102_ly_sign);
        vpStart = (ViewPager) vgContent.findViewById(R.id.p0102_vp_start);

        imageViews = new ImageView[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(32, 24));
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.mipmap.tab_foucs);
            } else {
                imageViews[i].setBackgroundResource(R.mipmap.tab_unfoucs);
            }
            vgSign.addView(imageViews[i]);
        }

        this.vpStart.setOnPageChangeListener(new PageChange());
        setContentView(vgContent);

        vpStart.setAdapter(new StartAdapter());
        vpStart.setCurrentItem(0); //设置默认当前页

    }

    private void initView() {
        this.vpStart = (ViewPager) findViewById(R.id.p0102_vp_start);
    }


    private class PageChange implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < imageViews.length; i++) {
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource(R.mipmap.tab_unfoucs);
                } else {
                    imageViews[arg0].setBackgroundResource(R.mipmap.tab_foucs);
                }
            }
        }
    }

    private class StartAdapter extends PagerAdapter {

        private StartAdapter() {
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
