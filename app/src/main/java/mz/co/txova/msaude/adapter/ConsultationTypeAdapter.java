package mz.co.txova.msaude.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mz.co.txova.msaude.R;
import mz.co.txova.msaude.consultation.ConsultationType;

/**
 * Created by Stélio Moiane on 6/14/17.
 */
public class ConsultationTypeAdapter extends BaseAdapter {

    private final Context context;
    private final List<ConsultationType> consultationTypes;

    public ConsultationTypeAdapter(final Context context, List<ConsultationType> consultationTypes) {
        this.context = context;
        this.consultationTypes = consultationTypes;
    }

    @Override
    public int getCount() {
        return consultationTypes.size();
    }

    @Override
    public ConsultationType getItem(int position) {
        return consultationTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.consultationTypes.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = LayoutInflater.from(this.context);

        if (view == null) {
            view = inflater.inflate(R.layout.list_consultation_type, parent, false);
        }

        ConsultationType consultationType = consultationTypes.get(position);


        ImageView consultationTypeImage = (ImageView) view.findViewById(R.id.consultation_type_icon);
        consultationTypeImage.setImageResource(R.mipmap.ic_cardiology);

        TextView consultationTypeName = (TextView) view.findViewById(R.id.consultation_type);
        consultationTypeName.setText(consultationType.getConsultationType());

        return view;
    }
}
