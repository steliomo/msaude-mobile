package mz.co.txova.msaude.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.adapter.ConsultationAdapter;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.consultation.event.ConsultationEvent;
import mz.co.txova.msaude.consultation.model.Consultation;
import mz.co.txova.msaude.consultation.model.Hour;
import mz.co.txova.msaude.decoration.DividerItemDecoration;
import mz.co.txova.msaude.doctor.model.Doctor;
import mz.co.txova.msaude.healthfacility.model.City;
import mz.co.txova.msaude.healthfacility.model.HealthFacility;
import mz.co.txova.msaude.listner.ClickListner;

public class ConsultationsActivity extends BaseAuthenticateActivity implements ActionMode.Callback, ClickListner {

    @BindView(R.id.add_new_consultation)
    ImageButton addNewconsultation;

    @BindView(R.id.consultations)
    RecyclerView consultations;

    @Inject
    EventBus eventBus;

    private List<Consultation> consultationList;

    private ActionMode actionMode;

    private ConsultationAdapter adapter;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_consultations);
        SaudeComponent component = application.getComponent();
        component.inject(this);
        eventBus.register(this);

        toolbar.setTitle("Consultas");

        Doctor alima = new Doctor("Alima", "Moiane", "Pediatra");
        HealthFacility clinicare = new HealthFacility("Clinicare", "clinicare@gmail.com", null, null);
        clinicare.setCity(new City("Maputo", "Moçambique"));

        Consultation consultation = new Consultation("Maputo", "Pediatria");
        consultation.setHealthFacility(clinicare);
        consultation.setDoctor(alima);
        consultation.setScheduledDate("28-08-1984");
        consultation.setHour(Hour.TEN_HALF_TO_ELEVEN);
        consultationList = new ArrayList<>();

        populateConsultation(consultation);
        registerForContextMenu(consultations);
    }

    private void populateConsultation(Consultation consultation) {
        consultationList.add(consultation);
        adapter = new ConsultationAdapter(this, consultationList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        consultations.setLayoutManager(layoutManager);
        consultations.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        consultations.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consultations, menu);
        return true;
    }

    @OnClick(R.id.add_new_consultation)
    public void OnclickAddNewConsultation() {

        if (actionMode != null) {
            actionMode.finish();
            clear();
        }

        Toast.makeText(this, "Nova consulta", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SearchConsultationActivity.class));
    }

    @Subscribe
    public void onEvent(ConsultationEvent event) {
        populateConsultation(event.getConsultation());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteConsultations();
                actionMode.finish();
                break;

        }

        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        this.actionMode = null;
        this.clear();
    }

    private void deleteConsultations() {
        adapter.delete();
    }

    @Override
    public void onClick(View view, int position) {

        if (actionMode == null) {
            return;
        }

        adapter.toggleSelection(view, position);
        actionMode.setTitle(String.valueOf(adapter.getSelectedItemsCount()));

        if (adapter.getSelectedItems().size() == 0 && actionMode != null) {
            actionMode.finish();
        }

        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        Snackbar.make(view, "TESTE", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(View view, int position) {

        if (actionMode == null) {
            actionMode = startSupportActionMode(this);
        }

        adapter.toggleSelection(view, position);
        actionMode.setTitle(String.valueOf(adapter.getSelectedItemsCount()));

        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        Snackbar.make(view, "TESTE2", Snackbar.LENGTH_SHORT).show();
    }

    private void clear() {

        for (Integer viewPosition : adapter.getSelectedItems()) {

            RecyclerView.ViewHolder viewHolder = consultations.findViewHolderForAdapterPosition(viewPosition.intValue());

            if (viewHolder != null) {
                viewHolder.itemView.setActivated(Boolean.FALSE);
                View selectedIcon = viewHolder.itemView.findViewById(R.id.selected_row);
                selectedIcon.setVisibility(View.GONE);
            }
        }
        adapter.clear();
    }
}
