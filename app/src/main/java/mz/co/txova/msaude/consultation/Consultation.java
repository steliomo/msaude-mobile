package mz.co.txova.msaude.consultation;


import java.util.Date;

import mz.co.txova.msaude.doctor.model.Doctor;
import mz.co.txova.msaude.healthfacility.HealthFacility;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class Consultation {

    private ConsultationType consultationType;
    private Doctor doctor;
    private HealthFacility healthFacility;
    private Date scheduledDate;

    public Consultation(ConsultationType consultationType, Doctor doctor, HealthFacility healthFacility, Date scheduledDate) {
        this.consultationType = consultationType;
        this.doctor = doctor;
        this.healthFacility = healthFacility;
        this.scheduledDate = scheduledDate;
    }

    public ConsultationType getConsultationType() {
        return consultationType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }
}
