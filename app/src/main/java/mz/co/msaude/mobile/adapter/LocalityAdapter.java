package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.holder.LocalityViewHolder;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.location.model.Locality;

/**
 * Created by Stélio Moiane on 6/14/17.
 */
public class LocalityAdapter extends RecyclerView.Adapter<LocalityViewHolder> {

    private Context context;

    private List<Locality> localities;

    private ClickListner listner;

    public LocalityAdapter(final Context context, List<Locality> localities) {
        this.context = context;
        this.localities = localities;
    }

    @NonNull
    @Override
    public LocalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items_default, parent, false);
        LocalityViewHolder holder = new LocalityViewHolder(view);
        holder.setItemClicListner(listner);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalityViewHolder holder, int position) {
        Locality locality = localities.get(position);
        holder.bind(locality);
    }

    @Override
    public int getItemCount() {
        return localities.size();
    }

    public void setItemClickListner(ClickListner listner) {
        this.listner = listner;
    }
}
