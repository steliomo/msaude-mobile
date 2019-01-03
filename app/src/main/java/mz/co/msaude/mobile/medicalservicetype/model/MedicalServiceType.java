package mz.co.msaude.mobile.medicalservicetype.model;

import mz.co.msaude.mobile.model.GenericModel;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class MedicalServiceType extends GenericModel {

    private String name;

    private ServiceType serviceType;

    public String getName() {
        return name;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }
}
