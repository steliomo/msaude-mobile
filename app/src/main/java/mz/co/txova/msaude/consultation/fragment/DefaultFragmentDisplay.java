package mz.co.txova.msaude.consultation.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import mz.co.txova.msaude.R;
import mz.co.txova.msaude.fragment.DoctorFragment;
import mz.co.txova.msaude.fragment.HealthFacilityFragment;
import mz.co.txova.msaude.fragment.ScheduleConfirmationFragment;
import mz.co.txova.msaude.fragment.TimeTableFragment;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public class DefaultFragmentDisplay implements FragmentDisplay {

    public static final int PAGES = 4;

    private Context context;

    public DefaultFragmentDisplay(final Context context) {
        this.context = context;
    }

    @Override
    public Fragment getFragment(int position) {
        switch (position) {

            case 0:
                return new HealthFacilityFragment();

            case 1:
                return new DoctorFragment();

            case 2:
                return new TimeTableFragment();
        }

        return new ScheduleConfirmationFragment();
    }

    @Override
    public String getFragmentTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.clinics);

            case 1:
                return context.getString(R.string.doctors);

            case 2:
                return context.getString(R.string.time_table);
        }

        return context.getString(R.string.confirmation);
    }

    @Override
    public int getPages() {
        return PAGES;
    }
}
