package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.holder.ProvinceViewHolder;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.location.model.Province;

/**
 * Created by Stélio Moiane on 6/14/17.
 */
public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceViewHolder> {

    private Context context;

    private List<Province> provinces;

    private ClickListner listner;

    public ProvinceAdapter(final Context context, List<Province> provinces) {
        this.context = context;
        this.provinces = provinces;
    }

    @NonNull
    @Override
    public ProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items_default, parent, false);
        ProvinceViewHolder holder = new ProvinceViewHolder(view);
        holder.setItemClicListner(listner);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinceViewHolder holder, int position) {
        Province province = provinces.get(position);
        holder.bind(province);
    }

    @Override
    public int getItemCount() {
        return provinces.size();
    }

    public void setItemClickListner(ClickListner listner) {
        this.listner = listner;
    }
}
