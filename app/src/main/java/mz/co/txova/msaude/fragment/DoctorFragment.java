package mz.co.txova.msaude.fragment;

import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.adapter.DoctorAdapter;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.consultation.model.QueryResult;
import mz.co.txova.msaude.doctor.dto.DoctorDTO;
import mz.co.txova.msaude.doctor.event.DoctorEvent;
import mz.co.txova.msaude.doctor.model.Doctor;
import mz.co.txova.msaude.healthfacility.event.HealthFacilityEvent;


public class DoctorFragment extends BaseFragment implements FragmentValidator {

    @BindView(R.id.fragment_doctors)
    ListView doctorsView;

    @Inject
    EventBus eventBus;

    private Doctor doctor;

    @Override
    public int getResourceId() {
        return R.layout.fragment_doctor;
    }

    @Override
    public void onCreateView() {
        SaudeComponent component = application.getComponent();
        component.inject(this);
        eventBus.register(this);

        DoctorDTO doctorDTO = (DoctorDTO) getActivity().getIntent().getSerializableExtra(QueryResult.QUERY_RESULT);
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
        eventBus.post(new DoctorEvent(doctor));
    }

    @Subscribe
    public void onEvent(HealthFacilityEvent event) {
        populateDoctorView(event.getHealthFacilityDTO().getHealthFacility().getDoctors());
        doctor = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    public void validate(ViewPager viewPager, int position) {

        if (doctor != null) {
            return;
        }

        viewPager.setCurrentItem(position);
        Snackbar.make(getView(), getString(R.string.doctor_must_be_selected), Snackbar.LENGTH_SHORT).show();
    }
}
