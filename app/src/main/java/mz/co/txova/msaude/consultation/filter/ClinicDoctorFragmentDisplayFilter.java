package mz.co.txova.msaude.consultation.filter;

import android.content.Context;

import mz.co.txova.msaude.consultation.fragment.ClinicDoctorFragmentDisplay;
import mz.co.txova.msaude.consultation.fragment.DefaultFragmentDisplay;
import mz.co.txova.msaude.consultation.fragment.FragmentDisplay;
import mz.co.txova.msaude.consultation.model.ConsultationFilter;

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

        if (!consultationFilter.getHealthFacility().trim().isEmpty() && !consultationFilter.getDoctorName().trim().isEmpty()) {
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
