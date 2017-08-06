package mz.co.txova.msaude.consultation.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public interface FragmentDisplay {

    Fragment getFragment(int position);

    String getFragmentTitle(int position);

    int getPages();
}
