package mz.co.txova.msaude.consultation.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import mz.co.txova.msaude.R;
import mz.co.txova.msaude.fragment.ScheduleConfirmationFragment;
import mz.co.txova.msaude.fragment.TimeTableFragment;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public class ClinicDoctorFragmentDisplay implements FragmentDisplay {

    public static final int PAGES = 2;

    private Context context;

    public ClinicDoctorFragmentDisplay(final Context context) {
        this.context = context;
    }

    @Override
    public Fragment getFragment(int position) {
        switch (position) {
            case 0:
                return new TimeTableFragment();
        }

        return new ScheduleConfirmationFragment();
    }

    @Override
    public String getFragmentTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.time_table);
        }

        return context.getString(R.string.confirmation);
    }

    @Override
    public int getPages() {
        return PAGES;
    }
}
