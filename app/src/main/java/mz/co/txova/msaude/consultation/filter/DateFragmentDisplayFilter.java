package mz.co.txova.msaude.consultation.filter;

import android.content.Context;

import mz.co.txova.msaude.consultation.fragment.DateFragmentDisplay;
import mz.co.txova.msaude.consultation.fragment.DefaultFragmentDisplay;
import mz.co.txova.msaude.consultation.fragment.FragmentDisplay;
import mz.co.txova.msaude.consultation.model.ConsultationFilter;

/**
 * Created by Stélio Moiane on 7/21/17.
 */
public class DateFragmentDisplayFilter implements FragmentDisplayFilter {

    private FragmentDisplayFilter fragmentDisplayFilter;

    private Context context;

    public DateFragmentDisplayFilter(Context context) {
        this.context = context;
    }

    @Override
    public FragmentDisplay getFragmentDisplay(ConsultationFilter consultationFilter) {

        if (!consultationFilter.getConsultationDate().trim().isEmpty()) {
            return new DateFragmentDisplay(context);
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
