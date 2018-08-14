package mz.co.msaude.mobile.consultation.filter;

import android.content.Context;

import mz.co.msaude.mobile.consultation.fragment.ClinicDoctorFragmentDisplay;
import mz.co.msaude.mobile.consultation.fragment.DefaultFragmentDisplay;
import mz.co.msaude.mobile.consultation.fragment.FragmentDisplay;
import mz.co.msaude.mobile.consultation.model.ConsultationFilter;

/**
 * Created by Stélio Moiane on 7/21/17.
 */
public class ClinicDoctorFragmentDisplayFilter implements FragmentDisplayFilter {

    private FragmentDisplayFilter fragmentDisplayFilter;

    private Context context;

    public ClinicDoctorFragmentDisplayFilter(Context context) {
        this.context = context;
    }

    @Override
    public FragmentDisplay getFragmentDisplay(ConsultationFilter consultationFilter) {

        if (consultationFilter.getHealthFacility() != null && consultationFilter.getDoctor() != null) {
            return new ClinicDoctorFragmentDisplay(context);
        }

        if (fragmentDisplayFilter != null) {
            return fragmentDisplayFilter.getFragmentDisplay(consultationFilter);
        }

        return new DefaultFragmentDisplay(context);
    }

    @Override
    public void setNextFragmentDisplayFilter(FragmentDisplayFilter fragmentDisplayFilter) {
        this.fragmentDisplayFilter = fragmentDisplayFilter;
    }
}
