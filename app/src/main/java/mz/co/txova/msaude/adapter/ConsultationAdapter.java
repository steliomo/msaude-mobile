package mz.co.txova.msaude.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mz.co.txova.msaude.R;
import mz.co.txova.msaude.consultation.Consultation;
import mz.co.txova.msaude.util.DateUtil;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class ConsultationAdapter extends BaseAdapter {


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
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = LayoutInflater.from(this.context);

        if (view == null) {
            view = inflater.inflate(R.layout.list_consultations, parent, false);
        }

        Consultation consultation = consultations.get(position);

        TextView consultationType = (TextView) view.findViewById(R.id.type_of_consultation);
        consultationType.setText(consultation.getConsultationType().getConsultationType());

        TextView doctorName = (TextView) view.findViewById(R.id.doctor_name);
        doctorName.setText(consultation.getDoctor().getFullName());

        TextView healthFacility = (TextView) view.findViewById(R.id.health_facility);
        healthFacility.setText(consultation.getHealthFacility().getName());

        TextView dateScheduled = (TextView) view.findViewById(R.id.date_scheduled);
        dateScheduled.setText(DateUtil.format(consultation.getScheduledDate()));

        return view;
    }
}
