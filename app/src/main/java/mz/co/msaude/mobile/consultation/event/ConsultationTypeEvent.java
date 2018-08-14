package mz.co.msaude.mobile.consultation.event;

import mz.co.msaude.mobile.consultation.model.ConsultationType;

/**
 * Created by steliomo on 9/17/17.
 */

public class ConsultationTypeEvent {

    private ConsultationType consultationType;

    public ConsultationTypeEvent(ConsultationType consultationType) {
        this.consultationType = consultationType;
    }

    public ConsultationType getConsultationType() {
        return this.consultationType;
    }
}
