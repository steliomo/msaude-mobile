package mz.co.msaude.mobile.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.fragment.ConsultationDateFragment;
import mz.co.msaude.mobile.consultation.fragment.ConsultationTypeFragment;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.service.ConsultationService;
import mz.co.msaude.mobile.delegate.ScheduleConsultationDelegate;
import mz.co.msaude.mobile.dialog.AlertDialogManager;
import mz.co.msaude.mobile.dialog.ProgressDialogManager;
import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.doctor.service.DoctorService;
import mz.co.msaude.mobile.fragment.DoctorFragment;
import mz.co.msaude.mobile.fragment.HealthFacilityFragment;
import mz.co.msaude.mobile.fragment.LocalityFragment;
import mz.co.msaude.mobile.fragment.ProvinceFragment;
import mz.co.msaude.mobile.fragment.ScheduleConfirmationFragment;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.infra.SharedPreferencesManager;
import mz.co.msaude.mobile.listner.AlertListner;
import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;
import mz.co.msaude.mobile.location.service.LocationService;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;
import mz.co.msaude.mobile.medicalservicetype.model.ServiceType;
import mz.co.msaude.mobile.medicalservicetype.service.MedicalServiceTypeService;
import mz.co.msaude.mobile.patient.fragment.PatientFragment;
import mz.co.msaude.mobile.patient.model.Patient;
import mz.co.msaude.mobile.user.model.User;
import mz.co.msaude.mobile.util.FragmentTileUtil;

