package mz.co.msaude.mobile.consultation.fragment;

import android.support.v4.app.Fragment;

import com.stepstone.stepper.Step;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public interface FragmentDisplay extends Step {

    Fragment getFragment(int position);

    String getFragmentTitle(int position);

    int getPages();
}
