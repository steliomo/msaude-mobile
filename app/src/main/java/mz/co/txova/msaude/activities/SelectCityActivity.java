package mz.co.txova.msaude.activities;

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
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.adapter.CityAdapter;
import mz.co.txova.msaude.adapter.FilterableAdapter;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.healthfacility.event.CityEvent;
import mz.co.txova.msaude.healthfacility.model.City;

public class SelectCityActivity extends BaseAuthenticateActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.item_selection_list)
    ListView itemSelectionList;

    @Inject
    EventBus eventBus;

    private List<City> cities;

    private CityAdapter cityAdapter;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_select_city);
        toolbar.setTitle("Cidades");

        SaudeComponent component = application.getComponent();
        component.inject(this);

        cities = Arrays.asList(new City("Maputo", "Moçambique"), new City("Beira", "Moçambique"), new City("Matola", "Moçambique"), new City("Quelimane", "Moçambique"), new City("Nampula", "Moçambique"));
        cityAdapter = new CityAdapter(this, this.cities);
        itemSelectionList.setAdapter(cityAdapter);
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
        List<City> newCities = new ArrayList<>();

        for (City city : this.cities) {
            if (city.getCity().toLowerCase().contains(text.toLowerCase())) {
                newCities.add(city);
            }
        }

        cityAdapter.setFilter(newCities);
        return true;
    }

    @OnItemClick(R.id.item_selection_list)
    public void onItemClick(int position) {
        eventBus.post(new CityEvent(cityAdapter.getItem(position)));
        finish();
    }
}
