package mz.co.txova.msaude.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.consultation.model.Consultation;
import mz.co.txova.msaude.holder.ConsultationViewHolder;
import mz.co.txova.msaude.listner.ClickListner;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class ConsultationAdapter extends RecyclerView.Adapter<ConsultationViewHolder> {

    private Context context;
    private List<Consultation> consultations;
    private ClickListner clickListner;
    private SparseBooleanArray selectedItems;

    public ConsultationAdapter(final Context context, final List<Consultation> consultations, ClickListner clickListner) {
        this.context = context;
        this.consultations = consultations;
        this.clickListner = clickListner;
    }

    @Override
    public ConsultationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_consultations, parent, false);
        return new ConsultationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConsultationViewHolder holder, int position) {

        Consultation consultation = consultations.get(position);

        holder.getConsultationType().setText(consultation.getConsultationType());
        holder.getDoctorName().setText(consultation.getDoctor().getFullName());
        holder.getHealthFacility().setText(consultation.getHealthFacility().getName() + " / " + consultation.getCity());
        holder.getDateScheduled().setText(consultation.getScheduledDate());
        holder.getTimeScheduled().setText(consultation.getHour().getAvailability());
        holder.setItemClickListner(clickListner);
    }

    @Override
    public int getItemCount() {
        return this.consultations.size();
    }

    public void delete() {

        for (int position = (selectedItems.size() - 1); position >= 0; position--) {
            this.consultations.remove(position);
        }

        notifyDataSetChanged();
    }

    public void toggleSelection(View view, int position) {

        if (selectedItems == null) {
            selectedItems = new SparseBooleanArray();
        }

        View selectedIcon = view.findViewById(R.id.selected_row);

        if (!selectedItems.get(position, Boolean.FALSE)) {
            selectedItems.put(position, Boolean.TRUE);
            view.setActivated(Boolean.TRUE);
            selectedIcon.setVisibility(View.VISIBLE);
            return;
        }


        selectedIcon.setVisibility(View.GONE);
        selectedItems.delete(position);
        view.setActivated(false);
    }

    public List<Integer> getSelectedItems() {
        ArrayList<Integer> items = new ArrayList<>();

        for (int index = 0; index < selectedItems.size(); index++) {
            items.add(selectedItems.keyAt(index));
        }

        return items;
    }

    public int getSelectedItemsCount() {
        return selectedItems.size();
    }

    public void clear() {
        selectedItems.clear();
    }
}
