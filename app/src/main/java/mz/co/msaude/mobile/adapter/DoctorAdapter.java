package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.holder.DoctorViewHolder;
import mz.co.msaude.mobile.listner.ClickListner;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorViewHolder> {

    private final Context context;

    private final List<Doctor> doctors;

    private ClickListner listner;

    public DoctorAdapter(Context context, List<Doctor> doctors) {
        this.context = context;
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items_default, parent, false);
        DoctorViewHolder holder = new DoctorViewHolder(view);
        holder.setItemClicListner(listner);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.bind(doctor);
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public void setItemClickListner(ClickListner listner) {
        this.listner = listner;
    }
}
