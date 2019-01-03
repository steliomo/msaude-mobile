package mz.co.msaude.mobile.healthfacility.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;
import mz.co.msaude.mobile.model.GenericModel;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class HealthFacility extends GenericModel {

    private String name;

    private Locality locality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locality getLocality() {
        return this.locality;
    }
}
