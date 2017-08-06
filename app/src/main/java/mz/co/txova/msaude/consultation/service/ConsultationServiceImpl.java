package mz.co.txova.msaude.consultation.service;

import javax.inject.Inject;
import javax.inject.Named;

import mz.co.txova.msaude.consultation.model.ConsultationFilter;
import mz.co.txova.msaude.consultation.model.QueryResult;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public class ConsultationServiceImpl implements ConsultationService {

    @Inject
    @Named("defaultConsultationFilter")
    ConsultationQueryFilter defaultConsultationFilter;

    @Inject
    @Named("healthFacilityConsultationQueryFilter")
    ConsultationQueryFilter healthFacilityConsultationQueryFilter;

    @Inject
    @Named("doctorConsultationQueryFilter")
    ConsultationQueryFilter doctorConsultationQueryFilter;

    @Inject
    public ConsultationServiceImpl() {
    }

    @Override
    public QueryResult findConsultationsByFilter(ConsultationFilter consultationFilter) {

        //setup the chain
        healthFacilityConsultationQueryFilter.setNextConsultationQueryFilter(defaultConsultationFilter);
        doctorConsultationQueryFilter.setNextConsultationQueryFilter(healthFacilityConsultationQueryFilter);

        return doctorConsultationQueryFilter.find(consultationFilter);
    }
}
