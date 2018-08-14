package mz.co.msaude.mobile.doctor.event;

import mz.co.msaude.mobile.consultation.model.Hour;

/**
 * Created by Stélio Moiane on 7/26/17.
 */
public class DoctorAvailabilityTimeEvent {

    private Hour hour;

    public DoctorAvailabilityTimeEvent(Hour hour) {
        this.hour = hour;
    }

    public Hour getHour() {
        return hour;
    }
}
