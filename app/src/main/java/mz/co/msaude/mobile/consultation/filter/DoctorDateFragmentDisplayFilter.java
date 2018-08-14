package mz.co.msaude.mobile.consultation.filter;

import android.content.Context;

import mz.co.msaude.mobile.consultation.fragment.DefaultFragmentDisplay;
import mz.co.msaude.mobile.consultation.fragment.DoctorDateFragmentDisplay;
import mz.co.msaude.mobile.consultation.fragment.FragmentDisplay;
import mz.co.msaude.mobile.consultation.model.ConsultationFilter;

/**
 * Created by Stélio Moiane on 7/21/17.
 */
public class DoctorDateFragmentDisplayFilter implements FragmentDisplayFilter {

    private FragmentDisplayFilter fragmentDisplayFilter;

    private Context context;

    public DoctorDateFragmentDisplayFilter(Context context) {
        this.context = context;
    }

    @Override
    public FragmentDisplay getFragmentDisplay(ConsultationFilter consultationFilter) {

        if (consultationFilter.getDoctor() != null && consultationFilter.getConsultationDate() != null) {
            return new DoctorDateFragmentDisplay(context);
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
