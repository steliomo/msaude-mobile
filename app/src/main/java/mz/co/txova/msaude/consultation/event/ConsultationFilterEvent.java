package mz.co.txova.msaude.consultation.event;

import mz.co.txova.msaude.consultation.model.ConsultationFilter;

/**
 * Created by Stélio Moiane on 7/21/17.
 */
public class ConsultationFilterEvent {

    private ConsultationFilter consultationFilter;

    public ConsultationFilterEvent(ConsultationFilter consultationFilter) {
        this.consultationFilter = consultationFilter;
    }

    public ConsultationFilter getConsultationFilter() {
        return consultationFilter;
    }
}
