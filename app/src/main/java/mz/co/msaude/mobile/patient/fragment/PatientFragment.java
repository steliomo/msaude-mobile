package mz.co.msaude.mobile.patient.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.delegate.ScheduleConsultationDelegate;
import mz.co.msaude.mobile.delegate.ScheduleDelegate;
import mz.co.msaude.mobile.fragment.BaseFragment;


public class PatientFragment extends BaseFragment {

    @BindView(R.id.fragment_patient_recycle_view)
    RecyclerView depententsRecycleView;

    @BindView(R.id.fragment_patient_depents_title)
    TextView dependentsTitle;

    private ScheduleDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_patient;
    }

    @Override
    public void onCreateView() {
        delegate = (ScheduleDelegate) getActivity();
        delegate.setFragmentTitle(getArguments().getString(ScheduleDelegate.TITLE));

        if (delegate.getDependents().isEmpty()) {
            dependentsTitle.setText(getString(R.string.no_dependents));
        }
    }

    @OnClick(R.id.fragment_patient_member_btn)
    public void onClickMember() {
        delegate.onSelectMainMember();
    }
}
