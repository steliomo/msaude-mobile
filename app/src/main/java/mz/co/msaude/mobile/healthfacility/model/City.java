package mz.co.msaude.mobile.healthfacility.model;

import java.io.Serializable;

/**
 * Created by steliomo on 9/17/17.
 */

public class City implements Serializable {

    private String city;
    private String contry;

    public City(String city, String contry) {
        this.city = city;
        this.contry = contry;
    }

    public String getCity() {
        return city;
    }

    public String getContry() {
        return contry;
    }
}
