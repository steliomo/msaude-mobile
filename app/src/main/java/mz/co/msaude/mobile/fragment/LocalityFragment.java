package mz.co.msaude.mobile.fragment;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.LocalityAdapter;
import mz.co.msaude.mobile.delegate.ScheduleConsultationDelegate;
import mz.co.msaude.mobile.delegate.ScheduleDelegate;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.location.model.Locality;

public class LocalityFragment extends BaseFragment {


    @BindView(R.id.fragment_locality_recycle_view)
    RecyclerView localityRecycleView;

    private ScheduleDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_locality;
    }

    @Override
    public void onCreateView() {

        delegate = (ScheduleDelegate) getActivity();
        delegate.setFragmentTitle(getArguments().getString(ScheduleDelegate.TITLE));

        LocalityAdapter adapter = new LocalityAdapter(getActivity(), delegate.getLocalities());
        onSelectLocality(adapter);
        localityRecycleView.setAdapter(adapter);
    }

    private void onSelectLocality(LocalityAdapter adapter) {
        adapter.setItemClickListner(new ClickListner<Locality>() {
            @Override
            public void onClick(Locality item) {
                delegate.onSelectLocality(item);
            }

            @Override
            public void onLongClick(Locality item) {

            }
        });
    }
}
