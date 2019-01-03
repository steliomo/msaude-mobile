package mz.co.msaude.mobile.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;

public class MedicalServiceTypeViewHolder extends BaseViewHolder {

    @BindView(R.id.icon)
    ImageView imageView;

    @BindView(R.id.name)
    TextView name;

    private MedicalServiceType medicalServiceType;

    private ClickListner listner;

    public MedicalServiceTypeViewHolder(View view) {
        super(view);
        imageView.setImageResource(R.mipmap.ic_stethoscope);
    }

    @Override
    public void onClick(View view) {
        listner.onClick(medicalServiceType);
    }

    @Override
    public boolean onLongClick(View view) {
        listner.onLongClick(medicalServiceType);
        return true;
    }

    public void setItemClicListner(ClickListner listner) {
        this.listner = listner;
    }

    public void bind(MedicalServiceType medicalServiceType) {
        this.medicalServiceType = medicalServiceType;
        name.setText(medicalServiceType.getName());
    }
}
