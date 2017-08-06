package mz.co.txova.msaude.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.consultation.model.Consultation;
import mz.co.txova.msaude.util.DateUtil;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class ConsultationAdapter extends BaseAbstractAdapter {

    @BindView(R.id.type_of_consultation)
    TextView consultationType;

    @BindView(R.id.doctor_name)
    TextView doctorName;

    @BindView(R.id.health_facility)
    TextView healthFacility;

    @BindView(R.id.date_scheduled)
    TextView dateScheduled;

    private Context context;
    private List<Consultation> consultations;

    public ConsultationAdapter(final Context context, final List<Consultation> consultations) {
        this.context = context;
        this.consultations = consultations;
    }

    @Override
    public int getCount() {
        return consultations.size();
    }

    @Override
    public Consultation getItem(int position) {
        return consultations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return consultations.get(position).hashCode();
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public int getResourceId() {
        return R.layout.list_consultations;
    }

    @Override
    public void onCreateView(final int position) {

        Consultation consultation = consultations.get(position);

        consultationType.setText(consultation.getConsultationType().getConsultationType());
        doctorName.setText(consultation.getDoctor().getFullName());
        healthFacility.setText(consultation.getHealthFacility().getName());
        dateScheduled.setText(DateUtil.format(consultation.getScheduledDate()));
    }
}
