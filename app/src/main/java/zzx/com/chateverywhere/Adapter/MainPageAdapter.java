package zzx.com.chateverywhere.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by zhangzhixin on 2017/7/18.
 */

public class MainPageAdapter extends FragmentPagerAdapter{
    private Context c;
    private ArrayList<Fragment>fragments;

    public MainPageAdapter(FragmentManager fm, Context c, ArrayList<Fragment> fragments) {
        super(fm);
        this.c = c;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
