package mz.co.txova.msaude.doctor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mz.co.txova.msaude.consultation.model.Availability;
import mz.co.txova.msaude.consultation.model.Hour;

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
        for (Hour hour : hours) {
            this.hours.add(hour);
        }
    }
}
