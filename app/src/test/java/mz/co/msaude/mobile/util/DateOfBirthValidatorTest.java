package mz.co.msaude.mobile.util;

import org.junit.Test;

import mz.co.msaude.mobile.validator.DateOfBirthValidator;

import static org.junit.Assert.assertTrue;

public class DateOfBirthValidatorTest {

    @Test
    public void shouldValidateDateOfBirth(){
        String date = "29-82-1984";

        DateOfBirthValidator validador = new DateOfBirthValidator();

        assertTrue(validador.validate(date));
    }

}
