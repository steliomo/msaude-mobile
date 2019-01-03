package mz.co.msaude.mobile.consultation.fragment;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.MedicalServiceTypeAdapter;
import mz.co.msaude.mobile.delegate.ScheduleConsultationDelegate;
import mz.co.msaude.mobile.delegate.ScheduleDelegate;
import mz.co.msaude.mobile.fragment.BaseFragment;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;

public class ConsultationTypeFragment extends BaseFragment {

    @BindView(R.id.fragment_consultation_type_recycle_view)
    RecyclerView consultationTypeRecyclerView;

    private ScheduleDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_consultation_type;
    }

    @Override
    public void onCreateView() {

        delegate = (ScheduleDelegate) getActivity();
        delegate.setFragmentTitle(getArguments().getString(ScheduleDelegate.TITLE));

        MedicalServiceTypeAdapter adapter = new MedicalServiceTypeAdapter(getActivity(), delegate.getMedicalServiceTypes());
        onClickItem(adapter);
        consultationTypeRecyclerView.setAdapter(adapter);
    }

    private void onClickItem(MedicalServiceTypeAdapter adapter) {
        adapter.setItemClickListner(new ClickListner<MedicalServiceType>() {

            @Override
            public void onClick(MedicalServiceType medicalServiceType) {
                delegate.onSelectMedicalServiceType(medicalServiceType);
            }

            @Override
            public void onLongClick(MedicalServiceType medicalServiceType) {
            }
        });
    }
}
