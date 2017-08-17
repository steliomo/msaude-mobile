package mz.co.txova.msaude.consultation.event;

import mz.co.txova.msaude.consultation.model.Consultation;

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
