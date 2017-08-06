package mz.co.txova.msaude.fragment;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.activities.ScheduleConsultationActivity;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.consultation.model.ConsultationFilter;
import mz.co.txova.msaude.doctor.event.DoctorAvailabilityDateEvent;
import mz.co.txova.msaude.doctor.event.DoctorAvailabilityTimeEvent;
import mz.co.txova.msaude.doctor.event.DoctorEvent;
import mz.co.txova.msaude.healthfacility.event.HealthFacilityEvent;

public class ScheduleConfirmationFragment extends BaseFragment {

    @BindView(R.id.confirmation_consultation_city)
    TextView consultationCity;

    @BindView(R.id.confirmation_consultation_type)
    TextView consultationType;

    @BindView(R.id.confirmation_consultation_clinic)
    TextView consultationClinic;

    @BindView(R.id.confirmation_consultation_doctor)
    TextView consultationDoctor;

    @BindView(R.id.confirmation_consultation_date)
    TextView consultationDate;

    @BindView(R.id.confirmation_consultation_time)
    TextView consultationTime;

    @BindView(R.id.confirmation_consultation_setup)
    Button consultattioSetup;

    @BindView(R.id.confirmation_consultation_cancel)
    Button consultationCancel;

    @Inject
    EventBus eventBus;

    @Override
    public int getResourceId() {
        return R.layout.fragment_schedule_confirmation;
    }

    @Override
    public void onCreateView() {

        SaudeComponent component = application.getComponent();
        component.inject(this);
        eventBus.register(this);

        ScheduleConsultationActivity activity = (ScheduleConsultationActivity) getActivity();
        ConsultationFilter consultationFilter = activity.getConsultationFilter();

        consultationCity.setText(consultationFilter.getCity());
        consultationType.setText(consultationFilter.getConsultattionType());
        consultationClinic.setText(consultationFilter.getHealthFacility());
        consultationDoctor.setText(consultationFilter.getDoctorName());
        consultationDate.setText(consultationFilter.getConsultationDate());
        consultationTime.setText(null);
    }

    @OnClick(R.id.confirmation_consultation_cancel)
    public void onCancel() {
        getActivity().finish();
    }

    @OnClick(R.id.confirmation_consultation_setup)
    public void onSetup() {
        Toast.makeText(getActivity(), "Consulta marcada com sucesso!", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Subscribe
    public void onEvent(DoctorEvent event) {
        consultationDoctor.setText(event.getDoctor().getFullName());
    }

    @Subscribe
    public void onEvent(DoctorAvailabilityDateEvent event) {
        consultationDate.setText(event.getDoctorAvailability().getAvailability());
    }

    @Subscribe
    public void onEvent(DoctorAvailabilityTimeEvent event) {
        consultationTime.setText(event.getHour().getAvailability());
    }

    @Subscribe
    public void onEvent(HealthFacilityEvent event) {
        consultationClinic.setText(event.getHealthFacilityDTO().getHealthFacility().getName());
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}
