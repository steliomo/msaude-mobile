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
import mz.co.txova.msaude.consultation.event.ConsultationEvent;
import mz.co.txova.msaude.consultation.model.Consultation;
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

    private Consultation consultation;

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
        consultation = activity.getConsultation();

        consultationCity.setText(consultation.getCity());
        consultationType.setText(consultation.getConsultationType());
        populateConsultation();
    }

    private void populateConsultation() {
        consultationClinic.setText(consultation.getHealthFacility() != null ? consultation.getHealthFacility().getName() : null);
        consultationDoctor.setText(consultation.getDoctor() != null ? consultation.getDoctor().getFullName() : null);
        consultationDate.setText(consultation.getScheduledDate());
        consultationTime.setText(consultation.getHour() != null ? consultation.getHour().getAvailability() : null);
    }

    @OnClick(R.id.confirmation_consultation_cancel)
    public void onCancel() {
        getActivity().finish();
    }

    @OnClick(R.id.confirmation_consultation_setup)
    public void onSetup() {
        Toast.makeText(getActivity(), "Consulta marcada com sucesso!", Toast.LENGTH_SHORT).show();
        eventBus.post(new ConsultationEvent(consultation));
        getActivity().finish();
    }

    @Subscribe
    public void onEvent(DoctorEvent event) {
        consultation.setDoctor(event.getDoctorDTO().getDoctor());
        consultation.setHealthFacility(event.getDoctorDTO().getHealthFacility() != null ? event.getDoctorDTO().getHealthFacility() : consultation.getHealthFacility());
        populateConsultation();
    }

    @Subscribe
    public void onEvent(DoctorAvailabilityDateEvent event) {
        consultation.setScheduledDate(event.getDoctorAvailability().getAvailability());
        populateConsultation();
    }

    @Subscribe
    public void onEvent(DoctorAvailabilityTimeEvent event) {
        consultation.setHour(event.getHour());
        populateConsultation();
    }

    @Subscribe
    public void onEvent(HealthFacilityEvent event) {
        consultation.setHealthFacility(event.getHealthFacilityDTO().getHealthFacility());
        populateConsultation();
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}
