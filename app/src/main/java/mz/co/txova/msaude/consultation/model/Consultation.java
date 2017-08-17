package mz.co.txova.msaude.consultation.model;


import mz.co.txova.msaude.doctor.model.Doctor;
import mz.co.txova.msaude.healthfacility.model.HealthFacility;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class Consultation {

    private String city;
    private String consultationType;
    private Doctor doctor;
    private HealthFacility healthFacility;
    private String scheduledDate;
    private Hour hour;

    public Consultation(String city, String consultationType) {
        this.city = city;
        this.consultationType = consultationType;
    }

    public String getCity() {
        return city;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
    }
}
