package com.personal.basefuntionlibrary.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.personal.basefuntionlibrary.R;

/**
 * 可实现图片翻页功能的Fragment
 * Created by 杨培韬 on 2016/12/2.
 */

public class ViewPageFragment extends Fragment {

    public static final String KEY_CONTENT = "view_page_fragment_content_key";
    public static final String KEY_FULL_SCREEN = "view_page_fragment_full_screen_key";

    private int[] contentResource = null;
    private boolean fullScreenMark = false;

    private Context context;
    private ImageView[] signImages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup vgContent = (ViewGroup) inflater.inflate(R.layout.fragment_view_page, container, false);
        initParameter();
        initContent(vgContent);
        initSign(vgContent);
        return vgContent;
    }

    private synchronized Context getCurrentContext() {
        if (context == null) {
            context = getActivity();
        }
        return context;
    }

    private void initParameter() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(KEY_CONTENT)) {
                contentResource = bundle.getIntArray(KEY_CONTENT);
            }
            if (bundle.containsKey(KEY_FULL_SCREEN)) {
                fullScreenMark = bundle.getBoolean(KEY_FULL_SCREEN);
            }
        }
    }

    private void initSign(ViewGroup vg) {
        ViewGroup vgSign = (ViewGroup) vg.findViewById(R.id.fragment_view_page_ly_sign);
        int len = contentResource.length;

        signImages = new ImageView[len];
        for (int i = 0; i < len; i++) {
            ImageView imageView = new ImageView(getCurrentContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(32, 24));
            signImages[i] = imageView;
            if (i == 0) {
                signImages[i].setBackgroundResource(R.mipmap.tab_foucs);
            } else {
                signImages[i].setBackgroundResource(R.mipmap.tab_unfoucs);
            }
            vgSign.addView(signImages[i]);
        }
    }

    private void initContent(ViewGroup vg) {
        ViewPager vpContent = (ViewPager) vg.findViewById(R.id.fragment_view_page_vp_content);

        vpContent.setOnPageChangeListener(new PageChange());
        vpContent.setAdapter(new StartAdapter());
        vpContent.setCurrentItem(0); //设置默认当前页
    }

    private class PageChange implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            int len = contentResource.length;
            for (int i = 0; i < len; i++) {
                if (position != i) {
                    signImages[i].setBackgroundResource(R.mipmap.tab_unfoucs);
                } else {
                    signImages[position].setBackgroundResource(R.mipmap.tab_foucs);
                }
            }
        }
    }

    private class StartAdapter extends PagerAdapter {

        private StartAdapter() {
        }

        @Override
        public int getCount() {
            return contentResource.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //添加页面数据
            ImageView view = new ImageView(getCurrentContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            if (fullScreenMark) {
                view.setBackgroundResource(contentResource[position]);
            } else {
                view.setImageResource(contentResource[position]);
            }

            container.addView(view, position);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //  container.removeView(list[position]);
        }
    }
}

