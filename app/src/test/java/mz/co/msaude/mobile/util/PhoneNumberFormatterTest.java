package mz.co.msaude.mobile.util;

import org.junit.Before;
import org.junit.Test;

import mz.co.msaude.mobile.formatter.PhoneFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PhoneNumberFormatterTest {

    private PhoneFormatter formatter;

    @Before
    public void setup() {
        formatter = new PhoneFormatter();
    }

    @Test
    public void shouldFormatPhoneNumber() {
        String formattedNumber = formatter.format("840546824");
        assertThat(formattedNumber, equalTo("+(258) 84 054-6824"));
    }

    @Test
    public void shouldRemoveFormat() {
        String formattedNumber = formatter.remove("+(258) 84 054-6824");
        assertThat(formattedNumber, equalTo("840546824"));
    }
}
