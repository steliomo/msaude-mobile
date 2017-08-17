package mz.co.txova.msaude.fragment;

import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.activities.ScheduleConsultationActivity;
import mz.co.txova.msaude.adapter.HealthFacilityAdapter;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.consultation.dto.HealthFacilityDTO;
import mz.co.txova.msaude.consultation.model.Consultation;
import mz.co.txova.msaude.consultation.model.QueryResult;
import mz.co.txova.msaude.healthfacility.event.HealthFacilityEvent;
import mz.co.txova.msaude.healthfacility.model.HealthFacility;

public class HealthFacilityFragment extends BaseFragment implements FragmentValidator {

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

    @Override
    public void validate(ViewPager viewPager, int position) {
        if (healthFacility != null) {
            return;
        }

        viewPager.setCurrentItem(position);
        Snackbar.make(getView(), getString(R.string.clinic_must_be_selected), Snackbar.LENGTH_SHORT).show();
    }
}