public class ScheduleConsultationActivity extends BaseAuthenticateActivity implements ScheduleConsultationDelegate, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.schedule_consultation_fragment_title)
    TextView fragmentTitle;

    @Inject
    ConsultationService consultationService;

    @Inject
    LocationService locationService;

    @Inject
    DoctorService doctorService;

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    MedicalServiceTypeService medicalServiceTypeService;

    private FragmentManager fragmentManager;

    private AlertDialogManager alertDialogManager;

    private ProgressDialogManager progressDialogManager;

    private List<MedicalServiceType> consultationTypes;

    private List<Province> provinces;

    private List<Locality> localities;

    private ProgressDialog progressBar;

    private List<HealthFacility> healthFacilities;

    private List<Doctor> doctors;

    private Consultation consultation;

    private String patientName;

    private FragmentTileUtil fragmentTileUtil;

    @Override
    public void onMhealthCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_schedule_consultation);
        toolbar.setTitle(R.string.schedule_consultation);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(this);

        SaudeComponent component = application.getComponent();
        component.inject(this);

        consultation = new Consultation();

        alertDialogManager = new AlertDialogManager(this);
        progressDialogManager = new ProgressDialogManager(this);

        consultationTypes = new ArrayList<>();

        fragmentTileUtil = new FragmentTileUtil();

        progressBar = progressDialogManager.getProgressBar(getString(R.string.wait), getString(R.string.processing_request));
        progressBar.show();

        loadConsultationTypes(progressBar);
    }

    private void loadConsultationTypes(final ProgressDialog progressBar) {
        medicalServiceTypeService.findMedicalServiceTypes(application.getToken(), ServiceType.CONSULTATION, new ResponseListner<List<MedicalServiceType>>() {
            @Override
            public void success(List<MedicalServiceType> response) {

                if (response != null) {
                    consultationTypes.addAll(response);
                }

                progressBar.dismiss();
                fragmentTitle.setVisibility(View.VISIBLE);
                fragmentTitle.setText(R.string.select_consultation_type);

                Fragment fragment = fragmentTileUtil.getFragmentWithTile(new ConsultationTypeFragment(), getString(R.string.select_consultation_type));

                showFragment(fragment, Boolean.FALSE);
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, new AlertListner() {
                    @Override
                    public void perform() {
                        finish();
                    }
                });
            }
        });
    }

    private void showFragment(Fragment fragment, boolean onstack) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.schedule_consultation_frame_layout, fragment);

        if (onstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    @Override
    public void setFragmentTitle(String title) {
        fragmentTitle.setText(title);
    }

    @Override
    public List<MedicalServiceType> getMedicalServiceTypes() {
        return consultationTypes;
    }

    @Override
    public void onSelectMedicalServiceType(MedicalServiceType medicalServiceType) {

        consultation.setMedicalServiceType(medicalServiceType);

        provinces = new ArrayList<>();
        progressBar.show();

        locationService.findAllProvinces(application.getToken(), new ResponseListner<List<Province>>() {

            @Override
            public void success(List<Province> response) {
                progressBar.dismiss();
                provinces.addAll(response);

                Fragment fragment = fragmentTileUtil.getFragmentWithTile(new ProvinceFragment(), getString(R.string.select_province));

                showFragment(fragment, Boolean.TRUE);
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
            }
        });
    }

    @Override
    public List<Province> getProvinces() {
        return provinces;
    }

    @Override
    public List<Locality> getLocalities() {
        return localities;
    }

    @Override
    public void onSelectProvince(Province province) {

        consultation.setProvince(province);

        localities = new ArrayList<>();
        progressBar.show();

        locationService.findLocalityByProvinceUuid(application.getToken(), province.getUuid(), new ResponseListner<List<Locality>>() {
            @Override
            public void success(List<Locality> response) {
                progressBar.dismiss();
                localities.addAll(response);

                Fragment fragment = fragmentTileUtil.getFragmentWithTile(new LocalityFragment(), getString(R.string.select_locality));

                showFragment(fragment, Boolean.TRUE);
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
            }
        });
    }

    @Override
    public List<HealthFacility> getHealthFacilities() {
        return this.healthFacilities;
    }

    @Override
    public void onSelectLocality(Locality locality) {

        consultation.setLocality(locality);

        healthFacilities = new ArrayList<>();
        progressBar.show();

        locationService.findHealthFacilityByLocalityUuid(application.getToken(), locality.getUuid(), new ResponseListner<List<HealthFacility>>() {
            @Override
            public void success(List<HealthFacility> healthFacilities) {
                progressBar.dismiss();

                if (healthFacilities.isEmpty()) {
                    alertDialogManager.showAlert(getString(R.string.no_facility_was_found), R.layout.error_alert_dialog, new AlertListner() {
                        @Override
                        public void perform() {
                            fragmentManager.popBackStack();
                        }
                    });
                }

                ScheduleConsultationActivity.this.healthFacilities.addAll(healthFacilities);

                Fragment fragment = fragmentTileUtil.getFragmentWithTile(new HealthFacilityFragment(), getString(R.string.select_health_facility));

                showFragment(fragment, Boolean.TRUE);
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
            }
        });
    }

    @Override
    public void onSelectHealthFacility(HealthFacility healthFacility) {
        consultation.setHealthFacility(healthFacility);

        Fragment fragment = fragmentTileUtil.getFragmentWithTile(new ConsultationDateFragment(), getString(R.string.select_date));

        showFragment(fragment, Boolean.TRUE);
    }

    @Override
    public void onSelectDate(String consultationDate) {

        consultation.setConsultationDate(consultationDate);
        doctors = new ArrayList<>();
        progressBar.show();

        doctorService.findDoctorsByConsultationTypeAndHealthFacilityAndConsultationDate(application.getToken(), consultation.getMedicalServiceType().getUuid(), consultation.getHealthFacility().getUuid(), consultation.getConsultationDate(), new ResponseListner<List<Doctor>>() {
            @Override
            public void success(List<Doctor> response) {
                progressBar.dismiss();
                doctors.addAll(response);

                Fragment fragment = fragmentTileUtil.getFragmentWithTile(new DoctorFragment(), getString(R.string.select_doctor));

                showFragment(fragment, Boolean.TRUE);
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
            }
        });
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctors;
    }

    @Override
    public void onSelectDoctor(Doctor doctor) {
        consultation.setDoctor(doctor);

        Fragment fragment = fragmentTileUtil.getFragmentWithTile(new PatientFragment(), getString(R.string.select_patient));

        showFragment(fragment, Boolean.TRUE);
    }

    @Override
    public Consultation getConsultation() {
        return consultation;
    }

    @Override
    public void onSelectMainMember() {
        User userInfo = sharedPreferencesManager.getUserInfo();
        patientName = userInfo.getFullname();

        consultation.setPatient(userInfo.getUuid());

        Fragment fragment = fragmentTileUtil.getFragmentWithTile(new ScheduleConfirmationFragment(), getString(R.string.finalize_schedule));

        showFragment(fragment, Boolean.TRUE);
    }

    @Override
    public List<Patient> getDependents() {
        return new ArrayList<>();
    }

    @Override
    public String getPatientName() {
        return patientName;
    }

    @Override
    public void scheduleConsultation(Consultation consultation) {

        progressBar.show();

        consultationService.scheduleConsultation(application.getToken(), consultation, new ResponseListner<Consultation>() {
            @Override
            public void success(Consultation response) {
                progressBar.dismiss();

                if (response == null) {
                    alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
                    return;
                }

                alertDialogManager.showAlert(getString(R.string.consultation_scheduled_with_success), R.layout.success_alert_dialog, new AlertListner() {
                    @Override
                    public void perform() {
                        finish();
                    }
                });
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            return;
        }

        finish();
    }
}
