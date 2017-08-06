package mz.co.txova.msaude.consultation.filter;

import android.content.Context;

import mz.co.txova.msaude.consultation.fragment.AllFragmentDisplay;
import mz.co.txova.msaude.consultation.fragment.DefaultFragmentDisplay;
import mz.co.txova.msaude.consultation.fragment.FragmentDisplay;
import mz.co.txova.msaude.consultation.model.ConsultationFilter;

/**
 * Created by Stélio Moiane on 7/21/17.
 */
public class AllFragmentDisplayFilter implements FragmentDisplayFilter {

    private FragmentDisplayFilter fragmentDisplayFilter;

    private Context context;

    public AllFragmentDisplayFilter(Context context) {
        this.context = context;
    }

    @Override
    public FragmentDisplay getFragmentDisplay(ConsultationFilter consultationFilter) {

        if (!consultationFilter.getHealthFacility().trim().isEmpty() && !consultationFilter.getDoctorName().trim().isEmpty() && !consultationFilter.getConsultationDate().trim().isEmpty()) {
            return new AllFragmentDisplay(context);
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
