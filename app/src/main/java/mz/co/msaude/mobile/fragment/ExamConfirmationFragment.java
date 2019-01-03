package mz.co.msaude.mobile.fragment;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.delegate.ScheduleExamDelegate;
import mz.co.msaude.mobile.exam.model.Exam;


public class ExamConfirmationFragment extends BaseFragment {

    @BindView(R.id.fragment_exam_confirmation_exam_type)
    TextView consultationTypeTxt;

    @BindView(R.id.fragment_exam_confirmation_province)
    TextView provinceTxt;

    @BindView(R.id.fragment_exam_confirmation_locality)
    TextView localityTxt;

    @BindView(R.id.fragment_exam_confirmation_health_facility)
    TextView healthFacilityTxt;

    @BindView(R.id.fragment_exam_confirmation_consultation_date)
    TextView consultationDateTxt;

    @BindView(R.id.fragment_exam_confirmation_patient)
    TextView patientTxt;


    private Exam exam;

    private ScheduleExamDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_exam_confirmation;
    }

    @Override
    public void onCreateView() {

        delegate = (ScheduleExamDelegate) getActivity();
        delegate.setFragmentTitle(getArguments().getString(ScheduleExamDelegate.TITLE));

        exam = delegate.getExam();
        populateExam(exam);
    }

    private void populateExam(Exam exam) {
        consultationTypeTxt.setText(exam.getMedicalServiceType().getName());
        provinceTxt.setText(exam.getProvince().getName());
        localityTxt.setText(exam.getLocality().getName());
        healthFacilityTxt.setText(exam.getHealthFacility().getName());
        consultationDateTxt.setText(exam.getExamDate());
        patientTxt.setText(delegate.getPatientName());
    }

    @OnClick(R.id.confirmation_exam_cancel)
    public void onCancel() {
        getActivity().finish();
    }

    @OnClick(R.id.confirmation_exam_setup)
    public void onSetup() {
        delegate.scheduleExam(exam);
    }
}
