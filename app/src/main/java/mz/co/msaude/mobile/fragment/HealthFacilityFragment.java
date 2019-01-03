package mz.co.msaude.mobile.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.activities.ScheduleConsultationActivity;
import mz.co.msaude.mobile.adapter.HealthFacilityAdapter;
import mz.co.msaude.mobile.adapter.LocalityAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.dto.HealthFacilityDTO;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.QueryResult;
import mz.co.msaude.mobile.delegate.ScheduleConsultationDelegate;
import mz.co.msaude.mobile.delegate.ScheduleDelegate;
import mz.co.msaude.mobile.healthfacility.event.HealthFacilityEvent;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.location.model.Locality;

public class HealthFacilityFragment extends BaseFragment {

    @BindView(R.id.fragment_locality_recycle_view)
    RecyclerView localityRecycleView;

    private ScheduleDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_health_facility;
    }

    @Override
    public void onCreateView() {

        delegate = (ScheduleDelegate) getActivity();
        delegate.setFragmentTitle(getArguments().getString(ScheduleDelegate.TITLE));

        HealthFacilityAdapter adapter = new HealthFacilityAdapter(getActivity(), delegate.getHealthFacilities());
        onSelectHealthFacility(adapter);
        localityRecycleView.setAdapter(adapter);
    }

    private void onSelectHealthFacility(HealthFacilityAdapter adapter) {
        adapter.setItemClickListner(new ClickListner<HealthFacility>() {
            @Override
            public void onClick(HealthFacility healthFacility) {
                delegate.onSelectHealthFacility(healthFacility);
            }

            @Override
            public void onLongClick(HealthFacility item) {

            }
        });
    }
}
