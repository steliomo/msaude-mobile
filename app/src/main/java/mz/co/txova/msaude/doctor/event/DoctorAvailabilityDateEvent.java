package mz.co.txova.msaude.doctor.event;

import mz.co.txova.msaude.doctor.model.DoctorAvailability;

/**
 * Created by Stélio Moiane on 7/26/17.
 */
public class DoctorAvailabilityDateEvent {

    private DoctorAvailability doctorAvailability;

    public DoctorAvailabilityDateEvent(DoctorAvailability doctorAvailability) {
        this.doctorAvailability = doctorAvailability;
    }

    public DoctorAvailability getDoctorAvailability() {
        return doctorAvailability;
    }
}
