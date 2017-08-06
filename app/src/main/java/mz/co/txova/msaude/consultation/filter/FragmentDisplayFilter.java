package mz.co.txova.msaude.consultation.filter;

import mz.co.txova.msaude.consultation.fragment.FragmentDisplay;
import mz.co.txova.msaude.consultation.model.ConsultationFilter;

/**
 * Created by Stélio Moiane on 7/21/17.
 */
public interface FragmentDisplayFilter {

    FragmentDisplay getFragmentDisplay(ConsultationFilter consultationFilter);

    void setNextFragmentDisplayFilter(FragmentDisplayFilter fragmentDisplayFilter);
}
