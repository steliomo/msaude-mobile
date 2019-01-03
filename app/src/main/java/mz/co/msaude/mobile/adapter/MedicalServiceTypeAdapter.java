package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.holder.MedicalServiceTypeViewHolder;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;

/**
 * Created by Stélio Moiane on 6/14/17.
 */
public class MedicalServiceTypeAdapter extends RecyclerView.Adapter<MedicalServiceTypeViewHolder> {

    private Context context;

    private List<MedicalServiceType> medicalServiceTypes;

    private ClickListner listner;

    public MedicalServiceTypeAdapter(final Context context, List<MedicalServiceType> medicalServiceTypes) {
        this.context = context;
        this.medicalServiceTypes = medicalServiceTypes;
    }

    @NonNull
    @Override
    public MedicalServiceTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items_default, parent, false);
        MedicalServiceTypeViewHolder holder = new MedicalServiceTypeViewHolder(view);
        holder.setItemClicListner(listner);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalServiceTypeViewHolder holder, int position) {
        MedicalServiceType medicalServiceType = medicalServiceTypes.get(position);
        holder.bind(medicalServiceType);
    }

    @Override
    public int getItemCount() {
        return medicalServiceTypes.size();
    }

    public void setItemClickListner(ClickListner listner) {
        this.listner = listner;
    }
}
