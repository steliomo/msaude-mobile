package mz.co.msaude.mobile.fragment;

import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.delegate.ScheduleConsultationDelegate;
import mz.co.msaude.mobile.delegate.ScheduleDelegate;

public class ScheduleConfirmationFragment extends BaseFragment {

    @BindView(R.id.fragment_schedule_confirmation_consultation_type)
    TextView consultationTypeTxt;

    @BindView(R.id.fragment_schedule_confirmation_province)
    TextView provinceTxt;

    @BindView(R.id.fragment_schedule_confirmation_locality)
    TextView localityTxt;

    @BindView(R.id.fragment_schedule_confirmation_health_facility)
    TextView healthFacilityTxt;

    @BindView(R.id.fragment_schedule_confirmation_consultation_date)
    TextView consultationDateTxt;

    @BindView(R.id.fragment_schedule_confirmation_doctor)
    TextView doctorTxt;

    @BindView(R.id.fragment_schedule_confirmation_patient)
    TextView patientTxt;

    @BindView(R.id.confirmation_consultation_setup)
    Button consultattioSetup;

    @BindView(R.id.confirmation_consultation_cancel)
    Button consultationCancel;

    private Consultation consultation;

    private ScheduleConsultationDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_schedule_confirmation;
    }

    @Override
    public void onCreateView() {
        delegate = (ScheduleConsultationDelegate) getActivity();
        delegate.setFragmentTitle(getArguments().getString(ScheduleDelegate.TITLE));

        consultation = delegate.getConsultation();
        populateConsultation(consultation);
    }

    private void populateConsultation(Consultation consultation) {
        consultationTypeTxt.setText(consultation.getMedicalServiceType().getName());
        provinceTxt.setText(consultation.getProvince().getName());
        localityTxt.setText(consultation.getLocality().getName());
        healthFacilityTxt.setText(consultation.getHealthFacility().getName());
        consultationDateTxt.setText(consultation.getConsultationDate());
        doctorTxt.setText(consultation.getDoctor().getFullName());
        patientTxt.setText(delegate.getPatientName());
    }

    @OnClick(R.id.confirmation_consultation_cancel)
    public void onCancel() {
        getActivity().finish();
    }

    @OnClick(R.id.confirmation_consultation_setup)
    public void onSetup() {
        delegate.scheduleConsultation(consultation);
    }
}
