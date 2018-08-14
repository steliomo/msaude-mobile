package mz.co.msaude.mobile.holder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.listner.ClickListner;

/**
 * Created by steliomo on 10/16/17.
 */

public class ConsultationViewHolder extends BaseViewHolder {

    @BindView(R.id.type_of_consultation)
    TextView consultationType;

    @BindView(R.id.doctor_name)
    TextView doctorName;

    @BindView(R.id.health_facility)
    TextView healthFacility;

    @BindView(R.id.date_scheduled)
    TextView dateScheduled;

    @BindView(R.id.time_scheduled)
    TextView timeScheduled;

    private ClickListner clickListner;

    public ConsultationViewHolder(View view) {
        super(view);
    }

    public TextView getConsultationType() {
        return consultationType;
    }

    public TextView getDoctorName() {
        return doctorName;
    }

    public TextView getHealthFacility() {
        return healthFacility;
    }

    public TextView getDateScheduled() {
        return dateScheduled;
    }

    public TextView getTimeScheduled() {
        return timeScheduled;
    }

    public void setItemClickListner(final ClickListner clickListner) {
        this.clickListner = clickListner;
    }

    @Override
    public void onClick(View view) {
        clickListner.onClick(view, getAdapterPosition());
    }

    @Override
    public boolean onLongClick(View view) {
        clickListner.onLongClick(view, getAdapterPosition());
        return true;
    }
}
