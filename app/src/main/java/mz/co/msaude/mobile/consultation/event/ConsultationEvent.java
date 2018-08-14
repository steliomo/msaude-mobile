package mz.co.msaude.mobile.consultation.event;

import mz.co.msaude.mobile.consultation.model.Consultation;

/**
 * Created by Stélio Moiane on 7/21/17.
 */
public class ConsultationEvent {

    private Consultation consultation;

    public ConsultationEvent(Consultation consultation) {
        this.consultation = consultation;
    }

    public Consultation getConsultation() {
        return consultation;
    }
}
