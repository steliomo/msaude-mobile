package mz.co.txova.msaude.consultation.filter;

import android.content.Context;

import mz.co.txova.msaude.consultation.fragment.ClinicFragmentDisplay;
import mz.co.txova.msaude.consultation.fragment.DefaultFragmentDisplay;
import mz.co.txova.msaude.consultation.fragment.FragmentDisplay;
import mz.co.txova.msaude.consultation.model.ConsultationFilter;

/**
 * Created by Stélio Moiane on 7/21/17.
 */
public class ClinicFragmentDisplayFilter implements FragmentDisplayFilter {

    private Context context;

    private FragmentDisplayFilter fragmentDisplayFilter;

    public ClinicFragmentDisplayFilter(Context context) {
        this.context = context;
    }

    @Override
    public FragmentDisplay getFragmentDisplay(ConsultationFilter consultationFilter) {

        if (!consultationFilter.getHealthFacility().trim().isEmpty()) {
            return new ClinicFragmentDisplay(context);
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
