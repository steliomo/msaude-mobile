package mz.co.msaude.mobile.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.VerificationError;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.activities.ScheduleConsultationActivity;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.event.ConsultationEvent;
import mz.co.msaude.mobile.consultation.model.Consultation;

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

        ScheduleConsultationActivity activity = (ScheduleConsultationActivity) getActivity();
        consultation = activity.getConsultation();

        consultationCity.setText(consultation.getCity().getCity());
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

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        populateConsultation();
    }

    @Override
    public void onError(@NonNull VerificationError error) {
    }
}
