package mz.co.msaude.mobile.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.widget.ListView;

import com.stepstone.stepper.VerificationError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.DoctorAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.model.QueryResult;
import mz.co.msaude.mobile.doctor.dto.DoctorDTO;
import mz.co.msaude.mobile.doctor.event.DoctorEvent;
import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.healthfacility.event.HealthFacilityEvent;


public class DoctorFragment extends BaseFragment {

    @BindView(R.id.fragment_doctors)
    ListView doctorsView;

    @Inject
    EventBus eventBus;

    private Doctor doctor;

    private DoctorDTO doctorDTO;

    @Override
    public int getResourceId() {
        return R.layout.fragment_doctor;
    }

    @Override
    public void onCreateView() {
        SaudeComponent component = application.getComponent();
        component.inject(this);
        eventBus.register(this);

        QueryResult result = (QueryResult) getActivity().getIntent().getSerializableExtra(QueryResult.QUERY_RESULT);

        if (!(result instanceof DoctorDTO)) {
            return;
        }

        doctorDTO = (DoctorDTO) result;
        populateDoctorView(doctorDTO.getDoctors());
    }

    private void populateDoctorView(List<Doctor> doctors) {
        DoctorAdapter adapter = new DoctorAdapter(getActivity(), doctors);
        doctorsView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        doctorsView.setAdapter(adapter);
    }

    @OnItemClick(R.id.fragment_doctors)
    public void onItemClick(final int position) {
        doctor = (Doctor) doctorsView.getItemAtPosition(position);
        doctorDTO.setDoctor(doctor);
        eventBus.post(new DoctorEvent(doctorDTO));
    }

    @Subscribe
    public void onEvent(HealthFacilityEvent event) {
        populateDoctorView(event.getHealthFacilityDTO().getHealthFacility().getDoctors());
        if (doctorDTO == null) {
            doctorDTO = new DoctorDTO();
        }

        doctorDTO.setDoctorAvailability(event.getHealthFacilityDTO().getDoctorAvailability());

        doctor = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {

        if (doctor != null) {
            return null;
        }

        return new VerificationError(getString(R.string.doctor_must_be_selected));
    }

    @Override
    public void onSelected() {
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Snackbar.make(getView(), error.getErrorMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
