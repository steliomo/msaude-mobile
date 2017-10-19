package mz.co.txova.msaude.consultation.dto;

import java.util.Collections;
import java.util.List;

import mz.co.txova.msaude.consultation.model.QueryResult;
import mz.co.txova.msaude.doctor.model.Doctor;
import mz.co.txova.msaude.doctor.model.DoctorAvailability;
import mz.co.txova.msaude.healthfacility.model.HealthFacility;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public class HealthFacilityDTO implements QueryResult {

    private String city;

    private String consultationType;

    private Doctor doctor;

    private HealthFacility healthFacility;

    private DoctorAvailability doctorAvailability;

    private List<HealthFacility> healthFacilities;

    public HealthFacilityDTO(String city, String consultationType, List<HealthFacility> healthFacilities) {
        this.city = city;
        this.consultationType = consultationType;
        this.healthFacilities = healthFacilities;
    }

    public HealthFacilityDTO() {
    }

    public String getCity() {
        return city;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public List<HealthFacility> getHealthFacilities() {
        return Collections.unmodifiableList(healthFacilities);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public void setDoctorAvailability(DoctorAvailability doctorAvailability) {
        this.doctorAvailability = doctorAvailability;
    }

    public DoctorAvailability getDoctorAvailability() {
        return doctorAvailability;
    }
}