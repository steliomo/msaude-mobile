package mz.co.msaude.mobile.healthfacility.event;

import mz.co.msaude.mobile.healthfacility.model.City;

/**
 * Created by steliomo on 9/17/17.
 */

public class CityEvent {

    private City city;

    public CityEvent(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }
}
