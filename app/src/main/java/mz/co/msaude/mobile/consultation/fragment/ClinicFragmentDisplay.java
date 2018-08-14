package mz.co.msaude.mobile.consultation.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.stepstone.stepper.VerificationError;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.fragment.DoctorFragment;
import mz.co.msaude.mobile.fragment.ScheduleConfirmationFragment;
import mz.co.msaude.mobile.fragment.TimeTableFragment;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public class ClinicFragmentDisplay implements FragmentDisplay {

    public static final int PAGES = 3;

    private Context context;

    public ClinicFragmentDisplay(final Context context) {
        this.context = context;
    }

    @Override
    public Fragment getFragment(int position) {
        switch (position) {
            case 0:
                return new DoctorFragment();

            case 1:
                return new TimeTableFragment();
        }

        return new ScheduleConfirmationFragment();
    }

    @Override
    public String getFragmentTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.doctors);

            case 1:
                return context.getString(R.string.time_table);
        }

        return context.getString(R.string.confirmation);
    }

    @Override
    public int getPages() {
        return PAGES;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}