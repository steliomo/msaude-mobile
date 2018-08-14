package mz.co.msaude.mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.stepstone.stepper.StepperLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.SwipeAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.filter.AllFragmentDisplayFilter;
import mz.co.msaude.mobile.consultation.filter.ClinicDateFragmentDisplayFilter;
import mz.co.msaude.mobile.consultation.filter.ClinicDoctorFragmentDisplayFilter;
import mz.co.msaude.mobile.consultation.filter.ClinicFragmentDisplayFilter;
import mz.co.msaude.mobile.consultation.filter.DateFragmentDisplayFilter;
import mz.co.msaude.mobile.consultation.filter.DoctorDateFragmentDisplayFilter;
import mz.co.msaude.mobile.consultation.filter.DoctorFragmentDisplayFilter;
import mz.co.msaude.mobile.consultation.filter.FragmentDisplayFilter;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.ConsultationFilter;
import mz.co.msaude.mobile.doctor.event.DoctorAvailabilityDateEvent;
import mz.co.msaude.mobile.doctor.event.DoctorAvailabilityTimeEvent;
import mz.co.msaude.mobile.doctor.event.DoctorEvent;
import mz.co.msaude.mobile.healthfacility.event.HealthFacilityEvent;

public class ScheduleConsultationActivity extends BaseAuthenticateActivity {

    @BindView(R.id.stepperLayout)
    StepperLayout viewPager;

    @Inject
    EventBus eventBus;

    private ConsultationFilter consultationFilter;

    private Consultation consultation;

    @Override
    public void onMhealthCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_schedule_consultation);
        toolbar.setTitle("Finalização de Consultas");

        SaudeComponent component = application.getComponent();
        component.inject(this);
        eventBus.register(this);

        Intent intent = getIntent();
        consultationFilter = (ConsultationFilter) intent.getSerializableExtra(ConsultationFilter.FILTER);
        consultation = new Consultation(consultationFilter.getCity(), consultationFilter.getConsultationType());

        FragmentDisplayFilter allFragmentDisplayFilter = setUpChainFilter();

        SwipeAdapter adapter = new SwipeAdapter(getSupportFragmentManager(), allFragmentDisplayFilter.getFragmentDisplay(consultationFilter), this);
        viewPager.setAdapter(adapter);
    }

    @NonNull
    private FragmentDisplayFilter setUpChainFilter() {

        FragmentDisplayFilter allFragmentDisplayFilter = new AllFragmentDisplayFilter(this);
        FragmentDisplayFilter clinicDoctorFragmentDisplayFilter = new ClinicDoctorFragmentDisplayFilter(this);
        FragmentDisplayFilter clinicDateFragmentDisplayFilter = new ClinicDateFragmentDisplayFilter(this);
        FragmentDisplayFilter doctorDateFragmentDisplayFilter = new DoctorDateFragmentDisplayFilter(this);
        FragmentDisplayFilter clinicFragmentDisplayFilter = new ClinicFragmentDisplayFilter(this);
        FragmentDisplayFilter doctorFragmentDisplayFilter = new DoctorFragmentDisplayFilter(this);
        FragmentDisplayFilter dateFragmentDisplayFilter = new DateFragmentDisplayFilter(this);

        doctorFragmentDisplayFilter.setNextFragmentDisplayFilter(dateFragmentDisplayFilter);
        clinicFragmentDisplayFilter.setNextFragmentDisplayFilter(doctorFragmentDisplayFilter);
        clinicDoctorFragmentDisplayFilter.setNextFragmentDisplayFilter(clinicFragmentDisplayFilter);
        clinicDateFragmentDisplayFilter.setNextFragmentDisplayFilter(clinicDoctorFragmentDisplayFilter);
        doctorDateFragmentDisplayFilter.setNextFragmentDisplayFilter(clinicDateFragmentDisplayFilter);
        allFragmentDisplayFilter.setNextFragmentDisplayFilter(doctorDateFragmentDisplayFilter);

        return allFragmentDisplayFilter;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    @Subscribe
    public void onEvent(HealthFacilityEvent event) {
        consultation.setHealthFacility(event.getHealthFacilityDTO().getHealthFacility());
    }

    @Subscribe
    public void onEvent(DoctorEvent event) {
        consultation.setDoctor(event.getDoctorDTO().getDoctor());
    }

    @Subscribe
    public void onEvent(DoctorAvailabilityDateEvent event) {
        consultation.setScheduledDate(event.getDoctorAvailability().getAvailability());
    }

    @Subscribe
    public void onEvent(DoctorAvailabilityTimeEvent event) {
        consultation.setHour(event.getHour());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Map<String, String> event) {
        Toast.makeText(this, String.valueOf(event), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }
}
