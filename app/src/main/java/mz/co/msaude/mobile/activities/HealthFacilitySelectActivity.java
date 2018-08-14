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
import mz.co.msaude.mobile.adapter.HealthFacilityAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.dto.HealthFacilityDTO;
import mz.co.msaude.mobile.healthfacility.event.HealthFacilityEvent;
import mz.co.msaude.mobile.healthfacility.model.City;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;

public class HealthFacilitySelectActivity extends BaseAuthenticateActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.health_facility_list)
    ListView healthFacilityList;

    @Inject
    EventBus eventBus;

    private List<HealthFacility> healthFacilities;

    private HealthFacilityAdapter healthFacilityAdapter;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_select_health_facility);
        toolbar.setTitle("Clínicas");

        SaudeComponent component = application.getComponent();
        component.inject(this);

        City maputo = new City("Maputo", "Moçambique");
        HealthFacility clinica222 = new HealthFacility("Clinica 222", "clinica222@gmail.com", "Bairro da Coop, nr. 15", "+258822546100");
        clinica222.setCity(maputo);
        HealthFacility policinic = new HealthFacility("Policlinic", "policlinic@gmail.com", "Bairro 700, nr. 13", "+258822546100");
        policinic.setCity(maputo);
        HealthFacility hospitalPrivado = new HealthFacility("Hospital Privado", "hospital.privado@gmail.com", "Bairro Sommerchield, nr. 534", "+258822546100");
        hospitalPrivado.setCity(maputo);

        healthFacilities = Arrays.asList(clinica222, policinic, hospitalPrivado);
        healthFacilityAdapter = new HealthFacilityAdapter(this, healthFacilities);
        healthFacilityList.setAdapter(healthFacilityAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        ArrayList<HealthFacility> facilities = new ArrayList<>();

        for (HealthFacility healthFacility : this.healthFacilities) {
            if (healthFacility.getName().toLowerCase().contains(query.toLowerCase())) {
                facilities.add(healthFacility);
            }
        }

        healthFacilityAdapter.setFilter(facilities);
        return true;
    }

    @OnItemClick(R.id.health_facility_list)
    public void onItemClick(int position) {
        HealthFacilityDTO healthFacilityDTO = new HealthFacilityDTO();
        healthFacilityDTO.setHealthFacility(healthFacilityAdapter.getItem(position));
        eventBus.post(new HealthFacilityEvent(healthFacilityDTO));
        finish();
    }
}
