/**
 *
 */
package mz.co.msaude.mobile.exam.model;

import java.time.LocalDate;
import java.time.LocalTime;

import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;
import mz.co.msaude.mobile.model.GenericModel;

/**
 * @author Stélio Moiane
 */
public class Exam extends GenericModel {

    private MedicalServiceType medicalServiceType;

    private Province province;

    private Locality locality;

    private HealthFacility healthFacility;

    private String examDate;

    private String examTime;

    private ExamStatus examStatus;

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

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public ExamStatus getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(ExamStatus examStatus) {
        this.examStatus = examStatus;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
