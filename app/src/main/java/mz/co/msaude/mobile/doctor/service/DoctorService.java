package mz.co.msaude.mobile.doctor.service;

import java.util.List;

import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.listner.ResponseListner;

public interface DoctorService {

    void findDoctorsByConsultationTypeAndHealthFacilityAndConsultationDate(final String token, final String consultationType, final String healthFacility, final String consultationDate, final ResponseListner<List<Doctor>> listner);


}
