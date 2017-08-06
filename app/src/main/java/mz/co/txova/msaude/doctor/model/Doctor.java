package mz.co.txova.msaude.doctor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class Doctor implements Serializable {

    private String firstName;

    private String lastName;

    private String category;

    private List<DoctorAvailability> doctorAvailabilities;

    public Doctor(String firstName, String lastName, String category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
        this.doctorAvailabilities = new ArrayList<>();
    }

    public String getFullName() {
        return "Dr(a). " + this.firstName + " " + this.lastName;
    }

    public String getCategory() {
        return category;
    }

    public List<DoctorAvailability> getDoctorAvailabilities() {
        return Collections.unmodifiableList(this.doctorAvailabilities);
    }

    public void addDoctorAvailabilities(DoctorAvailability... doctorAvailabilities) {
        for (DoctorAvailability doctorAvailability : doctorAvailabilities) {
            this.doctorAvailabilities.add(doctorAvailability);
        }
    }
}
