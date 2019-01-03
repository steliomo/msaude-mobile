package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.holder.ConsultationViewHolder;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.listner.VerticalMenuClickListner;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class ConsultationAdapter extends RecyclerView.Adapter<ConsultationViewHolder> {

    private Context context;

    private List<Consultation> consultations;

    private VerticalMenuClickListner<Consultation> verticalMenuClickListner;

    public ConsultationAdapter(final Context context, final List<Consultation> consultations) {
        this.context = context;
        this.consultations = consultations;
    }

    @Override
    public ConsultationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_consultations, parent, false);
        ConsultationViewHolder holder = new ConsultationViewHolder(view);
        holder.setOnVerticalMenuClickListner(verticalMenuClickListner);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConsultationViewHolder holder, int position) {
        Consultation consultation = consultations.get(position);
        holder.bind(consultation);
    }

    @Override
    public int getItemCount() {
        return this.consultations.size();
    }

    public void setOnVerticalMenuClickListner(VerticalMenuClickListner<Consultation> verticalMenuClickListner) {
        this.verticalMenuClickListner = verticalMenuClickListner;
    }
}
