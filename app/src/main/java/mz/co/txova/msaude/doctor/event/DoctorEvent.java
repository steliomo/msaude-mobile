package mz.co.txova.msaude.doctor.event;

import mz.co.txova.msaude.doctor.model.Doctor;

/**
 * Created by Stélio Moiane on 7/22/17.
 */
public class DoctorEvent {

    private Doctor doctor;

    public DoctorEvent(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
