package mz.co.msaude.mobile.consultation.service;

import java.util.List;

import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.ConsultationStatus;
import mz.co.msaude.mobile.listner.ResponseListner;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public interface ConsultationService {

    void scheduleConsultation(final String token, final Consultation consultation, final ResponseListner<Consultation> listner);

    void findConsultationsByStatus(final String token, final ConsultationStatus consultationStatus, final ResponseListner<List<Consultation>> listner);

    void cancelConsultation(final String token, final Long id, final Consultation consultation, final ResponseListner<Consultation> listner);
}
