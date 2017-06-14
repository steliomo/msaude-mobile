package mz.co.txova.msaude.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mz.co.txova.msaude.fragment.ConsultationTypeFragment;
import mz.co.txova.msaude.fragment.HealthFacilityFragment;

/**
 * Created by Stélio Moiane on 6/14/17.
 */
public class SwipeAdapter extends FragmentPagerAdapter {

    public static final int PAGES = 2;

    public SwipeAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ConsultationTypeFragment();
        }

        Fragment fragment = new HealthFacilityFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Especialidade";
    }
}
