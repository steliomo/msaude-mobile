package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;

/**
 * Created by Stélio Moiane on 6/16/17.
 */
public class HealthFacilityAdapter extends BaseAbstractAdapter implements FilterableAdapter<HealthFacility> {


    @BindView(R.id.icon)
    ImageView icon;

    @BindView(R.id.health_facility_name)
    TextView healtFacilityName;

    @BindView(R.id.city)
    TextView city;

    @BindView(R.id.country)
    TextView country;

    private Context context;

    private List<HealthFacility> healthFacilities;

    public HealthFacilityAdapter(Context context, List<HealthFacility> healthFacilities) {
        this.context = context;
        this.healthFacilities = healthFacilities;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public int getResourceId() {
        return R.layout.list_health_facilities;
    }

    @Override
    public void onCreateView(int position) {

        HealthFacility healthFacility = healthFacilities.get(position);
        icon.setImageResource(R.mipmap.ic_hospital);
        healtFacilityName.setText(healthFacility.getName());

        if (healthFacility.getCity() == null) {
            return;
        }

        city.setText(healthFacility.getCity().getCity());
        country.setText(healthFacility.getCity().getContry());
    }

    @Override
    public int getCount() {
        return healthFacilities.size();
    }

    @Override
    public HealthFacility getItem(int position) {
        return healthFacilities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return healthFacilities.get(position).hashCode();
    }

    @Override
    public void setFilter(List<HealthFacility> items) {
        this.healthFacilities = items;
        notifyDataSetChanged();
    }
}
