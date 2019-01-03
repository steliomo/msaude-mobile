package mz.co.msaude.mobile.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.location.model.Locality;

public class HealtFacilityViewHolder extends BaseViewHolder {

    @BindView(R.id.icon)
    ImageView imageView;

    @BindView(R.id.name)
    TextView name;

    private ClickListner listner;

    private HealthFacility healthFacility;

    public HealtFacilityViewHolder(View view) {
        super(view);
        imageView.setImageResource(R.mipmap.ic_clinic);
    }

    @Override
    public void onClick(View view) {
        listner.onClick(healthFacility);
    }

    @Override
    public boolean onLongClick(View view) {
        listner.onLongClick(healthFacility);
        return true;
    }

    public void setItemClicListner(ClickListner listner) {
        this.listner = listner;
    }

    public void bind(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
        name.setText(healthFacility.getName());
    }
}
