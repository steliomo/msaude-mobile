package mz.co.msaude.mobile.consultation.model;


import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;
import mz.co.msaude.mobile.model.GenericModel;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class Consultation extends GenericModel {

    private MedicalServiceType medicalServiceType;

    private Province province;

    private Locality locality;

    private HealthFacility healthFacility;

    private String consultationDate;

    private String consultationTime;

    private ConsultationStatus consultationStatus;

    private Doctor doctor;

    private String patient;

    public MedicalServiceType getMedicalServiceType() {
        return medicalServiceType;
    }

    public void setMedicalServiceType(MedicalServiceType medicalServiceType) {
        this.medicalServiceType = medicalServiceType;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getConsultationTime() {
        return consultationTime;
    }

    public void setConsultationTime(String consultationTime) {
        this.consultationTime = consultationTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public ConsultationStatus getConsultationStatus() {
        return consultationStatus;
    }

    public void setConsultationStatus(ConsultationStatus consultationStatus) {
        this.consultationStatus = consultationStatus;
    }
}
