package mz.co.txova.msaude.doctor.dto;

import java.util.Collections;
import java.util.List;

import mz.co.txova.msaude.consultation.model.QueryResult;
import mz.co.txova.msaude.doctor.model.Doctor;
import mz.co.txova.msaude.healthfacility.model.HealthFacility;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public class DoctorDTO implements QueryResult {

    private String city;

    private String consultationType;

    private HealthFacility healthFacility;

    private List<Doctor> doctors;

    public DoctorDTO(final String city, final String consultationType, final HealthFacility healthFacility, final List<Doctor> doctors) {
        this.city = city;
        this.consultationType = consultationType;
        this.healthFacility = healthFacility;
        this.doctors = doctors;
    }

    public String getCity() {
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
}
