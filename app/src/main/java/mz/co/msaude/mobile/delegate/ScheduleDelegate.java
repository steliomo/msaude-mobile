package mz.co.msaude.mobile.delegate;

import java.util.List;

import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;
import mz.co.msaude.mobile.patient.model.Patient;

public interface ScheduleDelegate {

    String TITLE = "TITLE";

    public void setFragmentTitle(String title);

    List<MedicalServiceType> getMedicalServiceTypes();

    void onSelectMedicalServiceType(final MedicalServiceType consultationType);

    List<Province> getProvinces();

    List<Locality> getLocalities();

    void onSelectProvince(Province province);

    List<HealthFacility> getHealthFacilities();

    void onSelectLocality(Locality locality);

    void onSelectHealthFacility(HealthFacility healthFacility);

    void onSelectDate(String consultationDate);

    List<Patient> getDependents();

    String getPatientName();

    void onSelectMainMember();
}
