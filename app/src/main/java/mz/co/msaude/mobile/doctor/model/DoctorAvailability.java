package mz.co.msaude.mobile.doctor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mz.co.msaude.mobile.consultation.model.Availability;
import mz.co.msaude.mobile.consultation.model.Hour;

/**
 * Created by Stélio Moiane on 7/19/17.
 */
public class DoctorAvailability implements Availability {

    private String dateAvailability;

    private List<Hour> hours;

    public DoctorAvailability(String dateAvailability) {
        this.dateAvailability = dateAvailability;
        this.hours = new ArrayList<>();
    }

    @Override
    public String getAvailability() {
        return this.dateAvailability;
    }

    public List<Hour> getHours() {
        return Collections.unmodifiableList(this.hours);
    }

    public void addHours(Hour... hours) {
        Collections.addAll(this.hours, hours);
    }
}
