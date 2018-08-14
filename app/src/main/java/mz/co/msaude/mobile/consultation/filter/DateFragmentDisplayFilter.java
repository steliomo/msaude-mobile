package mz.co.msaude.mobile.consultation.filter;

import android.content.Context;

import mz.co.msaude.mobile.consultation.fragment.DateFragmentDisplay;
import mz.co.msaude.mobile.consultation.fragment.DefaultFragmentDisplay;
import mz.co.msaude.mobile.consultation.fragment.FragmentDisplay;
import mz.co.msaude.mobile.consultation.model.ConsultationFilter;

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

        if (consultationFilter.getConsultationDate() != null) {
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
