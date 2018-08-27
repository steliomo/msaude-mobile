package mz.co.msaude.mobile.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.widget.ListView;

import com.stepstone.stepper.VerificationError;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.activities.ScheduleConsultationActivity;
import mz.co.msaude.mobile.adapter.HealthFacilityAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.dto.HealthFacilityDTO;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.QueryResult;
import mz.co.msaude.mobile.healthfacility.event.HealthFacilityEvent;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;

public class HealthFacilityFragment extends BaseFragment {

    @BindView(R.id.fragment_health_facility)
    ListView healthFacilitiesView;

    @Inject
    EventBus eventBus;

    private HealthFacility healthFacility;

    private HealthFacilityDTO healthFacilityDTO;

    @Override
    public void onCreateView() {
        SaudeComponent component = application.getComponent();
        component.inject(this);

        ScheduleConsultationActivity activity = (ScheduleConsultationActivity) getActivity();
        healthFacilityDTO = (HealthFacilityDTO) activity.getIntent().getSerializableExtra(QueryResult.QUERY_RESULT);
        Consultation consultation = activity.getConsultation();
        consultation.setDoctor(healthFacilityDTO.getDoctor());
        consultation.setScheduledDate(healthFacilityDTO.getDoctorAvailability() != null ? healthFacilityDTO.getDoctorAvailability().getAvailability() : null);

        HealthFacilityAdapter adapter = new HealthFacilityAdapter(activity, healthFacilityDTO.getHealthFacilities());

        healthFacilitiesView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        healthFacilitiesView.setAdapter(adapter);
    }

    @Override
    public int getResourceId() {
        return R.layout.fragment_health_facility;
    }

    @OnItemClick(R.id.fragment_health_facility)
    public void onItemClick(final int position) {
        healthFacility = (HealthFacility) healthFacilitiesView.getItemAtPosition(position);
        healthFacilityDTO.setHealthFacility(healthFacility);

        eventBus.post(new HealthFacilityEvent(healthFacilityDTO));
    }

    @Nullable

    public VerificationError verifyStep() {

        if (healthFacility != null) {
            return null;
        }

        return new VerificationError(getString(R.string.clinic_must_be_selected));
    }


    public void onSelected() {

    }


    public void onError(@NonNull VerificationError error) {
        Snackbar.make(getView(), error.getErrorMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
