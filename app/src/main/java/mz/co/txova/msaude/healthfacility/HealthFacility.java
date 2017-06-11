package mz.co.txova.msaude.healthfacility;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class HealthFacility {

    private String name;
    private String email;
    private String address;
    private String contact;

    public HealthFacility(String name, String email, String address, String contact) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }
}
