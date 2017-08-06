package mz.co.txova.msaude.consultation.service;

import mz.co.txova.msaude.consultation.model.ConsultationFilter;
import mz.co.txova.msaude.consultation.model.QueryResult;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public interface ConsultationService {

    QueryResult findConsultationsByFilter(ConsultationFilter consultationFilter);

}
