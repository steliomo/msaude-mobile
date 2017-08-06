package mz.co.txova.msaude.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mz.co.txova.msaude.consultation.fragment.FragmentDisplay;

/**
 * Created by Stélio Moiane on 6/14/17.
 */
public class SwipeAdapter extends FragmentPagerAdapter {

    private FragmentDisplay fragmentDisplay;

    public SwipeAdapter(FragmentManager fragmentManager, FragmentDisplay fragmentDisplay) {
        super(fragmentManager);
        this.fragmentDisplay = fragmentDisplay;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentDisplay.getFragment(position);
    }

    @Override
    public int getCount() {
        return this.fragmentDisplay.getPages();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.fragmentDisplay.getFragmentTitle(position);
    }
}
