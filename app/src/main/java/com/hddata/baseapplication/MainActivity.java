package com.hddata.baseapplication;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.personal.basefuntionlibrary.Fragment.ViewPageFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();
        int[]aaa={
                R.mipmap.p0102_1,
                R.mipmap.p0102_2,
                R.mipmap.p0102_1,
                R.mipmap.p0102_2
        };
        bundle.putIntArray(ViewPageFragment.KEY_CONTENT, aaa);
        ViewPageFragment fragment = new ViewPageFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.activity_fragment, fragment);
        transaction.commit();
    }

}
