package mz.co.txova.msaude.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.healthfacility.model.HealthFacility;

/**
 * Created by Stélio Moiane on 6/16/17.
 */
public class HealthFacilityAdapter extends BaseAbstractAdapter {


    @BindView(R.id.icon)
    ImageView icon;

    @BindView(R.id.name)
    TextView healtFacilityName;

    private final Context context;

    private final List<HealthFacility> healthFacilities;

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
        return R.layout.list_consultation_type;
    }

    @Override
    public void onCreateView(int position) {

        HealthFacility healthFacility = healthFacilities.get(position);
        icon.setImageResource(context.getResources().getIdentifier("ic_clinic", "mipmap", context.getPackageName()));
        healtFacilityName.setText(healthFacility.getName());
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
}
