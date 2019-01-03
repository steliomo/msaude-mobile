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
import mz.co.msaude.mobile.delegate.ScheduleExamDelegate;
import mz.co.msaude.mobile.dialog.AlertDialogManager;
import mz.co.msaude.mobile.dialog.ProgressDialogManager;
import mz.co.msaude.mobile.exam.model.Exam;
import mz.co.msaude.mobile.exam.service.ExamService;
import mz.co.msaude.mobile.fragment.ExamConfirmationFragment;
import mz.co.msaude.mobile.fragment.HealthFacilityFragment;
import mz.co.msaude.mobile.fragment.LocalityFragment;
import mz.co.msaude.mobile.fragment.ProvinceFragment;
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

public class ScheduleExamActivity extends BaseAuthenticateActivity implements ScheduleExamDelegate, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.schedule_exam_fragment_title)
    TextView fragmentTitle;

    @Inject
    MedicalServiceTypeService medicalServiceTypeService;

    @Inject
    LocationService locationService;

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    ExamService examService;

    private FragmentManager fragmentManager;

    private ProgressDialog progressBar;

    private AlertDialogManager alertDialogManager;

    private ProgressDialogManager progressDialogManager;

    private List<MedicalServiceType> examTypes;

    private FragmentTileUtil fragmentTileUtil;

    private List<Province> provinces;

    private Exam exam;

    private List<Locality> localities;

    private List<HealthFacility> healthFacilities;

    private String patientName;


    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_schedule_exam);

        toolbar.setTitle(R.string.schedule_exam);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(this);

        SaudeComponent component = application.getComponent();
        component.inject(this);

        exam = new Exam();

        examTypes = new ArrayList<>();

        alertDialogManager = new AlertDialogManager(this);
        progressDialogManager = new ProgressDialogManager(this);

        fragmentTileUtil = new FragmentTileUtil();

        progressBar = progressDialogManager.getProgressBar(getString(R.string.wait), getString(R.string.processing_request));
        progressBar.show();

        loadExamTypes();
    }

    private void loadExamTypes() {
        medicalServiceTypeService.findMedicalServiceTypes(application.getToken(), ServiceType.EXAM, new ResponseListner<List<MedicalServiceType>>() {
            @Override
            public void success(List<MedicalServiceType> response) {

                if (response != null) {
                    examTypes.addAll(response);
                }

                progressBar.dismiss();
                fragmentTitle.setVisibility(View.VISIBLE);

                Fragment fragment = fragmentTileUtil.getFragmentWithTile(new ConsultationTypeFragment(), getString(R.string.select_exam_type));

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

    @Override
    public void onClick(View view) {

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            return;
        }

        finish();
    }

    private void showFragment(Fragment fragment, boolean onStack) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.schedule_exam_frame_layout, fragment);

        if (onStack) {
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
        return examTypes;
    }

    @Override
    public void onSelectMedicalServiceType(MedicalServiceType medicalServiceType) {

        exam.setMedicalServiceType(medicalServiceType);

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

        exam.setProvince(province);

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
        return healthFacilities;
    }

    @Override
    public void onSelectLocality(Locality locality) {

        exam.setLocality(locality);

        healthFacilities = new ArrayList<>();
        progressBar.show();

        locationService.findHealthFacilityByLocalityUuid(application.getToken(), locality.getUuid(), new ResponseListner<List<HealthFacility>>() {
            @Override
            public void success(List<HealthFacility> response) {
                progressBar.dismiss();

                if (response.isEmpty()) {
                    alertDialogManager.showAlert(getString(R.string.no_facility_was_found), R.layout.error_alert_dialog, new AlertListner() {
                        @Override
                        public void perform() {
                            fragmentManager.popBackStack();
                        }
                    });
                }

                healthFacilities.addAll(response);

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

        exam.setHealthFacility(healthFacility);

        Fragment fragment = fragmentTileUtil.getFragmentWithTile(new ConsultationDateFragment(), getString(R.string.select_date));

        showFragment(fragment, Boolean.TRUE);
    }

    @Override
    public void onSelectDate(String examDate) {
        exam.setExamDate(examDate);

        Fragment fragment = fragmentTileUtil.getFragmentWithTile(new PatientFragment(), getString(R.string.select_patient));

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
    public void onSelectMainMember() {

        User userInfo = sharedPreferencesManager.getUserInfo();
        patientName = userInfo.getFullname();

        exam.setPatient(userInfo.getUuid());

        Fragment fragment = fragmentTileUtil.getFragmentWithTile(new ExamConfirmationFragment(), getString(R.string.finalize_schedule));

        showFragment(fragment, Boolean.TRUE);
    }

    @Override
    public Exam getExam() {
        return exam;
    }

    @Override
    public void scheduleExam(Exam exam) {

        progressBar.show();

        examService.scheduleExam(application.getToken(), exam, new ResponseListner<Exam>() {
            @Override
            public void success(Exam response) {
                progressBar.dismiss();

                if (response == null) {
                    alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
                    return;
                }

                alertDialogManager.showAlert(getString(R.string.exam_scheduled_with_success), R.layout.success_alert_dialog, new AlertListner() {
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
}
