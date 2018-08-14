package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

import mz.co.msaude.mobile.consultation.fragment.FragmentDisplay;

/**
 * Created by Stélio Moiane on 6/14/17.
 */
public class SwipeAdapter extends AbstractFragmentStepAdapter {

    private FragmentDisplay fragmentDisplay;

    public SwipeAdapter(FragmentManager fragmentManager, FragmentDisplay fragmentDisplay, Context context) {
        super(fragmentManager, context);
        this.fragmentDisplay = fragmentDisplay;
    }

//    @Override
//    public Fragment getItem(int position) {
//        return fragmentDisplay.getFragment(position);
//    }

    @Override
    public Step createStep(int position) {
        return (Step) fragmentDisplay.getFragment(position);
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
