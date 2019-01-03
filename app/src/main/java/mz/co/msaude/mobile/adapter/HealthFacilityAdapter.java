package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.holder.HealtFacilityViewHolder;
import mz.co.msaude.mobile.listner.ClickListner;

/**
 * Created by Stélio Moiane on 6/16/17.
 */
public class HealthFacilityAdapter extends RecyclerView.Adapter<HealtFacilityViewHolder> {

    private Context context;

    private List<HealthFacility> healthFacilities;

    private ClickListner listner;

    public HealthFacilityAdapter(final Context context, List<HealthFacility> healthFacilities) {
        this.context = context;
        this.healthFacilities = healthFacilities;
    }

    @NonNull
    @Override
    public HealtFacilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items_default, parent, false);
        HealtFacilityViewHolder holder = new HealtFacilityViewHolder(view);
        holder.setItemClicListner(listner);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HealtFacilityViewHolder holder, int position) {
        HealthFacility healthFacility = healthFacilities.get(position);
        holder.bind(healthFacility);
    }

    @Override
    public int getItemCount() {
        return healthFacilities.size();
    }

    public void setItemClickListner(ClickListner listner) {
        this.listner = listner;
    }
}
