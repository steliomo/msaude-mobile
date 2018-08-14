package mz.co.msaude.mobile.consultation.filter;

import mz.co.msaude.mobile.consultation.fragment.FragmentDisplay;
import mz.co.msaude.mobile.consultation.model.ConsultationFilter;

/**
 * Created by Stélio Moiane on 7/21/17.
 */
public interface FragmentDisplayFilter {

    FragmentDisplay getFragmentDisplay(ConsultationFilter consultationFilter);

    void setNextFragmentDisplayFilter(FragmentDisplayFilter fragmentDisplayFilter);
}
