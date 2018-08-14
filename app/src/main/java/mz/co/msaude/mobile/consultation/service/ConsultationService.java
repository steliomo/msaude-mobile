package mz.co.msaude.mobile.consultation.service;

import mz.co.msaude.mobile.consultation.model.ConsultationFilter;
import mz.co.msaude.mobile.consultation.model.QueryResult;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public interface ConsultationService {

    QueryResult findConsultationsByFilter(ConsultationFilter consultationFilter);

}
