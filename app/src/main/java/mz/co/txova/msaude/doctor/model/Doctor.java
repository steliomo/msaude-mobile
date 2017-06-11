package mz.co.txova.msaude.doctor.model;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class Doctor {

    private String firstName;
    private String lastName;

    public Doctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return "Dr(a). " + this.firstName + " " + this.lastName;
    }
}
