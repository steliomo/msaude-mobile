package mz.co.msaude.mobile.consultation.model;

import java.io.Serializable;

import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.healthfacility.model.City;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public class ConsultationFilter implements Serializable {

    public static final String FILTER = "FILTER";

    private City city;
    private String consultationType;
    private HealthFacility healthFacility;
    private Doctor doctor;
    private String consultationDate;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }
}