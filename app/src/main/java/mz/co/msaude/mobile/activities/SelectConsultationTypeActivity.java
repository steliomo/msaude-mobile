package mz.co.msaude.mobile.activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.ConsultationTypeAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.event.ConsultationTypeEvent;
import mz.co.msaude.mobile.consultation.model.ConsultationType;


public class SelectConsultationTypeActivity extends BaseAuthenticateActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.item_selection_list)
    ListView itemSelectionList;

    @Inject
    EventBus eventBus;

    private ConsultationTypeAdapter consultationTypeAdapter;

    private List<ConsultationType> consultationTypes;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_select_city);
        toolbar.setTitle("Tipos de consultas");

        SaudeComponent component = application.getComponent();
        component.inject(this);

        consultationTypes = Arrays.asList(new ConsultationType("Cardiologia"), new ConsultationType("Medicina Geral"));
        consultationTypeAdapter = new ConsultationTypeAdapter(this, consultationTypes);
        itemSelectionList.setAdapter(consultationTypeAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_item, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String text) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String text) {

        List<ConsultationType> newConsultationTypes = new ArrayList<>();

        for (ConsultationType consultationType : this.consultationTypes) {
            if (consultationType.getConsultationType().toLowerCase().contains(text.toLowerCase())) {
                newConsultationTypes.add(consultationType);
            }
        }

        consultationTypeAdapter.setFilter(newConsultationTypes);
        return true;
    }

    @OnItemClick(R.id.item_selection_list)
    public void onItemClick(int position) {
        eventBus.post(new ConsultationTypeEvent(consultationTypeAdapter.getItem(position)));
        finish();
    }
}
