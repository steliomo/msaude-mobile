package mz.co.msaude.mobile.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.location.model.Province;

public class DoctorViewHolder extends BaseViewHolder {

    @BindView(R.id.icon)
    ImageView imageView;

    @BindView(R.id.name)
    TextView name;

    private ClickListner listner;

    private Doctor doctor;

    public DoctorViewHolder(View view) {
        super(view);
        imageView.setImageResource(R.mipmap.ic_doctor);
    }

    @Override
    public void onClick(View view) {
        listner.onClick(doctor);
    }

    @Override
    public boolean onLongClick(View view) {
        listner.onLongClick(doctor);
        return true;
    }

    public void setItemClicListner(ClickListner listner) {
        this.listner = listner;
    }

    public void bind(Doctor doctor) {
        this.doctor = doctor;
        name.setText(doctor.getFullName());
    }
}
