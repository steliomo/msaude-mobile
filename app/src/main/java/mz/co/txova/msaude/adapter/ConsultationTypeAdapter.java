package mz.co.txova.msaude.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.consultation.model.ConsultationType;

/**
 * Created by Stélio Moiane on 6/14/17.
 */
public class ConsultationTypeAdapter extends BaseAbstractAdapter implements FilterableAdapter<ConsultationType> {

    @BindView(R.id.icon)
    ImageView consultationTypeImage;

    @BindView(R.id.name)
    TextView consultationTypeName;

    private Context context;

    private List<ConsultationType> consultationTypes;

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
    public Context getContext() {
        return this.context;
    }

    @Override
    public int getResourceId() {
        return R.layout.list_consultation_type;
    }

    @Override
    public void onCreateView(int position) {

        ConsultationType consultationType = consultationTypes.get(position);

        consultationTypeImage.setImageResource(R.mipmap.ic_stethoscope);
        consultationTypeName.setText(consultationType.getConsultationType());
    }

    @Override
    public void setFilter(List<ConsultationType> items) {
        consultationTypes = items;
        notifyDataSetChanged();
    }
}
