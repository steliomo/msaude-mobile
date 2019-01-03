package mz.co.msaude.mobile.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import mz.co.msaude.mobile.fragment.BaseFragment;

public class ServiceViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private List<String> fragmentTitles;

    public ServiceViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragments = new ArrayList<>();
        fragmentTitles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    public void addFragment(BaseFragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }
}
