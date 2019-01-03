package mz.co.msaude.mobile.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import mz.co.msaude.mobile.delegate.ScheduleDelegate;

public class FragmentTileUtil {

    public Fragment getFragmentWithTile(Fragment fragment, String title) {
        Bundle bundle = new Bundle();

        bundle.putString(ScheduleDelegate.TITLE, title);
        fragment.setArguments(bundle);

        return fragment;
    }
}
