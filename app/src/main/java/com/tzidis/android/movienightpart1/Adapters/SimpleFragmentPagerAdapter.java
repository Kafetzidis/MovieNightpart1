package com.tzidis.android.movienightpart1.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tzidis.android.movienightpart1.Fragments.MostPopularFragment;
import com.tzidis.android.movienightpart1.Fragments.NowPlayingFragment;
import com.tzidis.android.movienightpart1.Fragments.TopRatedFragment;
import com.tzidis.android.movienightpart1.Fragments.UpcomingFragment;
import com.tzidis.android.movienightpart1.R;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 4;
    private String tabTitles[];

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        Resources res = c.getResources();
        tabTitles = res.getStringArray(R.array.tabTitles);
    }



    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MostPopularFragment();
        } else if (position == 1){
            return new TopRatedFragment();
        } else if (position == 2){
            return new NowPlayingFragment();
        } else{
            return new UpcomingFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
