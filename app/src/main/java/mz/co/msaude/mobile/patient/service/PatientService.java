package mz.co.msaude.mobile.patient.service;

import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.patient.model.Patient;

public interface PatientService {
    void createPatient(String token, Patient patient, ResponseListner<Patient> listner);
}
