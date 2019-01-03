package mz.co.msaude.mobile.doctor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mz.co.msaude.mobile.model.GenericModel;
import mz.co.msaude.mobile.patient.model.Gender;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class Doctor extends GenericModel {

    private String name;

    private String surname;

    private Gender gender;

    public String getFullName() {
        return "Dr(a). " + this.name + " " + this.surname;
    }

    public Gender getGender() {
        return gender;
    }
}
