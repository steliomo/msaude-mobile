package mz.co.txova.msaude.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.adapter.SwipeAdapter;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.consultation.filter.AllFragmentDisplayFilter;
import mz.co.txova.msaude.consultation.filter.ClinicDateFragmentDisplayFilter;
import mz.co.txova.msaude.consultation.filter.ClinicDoctorFragmentDisplayFilter;
import mz.co.txova.msaude.consultation.filter.ClinicFragmentDisplayFilter;
import mz.co.txova.msaude.consultation.filter.DateFragmentDisplayFilter;
import mz.co.txova.msaude.consultation.filter.DoctorDateFragmentDisplayFilter;
import mz.co.txova.msaude.consultation.filter.DoctorFragmentDisplayFilter;
import mz.co.txova.msaude.consultation.filter.FragmentDisplayFilter;
import mz.co.txova.msaude.consultation.model.ConsultationFilter;
import mz.co.txova.msaude.fragment.FragmentValidator;
import mz.co.txova.msaude.healthfacility.event.HealthFacilityEvent;

public class ScheduleConsultationActivity extends BaseAuthenticateActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_page)
    ViewPager viewPager;

    @Inject
    EventBus eventBus;

    private ConsultationFilter consultationFilter;

    private int currentPosition;

    @Override
    public void onMhealthCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_schedule_consultation);
        toolbar.setTitle("Finalização de Consultas");

        SaudeComponent component = application.getComponent();
        component.inject(this);
        eventBus.register(this);

        Intent intent = getIntent();
        consultationFilter = (ConsultationFilter) intent.getSerializableExtra(ConsultationFilter.FILTER);

        FragmentDisplayFilter allFragmentDisplayFilter = setUpChainFilter();

        SwipeAdapter adapter = new SwipeAdapter(getSupportFragmentManager(), allFragmentDisplayFilter.getFragmentDisplay(consultationFilter));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        //page validators
        currentPosition = 0;
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

    public ConsultationFilter getConsultationFilter() {
        return consultationFilter;
    }

    @Subscribe
    public void onEvent(HealthFacilityEvent event) {
        consultationFilter.setHealthFacility(event.getHealthFacilityDTO().getHealthFacility().getName());
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        SwipeAdapter adapter = (SwipeAdapter) viewPager.getAdapter();
        setCurrentPosition(position);

        Object item = adapter.instantiateItem(viewPager, getValidatorPosition());

        if (item instanceof FragmentValidator) {
            FragmentValidator fragment = (FragmentValidator) item;
            fragment.validate(viewPager, getValidatorPosition());
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void setCurrentPosition(int position) {

        if (position <= currentPosition) {
            return;
        }

        currentPosition = position;
    }

    public int getValidatorPosition() {
        return currentPosition - 1;
    }
}
