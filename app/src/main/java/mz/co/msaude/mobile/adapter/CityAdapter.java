package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.healthfacility.model.City;

/**
 * Created by steliomo on 9/17/17.
 */

public class CityAdapter extends BaseAbstractAdapter implements FilterableAdapter<City> {

    @BindView(R.id.city)
    TextView cityView;

    @BindView(R.id.country)
    TextView countryView;

    private Context context;

    private List<City> cities;

    public CityAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cities = cities;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public int getResourceId() {
        return R.layout.list_consultation_city_select;
    }

    @Override
    public void onCreateView(int position) {

        City city = cities.get(position);
        cityView.setText(city.getCity());
        countryView.setText(city.getContry());
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public City getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cities.get(position).hashCode();
    }

    @Override
    public void setFilter(List<City> items) {
        this.cities = items;
        notifyDataSetChanged();
    }
}
