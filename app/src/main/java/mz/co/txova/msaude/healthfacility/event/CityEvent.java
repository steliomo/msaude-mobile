package mz.co.txova.msaude.healthfacility.event;

import mz.co.txova.msaude.healthfacility.model.City;

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
