package mz.co.msaude.mobile.fragment;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.ProvinceAdapter;
import mz.co.msaude.mobile.delegate.ScheduleConsultationDelegate;
import mz.co.msaude.mobile.delegate.ScheduleDelegate;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.location.model.Province;

public class ProvinceFragment extends BaseFragment {

    private ScheduleDelegate delegate;

    @BindView(R.id.fragment_province_recycle_view)
    RecyclerView provinceRecycleView;

    @Override
    public int getResourceId() {
        return R.layout.fragment_province;
    }

    @Override
    public void onCreateView() {

        delegate = (ScheduleDelegate) getActivity();
        delegate.setFragmentTitle(getArguments().getString(ScheduleDelegate.TITLE));

        ProvinceAdapter adapter = new ProvinceAdapter(getActivity(), delegate.getProvinces());
        onSelectProvince(adapter);
        provinceRecycleView.setAdapter(adapter);

    }

    private void onSelectProvince(ProvinceAdapter adapter) {
        adapter.setItemClickListner(new ClickListner<Province>() {
            @Override
            public void onClick(Province province) {
                delegate.onSelectProvince(province);
            }

            @Override
            public void onLongClick(Province item) {

            }
        });
    }
}
