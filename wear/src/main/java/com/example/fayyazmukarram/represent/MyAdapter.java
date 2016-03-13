package com.example.fayyazmukarram.represent;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fayyazmukarram on 3/1/16.
 */
public class MyAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT=4;

//    private String tabtitles[] = new String[] {"Rep1","Rep2","Rep3","Election"};
//    Context context;

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {

            case 0:
                WatchOne watch1 = new WatchOne();
                return watch1;
            case 1:
                WatchTwo watch2 = new WatchTwo();
                return watch2;
            case 2:
                WatchThree watch3 = new WatchThree();
                return watch3;
            case 3:
                if (MainActivity.repName.equals("rep1")) {
                    WatchFive watch5 = new WatchFive();
                    return watch5;
                } else {
                    WatchFour watch4 = new WatchFour();
                    return watch4;
        }
    }
                return null;
    }
//    @Override
//    public CharSequence getPageTitle(int i){
//        return tabtitles[i];
//    }

    @Override
    public int getCount() {

        return PAGE_COUNT;
    }
}
