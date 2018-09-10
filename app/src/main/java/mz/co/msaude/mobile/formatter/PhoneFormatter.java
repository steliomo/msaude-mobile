package mz.co.msaude.mobile.formatter;

public class PhoneFormatter {

    public String format(String phoneNumber) {
        return phoneNumber.replaceAll("([0-9]{2})([0-9]{3})([0-9]{4})", "+(258) $1 $2-$3");
    }

    public String remove(String phoneNumber) {
        return phoneNumber
                .replace("+(258)", "")
                .replace("-", "")
                .replace(" ", "");
    }
}
