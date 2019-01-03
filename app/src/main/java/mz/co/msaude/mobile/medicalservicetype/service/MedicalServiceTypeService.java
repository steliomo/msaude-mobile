package mz.co.msaude.mobile.medicalservicetype.service;

import java.util.List;

import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;
import mz.co.msaude.mobile.medicalservicetype.model.ServiceType;

public interface MedicalServiceTypeService {

    void findMedicalServiceTypes(final String token, ServiceType serviceType, ResponseListner<List<MedicalServiceType>> listner);

}
