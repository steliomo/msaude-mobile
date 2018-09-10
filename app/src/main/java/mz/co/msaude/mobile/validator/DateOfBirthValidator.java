package mz.co.msaude.mobile.validator;

import android.support.design.widget.TextInputLayout;

public class DateOfBirthValidator implements Validator {

    public static final String REGEX = "[0-9]{2}\\-[0-9]{2}\\-[0-9]{4}";

    private Validator validator;

    private TextInputLayout textInputLayout;


    public DateOfBirthValidator() {
    }

    public DateOfBirthValidator(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        validator = new StandardValidator(textInputLayout);
    }

    @Override
    public boolean isValid() {

        String value = textInputLayout.getEditText().getText().toString();

        if (!validator.isValid()) {
            return false;
        }

        if (!validate(value)) {
            textInputLayout.setError("A data de nascimento é inválida!");
            return false;
        }

        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    public boolean validate(String date) {

        if (date.matches(REGEX)) {
            return true;
        }

        return false;
    }
}
