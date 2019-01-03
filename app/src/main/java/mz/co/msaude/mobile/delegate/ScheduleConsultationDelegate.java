package mz.co.msaude.mobile.delegate;

import java.util.List;

import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;
import mz.co.msaude.mobile.patient.model.Patient;

public interface ScheduleConsultationDelegate extends ScheduleDelegate {

    List<Doctor> getDoctors();

    void onSelectDoctor(Doctor doctor);

    Consultation getConsultation();

    void scheduleConsultation(Consultation consultation);
}
