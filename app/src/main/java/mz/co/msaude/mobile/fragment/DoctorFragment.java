package mz.co.msaude.mobile.fragment;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.DoctorAdapter;
import mz.co.msaude.mobile.delegate.ScheduleConsultationDelegate;
import mz.co.msaude.mobile.delegate.ScheduleDelegate;
import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.listner.ClickListner;


public class DoctorFragment extends BaseFragment implements ClickListner<Doctor> {

    @BindView(R.id.fragment_doctor_recycle_view)
    RecyclerView doctorRecycleView;

    private ScheduleConsultationDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_doctor;
    }

    @Override
    public void onCreateView() {
        delegate = (ScheduleConsultationDelegate) getActivity();
        delegate.setFragmentTitle(getArguments().getString(ScheduleDelegate.TITLE));

        DoctorAdapter adapter = new DoctorAdapter(getActivity(), delegate.getDoctors());
        adapter.setItemClickListner(this);
        doctorRecycleView.setAdapter(adapter);
    }

    @Override
    public void onClick(Doctor doctor) {
        delegate.onSelectDoctor(doctor);
    }

    @Override
    public void onLongClick(Doctor doctor) {
    }
}
