package mz.co.msaude.mobile.healthfacility.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mz.co.msaude.mobile.doctor.model.Doctor;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class HealthFacility implements Serializable {

    private String name;

    private String email;

    private String address;

    private String contact;

    private City city;

    private List<Doctor> doctors;

    public HealthFacility(String name, String email, String address, String contact) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }

    public HealthFacility(String name) {
        this.name = name;
        this.doctors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public List<Doctor> getDoctors() {
        return Collections.unmodifiableList(this.doctors);
    }

    public void addDoctors(final Doctor... doctors) {
        for (Doctor doctor : doctors) {
            this.doctors.add(doctor);
        }
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
