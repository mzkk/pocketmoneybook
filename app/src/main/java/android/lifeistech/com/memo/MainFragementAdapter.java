package android.lifeistech.com.memo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;


public class MainFragementAdapter extends FragmentPagerAdapter {

    public MainFragementAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Homefragment.newInstance();

            case 1:
                return Mainfragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "HOME";
        }else{
            return "LIST";
        }
    }
}
