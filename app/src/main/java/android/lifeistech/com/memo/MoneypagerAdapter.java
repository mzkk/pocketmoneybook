package android.lifeistech.com.memo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mizuki on 2018/05/02.
 */

public class MoneypagerAdapter extends FragmentPagerAdapter {

    public MoneypagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Mainfragment.newInstance();
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
        return "ページ" + (position + 1);
    }
}
