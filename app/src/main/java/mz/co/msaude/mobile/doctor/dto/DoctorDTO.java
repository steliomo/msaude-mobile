package mz.co.msaude.mobile.doctor.dto;

import java.util.Collections;
import java.util.List;

import mz.co.msaude.mobile.consultation.model.QueryResult;
import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.doctor.model.DoctorAvailability;
import mz.co.msaude.mobile.healthfacility.model.City;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public class DoctorDTO implements QueryResult {

    private City city;

    private String consultationType;

    private HealthFacility healthFacility;

    private Doctor doctor;

    private DoctorAvailability doctorAvailability;

    private List<Doctor> doctors;

    public DoctorDTO(final City city, final String consultationType, final HealthFacility healthFacility, final List<Doctor> doctors) {
        this.city = city;
        this.consultationType = consultationType;
        this.healthFacility = healthFacility;
        this.doctors = doctors;
    }

    public DoctorDTO() {
    }

    public City getCity() {
        return city;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public List<Doctor> getDoctors() {
        return Collections.unmodifiableList(doctors);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctorAvailability(DoctorAvailability doctorAvailability) {
        this.doctorAvailability = doctorAvailability;
    }

    public DoctorAvailability getDoctorAvailability() {
        return doctorAvailability;
    }
}
