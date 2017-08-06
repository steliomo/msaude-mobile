package mz.co.txova.msaude.consultation.model;

import java.io.Serializable;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public class ConsultationFilter implements Serializable {

    public static final String FILTER= "FILTER";

    private String city;
    private String consultattionType;
    private String healthFacility;
    private String doctorName;
    private String consultationDate;

    public ConsultationFilter(String city, String consultattionType, String healthFacility, String doctorName, String consultationDate) {
        this.city = city;
        this.consultattionType = consultattionType;
        this.healthFacility = healthFacility;
        this.doctorName = doctorName;
        this.consultationDate = consultationDate;
    }

    public String getCity() {
        return city;
    }

    public String getConsultattionType() {
        return consultattionType;
    }

    public String getHealthFacility() {
        return healthFacility;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setHealthFacility(String healthFacility) {
        this.healthFacility = healthFacility;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}